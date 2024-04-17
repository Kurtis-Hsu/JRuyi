package jruyi.kotlin.lib

import java.util.*

var SYSTEM_PROPS: Properties
    get() = System.getProperties()
    set(value) = System.setProperties(value)

// 通用
// Date: 2024-04-16 20:04

/**
 * 该对象是否为 null
 */
val Any?.isNull get() = this == null

/**
 * 该对象是否非 null
 */
val Any?.notNull get() = this != null

/**
 * 该对象是否无效（这里与[isNull]相同），可通过重载该属性附加额外效果
 */
val Any?.nil get() = isNull

fun main()
{
    SYSTEM_PROPS.forEach { println("${it.key} --- ${it.value}") }
}