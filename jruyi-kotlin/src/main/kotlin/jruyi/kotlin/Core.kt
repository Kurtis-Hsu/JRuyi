package jruyi.kotlin

// Date: 2024-04-12 15:53

/**
 * 该对象是否为 null
 */
val Any?.isNull get() = this == null

/**
 * 该对象是否非 null
 */
val Any?.notNull get() = this != null

/**
 * 判断该字符序列是否为 null 或长度是否为 0
 */
val CharSequence?.isNil get() = this?.length == 0

/**
 * 判断字符序列是否包含有效文本
 */
val CharSequence.hasText: Boolean
    get()
    {
        forEach { if (!it.isWhitespace()) return true }
        return false
    }

/**
 * 判断字符序列是否为 null 或为无效文本
 */
val CharSequence?.isBlank get() = this?.hasText == true

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