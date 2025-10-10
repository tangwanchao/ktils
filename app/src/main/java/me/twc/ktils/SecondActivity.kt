package me.twc.ktils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author 唐万超
 * @date 2025/10/10
 */
class SecondActivity : ComponentActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(2000)
            setResult(RESULT_OK, Intent().apply { putExtra("key", "ok") })
            finish()
        }
    }

    class Contract : ActivityResultContract<Unit, String>() {
        override fun createIntent(context: Context, input: Unit): Intent {
            return Intent(context, SecondActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String {
            return intent?.getStringExtra("key") ?: "null"
        }
    }
}