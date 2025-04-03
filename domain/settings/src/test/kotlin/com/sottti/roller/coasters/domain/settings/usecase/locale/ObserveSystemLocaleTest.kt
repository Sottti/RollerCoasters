package com.sottti.roller.coasters.domain.settings.usecase.locale

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
internal class ObserveSystemLocaleTest {

    private val repository = mockk<SettingsRepository>()
    private lateinit var useCase: ObserveSystemLocale

    @Before
    fun setup() {
        useCase = ObserveSystemLocale(repository)
    }

    @Test
    fun `invoking use case delegates to repository`() = runTest {
        every { repository.observeSystemLocale() } returns flowOf(Locale.UK)
        useCase.invoke()
        verify { repository.observeSystemLocale() }
    }

    @Test
    fun `invoking use case emits locale uk from repository`() = runTest {
        every { repository.observeSystemLocale() } returns flowOf(Locale.UK)
        val result = useCase.invoke().toList()
        assertThat(result).containsExactly(Locale.UK)
    }

    @Test
    fun `invoking use case emits multiple locales from repository`() = runTest {
        every { repository.observeSystemLocale() } returns flowOf(Locale.UK, Locale("es", "ES"))
        val result = useCase.invoke().toList()
        assertThat(result).containsExactly(Locale.UK, Locale("es", "ES")).inOrder()
    }
}