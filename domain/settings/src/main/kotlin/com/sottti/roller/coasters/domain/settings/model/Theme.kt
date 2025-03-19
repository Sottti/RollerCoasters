package com.sottti.roller.coasters.domain.settings.model

public sealed class Theme {
    public data object DarkTheme : Theme()
    public data object LightTheme : Theme()
    public data object SystemTheme : Theme()
}