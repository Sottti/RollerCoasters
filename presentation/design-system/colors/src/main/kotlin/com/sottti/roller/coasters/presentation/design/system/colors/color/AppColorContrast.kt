package com.sottti.roller.coasters.presentation.design.system.colors.color

public sealed class AppColorContrast {
    public object HighContrast : AppColorContrast()
    public object MediumContrast : AppColorContrast()
    public object StandardContrast : AppColorContrast()
}