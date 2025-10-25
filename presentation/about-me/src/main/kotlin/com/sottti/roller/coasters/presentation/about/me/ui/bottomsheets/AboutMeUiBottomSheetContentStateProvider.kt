package com.sottti.roller.coasters.presentation.about.me.ui.bottomsheets

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.about.me.data.initialState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeBottomSheetPreviewState
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescription

internal class AboutMeUiBottomSheetContentStateProvider :
    PreviewParameterProvider<AboutMeBottomSheetPreviewState> {
    override val values: Sequence<AboutMeBottomSheetPreviewState> = sequence {
        yield(state(state = initialState.getToKnowMe.android.firstTopic.description))
        yield(state(state = initialState.getToKnowMe.android.fourthTopic.description))
        yield(state(state = initialState.getToKnowMe.android.secondTopic.description))
        yield(state(state = initialState.getToKnowMe.android.thirdTopic.description))
        yield(state(state = initialState.getToKnowMe.hobbies.firstTopic.description))
        yield(state(state = initialState.getToKnowMe.hobbies.fourthTopic.description))
        yield(state(state = initialState.getToKnowMe.hobbies.secondTopic.description))
        yield(state(state = initialState.getToKnowMe.hobbies.thirdTopic.description))
        yield(state(state = initialState.getToKnowMe.journey.description))
        yield(state(state = initialState.getToKnowMe.languages.firstTopic.description))
        yield(state(state = initialState.getToKnowMe.languages.fourthTopic.description))
        yield(state(state = initialState.getToKnowMe.languages.secondTopic.description))
        yield(state(state = initialState.getToKnowMe.languages.thirdTopic.description))
    }
}

private fun state(state: TopicDescription) =
    AboutMeBottomSheetPreviewState(
        onAction = {},
        state = state,
    )
