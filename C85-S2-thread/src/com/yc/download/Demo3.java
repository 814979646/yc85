package com.yc.download;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Demo3 {
	public static void main(String[] args) throws IOException {
		URL url = new URL(
				"https://mirror.bit.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		String filename = "C:/新建文件夹/apache-tomcat-10.0.0-M7-windows-x64.zip";
		long time = System.currentTimeMillis();

		URLConnection conn = url.openConnection();

		int filesize = conn.getContentLength();
		int blocksize = 2 * 1024 * 1024;
		int blocknums = filesize / blocksize;
		if (filesize % blocksize != 0) {
			blocknums++;
		}
		System.out.println("开始下载");

		for (int i = 0; i < blocknums; i++) {
			System.out.println("第" + (i + 1) + "块开始下载");
			/**
			 * 每次循环中，获取一个连接对象
			 */
			conn = url.openConnection();
			InputStream in = conn.getInputStream();
			FileOutputStream out = new FileOutputStream("C:/新建文件夹/apache-tomcat-10.0.0-M7-windows-x64.zip" + i);

			int beginBytes = i * blocksize;
			int endBytes = beginBytes + blocksize;
			if (endBytes > filesize) {
				endBytes = filesize;
			}

			in.skip(beginBytes);

			int position = beginBytes;
			byte[] buffer = new byte[1024];
			int count;
			while ((count = in.read(buffer)) > 0) {
				if (position + count > endBytes) {
					int a = position + count - endBytes;
					count = count - a;
				}
				out.write(buffer, 0, count);
				position += count;

				if (position >= endBytes) {
					break;
				}
			}
			in.close();
			out.close();
			System.out.println("第" + (i + 1) + "块结束下载");
		}
		// 合并文件
		marge(filename,blocknums);
		System.out.println("共花了：" + (System.currentTimeMillis() - time) / 1000 + "秒");
		System.out.println("下载完成");
	}
	
	
	public static void marge(String path, int filename) throws IOException {

		FileOutputStream fos = new FileOutputStream(path);
		for (int i = 0; i < filename; i++) {
			FileInputStream fis = new FileInputStream(path + i);
			byte[] buffer = new byte[1024];
			int count;
			while ((count = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fis.close();
		}
		fos.close();
	}
}