package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(8090);
        System.out.println("TCP 서버 대기 중...");

        Socket so = ss.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));

        while(true) {
            System.out.println("클라이언트의 메세지를 기다리는 중...");
            String getMsg = br.readLine();
            System.out.println(so.getInetAddress() + " : " + getMsg);

            System.out.print("보낼 메세지 : ");
            String sendMsg = in.readLine();
            bw.write(sendMsg + "\n");
            bw.flush();
        }

    }

}
