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
	// --�̳߳�.
	private ExecutorService threadPool;

	/**
	 * 
	 * @param port
	 *            �˿ں�
	 */
	public ChatServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			maps = new HashMap<>();
			/**
			 * ��ʼ���̳߳ض���.�趨�̳߳����߳�����Ϊ10.
			 * 
			 * Ĭ���̳߳�����û���̵߳�.ֻ�е��������ʱ�Żᴴ��. �������������Ҫ<=10 .������10���ֵ��ʱ��.������������ȴ�����.
			 * ���ȴ���������.���������������������״̬.
			 * 
			 */
			threadPool = Executors.newFixedThreadPool(10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����˴���������
	 */
	private void start() {
		System.out.println("���������,������1100�˿�,�ȴ��ͻ�������");
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("�ͻ���IP��ַ:" + socket.getInetAddress());
				/*
				 * ��Ϊ����Զ���ͻ��˵�.���������Ĵ���ֱ��д������. �ǿ��������һ�������һ���ͻ���.�ͻ���������߼�
				 * 
				 * ����Runnable���� �ö���ֻ�����ȡ�ͻ��˷�����������.�߳�������������.
				 */
				Runnable run = new GetClientMshHandler(socket);
				// --���̳߳ؿ�ʼִ������߳�.
				threadPool.execute(run);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����˽��ܿͻ�����Ϣ�����߳�.
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
		 * ��ȡ�ͻ��˷�����������
		 * 
		 * @return �Ϸ����û���.
		 */
		public String getClientName() {
			try {
				// --ͨ��Socket�����ȡ��Ӧ������������.
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				// --��Ϊ����˻���Ҫ������Ϣ���ͻ���.��˻���Ҫ���������
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				PrintWriter pw = new PrintWriter(osw, true);

				// --���ܿͻ��˷��������û���
				String name = br.readLine();
				// --ͨ��ѭ������֤�ͻ��˴��������û����Ƿ�OK.
				while (true) {
					if (name.trim().length() == 0) {
						pw.println("FAIL");
					}
					// --�жϴ�����������.��Ϊ���Ƿ���maps�д��ڹ�.
					if (maps.containsKey(name)) {
						pw.println("FAIL");
					} else {
						pw.println("OK");
						return name;
					}
					// --�ٽ�һ�οͻ��˷�����������.
					name = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			// --��û��ʲô����һ�ֵķ�ʽ.�������ⲻ��return
			return "";
		}

		@Override
		public void run() {
			
			// --ͨ��Socket��ȡ���������.���ڽ���Ϣ���͸��ͻ���.
	
			try {
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				PrintWriter pw  = new PrintWriter(osw,true);

				// --����getClientName���� ��ȡ�û���
				clientName = getClientName();
				
				// --���û����Ͷ�Ӧ��PrintWriter���浽Map��
				addClient(clientName, pw);

				Thread.sleep(100);

				/*
				 * ����˷��ͻ�ӭ�û���¼����Ϣ.�������еĿͻ���.
				 */
				sendMsgToAll("[ϵͳ֪ͨ]:��ӭ" + clientName + "��¼������");

				/*
				 * �ṩ������������տͻ��˵���Ϣ
				 */
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				// --�ṩ�������ڱ���ÿ�ζ�ȡ������.
				String line = "";
				
				// --��Ϊ��֪���ͻ��˷������������ж೤.ʹ��ѭ����ȡ.
				//System.out.println("br.readLine():"+br.readLine());
				while (null != (line = br.readLine())) {
					// --��Ҫֱ���ͻ��˷���������˽�Ļ���Ⱥ��.
					// --����QQ @���趨 .ʹ��@�û��� ����.��ʾ����˽��.
					// �������û�A B C @B ���.
					// --ͨ��startsWidth ����֤����������Ϣ�Ƿ���@������Ϊ��ͷ.
					if (line.startsWith("@")) {
						// @����-���ȱ� : ����
						int index = line.indexOf(":");
						if (index >= 0) {
							// --���û���
							String name = line.substring(1, index);
							String content = line.substring(index + 1, line.length());
							// --������ǰ�Ӹ�ǰ׺.��ǰ�ͻ������� + "����˵"
							content = clientName + "����˵:" + content;
							// --����Ϣ��������.
							//System.out.println("name:" + name);
							//System.out.println("content:" + content);
							sendMsgToPrivate(name, content);
							continue;
						}
					}else {
						// --Ⱥ��.
						// --���������������Ƿ�����Լ�Ϊ�˷������.��ӡ���Լ�����.
						System.out.println(clientName + "˵:" + line);
						sendMsgToAll(clientName + "˵:" + line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				removeClient(clientName);
				System.out.println(clientName + "�Ѿ��뿪������");
				System.out.println("��ǰ��������:" + maps.size());
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
		 * ���ͻ������ƺͶ�Ӧ��PrintWriter�������Map������.
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
		 * �����û����Ӽ������Ƴ�PrintWriter����.
		 * 
		 * @param name
		 */
		private synchronized void removeClient(String name) {
			// --���ݼ��Ƴ�value��.������Ȼ�Ƴ����Ǽ���ͬʱҲ�Ƴ���value.
			maps.remove(name);
		}

		/**
		 * ����Ϣ�������˵ķ���
		 * 
		 * @param name
		 *            : �ռ���
		 * @param content
		 *            : ��������.
		 */
		private synchronized void sendMsgToPrivate(String name, String content) {
			// --���ݼ���ȡֵ,�����ݿͻ����ǳƻ�ȡ��Ӧ��PrintWriter����.
			PrintWriter pw = maps.get(name);
			if (pw != null) {
				pw.println(content);
				System.out.println("��ǰ��������:" + maps.size());
			}
		}

		/**
		 * �������û�������Ϣ
		 * 
		 * @param string
		 */
		private synchronized void sendMsgToAll(String msg) {
			/*
			 * maps.values() ��ȡMap������ֵ�ļ��� ���ص���һ��Collection���͵Ķ���. Collection���е�����,����ʹ��foreach
			 *
			 * 
			 */
			for (PrintWriter pw : maps.values()) {
				pw.println(msg);
				System.out.println("��ǰ��������:" + maps.size());
			}
		}

	}

	public static void main(String[] args) {
		ChatServer server = new ChatServer(1100);
		// --���������
		server.start();
	}
}