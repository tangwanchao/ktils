package me.twc.ktils

import android.app.Application
import me.twc.ktils.core.Ktils

/**
 * @author 唐万超
 * @date 2025/09/24
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Ktils.init(this,true)
    }
}