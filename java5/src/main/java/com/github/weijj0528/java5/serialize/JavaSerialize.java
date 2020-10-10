package com.github.weijj0528.java5.serialize;

import java.io.*;

/**
 * @author William
 * @Date 2019/2/27
 * @Description Java序列化
 */
public class JavaSerialize {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Entity entity = new Entity("Test", 18);
        System.out.println(entity.toString());
        System.out.println("Name:" + entity.getName());
        System.out.println("Age:" + entity.getAge());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutput oop = new ObjectOutputStream(baos);
        oop.writeObject(entity);
        oop.flush();
        oop.close();
        // deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        entity = (Entity) ois.readObject();
        ois.close();
        System.out.println("===============================================");
        System.out.println(entity.toString());
        System.out.println("Name:" + entity.getName());
        System.out.println("Age:" + entity.getAge());
    }

}
