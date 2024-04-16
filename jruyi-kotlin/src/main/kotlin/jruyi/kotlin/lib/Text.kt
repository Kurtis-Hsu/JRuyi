package jruyi.kotlin.lib

// 文本相关
// Date: 2024-04-16 20:06

/**
 * 判断该字符序列是否为 null 或长度是否为 0
 */
val CharSequence?.nil get() = this?.length == 0

/**
 * 判断字符序列是否为 null 或为无效文本
 */
val CharSequence?.blank: Boolean
    get()
    {
        if (this == null) return true
        forEach { if (!it.isWhitespace()) return true }
        return false
    }

private typealias DefaultSystemProperty = () -> String

/**
 * 获取系统属性
 */
fun CharSequence.toSystemProperty(default: DefaultSystemProperty? = null): String? =
    System.getProperty(this.toString()) ?: default?.invoke()

/**
 * 设置系统属性
 */
fun CharSequence.toSystemProperty(value: CharSequence?, default: DefaultSystemProperty? = null): String? =
    System.setProperty(this.toString(), (value?.toString() ?: default?.invoke())!!)