package jruyi.util;

import jakarta.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <h2>反射工具</h2>
 *
 * @Date 2024-02-21 15:39
 */
public abstract class ReflectionUtil
{
    // PART ----- FIELD -----

    /**
     * @param cls 指定类
     * @return 指定类及其原型链上的所有类的所有字段的数组，参数为 null 时返回 null
     */
    public static Field[] getFields(Class<?> cls) { return getFieldsList(cls).toArray(Field[]::new); }

    /**
     * @param cls 指定类
     * @return 指定类及其原型链上的所有类的所有字段的列表，参数为 null 时返回 null
     */
    public static List<Field> getFieldsList(@Nullable Class<?> cls)
    {
        if (cls == null) return null;
        List<Field> fields = new LinkedList<>();
        for (; cls != null; cls = cls.getSuperclass())
            Collections.addAll(fields, cls.getDeclaredFields());
        return fields;
    }
}