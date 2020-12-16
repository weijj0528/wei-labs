package com.github.weijj0528.java5.io;

import java.io.*;

/**
 * 序列化试验
 *
 * @author William.Wei
 */
public class SerializableDemo {

    public static void main(String[] args) throws Exception {
        User user = new User("William", 18);
        serializable(user);
        Student student = new Student("William",  18, "001");
        serializable(student);
        Person person = new Person("William", 18);
        serializable(person);
    }

    private static void serializable(Object o) throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        final byte[] bytes = baos.toByteArray();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));

        final Object object = ois.readObject();
        ois.close();
        System.out.println(object);
    }


    static class User implements Serializable {

        private String name;

        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    /**
     * 序列化忽略指定字段
     */
    static class Student extends User implements Serializable {

        private transient String no;

        public Student() {
        }

        public Student(String name, int age, String no) {
            super(name, age);
            this.no = no;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Student{" +
                    super.toString() +
                    ", no='" + no + '\'' +
                    '}';
        }
    }

    /**
     * 自定义序列化
     */
    static class Person extends User implements Externalizable {

        public Person() {
        }

        public Person(String name, int age) {
            super(name, age);
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(getName());
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            setName((String) in.readObject());
        }

        @Override
        public String toString() {
            return "Person{" +
                    super.toString() +
                    '}';
        }
    }


}
