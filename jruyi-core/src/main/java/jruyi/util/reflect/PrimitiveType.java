package jruyi.util.reflect;

import jakarta.annotation.Nullable;
import jruyi.util.Assert;
import jruyi.util.StringUtil;

/**
 * <h2>原始类型枚举</h2>
 */
public enum PrimitiveType
{
    BYTE(byte.class, Byte.class), SHORT(short.class, Short.class), INTEGER(int.class, Integer.class),
    LONG(long.class, Long.class), FLOAT(float.class, Float.class), DOUBLE(double.class, Double.class),
    CHAR(char.class, Character.class), BOOLEAN(boolean.class, Boolean.class), VOID(void.class, Void.class),
    ;

    /**
     * 类型名称与包装类的类名
     */
    private final String primitiveName, wrapperName;
    /**
     * 原始类对象与包装类对象
     */
    private final Class<?> primitive, wrapper;

    PrimitiveType(Class<?> primitive, Class<?> wrapper)
    {
        this.primitiveName = primitive.getName();
        this.primitive = primitive;
        this.wrapper = wrapper;
        this.wrapperName = wrapper.getName();
    }

    /**
     * <p>
     * 通过名称获取，
     * 可使用原始类型名或包装类型名
     * </p>
     *
     * @param name 类型名称
     * @return 原始类型枚举
     */
    @Nullable
    static PrimitiveType of(String name)
    {
        Assert.paramNonnull(name, "primitiveName");
        if (!StringUtil.hasText(name)) return null;
        for (var v : values())
            if (v.primitiveName.equals(name) || v.wrapperName.equals(name))
                return v;
        return null;
    }

    /**
     * <p>
     * 通过类型获取,
     * 可使用原始类型或包装类型
     * </p>
     *
     * @param type 类型名称
     * @return 原始类型枚举
     */
    @Nullable
    static PrimitiveType of(Class<?> type)
    {
        Assert.paramNonnull(type, "type");
        for (var v : values())
            if (v.primitive == type || v.wrapper == type)
                return v;
        return null;
    }

    public String primitiveName() { return primitiveName; }

    public String wrapperName() { return wrapperName; }

    public Class<?> primitive() { return primitive; }

    public Class<?> wrapper() { return wrapper; }
}