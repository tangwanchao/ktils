package me.twc.ktils

import android.app.Application
import me.twc.ktils.core.ext.Ktils

/**
 * @author 唐万超
 * @date 2025/09/24
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Ktils.init(this)
    }
}