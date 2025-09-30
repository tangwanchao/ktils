package me.twc.ktils.core.utils

import android.os.StatFs

/**
 * @author 唐万超
 * @date 2025/09/29
 */
object FileUtil {
    /**
     * 获取指定文件路径下所有文件大小和
     *
     * @param path 文件或文件夹路径
     * @return 路径下空间大小, 单位:b
     */
    fun getFileSize(path: String): Long {
        try {
            val statFs = StatFs(path)
            return statFs.blockCountLong * statFs.blockSizeLong
        } catch (th: Exception) {
            th.printStackTrace()
        }
        return 0
    }
}