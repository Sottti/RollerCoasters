package com.sottti.roller.coasters.utils.device.system

import java.util.Locale

public interface LocaleProvider {
    public fun getAppLocale(): Locale?
    public fun getDefaultLocale(): Locale
}