package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.sotti.roller.coasters.presentation.design.system.icons.model.IconState

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
internal sealed class AppSelectedLanguageState {
    data object Loading : AppSelectedLanguageState()
    data class Loaded(val appLanguage: AppLanguageUi) : AppSelectedLanguageState()
}

@Immutable
internal data class AppLanguagePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val appLanguages: List<AppLanguageUi>,
)

@Immutable
internal sealed class AppLanguageUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class SpanishSpainLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppLanguageUi(text, icon, selected)

    class EnglishGbLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppLanguageUi(text, icon, selected)

    class GalicianLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppLanguageUi(text, icon, selected)

    class SystemLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppLanguageUi(text, icon, selected)
}