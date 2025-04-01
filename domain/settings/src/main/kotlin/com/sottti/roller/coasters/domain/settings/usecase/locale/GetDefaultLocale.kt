package com.sottti.roller.coasters.domain.settings.usecase.locale

import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import java.util.Locale
import javax.inject.Inject

public class GetDefaultLocale @Inject constructor(
    private val repository: SettingsRepository,
) {
    public operator fun invoke(): Locale =
        repository.getDefaultLocale()
}