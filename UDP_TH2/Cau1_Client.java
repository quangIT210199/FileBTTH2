/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_TH2;

import java.io.IOException;
import static java.lang.Integer.max;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author quang
 */
public class Cau1_Client {
    public static void main(String[] args) throws SocketException, IOException {
        System.out.println("Client===");
        
        DatagramSocket client = new DatagramSocket();
        
        //gửi thông điệp cho server
        sendPK(";B17DCAT148", client);
        //lấy thông tin dc trả về để xử lý
        String question = receive(client);
        
        String[] arr = question.split(";", 2);//cắt 2 phần từ thôi
        
        int min = minStr(arr[1]);//tính min
        
        String kq = arr[0] + ";" + maxStr(arr[1]) + "," + minStr(arr[1]);
    
        System.out.println(kq);
        //gửi DL đi
        sendPK(kq, client);
//        client.close();
    }
    
    public static int minStr(String str){
        String[] arr = str.split(",");
        int[] arrData = new int[arr.length];
        int min = arrData[0];
        
        for (int i = 0; i < arr.length; i++) {
            arrData[i] = Integer.parseInt(arr[i]);
            
            if(min > arrData[i]){
                min = arrData[i];
            }
        }
        return min;
    }
    
    public static int maxStr(String str){
        int num = 0, res = 0;
        
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                num = num * 10 + (str.charAt(i) - '0');//đổi ra int
            }
            else {
                res = max(res, num);
                
                num = 0;// tìm dc max ms xét num về 0 để xét tiếp
            }
        }
        
        return max(res, num); //trả kq max cuối cùng
    }
    
    public static void sendPK(String result, DatagramSocket client) throws UnknownHostException, IOException{
        if(client != null){
            byte[] sendData = new byte[1024];
            
            InetAddress inet = InetAddress.getByName("localhost");
            
            sendData = result.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inet, 1107);
            
            client.send(sendPacket);
        }
    }
    
    public static String receive(DatagramSocket client) throws IOException{
        if(client != null){
            byte[] recieveData = new byte[1024];
            
            DatagramPacket rePacket = new DatagramPacket(recieveData, recieveData.length);
            
            client.receive(rePacket);//nhận dữ liệu vào gói Packet
            return new String(rePacket.getData()).trim();
        }
        
        return null;
    }
}

