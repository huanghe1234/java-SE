package com.example.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * 希望通过Socket来实现一个简单的BBS(聊天室) 1.端对端聊天 2.群聊 消息的传输是通过Socket进行读和写. 客户端发消息到服务端为例
 * 客户端向Socket中写信息 服务端从Socket中读信息
 * 
 * 如果只有上面代码那只能实现 客户端和服务端进行聊天(因为服务端accept接口请求后会生成Socket对象),而我们是希望进行 客户端和客户端之间的聊天
 * 1.私聊: 把信息发给指定的Socket 2.群里: 把信息发给所有Socket
 * 
 * 我们把Socket和用户名放在Map<用户名,Socket对象|通过Socket对象所打开的流>
 * 
 * @author Administrator
 *
 */
public class ChatClient {

	/**
	 * 用于请求服务端,和服务端进行绑定,建立连接 因为测试方法是写在该类中.
	 */
	private Socket mSocket;

	/**
	 * 有参构造来完成 Host和Port的初始化操作
	 * 
	 * @param host
	 *            请求的服务器主机地址
	 * @param port
	 *            端口号 每一个进程(应用)执行时都会占用一个端口号,这个端口号被占用后,其它应用 无法使用.所以运行的应用和端口号是一对一关系.
	 */
	public ChatClient(String host, int port) {
		try {
			mSocket = new Socket(host, port);
		} catch (IOException e) {
			// --项目没有上线前,都是测试代码.测试代码是可以打印异常信息的
			// --但是上线的项目是不允许有显示的异常信息打印给客户的.
			// --把异常信息通过网络发送给开发者.或者把异常信息写入日志文件.(log4j)日志最终也是要发送给开发者的.
			e.printStackTrace();
			// System.out.println("开启Socket失败!");
		}
	}

	/**
	 * 客户端从这里启动.
	 */
	private void start() {

		try {
			/*
			 * 提供Scanner对象用于供客户端输入用户名
			 */
			Scanner scan = new Scanner(System.in);
			// --注册用户名
			inputUserName(scan);
			// --下面就应该是服务端和客户端开始进行聊天

			// --专门用来服务端消息的. 线程中是输入流
			Runnable run = new GetServiceMsgHandler();
			Thread t = new Thread(run);
			t.start();

			// --开启输出流向服务端发消息.
			OutputStream os = mSocket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			PrintWriter pw = new PrintWriter(osw,true);

			// --卡死循环用于向服务端发消息
			while (true) {
				pw.println(scan.nextLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// --关闭客户端
			if (mSocket != null) {
				try {
					mSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 录入用户名
	 * 
	 * @param scan
	 * @throws IOException
	 */
	private void inputUserName(Scanner scan) throws IOException {
		// --1.参数 检查
		if (scan == null) {
			throw new IllegalArgumentException("Scanner为null");
		}

		// --2.提供一个String变量用于保存从控制台录入的用户名
		String name = "";

		// --3.通过Socket对象获取输入和输出流
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(mSocket.getOutputStream(), "UTF-8"), true);
		BufferedReader br = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF-8"));

		// --4.卡死循环.在客户端录入用户名,发送到服务端.并等待响应结果
		while (true) {
			System.out.println("请创建你的昵称:");
			// --nextLine() 是读取一行.
			name = scan.nextLine();
			// --防止用户名是空格等空字符
			if (name.trim().length() == 0) {
				System.out.println("昵称不可以为空");
			} else {
				// --把用户名发送给服务端
				// --没有ln是不会自动刷新的.
				pw.println(name);
				/*
				 * 接受服务端返回的结果 因为io是阻塞的.客户端会一直停留在读这里,直到读取到内容.
				 */
				String answer = br.readLine();
				// --对返回值做验证 OK 代表用户名是唯一的.通过注册了.
				if (answer != null && !answer.equals("OK")) {
					System.out.println("用户名已经被占用,请重新输入");
				} else {
					System.out.println("欢迎" + name + "来到车友社区!");
					break;
				}
			}
		}
	}

	/**
	 * 开启子线程用于接收服务端消息.
	 * 
	 * @author Administrator
	 *
	 */
	class GetServiceMsgHandler implements Runnable {

		@Override
		public void run() {

			try {
				// --获取输入流对象
				InputStream is = mSocket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				// --接受从客户端发过来的消息

				// --保存从客户端发过来的消息
				String msg = "";
				// --为了保证线程不结束,写死循环
				while ((msg = br.readLine()) != null) {
					System.out.println("接受服务端信息:" + msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 端对端 : 只和某一个人聊天 端对All : 所有人聊天
	 */
	public static void main(String[] args) {

		ChatClient client = new ChatClient("127.0.0.1", 1100);
		client.start();
	}

}