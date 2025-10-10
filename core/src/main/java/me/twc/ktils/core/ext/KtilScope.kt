package me.twc.ktils.core.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * @author 唐万超
 * @date 2025/10/10
 */

val ktilScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())