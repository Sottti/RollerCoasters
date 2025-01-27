package com.sottti.roller.coasters.utils.device.system

public sealed class SystemColorContrast {
    public object LowContrast : SystemColorContrast()
    public object StandardContrast : SystemColorContrast()
    public object MediumContrast : SystemColorContrast()
    public object HighContrast : SystemColorContrast()
}