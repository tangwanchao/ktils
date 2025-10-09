package me.twc.ktils.core.utils

import com.orhanobut.logger.Logger

/**
 * @author 唐万超
 * @date 2025/10/09
 */
fun <R> safeExecute(
    quiet: Boolean = true,
    block: () -> R
): R? {
    try {
        return block()
    } catch (ex: Exception) {
        if (!quiet) {
            Logger.e(ex, "safeExecute error")
        }
    }
    return null
}