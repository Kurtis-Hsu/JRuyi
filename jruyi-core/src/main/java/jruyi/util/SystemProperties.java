package jruyi.util;

import jakarta.annotation.Nullable;

import java.util.Properties;
import java.util.function.Supplier;

import static jruyi.util.Util.safeGet;

/**
 * <h2>系统属性</h2>
 *
 * <p>该对象获取值时需要与系统交互，有性能需求时需考虑缓存</p>
 *
 * @Date 2024-01-15 09:34
 */
@SuppressWarnings("unused")
public abstract class SystemProperties
{
    /**
     * @param props 属性列表
     * @see System#setProperties(Properties)
     */
    public static void set(@Nullable Properties props)
    {
        System.setProperties(props);
    }

    /**
     * @return 系统属性列表
     * @see System#setProperties(Properties)
     */
    public static Properties get()
    {
        return System.getProperties();
    }

    /**
     * 设置系统属性
     *
     * @param key   key 值
     * @param value value 值
     */
    public static void set(Object key, Object value) { System.setProperty(key.toString(), value.toString()); }

    /**
     * 获取不到属性时默认返回 {@code null}
     *
     * @param key 参数 key
     * @return 系统参数
     */
    @Nullable
    public static String get(String key)
    {
        return get(key, (String) null);
    }

    /**
     * @param key          参数 key
     * @param defaultValue 获取不到结果时的替代值
     * @return 系统参数
     */
    @Nullable
    public static String get(String key, @Nullable String defaultValue)
    {
        return safeGet(System.getProperty(key), defaultValue);
    }

    /**
     * @param key          参数 key
     * @param defaultValue 获取不到结果时的替代值
     * @return 系统参数
     */
    @Nullable
    public static String get(String key, Supplier<String> defaultValue)
    {
        Assert.notBlank(key, "the key to get system property is invalid");
        var property = System.getProperty(key);
        return StringUtil.isEmpty(property) ? defaultValue.get() : property;
    }

    /**
     * @param key     系统属性 key
     * @param replace 获取不到结果时的替代值
     * @return 布尔值形式的系统属性
     */
    public static int getDigit(String key, Supplier<Integer> replace)
    {
        var property = get(key);
        return property != null ? Integer.parseInt(property) : replace.get();
    }

    /**
     * @param key     系统属性 key
     * @param replace 获取不到结果时的替代值
     * @return 布尔值形式的系统属性
     */
    public static boolean getBoolean(String key, Supplier<Boolean> replace)
    {
        var property = get(key);
        return property == null ? replace.get() : Boolean.parseBoolean(property);
    }

    /**
     * 操作系统名称
     */
    public static final String OS_NAME = "os.name";

    public static String osName() { return get(OS_NAME); }

    /**
     * 操作系统版本
     */
    public static final String OS_VERSION = "os.version";

    public static String osVersion() { return get(OS_VERSION); }

    /**
     * 操作系统默认字符编码
     */
    public static final String OS_ENCODING = "sun.jnu.encoding";

    public static String osEncoding() { return get(OS_ENCODING); }

    /**
     * 运行当前 Java 程序的命令
     */
    public static final String OS_COMMAND = "sun.java.command";

    public static String osCommand() { return get(OS_COMMAND); }

    /**
     * 操作系统架构名称
     */
    public static final String OS_ARCH = "os.arch";

    public static String osArch() { return get(OS_ARCH); }

    /**
     * CPU 指令集
     */
    public static final String CPU_ISA_LIST = "sun.cpu.isalist";

    public static String cpuIsaList() { return get(CPU_ISA_LIST); }

    /**
     * CPU 数据位数，例如 32位 或 64位
     */
    public static final String CPU_DATA_MODEL = "sun.arch.data.model";

    public static String cpuDataModel() { return get(CPU_DATA_MODEL); }

    /**
     * 系统用户当前工作目录
     */
    public static final String USER_DIR = "user.dir";

    public static String userDir() { return get(USER_DIR); }

    /**
     * 系统用户名称
     */
    public static final String USER_NAME = "user.name";

    public static String userName() { return get(USER_NAME); }

    /**
     * 系统用户目录
     */
    public static final String USER_HOME = "user.home";

    public static String userHome() { return get(USER_HOME); }

    /**
     * 系统用户变量
     */
    public static final String USER_VARIANT = "user.variant";

    public static String userVariant() { return get(USER_VARIANT); }

    /**
     * 系统用户国家代号，例如 CN
     */
    public static final String USER_COUNTRY = "user.country";

    public static String userCountry() { return get(USER_COUNTRY); }

    /**
     * 系统用户语言代号，例如 zh
     */
    public static final String USER_LANGUAGE = "user.language";

    public static String userLanguage() { return get(USER_LANGUAGE); }

    /**
     * 系统用户脚本
     */
    public static final String USER_SCRIPT = "user.script";

    public static String userScript() { return get(USER_SCRIPT); }

    /**
     * JVM 名称
     */
    public static final String JVM_NAME = "java.vm.name";

    public static String jvmName() { return get(JVM_NAME); }

    /**
     * JVM 规范名称
     */
    public static final String JVM_SPEC_NAME = "java.vm.specification.name";

    public static String jvmSpecName() { return get(JVM_SPEC_NAME); }

    /**
     * JVM 发行商
     */
    public static final String JVM_VENDOR = "java.vm.vendor";

