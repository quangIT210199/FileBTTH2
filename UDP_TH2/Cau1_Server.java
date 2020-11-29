/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_TH2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author quang
 */
public class Cau1_Server {
    public static DatagramPacket receivePacket = null;
    
    public static void main(String[] args) throws SocketException, IOException {
        String input;
        DatagramSocket server = new DatagramSocket(1107);
        
        System.out.println("Server run: ");
        
        while(true){
            //Nhận từ client
            input = receive(server);
        
            System.out.println(input);
            String question = "B17DCAT148;5456498,-2,-021654,5465,548,42,1485,2314,842,10,485,1218,420,248,12,185,23012,3458,231,5674,5102,0564,320,325456,1,156,132,1894,5,14564,231,84,2,854,2058,120,245,2,1851,23,586,4230,56,145321,21,654132,023,564,3210,65,848";
            
            send(question, server);
            
            String output = receive(server);
            
            System.out.println(output);
        
        }
    }
    
    public static void send(String str, DatagramSocket server) throws IOException{
        if(server != null){
            byte[] sendData = new byte[1024];
            //cho vào bộ đệm để gửi
            sendData = str.getBytes();
            //Tạo DatagramPacket để gửi
            
            DatagramPacket sendPacket = 
                    new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(),receivePacket.getPort());
            
            server.send(sendPacket);
        }
    }
    
    public static String receive(DatagramSocket server) throws IOException{
        if(server != null){
            byte[] receiveData = new byte[1024];
            //Nhận thì chỉ cần biết biến chứa và độ dài
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            
            server.receive(receivePacket);
            
            return new String(receivePacket.getData()).trim();
        }
        
        return null;
    }
}
