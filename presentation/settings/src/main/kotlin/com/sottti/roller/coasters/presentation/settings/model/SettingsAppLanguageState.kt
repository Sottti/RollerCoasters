package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class AppLanguageState(
    val listItem: AppLanguageListItemState,
    val picker: AppLanguagePickerState?,
)

@Immutable
internal data class AppLanguageListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedAppLanguage: AppSelectedLanguageState,
    val icon: IconState,
)

@Immutable
internal sealed interface AppSelectedLanguageState {
    @Immutable
    data object Loading : AppSelectedLanguageState

    @Immutable
    data class Loaded(val appLanguage: AppLanguageUi) : AppSelectedLanguageState
}

@Immutable
internal data class AppLanguagePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val appLanguages: List<AppLanguageUi>,
)

@Immutable
internal sealed interface AppLanguageUi {
    @get:StringRes
    val text: Int
    val icon: IconState
    val selected: Boolean
}

@Immutable
internal data class SpanishSpainLanguage(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppLanguageUi

@Immutable
internal data class EnglishGbLanguage(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppLanguageUi

@Immutable
internal data class GalicianLanguage(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppLanguageUi

@Immutable
internal data class SystemLanguage(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppLanguageUi
