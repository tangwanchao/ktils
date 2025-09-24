package me.twc.ktils.core.ext

import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * @author 唐万超
 * @date 2025/09/23
 */

const val INCHES_PER_PT = (1.0f / 72)

/**
 * pt 转 px
 */
fun Float.pt(metrics: DisplayMetrics = Ktils.displayMetrics): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, this, metrics)
}

/**
 * dp 转 px
 */
fun Float.dp(metrics: DisplayMetrics = Ktils.displayMetrics): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)
}

/**
 * sp 转 px
 */
fun Float.sp(metrics: DisplayMetrics = Ktils.displayMetrics): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, metrics)
}

/**
 * px 转 pt
 */
fun Float.toPt(metrics: DisplayMetrics = Ktils.displayMetrics): Float {
    if (metrics.xdpi == 0f) {
        return 0f
    }
    return (this / metrics.xdpi / INCHES_PER_PT)
}

/**
 * px 转 dp
 */
fun Float.toDp(metrics: DisplayMetrics = Ktils.displayMetrics): Float {
    if (metrics.density == 0f) {
        return 0f
    }
    return this / metrics.density
}

/**
 * 向上取整
 */
fun Float.ceilInt(): Int {
    return kotlin.math.ceil(this).toInt()
}

/**
 * 向下取整
 */
fun Float.floorInt(): Int {
    return kotlin.math.floor(this).toInt()
}


