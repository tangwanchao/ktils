package me.twc.ktils.core

import me.twc.ktils.core.utils.DateTimeUtil
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author 唐万超
 * @date 2025/09/29
 */
class DateTimeTest {

    @Test
    fun millis2StringTest() {
        val millis = 1759139286000
        val dateTimeString = DateTimeUtil.millis2String(millis)
        assertEquals("2025-09-29 17:48:06", dateTimeString)

        val newMillis = DateTimeUtil.string2Millis(dateTimeString)
        assertEquals(millis,newMillis)
    }
}