package com.example.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

	private ServerSocket serverSocket;
	private HashMap<String, PrintWriter> maps;
	// --线程池.
	private ExecutorService threadPool;

	/**
	 * 
	 * @param port
	 *            端口号
	 */
	public ChatServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			maps = new HashMap<>();
			/**
			 * 初始化线程池对象.设定线程池中线程数量为10.
			 * 
			 * 默认线程池中是没有线程的.只有当任务进入时才会创建. 进入的任务数量要<=10 .当超过10这个值的时候.多余的任务进入等待队列.
			 * 当等待队列满了.后续向进入的任务进入阻塞状态.
			 * 
			 */
			threadPool = Executors.newFixedThreadPool(10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 服务端从这里启动
	 */
	private void start() {
		System.out.println("服务端启动,并监听1100端口,等待客户端连接");
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("客户端IP地址:" + socket.getInetAddress());
				/*
				 * 因为是针对多个客户端的.如果把聊天的代码直接写在这里. 那可以聊天的一定是最后一个客户端.客户端聊天的逻辑
				 * 
				 * 创建Runnable对象 该对象只负责读取客户端发过来的内容.线程里面是输入流.
				 */
				Runnable run = new GetClientMshHandler(socket);
				// --让线程池开始执行这个线程.
				threadPool.execute(run);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 服务端接受客户端信息的子线程.
	 * 
	 * @author Administrator
	 *
	 */
	class GetClientMshHandler implements Runnable {

		private Socket socket;
		private String clientName;

		public GetClientMshHandler(Socket socket) {
			this.socket = socket;
		}

		/**
		 * 获取客户端发过来的名称
		 * 
		 * @return 合法的用户名.
		 */
		public String getClientName() {
			try {
				// --通过Socket对象获取对应的输入流对象.
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				// --因为服务端还需要返回信息给客户端.因此还需要输出流对象
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				PrintWriter pw = new PrintWriter(osw, true);

				// --接受客户端发过来的用户名
				String name = br.readLine();
				// --通过循环来验证客户端传过来的用户名是否OK.
				while (true) {
					if (name.trim().length() == 0) {
						pw.println("FAIL");
					}
					// --判断传过来的姓名.作为键是否在maps中存在过.
					if (maps.containsKey(name)) {
						pw.println("FAIL");
					} else {
						pw.println("OK");
						return name;
					}
					// --再接一次客户端发过来的内容.
					name = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			// --有没有什么样的一种的方式.可以让这不加return
			return "";
		}

		@Override
		public void run() {
			
			// --通过Socket获取输出流对象.用于将消息发送给客户端.
	
			try {
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				PrintWriter pw  = new PrintWriter(osw,true);

				// --调用getClientName方法 获取用户名
				clientName = getClientName();
				
				// --将用户名和对应的PrintWriter保存到Map中
				addClient(clientName, pw);

				Thread.sleep(100);

				/*
				 * 服务端发送欢迎用户登录的信息.发给所有的客户端.
				 */
				sendMsgToAll("[系统通知]:欢迎" + clientName + "登录聊天室");

				/*
				 * 提供输入流对象接收客户端的信息
				 */
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				// --提供变量用于保存每次读取的内容.
				String line = "";
				
				// --因为不知道客户端发过来的内容有多长.使用循环读取.
				//System.out.println("br.readLine():"+br.readLine());
				while (null != (line = br.readLine())) {
					// --需要直到客户端发过来的是私聊还是群聊.
					// --仿照QQ @的设定 .使用@用户名 内容.表示这是私聊.
					// 假设有用户A B C @B 你好.
					// --通过startsWidth 来验证发过来的消息是否以@符号作为开头.
					if (line.startsWith("@")) {
						// @贵州-潘先彬 : 内容
						int index = line.indexOf(":");
						if (index >= 0) {
							// --求用户名
							String name = line.substring(1, index);
							String content = line.substring(index + 1, line.length());
							// --给正文前加个前缀.当前客户端名称 + "对你说"
							content = clientName + "对你说:" + content;
							// --将消息发给个人.
							//System.out.println("name:" + name);
							//System.out.println("content:" + content);
							sendMsgToPrivate(name, content);
							continue;
						}
					}else {
						// --群聊.
						// --下面这条输出语句是服务端自己为了方便测试.打印给自己看的.
						System.out.println(clientName + "说:" + line);
						sendMsgToAll(clientName + "说:" + line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				removeClient(clientName);
				System.out.println(clientName + "已经离开聊天室");
				System.out.println("当前在线人数:" + maps.size());
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		/**
		 * 将客户端名称和对应的PrintWriter对象放入Map集合中.
		 * 
		 * @param clientName
		 * @param pw
		 */
		private void addClient(String clientName, PrintWriter pw) {
			synchronized (this) {
				maps.put(clientName, pw);
			}
		}

		/**
		 * 根据用户名从集合中移除PrintWriter对象.
		 * 
		 * @param name
		 */
		private synchronized void removeClient(String name) {
			// --根据键移除value的.我们虽然移除的是键但同时也移除了value.
			maps.remove(name);
		}

		/**
		 * 把消息发给个人的方法
		 * 
		 * @param name
		 *            : 收件人
		 * @param content
		 *            : 正文内容.
		 */
		private synchronized void sendMsgToPrivate(String name, String content) {
			// --根据键获取值,即根据客户端昵称获取对应的PrintWriter对象.
			PrintWriter pw = maps.get(name);
			if (pw != null) {
				pw.println(content);
				System.out.println("当前在线人数:" + maps.size());
			}
		}

		/**
		 * 向所有用户发送信息
		 * 
		 * @param string
		 */
		private synchronized void sendMsgToAll(String msg) {
			/*
			 * maps.values() 获取Map中所有值的集合 返回的是一个Collection类型的对象. Collection中有迭代器,可以使用foreach
			 *
			 * 
			 */
			for (PrintWriter pw : maps.values()) {
				pw.println(msg);
				System.out.println("当前在线人数:" + maps.size());
			}
		}

	}

	public static void main(String[] args) {
		ChatServer server = new ChatServer(1100);
		// --启动服务端
		server.start();
	}
}