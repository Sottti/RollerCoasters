package com.sottti.roller.coasters.utils.device.accesibility

public sealed class DeviceColorContrast {
    public object LowContrast : DeviceColorContrast()
    public object StandardContrast : DeviceColorContrast()
    public object MediumContrast : DeviceColorContrast()
    public object HighContrast : DeviceColorContrast()
}