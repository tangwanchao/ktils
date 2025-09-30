package me.twc.ktils.core.ext

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log
import me.twc.ktils.core.Ktils
import me.twc.ktils.core.utils.DateTimeUtil
import java.io.File
import java.io.FileWriter

/**
 * @author 唐万超
 * @date 2025/09/29
 */
class WriteFileHandler internal constructor(
    val loggerFolder: File = File(Ktils.app.filesDir, "logger"),
    val fileName: String = "log",
    val maxFileSize: Int = 4096,
    looper: Looper = HandlerThread("LoggerWriteFileHandler").looper
) : Handler(looper) {

    private var currentLogFile: File? = null
    private var currentDateString = getCurrentDateString()

    override fun handleMessage(msg: Message) {
        // 必要的异常捕获
        try {
            val content = msg.obj as? String ?: return
            val logFile = getLogFile() ?: return
            FileWriter(logFile, true).use {
                it.append(content)
                it.flush()
            }
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }

    private fun getLogFile(): File? {
        // 检查日期是否变了
        val newDateString = getCurrentDateString()
        if (newDateString != currentDateString) {
            currentDateString = newDateString
            currentLogFile = null
        }

        // 检测缓存
        val cache = currentLogFile
        if (cache != null && cache.exists() && cache.length() < maxFileSize) {
            return cache
        }

        // 保证文件夹存在
        val folder = File(loggerFolder, currentDateString)
        if (!folder.exists() && !folder.mkdirs()) {
            Log.d("Ktils", "无法创建日志文件夹")
            return null
        }

        // 创建新的日志文件
        currentLogFile = File(folder, "${fileName}_${currentDateString}_${System.currentTimeMillis()}.csv")
        return currentLogFile
    }

    private fun getCurrentDateString() = DateTimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd")
}
