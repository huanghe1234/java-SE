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
 * ϣ��ͨ��Socket��ʵ��һ���򵥵�BBS(������) 1.�˶Զ����� 2.Ⱥ�� ��Ϣ�Ĵ�����ͨ��Socket���ж���д. �ͻ��˷���Ϣ�������Ϊ��
 * �ͻ�����Socket��д��Ϣ ����˴�Socket�ж���Ϣ
 * 
 * ���ֻ�����������ֻ��ʵ�� �ͻ��˺ͷ���˽�������(��Ϊ�����accept�ӿ�����������Socket����),��������ϣ������ �ͻ��˺Ϳͻ���֮�������
 * 1.˽��: ����Ϣ����ָ����Socket 2.Ⱥ��: ����Ϣ��������Socket
 * 
 * ���ǰ�Socket���û�������Map<�û���,Socket����|ͨ��Socket�������򿪵���>
 * 
 * @author Administrator
 *
 */
public class ChatClient {

	/**
	 * ������������,�ͷ���˽��а�,�������� ��Ϊ���Է�����д�ڸ�����.
	 */
	private Socket mSocket;

	/**
	 * �вι�������� Host��Port�ĳ�ʼ������
	 * 
	 * @param host
	 *            ����ķ�����������ַ
	 * @param port
	 *            �˿ں� ÿһ������(Ӧ��)ִ��ʱ����ռ��һ���˿ں�,����˿ںű�ռ�ú�,����Ӧ�� �޷�ʹ��.�������е�Ӧ�úͶ˿ں���һ��һ��ϵ.
	 */
	public ChatClient(String host, int port) {
		try {
			mSocket = new Socket(host, port);
		} catch (IOException e) {
			// --��Ŀû������ǰ,���ǲ��Դ���.���Դ����ǿ��Դ�ӡ�쳣��Ϣ��
			// --�������ߵ���Ŀ�ǲ���������ʾ���쳣��Ϣ��ӡ���ͻ���.
			// --���쳣��Ϣͨ�����緢�͸�������.���߰��쳣��Ϣд����־�ļ�.(log4j)��־����Ҳ��Ҫ���͸������ߵ�.
			e.printStackTrace();
			// System.out.println("����Socketʧ��!");
		}
	}

	/**
	 * �ͻ��˴���������.
	 */
	private void start() {

		try {
			/*
			 * �ṩScanner�������ڹ��ͻ��������û���
			 */
			Scanner scan = new Scanner(System.in);
			// --ע���û���
			inputUserName(scan);
			// --�����Ӧ���Ƿ���˺Ϳͻ��˿�ʼ��������

			// --ר�������������Ϣ��. �߳�����������
			Runnable run = new GetServiceMsgHandler();
			Thread t = new Thread(run);
			t.start();

			// --��������������˷���Ϣ.
			OutputStream os = mSocket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			PrintWriter pw = new PrintWriter(osw,true);

			// --����ѭ�����������˷���Ϣ
			while (true) {
				pw.println(scan.nextLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// --�رտͻ���
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
	 * ¼���û���
	 * 
	 * @param scan
	 * @throws IOException
	 */
	private void inputUserName(Scanner scan) throws IOException {
		// --1.���� ���
		if (scan == null) {
			throw new IllegalArgumentException("ScannerΪnull");
		}

		// --2.�ṩһ��String�������ڱ���ӿ���̨¼����û���
		String name = "";

		// --3.ͨ��Socket�����ȡ����������
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(mSocket.getOutputStream(), "UTF-8"), true);
		BufferedReader br = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF-8"));

		// --4.����ѭ��.�ڿͻ���¼���û���,���͵������.���ȴ���Ӧ���
		while (true) {
			System.out.println("�봴������ǳ�:");
			// --nextLine() �Ƕ�ȡһ��.
			name = scan.nextLine();
			// --��ֹ�û����ǿո�ȿ��ַ�
			if (name.trim().length() == 0) {
				System.out.println("�ǳƲ�����Ϊ��");
			} else {
				// --���û������͸������
				// --û��ln�ǲ����Զ�ˢ�µ�.
				pw.println(name);
				/*
				 * ���ܷ���˷��صĽ�� ��Ϊio��������.�ͻ��˻�һֱͣ���ڶ�����,ֱ����ȡ������.
				 */
				String answer = br.readLine();
				// --�Է���ֵ����֤ OK �����û�����Ψһ��.ͨ��ע����.
				if (answer != null && !answer.equals("OK")) {
					System.out.println("�û����Ѿ���ռ��,����������");
				} else {
					System.out.println("��ӭ" + name + "������������!");
					break;
				}
			}
		}
	}

	/**
	 * �������߳����ڽ��շ������Ϣ.
	 * 
	 * @author Administrator
	 *
	 */
	class GetServiceMsgHandler implements Runnable {

		@Override
		public void run() {

			try {
				// --��ȡ����������
				InputStream is = mSocket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				// --���ܴӿͻ��˷���������Ϣ

				// --����ӿͻ��˷���������Ϣ
				String msg = "";
				// --Ϊ�˱�֤�̲߳�����,д��ѭ��
				while ((msg = br.readLine()) != null) {
					System.out.println("���ܷ������Ϣ:" + msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * �˶Զ� : ֻ��ĳһ�������� �˶�All : ����������
	 */
	public static void main(String[] args) {

		ChatClient client = new ChatClient("127.0.0.1", 1100);
		client.start();
	}

}