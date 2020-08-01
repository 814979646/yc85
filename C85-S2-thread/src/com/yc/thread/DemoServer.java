package com.yc.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class DemoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		System.out.println("服务器启动成功，监听端口8888");

		Socket socket = server.accept();

		InetAddress myAddress = socket.getInetAddress();
		SocketAddress otherAddress = socket.getRemoteSocketAddress();
		System.out.println("我的地址：" + myAddress);
		System.out.println("客户端的地址：" + otherAddress);

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		Scanner sc = new Scanner(System.in);

		new Thread() {
			public void run() {
				byte[] buffer = new byte[1024];
				int count;
				while (true) {
					try {
						count = in.read(buffer);
						System.out.println("他说：" + new String(buffer, 0, count));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				while (true) {
					try {
						System.out.println("我说：");
						out.write(sc.nextLine().getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
