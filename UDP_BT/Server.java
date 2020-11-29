/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_BT;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author luongtx
 */
public class Server {
    DatagramSocket serverSocket;
    DatagramPacket sendPk;
    DatagramPacket receivePk;
    public Server(){
        try{
            serverSocket = new DatagramSocket(1109);
            System.out.println("server is running");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void sendSV(Student std){
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(std);
            byte[] buf = baos.toByteArray();
            sendPk = new DatagramPacket(buf, buf.length, receivePk.getSocketAddress());
            serverSocket.send(sendPk);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Student receiveSV(){
        Student std = null;
        try{
            byte[] buf = new byte[1024];
            receivePk = new DatagramPacket(buf, buf.length);
            serverSocket.receive(receivePk);
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            std = (Student) ois.readObject();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return std;
    }
    public void listen(){
        Student std = receiveSV();
        std.setId(100);
        std.setCode("B16DCAT100");
        std.setGpa((float)3.5);
        sendSV(std);
        std = receiveSV();
        System.out.println(std.getId());
        System.out.println(std.getCode());
        System.out.println(std.getGpaLetter());
    }
    public static void main(String[] args) {
        Server server = new Server();
        while(true){
            server.listen();
        }
    }
}