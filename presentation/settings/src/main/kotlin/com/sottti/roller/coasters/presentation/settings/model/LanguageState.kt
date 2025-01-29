package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class LanguageState(
    val listItem: LanguageListItemState,
    val picker: LanguagePickerState?,
)

@Immutable
internal data class LanguageListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedLanguage: SelectedLanguageState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedLanguageState {
    data object Loading : SelectedLanguageState()
    data class Loaded(val language: LanguageUi) : SelectedLanguageState()
}

@Immutable
internal data class LanguagePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val languages: List<LanguageUi>,
)

@Immutable
internal sealed class LanguageUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class SpanishLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : LanguageUi(text, icon, selected)

    class EnglishLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : LanguageUi(text, icon, selected)

    class GalicianLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : LanguageUi(text, icon, selected)

    class SystemLanguage(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : LanguageUi(text, icon, selected)
}