package me.twc.ktils.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.os.Vibrator
import me.twc.ktils.core.Ktils

/**
 * @author 唐万超
 * @date 2025/10/09
 */
object SystemFeatureUtil {

    fun hasSystemFeature(featureName: String): Boolean {
        return Ktils.app.packageManager.hasSystemFeature(featureName)
    }

    /**
     * @return 是否支持通话功能
     */
    fun isSupportTelephony(): Boolean = hasSystemFeature(PackageManager.FEATURE_TELEPHONY)

    /**
     * @return 是否支持震动
     */
    fun isSupportVibrator(): Boolean {
        val vibrator = Ktils.app.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        return vibrator.hasVibrator()
    }

    /**
     * @return 是否支持指纹识别
     */
    fun isSupportFingerPrint(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false
        }
        // 肯定不支持指纹识别机器型号列表
        val unsupportArray = arrayOf("pahm00")
        if (RomUtil.isOppo() && unsupportArray.contains(Build.MODEL.lowercase())) {
            return false
        }
        return Ktils.app.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    /**
     * @return 是否支持多点触控
     */
    fun isSupportMultiTouch(): Boolean = hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)

    /**
     * @return 是否支持闪光灯
     */
    fun isSupportFlash(): Boolean = hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

    /**
     * @return 是否支持陀螺仪
     */
    fun isSupportGyroscopeSensor(): Boolean = isSupportSensor(Sensor.TYPE_GYROSCOPE)

    /**
     * @return 是否支持光传感器
     */
    fun isSupportLightSensor(): Boolean = isSupportSensor(Sensor.TYPE_LIGHT)

    /**
     * @return 是否支持加速度传感器
     */
    fun isSupportAccelerometerSensor(): Boolean = isSupportSensor(Sensor.TYPE_ACCELEROMETER)

    /**
     * @return 是否支持磁场传感器
     */
    fun isSupportMagneticFieldSensor(): Boolean = isSupportSensor(Sensor.TYPE_MAGNETIC_FIELD)

    /**
     * @return 是否支持压力传感器
     */
    fun isSupportPressureSensor(): Boolean = isSupportSensor(Sensor.TYPE_PRESSURE)

    /**
     * @return 是否支持距离传感器
     */
    fun isSupportProximitySensor(): Boolean = isSupportSensor(Sensor.TYPE_PROXIMITY)

    /**
     * @return 是否支持温度传感器
     */
    fun isSupportTemperatureSensor(): Boolean = isSupportSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

    /**
     * @return 是否支持重力传感器
     */
    fun isSupportGravitySensor(): Boolean = isSupportSensor(Sensor.TYPE_GRAVITY)

    /**
     * @return 是否支持线性加速度传感器
     */
    fun isSupportLinearAccelerationSensor(): Boolean = isSupportSensor(Sensor.TYPE_LINEAR_ACCELERATION)

    /**
     * @return 是否支持旋转矢量传感器
     */
    fun isSupportRotationVectorSensor(): Boolean = isSupportSensor(Sensor.TYPE_ROTATION_VECTOR)

    /**
     * @return 是否支持湿度传感器
     */
    fun isSupportRelativeHumiditySensor(): Boolean = isSupportSensor(Sensor.TYPE_RELATIVE_HUMIDITY)

    /**
     * @return 设备是否支持指南针
     */
    fun isSupportCompass(): Boolean = hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)

    /**
     * @return 是否支持前置摄像头
     */
    fun isSupportFrontCamera(): Boolean = hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)

    /**
     * @return 是否支持后置摄像头
     */
    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    fun isSupportBackCamera(): Boolean = hasSystemFeature(PackageManager.FEATURE_CAMERA)

    /**
     * @return 是否支持 WIFI
     */
    fun isSupportWIFI(): Boolean = hasSystemFeature(PackageManager.FEATURE_WIFI)

    /**
     * @return 是否支持 GPS
     */
    fun isSupportGPS(): Boolean = hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)

    /**
     * @return 是否支持麦克风
     */
    fun isSupportMicroPhone(): Boolean = hasSystemFeature(PackageManager.FEATURE_MICROPHONE)

    fun isSupportSensor(sensorType: Int): Boolean {
        return (Ktils.app.getSystemService(Context.SENSOR_SERVICE) as? SensorManager)?.let {
            val magneticFieldSensor = it.getDefaultSensor(sensorType)
            return magneticFieldSensor != null
        } ?: false
    }
}