package main.java.study;


import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public class basedao<T> {
    protected  Class<T> clazz;

    public basedao(){
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
    }

    public void del(){
        try {
            Object obj = clazz.newInstance();
            Method setId = clazz.getMethod("setId",Integer.class);
            System.out.println(setId);
            setId.invoke(obj,new Integer(1));
            Method getId =obj.getClass().getMethod("getId");
            System.out.println(getId.invoke(obj));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
