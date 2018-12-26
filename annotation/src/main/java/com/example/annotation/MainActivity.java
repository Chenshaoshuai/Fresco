package com.example.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectViewAnnotation(R.id.btn_ok)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parser.bind(this);

    }
    private void reflexDemoBean() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException {
        UserBean userBean = new UserBean();
        Class c = Class.forName(userBean.getClass().getCanonicalName());

        Field[] fields = c.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        sb.append(Modifier.toString(userBean.getClass().getModifiers()) + " class " + userBean.getClass().getSimpleName() + "{\n");

        for (Field field : fields) {
            field.setAccessible(true);
            sb.append(Modifier.toString(field.getModifiers()) + " ");
            sb.append(field.getType().getSimpleName() + " ");
            sb.append(field.getName() + ";\n");
            field.set(userBean, "aaaaaaaaaaaaaa");
            Object o = field.get(userBean);
            if (o instanceof String) {
                String str = String.valueOf(o);
                sb.append(str + ";\n");
            }
        }
        sb.append("}\n");
        System.out.println("-------------------------------------");
        System.out.println(sb);

        Constructor<?> cons = c.getConstructor();
        System.out.println("------------------------------------");
        System.out.println("构造方法方法名：" + cons.getName());
        System.out.println("构造方法修饰符：" + Modifier.toString(cons.getModifiers()));
        System.out.println("构造方法全部：" + cons);


        Method[] methods = c.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("----------------------------------");
            System.out.println("方法名：" + methods[i].getName());
            System.out.println("方法返回类型：" + methods[i].getReturnType());
            System.out.println("方法访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("方法代码写法： " + methods[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                try {
                    reflexDemoBean();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                break;
                default:
                    break;
        }
    }
}
