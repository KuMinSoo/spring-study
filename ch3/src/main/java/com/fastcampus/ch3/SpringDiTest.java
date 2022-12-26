/*
package com.fastcampus.ch3;


import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;


class Engine{} // <bean id="engine" class="com.campus.ch3.Engine"/>
@Component class SuperEngine extends Engine{}
@Component class TurboEngine extends Engine{}
@Component
class Door{}
@Component
class Car{
    @Value("red")
    String color;
    @Value("100")
    int oil;
   @Autowired
   @Qualifier("superEngine")
   Engine engine;
    @Autowired
    Door[] doors;

    public Car(){

    }

    public Car(String color, int oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoors(Door[] door) {
        this.doors = door;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", door=" + Arrays.toString(doors) +
                '}';
    }
}

public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac= new GenericXmlApplicationContext("config.xml");
        //Car car=(Car)ac.getBean(Car.class);
        Car car=(Car)ac.getBean("car");
        Car car2=ac.getBean("car",Car.class);

        //Engine engine=(Engine) ac.getBean("engine");
        Engine engine=(Engine) ac.getBean("superEngine");
        Door door =(Door)ac.getBean("door");
    */
/*    car.setColor("red");
        car.setOil(100);
        car.setEngine(engine);
        car.setDoors(new Door[]{ac.getBean("door", Door.class),ac.getBean("door", Door.class) });
      *//*
  System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }
}
*/
