package TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        InetAddress is = InetAddress.getByName("127.0.0.1");
        Socket so = new Socket(is, 5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));

        while(true) {
            System.out.print("보낼 메세지 : ");
            String sendMsg = in.readLine();
            bw.write(sendMsg + "\n");
            bw.flush();

            System.out.println("서버의 메세지를 기다리는 중...");
            String getMsg = br.readLine();
            System.out.println(so.getInetAddress() + " : " + getMsg);
        }

    }

}
