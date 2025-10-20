package com.sottti.roller.coasters.domain.settings.usecase.colorContrast

import com.sottti.roller.coasters.domain.settings.mapper.toResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetResolvedColorContrast @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): ResolvedColorContrast =
        settingsRepository
            .getAppColorContrast()
            .resolve()

    private fun AppColorContrast.resolve(): ResolvedColorContrast =
        when (this) {
            AppColorContrast.HighContrast,
            AppColorContrast.MediumContrast,
            AppColorContrast.StandardContrast,
                -> toResolvedColorContrast()

            AppColorContrast.System ->
                settingsRepository
                    .getSystemColorContrast()
                    .toResolvedColorContrast()
        }
}
