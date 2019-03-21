package until;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 新的json工具类
 * @author IMajes
 */
public class JsonUtil {

    /**
     * 应该是把一个集合转换成json字符串
     * @param cls    集合保存的类型，映射class
     * @param object （对象）
     * @param list   应该是要转换的集合
     * @return 返回一个json字符串
     */
    public static String  toJson(Class cls, Object object, List list) {
        //Field 提供有关类或接口的单个字段的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段。
        Field[] fields = cls.getDeclaredFields();//返回一个 Field 对象的数组，该对象反映此 Class 对象所表示的类或接口的所有已声明字段。
        String[] modelNames = new String[fields.length];

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < list.size(); i++) {
            //解析第i个元素，转换成json格式字符串
            JsonObject jsonObject = new JsonObject();
            for (int j = 0; j < modelNames.length; j++) {
                //得到属性的名字和对应的值给转换成键值对的字符串添加到jsonObject中
                jsonObject.addProperty(fields[j].getName(), getFieldValue(object, fields[j].getName()).toString());
            }
            //以上操作把集合中第i个元素转换成了json字符串
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }


    public static String  toJson(Class cls, List list) {
        //Field 提供有关类或接口的单个字段的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段。
        Field[] fields = cls.getDeclaredFields();//返回一个 Field 对象的数组，该对象反映此 Class 对象所表示的类或接口的所有已声明字段。
        String[] modelNames = new String[fields.length];

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < list.size(); i++) {
            //解析第i个元素，转换成json格式字符串
            JsonObject jsonObject = new JsonObject();
            for (int j = 0; j < modelNames.length; j++) {
                //得到属性的名字和对应的值给转换成键值对的字符串添加到jsonObject中
                jsonObject.addProperty(fields[j].getName(), getFieldValue(list.get(i), fields[j].getName()).toString());
            }
            //以上操作把集合中第i个元素转换成了json字符串
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    /**
     * 把一个对象转换成json字符串
     * @param cls 类型（映射class）
     * @param object
     * @return 返回一个json字符串
     */
    public static String toJson(Class cls, Object object) {
        //Field 提供有关类或接口的单个字段的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段。
        Field[] fields = cls.getDeclaredFields();//返回一个 Field 对象的数组，该对象反映此 Class 对象所表示的类或接口的所有已声明字段。
        String[] modelNames = new String[fields.length];
        JsonObject jsonObject = new JsonObject();
        for (int j = 0; j < modelNames.length; j++) {
            //得到属性的名字和对应的值给转换成键值对的字符串添加到jsonObject中
            jsonObject.addProperty(fields[j].getName(), getFieldValue(object, fields[j].getName()).toString());
        }
        return jsonObject.toString();
    }

    /**
     * 把一个对象转换成json对象
     * @param cls 映射类型
     * @param object 对象
     * @return 返回一个JsonObject
     */
    public static JsonObject toJsonObject(Class cls, Object object) {
        Field[] fields = cls.getDeclaredFields();
        String[] modelNames = new String[fields.length];
        JsonObject jsonObject = new JsonObject();
        for (int j = 0; j < modelNames.length; j++) {
            if (getFieldValue(object, fields[j].getName()).toString() != null) {
                jsonObject.addProperty(fields[j].getName(), getFieldValue(object, fields[j].getName()).toString());
            }
        }
        return jsonObject;
    }

    /**
     * 得到某个字段（属性）的值
     * @param owner     被操作的对象
     * @param fieldName 要得到值的字段（属性）名字
     * @return 返回一个属性的值（String类型）
     */
    private static String getFieldValue(Object owner, String fieldName) {
        try{
            //执行对象owner的fieldName属性对应的get方法，并变成字符串
            return invokeMethod(owner, fieldName, null).toString();
        }catch (Exception e){
            return "null";
        }
    }

    /**
     * 执行某个Field（属性）的getField（getXxx（））方法从而得到相对应的值
     * @param owner     类
     * @param fieldName 类的属性名称
     * @param args      参数，默认为null
     * @return 返回所求属性的值
     */
    private static Object invokeMethod(Object owner, String fieldName, Object[] args) {

        Class<? extends Object> ownerClass = owner.getClass();
        //根据fieldName提供的名字解析成真正的get方法的名字（get后面第一个字母是大写）比如：fieldName -> FieldName，
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        //声明一个方法对象
        Method method = null;
        try {
            method = ownerClass.getMethod("get" + methodName);//得到真正的get方法
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {
            return "";
        }
        //invoke getMethod
        try {
            //执行一下相应的get方法，并返回
            return method.invoke(owner);
        } catch (Exception e) {
            return "";
        }
    }
}
