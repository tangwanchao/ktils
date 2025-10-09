package me.twc.ktils.core.utils

import android.os.Build

/**
 * @author 唐万超
 * @date 2025/10/09
 */
object RomUtil {
    private val ROM_HUAWEI: Array<String> = arrayOf<String>("huawei")
    private val ROM_VIVO: Array<String> = arrayOf<String>("vivo")
    private val ROM_XIAOMI: Array<String> = arrayOf<String>("xiaomi")
    private val ROM_OPPO: Array<String> = arrayOf<String>("oppo")
    private val ROM_LEECO: Array<String> = arrayOf<String>("leeco", "letv")
    private val ROM_360: Array<String> = arrayOf<String>("360", "qiku")
    private val ROM_ZTE: Array<String> = arrayOf<String>("zte")
    private val ROM_ONEPLUS: Array<String> = arrayOf<String>("oneplus")
    private val ROM_NUBIA: Array<String> = arrayOf<String>("nubia")
    private val ROM_COOLPAD: Array<String> = arrayOf<String>("coolpad", "yulong")
    private val ROM_LG: Array<String> = arrayOf<String>("lg", "lge")
    private val ROM_GOOGLE: Array<String> = arrayOf<String>("google")
    private val ROM_SAMSUNG: Array<String> = arrayOf<String>("samsung")
    private val ROM_MEIZU: Array<String> = arrayOf<String>("meizu")
    private val ROM_LENOVO: Array<String> = arrayOf<String>("lenovo")
    private val ROM_SMARTISAN: Array<String> = arrayOf<String>("smartisan", "deltainno")
    private val ROM_HTC: Array<String> = arrayOf<String>("htc")
    private val ROM_SONY: Array<String> = arrayOf<String>("sony")
    private val ROM_GIONEE: Array<String> = arrayOf<String>("gionee", "amigo")
    private val ROM_MOTOROLA: Array<String> = arrayOf<String>("motorola")

    private const val VERSION_PROPERTY_HUAWEI: String = "ro.build.version.emui"
    private const val VERSION_PROPERTY_VIVO: String = "ro.vivo.os.build.display.id"
    private const val VERSION_PROPERTY_XIAOMI: String = "ro.build.version.incremental"
    private const val VERSION_PROPERTY_OPPO: String = "ro.build.version.opporom"
    private const val VERSION_PROPERTY_LEECO: String = "ro.letv.release.version"
    private const val VERSION_PROPERTY_360: String = "ro.build.uiversion"
    private const val VERSION_PROPERTY_ZTE: String = "ro.build.MiFavor_version"
    private const val VERSION_PROPERTY_ONEPLUS: String = "ro.rom.version"
    private const val VERSION_PROPERTY_NUBIA: String = "ro.build.rom.id"
    private const val UNKNOWN: String = "unknown"

    private var bean: RomInfo? = null

    fun isHuawei(): Boolean {
        return ROM_HUAWEI[0] == getRomInfo().name
    }

    fun isVivo(): Boolean {
        return ROM_VIVO[0] == getRomInfo().name
    }

    fun isXiaomi(): Boolean {
        return ROM_XIAOMI[0] == getRomInfo().name
    }

    fun isOppo(): Boolean {
        return ROM_OPPO[0] == getRomInfo().name
    }

    fun isLeeco(): Boolean {
        return ROM_LEECO[0] == getRomInfo().name
    }

    fun is360(): Boolean {
        return ROM_360[0] == getRomInfo().name
    }

    fun isZte(): Boolean {
        return ROM_ZTE[0] == getRomInfo().name
    }

    fun isOneplus(): Boolean {
        return ROM_ONEPLUS[0] == getRomInfo().name
    }

    fun isNubia(): Boolean {
        return ROM_NUBIA[0] == getRomInfo().name
    }

    fun isCoolpad(): Boolean {
        return ROM_COOLPAD[0] == getRomInfo().name
    }

    fun isLg(): Boolean {
        return ROM_LG[0] == getRomInfo().name
    }

    fun isGoogle(): Boolean {
        return ROM_GOOGLE[0] == getRomInfo().name
    }

    fun isSamsung(): Boolean {
        return ROM_SAMSUNG[0] == getRomInfo().name
    }

    fun isMeizu(): Boolean {
        return ROM_MEIZU[0] == getRomInfo().name
    }

    fun isLenovo(): Boolean {
        return ROM_LENOVO[0] == getRomInfo().name
    }

    fun isSmartisan(): Boolean {
        return ROM_SMARTISAN[0] == getRomInfo().name
    }

    fun isHtc(): Boolean {
        return ROM_HTC[0] == getRomInfo().name
    }

    fun isSony(): Boolean {
        return ROM_SONY[0] == getRomInfo().name
    }

    fun isGionee(): Boolean {
        return ROM_GIONEE[0] == getRomInfo().name
    }

    fun isMotorola(): Boolean {
        return ROM_MOTOROLA[0] == getRomInfo().name
    }

