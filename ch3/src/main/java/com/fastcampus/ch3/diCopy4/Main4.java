package com.fastcampus.ch3.diCopy4;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component class Car{
   @Resource
   Engine engine;
   @Resource
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
@Component class Door{}
@Component class SportsCar extends Car{ }
@Component class Truck extends Car{}
@Component class Engine{}
class AppContext{
    Map map;
    AppContext(){
        map=new HashMap();
        doComponentScan();
        doAutowired();
        doResource();
    }

    private void doResource() {
        try {
            for(Object bean: map.values()){
                for(Field fld: bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Resource.class)!=null){
                        fld.set(bean,getBean(fld.getName()));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        try {
            for(Object bean: map.values()){
                for(Field fld: bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Autowired.class)!=null){
                        fld.set(bean,getBean(fld.getType()));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doComponentScan() {
        try {
            ClassLoader classLoader=AppContext.class.getClassLoader();
            ClassPath classPath=ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set=classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

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
public class Main4 {


    public static void main(String[] args) throws Exception{

        AppContext ac=new AppContext();
        Engine engine=(Engine)ac.getBean("engine");
        Car car=(Car)ac.getBean("car");
        Door door=(Door) ac.getBean(Door.class);

     //   car.engine=engine;
     //   car.door=door;
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("car2 = " + door);
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
