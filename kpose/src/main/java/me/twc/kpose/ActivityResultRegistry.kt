package me.twc.kpose

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.CompletableDeferred

/**
 * @author 唐万超
 * @date 2025/10/10
 */
class LauncherWithAwait<I, O>() {
    internal var launcher: ActivityResultLauncher<I>? = null

    @Volatile
    private var deferred: CompletableDeferred<O>? = null

    suspend fun awaitLaunch(input: I): O {
        val d = CompletableDeferred<O>()
        deferred = d
        launcher?.launch(input) ?: throw IllegalStateException("")
        return d.await()
    }

    @MainThread
    fun resume(result: O) {
        deferred?.complete(result)
        deferred = null
    }
}


/**
 * rememberLauncherForActivityResult 便捷版本
 * 注意:此版本方法会导致当前 Activity 被系统回收后无法接收到结果,谨慎使用
 */
@Composable
fun <I, O> rememberLauncherForActivityResultAwait(
    contract: ActivityResultContract<I, O>
): LauncherWithAwait<I, O> {
    val launcherWithAwait = remember { LauncherWithAwait<I, O>() }

    launcherWithAwait.launcher = rememberLauncherForActivityResult(contract) { result ->
        launcherWithAwait.resume(result)
    }
    return launcherWithAwait
}