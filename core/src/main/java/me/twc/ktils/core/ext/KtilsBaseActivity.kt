package me.twc.ktils.core.ext

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity

/**
 * @author 唐万超
 * @date 2025/09/24
 */
abstract class KtilsBaseActivity : AppCompatActivity() {
    abstract val designWidth: Int

    override fun getResources(): Resources {
        var resources = super.getResources()
        return AdapterScreenUtil.adaptWidth(resources, designWidth)
    }
}