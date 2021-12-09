package org.fpm.di;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DummyBinder implements Binder {
    private Map<Class, Class> class_list;
    private Map<Class, Object> instance_list;
    @Override
    synchronized public <T> void bind(Class<T> clazz) {
        this.class_list.put(clazz,clazz);
    }

    @Override
    synchronized public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        if (isContainsClassList(clazz)) {
            this.class_list.replace(clazz, clazz, implementation);
        }
        else
            this.class_list.put(clazz,implementation);
        this.bind(implementation);
    }

    @Override
    synchronized public <T> void bind(Class<T> clazz, T instance) {
        this.instance_list.put(clazz,instance);
    }
    DummyBinder(Map<Class, Class> ClassList, Map<Class, Object> InstanceList) {
//        Class <?> cls = container.getClass();
//        Field class_list = cls.getDeclaredFields()[0];
//        Field instance_list = cls.getDeclaredFields()[1];
//        class_list.setAccessible(true);
////        instance_list.setAccessible(true);
//        try {
//            this.class_list = (HashMap<Class, Class>) class_list.get(container);
//            this.instance_list = (HashMap<Class, Object>) instance_list.get(container);
//        }
//        catch (IllegalAccessException ignored) {
//        }
        this.class_list = ClassList;
        this.instance_list = InstanceList;

    }
    synchronized <T> boolean isContainsClassList(Class<T> clazz){
        return class_list.containsKey(clazz) || class_list.containsValue(clazz);
    }
}
