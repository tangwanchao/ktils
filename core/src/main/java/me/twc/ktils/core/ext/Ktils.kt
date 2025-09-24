package me.twc.ktils.core.ext

import android.app.Application
import android.content.Context
import me.weishu.reflection.Reflection

/**
 * @author 唐万超
 * @date 2025/09/24
 */
object Ktils {
    lateinit var app: Application
        private set

    val resources get() = app.resources!!
    val displayMetrics get() = resources.displayMetrics!!

    fun attachBaseContext(base: Context) {
        Reflection.unseal(base)
    }

    fun init(app: Application) {
        this.app = app
    }
}