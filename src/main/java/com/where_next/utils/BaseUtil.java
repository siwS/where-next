package com.where_next.utils;

import java.lang.reflect.ParameterizedType;

public class BaseUtil {

    public static Class getGenericType(Class clazz) {
        return (Class) ((ParameterizedType)clazz.
                getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }
}
