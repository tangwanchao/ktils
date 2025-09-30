package me.twc.ktils.core

import android.app.Application
import android.content.Context
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.CsvFormatStrategy
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.DiskLogStrategy
import com.orhanobut.logger.DiskLogStrategy.WriteHandler
import com.orhanobut.logger.Logger
import me.twc.ktils.core.ext.WriteFileHandler
import me.weishu.reflection.Reflection
import java.io.File

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

    /**
     * @param isDebug 是否是 debug 环境
     * @param initLogger 是否初始化日志,debug 环境日志写到 logcat, 其他环境日志写到文件
     */
    fun init(
        app: Application,
        isDebug: Boolean,
        initLogger: Boolean = true,
    ) {
        this.app = app
        if (initLogger) {
            initLogger(isDebug)
        }
    }

    private fun initLogger(isDebug: Boolean) {
        val adapter = if (!isDebug) {
            val formatStrategy = CsvFormatStrategy.newBuilder()
                .logStrategy(DiskLogStrategy(WriteFileHandler()))
                .build()
            DiskLogAdapter(formatStrategy)
        } else AndroidLogAdapter()
        Logger.addLogAdapter(adapter)
    }
}