package com.example.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class parser {
    public static void bind(Object object){
        try {
            parse(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parse(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        View view =null;
        Field[] declaredField = clazz.getDeclaredFields();
        for(Field field : declaredField){
           if(field.isAnnotationPresent(InjectViewAnnotation.class)){
               InjectViewAnnotation annotation = field.getAnnotation(InjectViewAnnotation.class);
               int value = annotation.value();
               if(value<0){
                   throw new Exception("faild");
               }else {
                   field.setAccessible(true);
                   if(object instanceof View){
                        view = ((View) object).findViewById(value);
                        view.setOnClickListener((View.OnClickListener)object);
                   }else if(object instanceof Activity) {
                       view = ((Activity) object).findViewById(value);
                       view.setOnClickListener((View.OnClickListener)object);
                   }
                   field.set(object,view);
               }
           }
        }

    }
}
