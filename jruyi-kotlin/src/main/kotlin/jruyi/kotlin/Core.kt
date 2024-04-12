package jruyi.kotlin

// Date: 2024-04-12 15:53

/**
 * 该对象是否为 null
 */
val <T> T?.isnull get() = this == null

/**
 * 该对象是否非 null
 */
val <T> T?.notnull get() = this != null