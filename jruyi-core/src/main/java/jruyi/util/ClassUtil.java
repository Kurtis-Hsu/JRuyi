package jruyi.util;

import jakarta.annotation.Nullable;
import jruyi.core.exception.ReflectionException;

/**
 * <h2>类工具</h2>
 *
 * @Date 2024-01-29 13:19
 */
public abstract class ClassUtil
{
    // PART ----- CONSTANTS -----

    public static final String ARRAY_SUFFIX = "[]";

    // PART ----- CHECK -----

    /**
     * <p>该方法仅用于检测, 不会加载类</p>
     *
     * <p>使用默认类加载器 {@link #getDefaultClassLoader()}</p>
     *
     * @param name 类名称
     * @return 指定类是否存在
     * @see Class#forName(String, boolean, ClassLoader)
     */
    public static boolean isPresent(String name) { return isPresent(name, null); }

    /**
     * <p>该方法仅用于检测, 不会加载类</p>
     *
     * @param name   类名称
     * @param loader 类加载器, 若该属性为 null, 使用默认类加载器 {@link #getDefaultClassLoader()}
     * @return 指定类是否存在
     * @see Class#forName(String, boolean, ClassLoader)
     */
    public static boolean isPresent(String name, @Nullable ClassLoader loader) { return forName(name, loader) != null; }

    // PART ----- FOR NAME -----

    /**
     * <p>默认不初始化类</p>
     *
     * @param name   类名
     * @param loader 类加载器，加载规则参考：{@link #getDefaultClassLoader()}
     * @return 类
     * @throws ReflectionException      找不到类
     * @throws IllegalArgumentException 类名为无效字符串
     * @see Class#forName(String, boolean, ClassLoader)
     */
    @Nullable
    public static Class<?> forName(String name, @Nullable ClassLoader loader) { return forName(name, false, loader); }

    /**
     * @param name       类名，支持使用数组后缀（{@code java.lang.Object[]}）风格类名
     * @param initialize 是否初始化类
     * @param loader     类加载器，加载规则参考：{@link #getDefaultClassLoader()}
     * @return 类，找不到名称对应的类时返回 null
     * @throws ReflectionException      找不到类
     * @throws IllegalArgumentException 类名为无效字符串
     * @see Class#forName(String, boolean, ClassLoader)
     */
    @Nullable
    public static Class<?> forName(String name, boolean initialize, @Nullable ClassLoader loader)
    {
        Assert.notBlank(name, "class name must be valid");

        // 尝试获取原始类型
        var result = getPrimitive(name);
        if (result != null) return result;

        // 检测是否为后缀为"[]"风格的数组类型名
        if (name.endsWith(ARRAY_SUFFIX))
        {
            result = forName(name.substring(0, name.length() - ARRAY_SUFFIX.length()), initialize, loader);
            return result != null ? result.arrayType() : null;
        }

        if (loader == null) loader = getDefaultClassLoader();

        // 通过 Class#forName 获取
        try { return Class.forName(name, initialize, loader); }
        catch (ClassNotFoundException _) { return null; }
    }

    /**
     * @param name 类型名称
     * @return 原始类型
     * @see PrimitiveType#of(String)
     */
    @Nullable
    public static Class<?> getPrimitive(@Nullable String name)
    {
        var type = PrimitiveType.of(name);
        return type == null ? null : type.primitive;
    }

    // PART ----- CLASS_LOADER -----

    /**
     * <p>
     * 按顺序查找类加载器：
     *     <ol>
     *         <li>线程上下文类加载器</li>
     *         <li>{@link ClassUtil} 的类加载器</li>
     *         <li>系统类加载器</li>
     *     </ol>
     *     返回第一个不为 null 的实例
     * </p>
     *
     * @return 默认类加载器
     */
    public static ClassLoader getDefaultClassLoader()
    {
        var loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
        {
            loader = ClassUtil.class.getClassLoader();
            if (loader == null) loader = ClassLoader.getSystemClassLoader();
        }
        return loader;
    }

    /**
     * <h2>原始类型枚举</h2>
     */
    enum PrimitiveType
    {
        BYTE(byte.class, Byte.class), SHORT(short.class, Short.class), INTEGER(int.class, Integer.class),
        LONG(long.class, Long.class), FLOAT(float.class, Float.class), DOUBLE(double.class, Double.class),
        CHAR(char.class, Character.class), BOOLEAN(boolean.class, Boolean.class), VOID(void.class, Void.class),
        ;

        /**
         * 类型名称与包装类的类名
         */
        final String name, wrapperName;
        /**
         * 原始类对象与包装类对象
         */
        final Class<?> primitive, wrapper;

        PrimitiveType(Class<?> primitive, Class<?> wrapper)
        {
            this.name = primitive.getName();
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
        static PrimitiveType of(@Nullable String name)
        {
            if (StringUtil.isBlank(name)) return null;
            for (var v : values())
                if (v.name.equals(name) || v.wrapperName.equals(name))
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
        static PrimitiveType of(@Nullable Class<?> type)
        {
            if (type == null) return null;
            for (var v : values())
                if (v.primitive == type || v.wrapper == type)
                    return v;
            return null;
        }
    }
}