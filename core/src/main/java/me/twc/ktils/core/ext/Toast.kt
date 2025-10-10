package me.twc.ktils.core.ext

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import me.twc.ktils.core.Ktils
import me.twc.ktils.core.R

class ToastConfig {
    // px
    var paddingLeft: Int = 12f.dp().ceilInt()

    // px
    var paddingTop: Int = 8f.dp().ceilInt()

    // px
    var paddingRight: Int = paddingLeft

    // px
    var paddingBottom: Int = paddingTop

    @DrawableRes
    var backgroundResId: Int = R.drawable.ktils_bg_toast
    var textColor: Int = Color.WHITE

    // px
    var textSize: Float = 15f.sp()

    var isLongToast: Boolean = false

    // 是否展示空白字符消息
    var showBlankMessage: Boolean = false
}

fun showShortToast(
    message: String,
    context: Context = Ktils.app,
    builder: (ToastConfig.() -> Unit)? = null
) {
    showToast(message, context, builder)
}

fun showLongToast(
    message: String,
    context: Context = Ktils.app,
    builder: (ToastConfig.() -> Unit)? = null
) {
    showToast(message, context) {
        isLongToast = true
        builder?.invoke(this)
    }
}

/**
 * @author 唐万超
 * @date 2025/09/24
 */
fun showToast(
    message: String,
    context: Context = Ktils.app,
    builder: (ToastConfig.() -> Unit)? = null
) {
    val config = ToastConfig()
    builder?.invoke(config)
    if (!config.showBlankMessage && message.isBlank()) {
        return
    }
    Toast.makeText(context, message, Toast.LENGTH_SHORT).apply {
        view = TextView(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundResource(config.backgroundResId)
            setPadding(config.paddingLeft, config.paddingTop, config.paddingRight, config.paddingBottom)
            setTextColor(config.textColor)
            text = message
            setTextSize(TypedValue.COMPLEX_UNIT_PX, config.textSize)
        }
        setGravity(Gravity.CENTER, 0, 0)
    }.show()
}