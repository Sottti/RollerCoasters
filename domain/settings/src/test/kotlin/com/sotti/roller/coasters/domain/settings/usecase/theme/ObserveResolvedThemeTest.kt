package com.sotti.roller.coasters.domain.settings.usecase.theme

import app.cash.turbine.test
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme.System
import com.sotti.roller.coasters.domain.settings.model.theme.ResolvedTheme.DarkResolvedTheme
import com.sotti.roller.coasters.domain.settings.model.theme.ResolvedTheme.LightResolvedTheme
import com.sotti.roller.coasters.domain.settings.model.theme.SystemTheme
import com.sotti.roller.coasters.domain.settings.model.theme.SystemTheme.DarkSystemTheme
import com.sotti.roller.coasters.domain.settings.model.theme.SystemTheme.LightSystemTheme
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ObserveResolvedThemeTest {

    private lateinit var appThemeFlow: MutableSharedFlow<AppTheme>
    private lateinit var observeResolvedTheme: ObserveResolvedTheme
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var systemThemeFlow: MutableSharedFlow<SystemTheme>

    @BeforeTest
    fun setUp() {
        appThemeFlow = MutableSharedFlow(replay = 1)
        systemThemeFlow = MutableSharedFlow(replay = 1)
        settingsRepository = mockk()
        coEvery { settingsRepository.observeAppTheme() } returns appThemeFlow
        coEvery { settingsRepository.observeSystemTheme() } returns systemThemeFlow
        observeResolvedTheme = ObserveResolvedTheme(settingsRepository)
    }

    @Test
    fun `app light theme always resolves to light theme regardless of system theme`() = runTest {
        appThemeFlow.emit(LightAppTheme)
        systemThemeFlow.emit(DarkSystemTheme)
        observeResolvedTheme().test {
            assertEquals(LightResolvedTheme, awaitItem())
            systemThemeFlow.emit(LightSystemTheme)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `app dark theme always resolves to dark theme regardless of system theme`() = runTest {
        appThemeFlow.emit(DarkAppTheme)
        systemThemeFlow.emit(LightSystemTheme)
        observeResolvedTheme().test {
            assertEquals(DarkResolvedTheme, awaitItem())
            systemThemeFlow.emit(DarkSystemTheme)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `app system theme mirrors system light`() = runTest {
        appThemeFlow.emit(System)
        systemThemeFlow.emit(LightSystemTheme)
        observeResolvedTheme().test {
            assertEquals(LightResolvedTheme, awaitItem())
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `app system theme mirrors system dark`() = runTest {
        appThemeFlow.emit(System)
        systemThemeFlow.emit(DarkSystemTheme)
        observeResolvedTheme().test {
            assertEquals(DarkResolvedTheme, awaitItem())
            expectNoEvents()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emits only when resolved theme actually changes (distinct until changed)`() = runTest {
        appThemeFlow.emit(LightAppTheme)
        systemThemeFlow.emit(LightSystemTheme)
        observeResolvedTheme().test {
            assertEquals(LightResolvedTheme, awaitItem())
            appThemeFlow.emit(LightAppTheme)
            systemThemeFlow.emit(LightSystemTheme)
            expectNoEvents()
            appThemeFlow.emit(DarkAppTheme)
            assertEquals(DarkResolvedTheme, awaitItem())
            systemThemeFlow.emit(DarkSystemTheme)
            expectNoEvents()
            appThemeFlow.emit(System)
            expectNoEvents()
            systemThemeFlow.emit(LightSystemTheme)
            assertEquals(LightResolvedTheme, awaitItem())
            systemThemeFlow.emit(LightSystemTheme)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }
}