    public static String jvmVendor() { return get(JVM_VENDOR); }

    /**
     * JVM 发行商网址
     */
    public static final String JVM_VENDOR_URL = "java.vendor.url";

    public static String jvmVendorUrl() { return get(JVM_VENDOR_URL); }

    /**
     * JVM 版本，例如 21.0.1+12-LTS-29
     */
    public static final String JVM_VERSION = "java.vm.version";

    public static String jvmVersion() { return get(JVM_VERSION); }

    /**
     * JVM 规范版本
     */
    public static final String JVM_SPEC_VERSION = "java.vm.specification.version";

    public static String jvmSpecVersion() { return get(JVM_SPEC_VERSION); }

    /**
     * JVM 规范发行商
     */
    public static final String JVM_SPEC_VENDOR = "java.vm.specification.vendor";

    public static String jvmSpecVendor() { return get(JVM_SPEC_VENDOR); }

    /**
     * Java 运行名称
     */
    public static final String JAVA_RUN_NAME = "java.runtime.name";

    public static String javaRunName() { return get(JAVA_RUN_NAME); }

    /**
     * Java 目录
     */
    public static final String JAVA_HOME = "java.home";

    public static String javaHome() { return get(JAVA_HOME); }

    /**
     * Java 版本
     */
    public static final String JAVA_VERSION = "java.version";

    public static String javaVersion() { return get(JAVA_VERSION); }

    /**
     * Java 规范版本
     */
    public static final String JAVA_SPEC_VERSION = "java.specification.version";

    public static String javaSpecVersion() { return get(JAVA_SPEC_VERSION); }

    /**
     * 当前 Java 版本发布日期
     */
    public static final String JAVA_VERSION_DATE = "java.version.date";

    public static String javaVersionDate() { return get(JAVA_VERSION_DATE); }

    /**
     * Java 规范名称
     */
    public static final String JAVA_SPEC_NAME = "java.specification.name";

    public static String javaSpecName() { return get(JAVA_SPEC_NAME); }

    /**
     * Java 规范发行商
     */
    public static final String JAVA_SPEC_VENDOR = "java.specification.vendor";

    public static String javaSpecVendor() { return get(JAVA_SPEC_VENDOR); }

    /**
     * Java class 文件版本
     */
    public static final String JAVA_CLASS_VERSION = "java.class.version";

    public static String javaClassVersion() { return get(JAVA_CLASS_VERSION); }

    /**
     * Java 类路径
     */
    public static final String JAVA_CLASS_PATH = "java.class.path";

    public static String javaClassPath() { return get(JAVA_CLASS_PATH); }

    /**
     * Java 发行版名称
     */
    public static final String JAVA_LAUNCHER = "sun.java.launcher";

    public static String javaLauncher() { return get(JAVA_LAUNCHER); }

    /**
     * Java bin 路径
     */
    public static final String JAVA_BIN_PATH = "sun.boot.library.path";

    public static String javaBinPath() { return get(JAVA_BIN_PATH); }

    /**
     * Java 可以搜索的所有路径，包括 bin 和 操作系统 Path，以 ';' 分隔
     */
    public static final String JAVA_LIB_PATH = "java.library.path";

    public static String javaLibPath() { return get(JAVA_LIB_PATH); }

    /**
     * Java 临时 IO 目录（例如：win 系统的 "C:\Users\{user.dir}\AppData\Local\Temp\"）
     */
    public static final String JAVA_TMP_DIR = "java.io.tmpdir";

    public static String javaTmpDir() { return get(JAVA_TMP_DIR); }

    /**
     * Java bug 报告网址
     */
    public static final String JAVA_BUG_URL = "java.vendor.url.bug";

    public static String javaBugUrl() { return get(JAVA_BUG_URL); }

    /**
     * 程序主模块
     */
    public static final String MODULE_MAIN = "jdk.module.main";

    public static String moduleMain() { return get(MODULE_MAIN); }

    /**
     * 程序主模块主类
     */
    public static final String MODULE_MAIN_CLASS = "jdk.module.main.class";

    public static String moduleMainClass() { return get(MODULE_MAIN_CLASS); }

    /**
     * 当前系统的文件路径分隔符（例如：win 系统的 "\"）
     */
    public static final String FILE_SEPARATOR = "file.separator";

    public static String fileSeparator() { return get(FILE_SEPARATOR); }

    /**
     * 当前系统的行分隔符（例如：win 系统的 "\r\n"）
     */
    public static final String LINE_SEPARATOR = "line.separator";

    public static String lineSeparator() { return get(LINE_SEPARATOR); }

    /**
     * 当前系统的路径分隔符（例如：win 系统的 ";"）
     */
    public static final String PATH_SEPARATOR = "path.separator";

    public static String pathSeparator() { return get(PATH_SEPARATOR); }

    /**
     * {@link System#out} 使用的字符编码
     */
    public static final String STDOUT_ENCODING = "stdout.encoding";

    public static String stdoutEncoding() { return get(STDOUT_ENCODING); }

    /**
     * {@link System#err} 使用的字符编码
     */
    public static final String STDERR_ENCODING = "stderr.encoding";

    public static String stderrEncoding() { return get(STDERR_ENCODING); }

    /**
     * 系统默认编码
     */
    public static final String NATIVE_ENCODING = "native.encoding";

    public static String nativeEncoding() { return get(NATIVE_ENCODING); }
}