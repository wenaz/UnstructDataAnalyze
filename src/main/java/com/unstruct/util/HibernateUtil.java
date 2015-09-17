package com.unstruct.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 获取实体所有字段
     * @param clazz 实体类型
     * @param strs 需要排除的字段
     * @return 其中key为数据库字段名称，value为字段对应的get方法
     */
    public static Map<String,Method> getFields(Class clazz,List<String> strs){
        Map<String,Method> map=new HashMap<>();
        Method[] methods=clazz.getMethods();
        for (Method method:methods){
            Column c=method.getAnnotation(Column.class);
            if (null!=strs&&strs.contains(c.name())) {
                continue;
            }
            if (null != c) {
                map.put(c.name(), method);
            } else {
                JoinColumn jc = method.getAnnotation(JoinColumn.class);
                if (null!=strs&&strs.contains(jc.name())) {
                    continue;
                }
                if (null != jc) {
                    map.put(jc.name(), method);
                }
            }

        }
        return map;
    }

}