/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_TH2;

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
public class Cau3_Server {
    public static DatagramPacket recPK = null;
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        DatagramSocket server = new DatagramSocket(1109);
        
        System.out.println("Server===");
        while(true) {
            Student student = receive(server);
            System.out.println(student.getCode());
            
            Student student2 = new Student("281", student.getCode(), "nGuyEn  Van Quang", null);
            send(student2, server);
            
            Student student3 = receive(server);
            System.out.println(student3.getName() + " " + student3.getEmail());
        }
    }
    
    public static void send(Student st, DatagramSocket server) throws IOException{
        if(server != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(st);
            oos.flush();
            
            byte[] dataSend = baos.toByteArray();
            
            DatagramPacket sendPK = new DatagramPacket(dataSend, dataSend.length, recPK.getAddress(), recPK.getPort());
            
            server.send(sendPK);
        }
    }
    
    public static Student receive(DatagramSocket server) throws IOException, ClassNotFoundException{
        Student st = null;
        if(server != null){
            byte[] data = new byte[1024];
            
            recPK = new DatagramPacket(data, data.length);
            
            server.receive(recPK);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            st = (Student) ois.readObject();
            
            return st;
        }
        
        return null;
    }
}
