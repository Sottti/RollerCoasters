package com.sottti.roller.coasters.domain.model

public sealed class ColorContrast {
    public data object HighContrast : ColorContrast()
    public data object MediumContrast : ColorContrast()
    public data object StandardContrast : ColorContrast()
    public data object SystemContrast : ColorContrast()
}