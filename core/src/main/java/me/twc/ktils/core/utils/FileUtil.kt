package me.twc.ktils.core.utils

import android.os.StatFs
import java.text.DecimalFormat

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

    /**
     * 单位换算
     *
     * @param size  单位为B
     * @param radix 进制,1000 or 1024
     *
     * @return 转换后的结果
     */
    fun formatFileSize(
        size: Long,
        pattern: String = "0.0",
        radix: Int = 1000
    ): String {
        val format = DecimalFormat(pattern)
        val fileSizeString: String
        @Suppress("LiftReturnOrAssignment")
        if (size < radix && size > 0) {
            fileSizeString = format.format(size) + "B"
        } else if (size < radix * radix) {
            fileSizeString = format.format(size / radix.toDouble()) + "K"
        } else if (size < radix * radix * radix) {
            fileSizeString = format.format(size / (radix * radix).toDouble()) + "M"
        } else {
            fileSizeString = format.format(size / (radix * radix * radix).toDouble()) + "G"
        }
        return fileSizeString
    }
}