package com.sottti.roller.coasters.domain.settings.di

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class InAppThemeChangeSignal @Inject constructor() {
    public var activityRecreationNeeded: Boolean = false
}
