package com.yc.d0727;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo1 {

	public static void main(String[] args) throws IOException {

		ServerSocket tomcat = new ServerSocket(8080);
		System.out.println("tomcat 服务器启动完成, 监听端口:8080");
		boolean running = true;
		while (running) {
			Socket socket = tomcat.accept();

			new Thread() {
				public void run() {
					try {
						System.out.println("接收到请求");
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						byte[] buffer = new byte[1024];
						int count;
						count = in.read(buffer);

						if (count > 0) {
							// 打印请求报文
							String requestText = new String(buffer, 0, count);
							System.out.println(requestText);

							String[] lines = requestText.split("\\n");
							String[] requestLines = lines[0].split("\\s");
							String webpath = requestLines[1];
							String contentType;

							int statuscode = 200;

							String path = "C:/Tomcat/webapps/" + webpath;
							File file = new File(path);
							if (!file.exists()) {
								statuscode = 404;
								path = "C:/Tomcat/webapps/photo/404.html";
							}
							if (webpath.endsWith(".js")) {
								contentType = "application/javascript; charset=utf-8";
							} else if (webpath.endsWith(".css")) {
								contentType = "text/css; charset=utf-8";
							} else {
								contentType = "text/html; charset=utf-8";
							}

							// System.out.println(new String(buffer, 0, count));

							out.write(("HTTP/1.1 " + statuscode + " OK\n").getBytes());

							out.write(("Content-Type: " + contentType + "\n").getBytes());

							out.write("\n".getBytes());

							// out.write("<h1>Hello World</h1>".getBytes());

							FileInputStream fis = new FileInputStream(path);
							while ((count = fis.read(buffer)) > 0) {
								out.write(buffer, 0, count);
							}
							fis.close();

						}
						socket.close();

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}

		tomcat.close();
	}
}