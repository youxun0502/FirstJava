package network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientWithThread {
    public static void main(String[] args)  {


        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入要連線的主機IP");
        String host = scanner.nextLine();
        System.out.println("請輸入要連線的Port");
        int port = Integer.parseInt(scanner.nextLine());

        try (Socket socket = new Socket(host, port);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw);
             InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)
        ) {

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    String line;
                    try {
                        while (true) {
                                if ((line = br.readLine()) != null) {
                                    System.out.println("接收到:" + line);
                                }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.setDaemon(true);
            t1.start();
            
            System.out.print("請輸入傳送文字，輸入b中斷：");
            String input = scanner.nextLine();
            while (input != null) {
                //送Hello World出去
                bw.write(input + System.lineSeparator());
                bw.flush();

                System.out.print("請輸入傳送文字，輸入b中斷：");
                input = scanner.nextLine();
                if (input.equals("b")) {
                    break;
                }
            }
           
        } catch (UnknownHostException e) {
            System.out.println("找不到主機");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
