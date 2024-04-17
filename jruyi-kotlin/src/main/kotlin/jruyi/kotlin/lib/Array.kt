package jruyi.kotlin.lib

// 数组相关
// Date: 2024-04-17 10:11

/**
 * 是否为 null 或元素数为 0
 */
val Array<*>?.nil get() = this.isNullOrEmpty()