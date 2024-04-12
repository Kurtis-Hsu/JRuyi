/**
 * <h2>Jade-Ruyi 核心模块</h2>
 *
 * @Date 2023-12-23 9:43
 */
module jruyi.core
{
    requires transitive jakarta.annotation;

    exports jruyi.util;
    exports jruyi.util.reflect;
    exports jruyi.core.mutable;
}