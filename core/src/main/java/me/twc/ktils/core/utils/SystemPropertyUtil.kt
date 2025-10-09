package me.twc.ktils.core.utils

import android.annotation.SuppressLint
import android.os.Environment
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

/**
 * @author 唐万超
 * @date 2025/10/09
 */
object SystemPropertyUtil {

    /**
     * 系统属性的名字,例如:ro.build.uiversion
     */
    fun getSystemProperty(propName: String): String {
        if(propName.isBlank()) return ""
        var prop: String = getSystemPropertyByShell(propName)
        if (prop.isNotBlank()) return prop
        prop = getSystemPropertyByStream(propName)
        if (prop.isNotBlank()) return prop
        return getSystemPropertyByReflect(propName)
    }

    private fun getSystemPropertyByShell(propName: String): String {
        return safeExecute {
            val process = Runtime.getRuntime().exec("getprop $propName")
            val reader = BufferedReader(InputStreamReader(process.inputStream), 1024)
            return@safeExecute reader.use { it.readLine() }
        } ?: return ""
    }

    private fun getSystemPropertyByStream(key: String?): String {
        return safeExecute {
            val prop = Properties()
            val input = FileInputStream(File(Environment.getRootDirectory(), "build.prop"))
            prop.load(input)
            return@safeExecute prop.getProperty(key, "")
        } ?: return ""
    }

    @SuppressLint("PrivateApi")
    private fun getSystemPropertyByReflect(key: String?): String {
        return safeExecute {
            val clz = Class.forName("android.os.SystemProperties")
            val getMethod = clz.getMethod("get", String::class.java, String::class.java)
            return@safeExecute getMethod.invoke(clz, key, "") as String
        } ?: return ""
    }
}