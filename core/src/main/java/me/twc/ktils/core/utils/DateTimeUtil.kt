package me.twc.ktils.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author 唐万超
 * @date 2025/09/29
 */
object DateTimeUtil {

    private val dateTimeFormat by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) }

    /**
     * 毫秒值转时间字符串,最终格式为 "yyyy-MM-dd HH:mm:ss"
     *
     * @param millis 毫秒值
     */
    fun millis2String(millis: Long = System.currentTimeMillis()): String = dateTimeFormat.format(millis)

    /**
     * 时间字符串转毫秒值
     *
     * @param dateTimeString 格式必须为 yyyy-MM-dd HH:mm:ss
     */
    fun string2Millis(dateTimeString: String): Long = try {
        dateTimeFormat.parse(dateTimeString)!!.time
    } catch (ex: Exception) {
        0L
    }

    fun format(millis: Long, pattern: String, locale: Locale = Locale.getDefault()): String = try {
        SimpleDateFormat(pattern, locale).format(millis)
    } catch (ex: Exception) {
        ""
    }

    fun parse(text: String, pattern: String, locale: Locale = Locale.getDefault()): Long = try {
        SimpleDateFormat(pattern, locale).parse(text)!!.time
    } catch (ex: Exception) {
        0L
    }
}