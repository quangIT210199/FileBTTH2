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
import java.net.UnknownHostException;

/**
 *
 * @author quang
 */
public class Cau2_Client {
    public static void main(String[] args) throws SocketException, IOException {
        System.out.println("Client===");
        DatagramSocket client = new DatagramSocket();
        
        sendData(";B17DCAT148", client);
        
        String ques = recieveData(client);
        
        String[] arr = ques.split(";", 2);
        
        String kq = arr[1].toLowerCase();//cho về chữ thg hết
        
        String kq2 = "";    
        
        for (int i = 0; i < kq.length(); i++) {
            if(i == 0 || (kq.charAt(i - 1) == ' ' && kq.charAt(i) != ' ')){
                char c = Character.toUpperCase(kq.charAt(i));
                
                kq2 += c;
            }
            else
                kq2 += kq.charAt(i);
        }
        
        System.out.println(kq2);
        sendData(kq2, client);
    }
    
    public static void sendData(String str, DatagramSocket client) throws UnknownHostException, IOException{
        if(client != null){
            byte[] data = new byte[1024];
            
            data = str.getBytes();
            
            InetAddress inet = InetAddress.getByName("localhost");
        
            DatagramPacket DataPK = new DatagramPacket(data, data.length, inet, 1108);
            
            client.send(DataPK);
        }
    }
    
    public static String recieveData(DatagramSocket client) throws IOException{
        if(client != null){
            byte[] data = new byte[1024];
            
            DatagramPacket dataPK = new DatagramPacket(data, data.length);
            
            client.receive(dataPK);
            
            return new String(dataPK.getData()).trim();
        }
        return null;
    }
}
