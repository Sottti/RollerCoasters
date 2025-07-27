package com.sottti.roller.coasters.presentation.about.me.data

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.presentation.navigation.external.ExternalNavigation
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.justRun
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class AboutMeViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var externalNavigation: ExternalNavigation

    private lateinit var viewModel: AboutMeViewModel

    @Before
    fun setUp() {
        viewModel = AboutMeViewModel(externalNavigation)
    }

    @Test
    fun `initial state is emitted correctly`() = runTest {
        val expectedInitialState = initialState
        val actualState = viewModel.state.first()

        assertThat(actualState).isEqualTo(expectedInitialState)
    }

    @Test
    fun `on action open url calls external navigation and does not mutate state`() = runTest {

        val stateInitial = viewModel.state.first()

        justRun {
            externalNavigation.openUrl(
                urlResId = urlResId,
                toolbarColor = toolbarColorInt,
            )
        }

        viewModel.onAction(openUrlAction)

        verify(exactly = 1) {
            externalNavigation.openUrl(
                urlResId = urlResId,
                toolbarColor = toolbarColorInt,
            )
        }

        val stateFinal = viewModel.state.first()
        assertThat(stateFinal).isEqualTo(stateInitial)
    }
}
