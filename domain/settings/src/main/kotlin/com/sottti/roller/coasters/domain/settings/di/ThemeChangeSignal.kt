package com.sottti.roller.coasters.domain.settings.di

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class ThemeChangeSignal @Inject constructor() {
    public var activityRecreationNeeded: Boolean = false
}
