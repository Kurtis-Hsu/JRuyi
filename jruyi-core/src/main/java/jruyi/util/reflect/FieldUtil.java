package jruyi.util.reflect;

import jakarta.annotation.Nullable;
import jruyi.util.Assert;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <h2>{@link Field} 工具</h2>
 *
 * @Date 2024-02-21 15:39
 */
public abstract class FieldUtil extends MemberUtil
{
    /**
     * @param type 指定类
     * @return 指定类及其原型链上的所有类的所有字段的数组
     */
    public static Field[] getFields(Class<?> type) { return getFieldList(type).toArray(Field[]::new); }

    /**
     * @param type 指定类
     * @return 指定类及其原型链上的所有类的所有字段的列表
     */
    public static List<Field> getFieldList(Class<?> type)
    {
        Assert.paramNonnull(type, "type");
        var fields = new LinkedList<Field>();
        for (var target = type; target != null; target = target.getSuperclass())
            Collections.addAll(fields, type.getDeclaredFields());
        return fields;
    }

    /**
     * 遍历指定类的所有字段并调用回调函数进行处理，默认不使用过滤器
     *
     * @param type     指定类
     * @param consumer 处理字段的回调函数
     * @see #handleField(Class, MemberConsumer, MemberFilter)
     */
    public static void handleField(Class<?> type, MemberConsumer<Field> consumer) { handleField(type, consumer, null); }

    /**
     * 遍历指定类的所有字段并调用回调函数进行处理
     *
     * @param type     指定类
     * @param consumer 处理字段的回调函数
     * @param filter   筛选需要处理的字段的过滤器函数
     */
    public static void handleField(Class<?> type, MemberConsumer<Field> consumer, @Nullable MemberFilter<Field> filter)
    {
        Assert.paramNonnull(type, "type");
        Assert.paramNonnull(consumer, "consumer");
        for (var f : type.getDeclaredFields())
            if (filter == null || filter.apply(f))
                try { consumer.accept(f); }
                catch (IllegalAccessException e)
                {
                    throw new IllegalStateException(STR."Not allowed to access field [\{f.getName()}]: \{e}");
                }
    }

    /**
     * 遍历指定类及其原型链上的所有类的所有字段并调用回调函数进行处理，默认不使用过滤器
     *
     * @param type     指定类
     * @param consumer 处理字段的回调函数
     * @see #handleFields(Class, MemberConsumer, MemberFilter)
     */
    public static void handleFields(Class<?> type, MemberConsumer<Field> consumer)
    {
        handleFields(type, consumer, null);
    }

    /**
     * 遍历指定类及其原型链上的所有类的所有字段并调用回调函数进行处理
     *
     * @param type     指定类
     * @param consumer 处理字段的回调函数
     * @param filter   筛选需要处理的字段的过滤器函数
     */
    public static void handleFields(Class<?> type, MemberConsumer<Field> consumer, @Nullable MemberFilter<Field> filter)
    {
        Assert.paramNonnull(type, "type");
        Assert.paramNonnull(consumer, "consumer");
        for (var target = type; target != null && target != Object.class; target = target.getSuperclass())
            for (var f : target.getDeclaredFields())
                if (filter == null || filter.apply(f))
                    try { consumer.accept(f); }
                    catch (IllegalAccessException e)
                    {
                        throw new IllegalStateException(STR."Not allowed to access field [\{f.getName()}]: \{e}");
                    }
    }
}