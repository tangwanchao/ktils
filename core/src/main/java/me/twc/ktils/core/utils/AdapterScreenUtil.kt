package me.twc.ktils.core.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import me.twc.ktils.core.Ktils
import java.lang.reflect.Field

/**
 * @author 唐万超
 * @date 2025/09/24
 */
object AdapterScreenUtil {

    private val sMetricsFields: MutableList<Field> by lazy { mutableListOf<Field>() }

    fun computeXdpi(resources: Resources, designWidth: Int) = (resources.displayMetrics.widthPixels * 72f) / designWidth

    /**
     * 按照宽度适配
     */
    fun adaptWidth(resources: Resources, designWidth: Int): Resources {
        val newXdpi = computeXdpi(resources, designWidth)
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }

    /**
     * 关闭适配,关闭适配后 pt 单位变 dp
     */
    fun closeAdapt(resources: Resources): Resources {
        val newXdpi = Resources.getSystem().displayMetrics.density * 72f
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }

    private fun applyDisplayMetrics(resources: Resources, newXdpi: Float) {
        resources.displayMetrics.xdpi = newXdpi
        Ktils.displayMetrics.xdpi = newXdpi
        applyOtherDisplayMetrics(resources, newXdpi)
    }

    private fun applyOtherDisplayMetrics(resources: Resources, newXdpi: Float) {
        if (sMetricsFields.isEmpty()) {
            var resCls: Class<*>? = resources.javaClass
            var declaredFields = resCls?.declaredFields
            while (declaredFields != null && declaredFields.size > 0) {
                for (field in declaredFields) {
                    if (field.type.isAssignableFrom(DisplayMetrics::class.java)) {
                        field.isAccessible = true
                        val tmpDm: DisplayMetrics? = getMetricsFromField(resources, field)
                        if (tmpDm != null) {
                            sMetricsFields.add(field)
                            tmpDm.xdpi = newXdpi
                        }
                    }
                }
                resCls = resCls?.getSuperclass()
                if (resCls != null) {
                    declaredFields = resCls.declaredFields
                } else {
                    break
                }
            }
        } else {
            applyMetricsFields(resources, newXdpi)
        }
    }

    private fun applyMetricsFields(resources: Resources, newXdpi: Float) {
        for (metricsField in sMetricsFields) {
            try {
                val dm = metricsField.get(resources) as DisplayMetrics?
                dm?.xdpi = newXdpi
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getMetricsFromField(resources: Resources, field: Field): DisplayMetrics? = try {
        field.get(resources) as DisplayMetrics?
    } catch (_: Exception) {
        null
    }
}