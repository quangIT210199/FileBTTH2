/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_TH2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author quang
 */
public class Cau2_Server {
    //phải khởi tạo 1 datagramPK để nhận gói thông qua
    public static DatagramPacket receivePacket = null;
    public static void main(String[] args) throws SocketException, IOException {
        String input;
        System.out.println("Server nhận DL liên tục");
        DatagramSocket server = new DatagramSocket(1108);

        while(true){
            //gui DL
            input = receive(server);
            System.out.println(input);
            
            String question = "B17DCAT148;LaP tRINH mang 2018";
            send(question, server);
            
            String output = receive(server);
            System.out.println(output);
        }
    }
    
    public static void send(String str, DatagramSocket server) throws IOException{
        if(server != null){
            byte[] data = new byte[1024];
            
            data = str.getBytes();
            
            DatagramPacket sendPK = 
                    new DatagramPacket(data, data.length, receivePacket.getAddress(), receivePacket.getPort());
            
            server.send(sendPK);
        }
    }
    
    public static String receive(DatagramSocket server) throws IOException{
        if(server != null){
            byte[] data = new byte[1024];
            
            receivePacket = new DatagramPacket(data, data.length);
            
            server.receive(receivePacket);

            return new String(receivePacket.getData()).trim();
        }
        
        return null;
    }
}
