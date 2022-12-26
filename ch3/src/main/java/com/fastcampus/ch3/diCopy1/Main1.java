package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Objects;
import java.util.Properties;

class Car{ }
class SportsCar extends Car{ }
class Truck extends Car{}
class Engine{}
public class Main1 {
    public static void main(String[] args) throws Exception{


        Engine engine=(Engine)getObject();
        System.out.println("car1 = " + engine);

    }
    static Object getObject() throws Exception{
        Properties p= new Properties();
        p.load(new FileReader("config.txt"));
        Class clazz=Class.forName(p.getProperty("engine"));
        return clazz.newInstance();
    }
    static Car getCar() throws Exception{
        Properties p= new Properties();
        p.load(new FileReader("config.txt"));
        Class clazz=Class.forName(p.getProperty("car"));
        return (Car)clazz.newInstance();
    }

}
