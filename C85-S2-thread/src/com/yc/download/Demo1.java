package com.yc.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo1 {
	public static void main(String[] args) throws IOException {
		URL url = new URL(
				"https://mirror.bit.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		URLConnection conn = url.openConnection();
		System.out.println("开始下载");
		InputStream in = conn.getInputStream();
		FileOutputStream out = new FileOutputStream("C:/新建文件夹/apache-tomcat-10.0.0-M7-windows-x64.zip");
		byte[] buffer = new byte[1024];
		int count;
		while ((count = in.read(buffer)) > 0) {
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
		System.out.println("下载完成");
	}
}
