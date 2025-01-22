package com.sottti.roller.coasters.domain.settings.model

public sealed class Theme(public val key: String) {
    public data object DarkTheme : Theme("dark")
    public data object LightTheme : Theme("light")
    public data object SystemTheme : Theme("system")
}