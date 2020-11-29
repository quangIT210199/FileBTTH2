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
public class UDP1_Client {
    public static void main(String[] args) throws SocketException, IOException {
        System.out.println("Client====");
        DatagramSocket client = new DatagramSocket();
        
        send(";B17DCAT148", client);
        
        String ques = receive(client);
        System.out.println(ques);
        
        String[] arr = ques.split(";", 2);
        
        String[] arrNum = arr[1].split(",", 2);
        int a = Integer.parseInt(arrNum[0]);
        int b = Integer.parseInt(arrNum[1]);
        
        int ucln = ucln(a, b);
        int bcnn = (a * b) / ucln;
        int sum = a + b;
        int mul = a * b;
        
        String kq = arr[0] +";" + ucln +"," + bcnn +","+ sum+","+mul;
        send(kq, client);
        
        client.close();
    }
    
    public static int ucln(int a, int b){
        while(a != b){
            if(a > b){
                a = a - b;
            }
            else{
                b = b - a;
            }
        }
        
        return a;
    }
    
    public static void send(String str, DatagramSocket client) throws UnknownHostException, IOException{
        if(client != null){
            byte[] dataSend = new byte[1024];
            
            dataSend = str.getBytes();
            
            InetAddress inet = InetAddress.getByName("localhost");
            
            DatagramPacket sendPK = new DatagramPacket(dataSend, dataSend.length, inet, 8080);
            
            client.send(sendPK);
        }
    }
    public static String receive(DatagramSocket client) throws IOException{
        if(client != null){
            byte[] dataRe = new byte[1024];
            
            DatagramPacket rePK = new DatagramPacket(dataRe, dataRe.length);
            
            client.receive(rePK);
            
            return new String(rePK.getData()).trim();
        }
        return null;
    }
}
