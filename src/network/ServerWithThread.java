package network;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerWithThread {
    public static void main(String[] args) {

        System.out.println("Server已啟動，等候連線中");
        try (ServerSocket ssc = new ServerSocket(8080);
             Socket socket = ssc.accept();  //在此等候對方的連線，沒有連線程式會停在此行
             InputStream inputStream = socket.getInputStream();
             InputStreamReader isr = new InputStreamReader(inputStream,"UTF8");
             BufferedReader br = new BufferedReader(isr);
             OutputStream outputStream = socket.getOutputStream();
             OutputStreamWriter osw = new OutputStreamWriter(outputStream,"UTF8")
             //ReadableByteChannel inChannel = Channels.newChannel(inputStream);
             //WritableByteChannel outChannel = Channels.newChannel(outputStream);
        ) {


            InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.println("收到來自" + remoteSocketAddress.getHostName());

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.print("請輸入回應訊息:");
                        Scanner scanner = new Scanner(System.in);
                        String message;
                        while ( (message = scanner.nextLine()) != null ){
                            message = message + System.lineSeparator();
                            osw.write(message);
                            osw.flush();
                            System.out.print("請輸入回應訊息:");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.setDaemon(true);
            t1.start();


            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("接收到:" + line);
            }
            
            /*
            //nio作法
            ByteBuffer buffer = ByteBuffer.allocate(80);
            while (inChannel.read(buffer) != -1) {
                buffer.flip();
                //以下範例可以列印出使用者輸入的字
                byte[] bufferBytes = new byte[buffer.limit()];
                buffer.get(bufferBytes);
                System.out.println(new String(bufferBsocketytes, StandardCharsets.UTF_8));
                可利用rewind復原position游標
                buffer.rewind();
                //end
                outChannel.write(buffer);
                buffer.clear();
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Echo Server end");
    }

}
