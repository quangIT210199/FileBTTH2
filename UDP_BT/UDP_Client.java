/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_BT;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author quang
 */
public class UDP_Client {
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        DatagramSocket client = new DatagramSocket();
        
        Student st = new Student("B17DCAT148");
        send(st, client);
        
        Student stu = receive(client);
        System.out.println(stu.getGpa());
        
        float gpa = stu.getGpa();
        
        if(gpa >= 3.7){
            stu.setGpaLetter("A");
        }else if(gpa >= 3.0){
            stu.setGpaLetter("B");
        }else if(gpa >= 2.0){
            stu.setGpaLetter("C");  
        }
        else if(gpa >= 1.0){
            stu.setGpaLetter("D");
        }else{
            stu.setGpaLetter("F");
        }
        
        send(stu, client);
        
        client.close();
    }
    
    public static void send(Student st, DatagramSocket client) throws IOException{
        if(client != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            
            oos.writeObject(st);// viết tk student vao luồng
            oos.flush();
            
            byte[] data = baos.toByteArray();
            
            DatagramPacket sendPK = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 1109);
            
            client.send(sendPK);
        }
    }
    
    public static Student receive(DatagramSocket client) throws IOException, ClassNotFoundException{
        
        if(client != null){
            Student st = null;
            
            byte[] data = new byte[1024];
            DatagramPacket rePK = new DatagramPacket(data, data.length);
            client.receive(rePK);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            st = (Student) ois.readObject();
            
            return st;
        }
        return null;
    }
}
