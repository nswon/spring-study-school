package UDP;

import java.io.IOException;

import java.net.DatagramPacket;

import java.net.DatagramSocket;



public class Server {

    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket(8040);

        byte[] data = new byte[65535];



        DatagramPacket dp = new DatagramPacket(data, data.length);

        System.out.println("UDP 서버 대기중...");



        while (true) {

            ds.receive(dp);

            System.out.println(dp.getAddress() + ": " + new String(dp.getData()).trim());

        }

    }

}
