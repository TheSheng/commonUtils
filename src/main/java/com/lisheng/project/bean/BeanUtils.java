package com.lisheng.project.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName： beanUtils
 * @description: 一些操作类的工具类
 * @author: 李胜
 * @create: 2020-06-06 14:39
 **/
public class BeanUtils {
    public static Map objectTranserToMap(Object obj) throws Exception{
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Map map=new HashMap();
        for(Field field:declaredFields){
            field.setAccessible(true);
            map.put(field.getName(),field.get(obj));
        }
        return map;
    }


    public static void objectTranserToObejct(Object obj,Object newObj) throws Exception{
        Map<String,Field> filedStore=new HashMap<>();
        Arrays.stream(newObj.getClass().getDeclaredFields()).forEach(field -> {
            filedStore.put(field.getName(),field);
        });
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            String targetName=null;
            field.setAccessible(true);
            Definition annotation = field.getAnnotation(Definition.class);
            if(null!=annotation){
                targetName=annotation.targetName();
                Field fieldTarget = filedStore.get(targetName);
                if(null==fieldTarget){
                    System.err.println(String.format("没有在目标类中找到名为%s的属性",targetName));
                     continue;
                }
                fieldTarget.setAccessible(true);
                BeanDefinition beanDefinition = annotation.target().newInstance();
                fieldTarget.set(newObj,beanDefinition.transerMethod(field.get(obj)));
            }else {
                targetName=field.getName();
                Field fieldTarget = filedStore.get(targetName);
                if(null==fieldTarget){
                    System.err.println(String.format("没有在目标类中找到名为%s的属性",targetName));
                    continue;
                }
                fieldTarget.setAccessible(true);
                fieldTarget.set(newObj,field.get(obj));
            }
        }
    }
}
