package com.sottti.roller.coasters.data.settings.model

public sealed class ColorContrast {
    public data object HighContrast : ColorContrast()
    public data object MediumContrast : ColorContrast()
    public data object StandardContrast : ColorContrast()
    public data object SystemContrast : ColorContrast()
}