package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component class Car{ }
@Component class SportsCar extends Car{ }
@Component class Truck extends Car{}
class Engine{}
class AppContext{
    Map map;
    AppContext(){
        map=new HashMap();
        doComponentScan();
    }

    private void doComponentScan() {
        try {
            ClassLoader classLoader=AppContext.class.getClassLoader();
            ClassPath classPath=ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set=classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for(ClassPath.ClassInfo classInfo : set){
                Class clazz=classInfo.load();
                Component component =(Component) clazz.getAnnotation(Component.class);
                if(component !=null){
                   String id= StringUtils.uncapitalize(classInfo.getSimpleName());
                   map.put(id,clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key){
        return map.get(key);
    }
    Object getBean(Class clazz){
        for(Object obj:map.values()){
            if(clazz.isInstance(obj))
                return obj;
        }
        return null;
    }
}
public class Main3 {


    public static void main(String[] args) throws Exception{

        AppContext ac=new AppContext();
        Engine engine=(Engine)ac.getBean("engine");
        Car car=(Car)ac.getBean("car");
        Car car2=(Car)ac.getBean(Car.class);
        System.out.println("engine = " + engine);
        System.out.println("car2 = " + car2);
    }
    static Object getBean() throws Exception{
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
