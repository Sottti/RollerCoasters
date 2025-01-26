package com.sottti.roller.coasters.utils.device

public sealed class ColorContrast {
    public object LowContrast : ColorContrast()
    public object StandardContrast : ColorContrast()
    public object MediumContrast : ColorContrast()
    public object HighContrast : ColorContrast()
}