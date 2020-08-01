package com.yc.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//服务端
public class FileUpload_Server {
    public static void main(String[] args) throws Exception {
        //1、创建服务
        ServerSocket serverSocket = new ServerSocket(8080);
        //2、监听客户端的连接
        //阻塞式监听，会一直等待客户端连接
        Socket socket = serverSocket.accept();
        //3、获取输入流
        InputStream is = socket.getInputStream();
        //4、文件输出
        FileOutputStream fos = new FileOutputStream(new File("receive.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len=is.read(buffer))!=-1) {
            fos.write(buffer,0,len);
        }

        //通知客户端我接收完毕
        OutputStream os = socket.getOutputStream();
        os.write("我接收完毕了，你可以断开了".getBytes());

        //5、关闭资源
        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
    }

}
