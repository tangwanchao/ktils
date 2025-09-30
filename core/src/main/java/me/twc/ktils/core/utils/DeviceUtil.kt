package me.twc.ktils.core.utils

import android.app.ActivityManager
import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.storage.StorageManager
import androidx.annotation.WorkerThread
import me.twc.ktils.core.Ktils
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.max

/**
 * @author 唐万超
 * @date 2025/09/29
 */
object DeviceUtil {

    /**
     * 获取企业宣传磁盘大小
     * Android 8.0 以上用的官方 api,8.0 以下自己统计的
     */
    private fun getRom(): Long {
        var result: Long = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val ssm = Ktils.app.getSystemService(StorageStatsManager::class.java)
            result = ssm.getTotalBytes(StorageManager.UUID_DEFAULT)
        } else {
            val dataDirSize: Long = FileUtil.getFileSize(Environment.getDataDirectory().path)
            val rootDirSize: Long = FileUtil.getFileSize(Environment.getRootDirectory().path)
            var result = dataDirSize + rootDirSize
            try {
                if (!Environment.isExternalStorageRemovable()) {
                    val externalSize = Environment.getExternalStorageDirectory().getTotalSpace()
                    result = max(result, externalSize)
                }
            } catch (_: Throwable) {
                // ignore
            }
        }
        return result
    }

    /**
     * 获取企业宣传磁盘大小,G 为单位
     * Android 8.0 以上用的官方 api,8.0 以下自己统计的
     */
    fun getRomString(clip: Boolean = false): String {
        val bigDecimal = BigDecimal(getRom() / 1000.0 * 1000.0 * 1000.0)
            .setScale(2, RoundingMode.HALF_DOWN)
        if (!clip) {
            return bigDecimal.toPlainString()
        }

        val size = bigDecimal.toDouble()
        if (size > 0 && size <= 4) {
            return "4G"
        } else if (size > 4 && size <= 8) {
            return "8G"
        } else if (size > 8 && size <= 16) {
            return "16G"
        } else if (size > 16 && size <= 32) {
            return "32G"
        } else if (size > 32 && size <= 64) {
            return "64G"
        } else if (size > 64 && size <= 128) {
            return "128G"
        } else if (size > 128 && size <= 256) {
            return "256G"
        } else if (size > 256 && size <= 512) {
            return "512G"
        } else if (size > 512 && size <= 1024) {
            return "1T"
        } else if (size > 1024 && size <= 2048) {
            return "2T"
        } else {
            return size.toInt().toString() + "G"
        }
    }

    /**
     * @param radix 换算进制
     *
     * @return Long 表示内存大小,B 为单位
     */
    @WorkerThread
    fun getRam(radix: Int = 1000): Long {
        // 首先读取 proc
        try {
            // MemTotal:       11483068 kB
            val file = File("/proc/meminfo")
            val line = file.readLines().firstOrNull { it.contains("MemTotal:") }?.trim()
            if (line != null) {
                var value = line.replace("""\D+""".toRegex(), "").toLong()
                return value * radix
            }
        } catch (_: Exception) {
            // ignore
        }

        // 用系统 api 读取
        val am = Ktils.app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        am.getMemoryInfo(memoryInfo)
        return memoryInfo.totalMem
    }
}