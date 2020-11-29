/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_BT;

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
public class UDP2_Client {
    public static void main(String[] args) throws SocketException, IOException {
        System.out.println("Client===");
        DatagramSocket client = new DatagramSocket();
        
        send(";B17DCAT148", client);
        String ques = receive(client);
        System.out.println(ques);
        String[] arr = ques.split(";", 2);
        
        String str = arr[1];
        
        StringBuilder kq1 = new StringBuilder();
        StringBuilder kq2 = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            
            if((49 <= asc && asc <= 57) || (65 <= asc && asc <= 90) || (97 <= asc && asc <= 122)){
                kq1.append(str.charAt(i));
            }
            else{
                kq2.append(str.charAt(i));
            }
        }
        String ketqua = arr[0] +";"+ kq1+ "," + kq2;
        send(ketqua, client);
    }
    
    public static void send(String str, DatagramSocket client) throws UnknownHostException, IOException{
        if(client != null){
            byte[] dataSend = new byte[1024];
            
            dataSend = str.getBytes();
            DatagramPacket sendPK = new DatagramPacket(dataSend, dataSend.length, InetAddress.getByName("localhost"), 8080);
            
            client.send(sendPK);
        }
    }
    
    public static String receive(DatagramSocket client) throws IOException{
        if(client != null){
            byte[] data = new byte[1024];
            
            DatagramPacket rePK = new DatagramPacket(data, data.length);
            
            client.receive(rePK);//nhận gói PK
            
            return new String(rePK.getData()).trim();
        }
        return null;
    }
}
