package tcp;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ZhangRui on 16/12/2.
 * 使用tcp协议发送socket
 */

public class Main {
    // 端口号 随意 避免常用的
    private static final int PORT = 2333;

    public static void main(String[] args) {
        try {
            boolean flag = true;// 是否一直的读取
            // 建立socket的服务端
            ServerSocket serverSocket = new ServerSocket(PORT);
            // 服务端 等待 客户端的链接
            // 返回值是链接过来的客户端
            // 该方法会让线程阻塞在这行代码, 直到有客户端链接
            System.out.println("服务器正在等待客户端连入");
            Socket client = serverSocket.accept();
            System.out.println("有客户端连入了");
            // 从客户端 获得输入/ 输出流
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintStream printStream = new PrintStream(outputStream);

            // 开始循环, 一直的去读取
            while (flag) {
                String result = "";
                String line = "";

                while (!"".equals(line = bufferedReader.readLine())) {
                    result += line;
                }

                // 打印客户端的数据
                System.out.println("客户端说:" + result);
                if ("886".equals(result)) {
                    // 如果客户端 发送的信息是886
                    // 就退出
                    flag = false;
                }
                // 读到了 客户端发来的数据
                String echo = "我是服务器, 我接到了你的数据\n";

                // 返回数据
                printStream.println(echo);// 一定要有换行因为我们都是按行读取信息
            }
            // 关闭掉所有的资源
            closeIO(bufferedReader, inputStreamReader, inputStream,
                    printStream, outputStream);
            // 除了流资源 socket 资源也需要关闭
            client.close();
            serverSocket.close();
            System.out.println("服务器关闭");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 关闭所有能关闭的东西
    public static void closeIO(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
