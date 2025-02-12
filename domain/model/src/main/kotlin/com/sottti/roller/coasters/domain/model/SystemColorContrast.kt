package com.sottti.roller.coasters.domain.model

public sealed class SystemColorContrast {
    public object LowContrast : SystemColorContrast()
    public object StandardContrast : SystemColorContrast()
    public object MediumContrast : SystemColorContrast()
    public object HighContrast : SystemColorContrast()
}