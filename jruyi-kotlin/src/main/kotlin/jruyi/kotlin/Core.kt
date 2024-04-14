package jruyi.kotlin

// Date: 2024-04-12 15:53

/**
 * 该对象是否为 null
 */
val <T> T?.isNull get() = this == null

/**
 * 该对象是否非 null
 */
val <T> T?.notNull get() = this != null