    private fun RomInfo.cached(): RomInfo {
        bean = this
        return this
    }

    fun getRomInfo(): RomInfo {
        val cache = bean
        if (cache != null) return cache
        val brand = getBrand()
        val manufacturer = getManufacturer()
        if (isRightRom(brand, manufacturer, *ROM_HUAWEI)) {
            val version = getRomVersion(VERSION_PROPERTY_HUAWEI)
            val temp: Array<String?> = version.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val romVersion = if (temp.size > 1) temp[1]!! else version
            return RomInfo(ROM_HUAWEI[0], romVersion).cached()
        }
        if (isRightRom(brand, manufacturer, *ROM_VIVO)) {
            return RomInfo(ROM_VIVO[0], getRomVersion(VERSION_PROPERTY_VIVO)).cached()
        }
        if (isRightRom(brand, manufacturer, *ROM_XIAOMI)) {
            return RomInfo(ROM_XIAOMI[0], getRomVersion(VERSION_PROPERTY_XIAOMI)).cached()
        }
        if (isRightRom(brand, manufacturer, *ROM_OPPO)) {
            return RomInfo(ROM_OPPO[0], getRomVersion(VERSION_PROPERTY_OPPO)).cached()
        }
        if (isRightRom(brand, manufacturer, *ROM_LEECO)) {
            return RomInfo(ROM_LEECO[0], getRomVersion(VERSION_PROPERTY_LEECO)).cached()
        }

        if (isRightRom(brand, manufacturer, *ROM_360)) {
            return RomInfo(ROM_360[0], getRomVersion(VERSION_PROPERTY_360)).cached()
        }
        if (isRightRom(brand, manufacturer, *ROM_ZTE)) {
            return RomInfo(ROM_ZTE[0], getRomVersion(VERSION_PROPERTY_ZTE)).cached()
        }
        if (isRightRom(brand, manufacturer, *ROM_ONEPLUS)) {
            return RomInfo(ROM_ONEPLUS[0], getRomVersion(VERSION_PROPERTY_ONEPLUS)).cached()
        }
        if (isRightRom(brand, manufacturer, *ROM_NUBIA)) {
            return RomInfo(ROM_NUBIA[0], getRomVersion(VERSION_PROPERTY_NUBIA)).cached()
        }

        var romInfo: RomInfo
        if (isRightRom(brand, manufacturer, *ROM_COOLPAD)) {
            romInfo = RomInfo(ROM_COOLPAD[0])
        } else if (isRightRom(brand, manufacturer, *ROM_LG)) {
            romInfo = RomInfo(ROM_LG[0])
        } else if (isRightRom(brand, manufacturer, *ROM_GOOGLE)) {
            romInfo = RomInfo(ROM_GOOGLE[0])
        } else if (isRightRom(brand, manufacturer, *ROM_SAMSUNG)) {
            romInfo = RomInfo(ROM_SAMSUNG[0])
        } else if (isRightRom(brand, manufacturer, *ROM_MEIZU)) {
            romInfo = RomInfo(ROM_MEIZU[0])
        } else if (isRightRom(brand, manufacturer, *ROM_LENOVO)) {
            romInfo = RomInfo(ROM_LENOVO[0])
        } else if (isRightRom(brand, manufacturer, *ROM_SMARTISAN)) {
            romInfo = RomInfo(ROM_SMARTISAN[0])
        } else if (isRightRom(brand, manufacturer, *ROM_HTC)) {
            romInfo = RomInfo(ROM_HTC[0])
        } else if (isRightRom(brand, manufacturer, *ROM_SONY)) {
            romInfo = RomInfo(ROM_SONY[0])
        } else if (isRightRom(brand, manufacturer, *ROM_GIONEE)) {
            romInfo = RomInfo(ROM_GIONEE[0])
        } else if (isRightRom(brand, manufacturer, *ROM_MOTOROLA)) {
            romInfo = RomInfo(ROM_MOTOROLA[0])
        } else {
            romInfo = RomInfo(brand)
        }
        romInfo.version = getRomVersion("")
        return romInfo.cached()
    }

    private fun isRightRom(brand: String, manufacturer: String, vararg names: String): Boolean {
        return names.any { brand.contains(it) || manufacturer.contains(it) }
    }

    private fun getManufacturer(): String {
        return safeExecute { Build.MANUFACTURER.lowercase() } ?: UNKNOWN
    }

    private fun getBrand(): String {
        return safeExecute { Build.BRAND.lowercase() } ?: UNKNOWN
    }

    private fun getDisplay(): String {
        return safeExecute { Build.DISPLAY.lowercase() } ?: UNKNOWN
    }

    private fun getRomVersion(propertyName: String): String {
        var ret = SystemPropertyUtil.getSystemProperty(propertyName)
        if (ret.isBlank() || ret == UNKNOWN) {
            ret = getDisplay()
        }
        return ret
    }

    data class RomInfo(
        val name: String,
        var version: String = ""
    )
}