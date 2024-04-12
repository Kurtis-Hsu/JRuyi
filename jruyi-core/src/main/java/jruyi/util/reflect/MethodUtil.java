package jruyi.util.reflect;

import jakarta.annotation.Nullable;
import jruyi.util.Assert;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <h2>{@link Method} 工具</h2>
 *
 * @Date 2024-03-21 11:18
 */
public abstract class MethodUtil extends MemberUtil
{
    /**
     * @param type 指定类
     * @return 指定类及其原型链上的所有类的所有方法的数组
     */
    public static Method[] getMethods(Class<?> type) { return getMethodList(type).toArray(Method[]::new); }

    /**
     * @param type 指定类
     * @return 指定类及其原型链上的所有类的所有方法的列表
     */
    public static List<Method> getMethodList(Class<?> type)
    {
        Assert.paramNonnull(type, "type");
        var methods = new LinkedList<Method>();
        for (var target = type; target != null; target = target.getSuperclass())
            Collections.addAll(methods, type.getDeclaredMethods());
        return methods;
    }

    /**
     * 遍历指定类的所有方法并调用回调函数进行处理，默认不使用过滤器
     *
     * @param type     指定类
     * @param consumer 处理方法的回调函数
     * @see #handleMethod(Class, MemberConsumer, MemberFilter)
     */
    public static void handleMethod(Class<?> type, MemberConsumer<Method> consumer)
    {
        handleMethod(type, consumer, null);
    }

    /**
     * 遍历指定类的所有方法并调用回调函数进行处理
     *
     * @param type     指定类
     * @param consumer 处理方法的回调函数
     * @param filter   筛选需要处理的方法的过滤器函数
     */
    public static void handleMethod(
            Class<?> type, MemberConsumer<Method> consumer, @Nullable MemberFilter<Method> filter
    )
    {
        Assert.paramNonnull(type, "type");
        Assert.paramNonnull(consumer, "consumer");
        for (var m : type.getDeclaredMethods())
            if (filter == null || filter.apply(m))
                try { consumer.accept(m); }
                catch (IllegalAccessException e)
                {
                    throw new IllegalStateException(STR."Not allowed to access method [\{m.getName()}]: \{e}");
                }
    }

    /**
     * 遍历指定类及其原型链上的所有类的所有方法并调用回调函数进行处理，默认不使用过滤器
     *
     * @param type     指定类
     * @param consumer 处理方法的回调函数
     * @see #handleMethods(Class, MemberConsumer, MemberFilter)
     */
    public static void handleMethods(Class<?> type, MemberConsumer<Method> consumer)
    {
        handleMethods(type, consumer, null);
    }

    /**
     * 遍历指定类及其原型链上的所有类的所有方法并调用回调函数进行处理
     *
     * @param type     指定类
     * @param consumer 处理方法的回调函数
     * @param filter   筛选需要处理的方法的过滤器函数
     */
    public static void handleMethods(
            Class<?> type, MemberConsumer<Method> consumer, @Nullable MemberFilter<Method> filter
    )
    {
        Assert.paramNonnull(type, "type");
        Assert.paramNonnull(consumer, "consumer");
        for (var target = type; target != null && target != Object.class; target = target.getSuperclass())
            for (var m : target.getDeclaredMethods())
                if (filter == null || filter.apply(m))
                    try { consumer.accept(m); }
                    catch (IllegalAccessException e)
                    {
                        throw new IllegalStateException(STR."Not allowed to access method [\{m.getName()}]: \{e}");
                    }
    }
}