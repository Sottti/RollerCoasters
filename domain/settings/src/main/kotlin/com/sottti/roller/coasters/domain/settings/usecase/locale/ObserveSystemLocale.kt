package com.sottti.roller.coasters.domain.settings.usecase.locale

import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import java.util.Locale
import javax.inject.Inject

public class ObserveSystemLocale @Inject constructor(
    private val repository: SettingsRepository,
) {
    public operator fun invoke(): Flow<Locale> =
        repository.observeSystemLocale()
}