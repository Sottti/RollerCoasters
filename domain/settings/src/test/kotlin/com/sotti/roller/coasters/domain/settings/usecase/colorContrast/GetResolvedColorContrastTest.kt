package com.sotti.roller.coasters.domain.settings.usecase.colorContrast

import com.google.common.truth.Truth.assertThat
import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class GetResolvedColorContrastTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getResolvedColorContrast: GetResolvedColorContrast

    @Before
    fun setUp() {
        settingsRepository = mockk()
        getResolvedColorContrast = GetResolvedColorContrast(settingsRepository)
    }

    @Test
    fun `returns standard contrast when app sets standard contrast`() = runTest {
        coEvery {
            settingsRepository.getAppColorContrast()
        } returns AppColorContrast.StandardContrast

        val result: ResolvedColorContrast = getResolvedColorContrast()

        assertThat(result).isEqualTo(ResolvedColorContrast.StandardContrast)
        verify(exactly = 0) { settingsRepository.getSystemColorContrast() }
    }

    @Test
    fun `returns medium contrast when app sets medium contrast`() = runTest {
        coEvery {
            settingsRepository.getAppColorContrast()
        } returns AppColorContrast.MediumContrast

        val result = getResolvedColorContrast()

        assertThat(result).isEqualTo(ResolvedColorContrast.MediumContrast)
        verify(exactly = 0) { settingsRepository.getSystemColorContrast() }
    }

    @Test
    fun `returns high contrast when app sets high contrast`() = runTest {
        coEvery {
            settingsRepository.getAppColorContrast()
        } returns AppColorContrast.HighContrast

        val result = getResolvedColorContrast()

        assertThat(result).isEqualTo(ResolvedColorContrast.HighContrast)
        verify(exactly = 0) { settingsRepository.getSystemColorContrast() }
    }


    @Test
    fun `delegates to system when app sets system contrast and returns high contrast`() = runTest {
        coEvery { settingsRepository.getAppColorContrast() } returns AppColorContrast.System
        every {
            settingsRepository.getSystemColorContrast()
        } returns SystemColorContrast.HighContrast

        val result = getResolvedColorContrast()

        assertThat(result).isEqualTo(ResolvedColorContrast.HighContrast)
        verify(exactly = 1) { settingsRepository.getSystemColorContrast() }
    }

    @Test
    fun `delegates to system when app sets system contrast and returns medium contrast`() =
        runTest {
            coEvery { settingsRepository.getAppColorContrast() } returns AppColorContrast.System
            every {
                settingsRepository.getSystemColorContrast()
            } returns SystemColorContrast.MediumContrast

            val result = getResolvedColorContrast()

            assertThat(result).isEqualTo(ResolvedColorContrast.MediumContrast)
            verify(exactly = 1) { settingsRepository.getSystemColorContrast() }
        }

    @Test
    fun `delegates to system when app sets system contrast and returns standard contrast`() =
        runTest {
            coEvery { settingsRepository.getAppColorContrast() } returns AppColorContrast.System
            every {
                settingsRepository.getSystemColorContrast()
            } returns SystemColorContrast.StandardContrast

            val result = getResolvedColorContrast()

            assertThat(result).isEqualTo(ResolvedColorContrast.StandardContrast)
            verify(exactly = 1) { settingsRepository.getSystemColorContrast() }
        }

    @Test
    fun `delegates to system when app sets system contrast and returns low contrast`() = runTest {
        coEvery { settingsRepository.getAppColorContrast() } returns AppColorContrast.System
        every {
            settingsRepository.getSystemColorContrast()
        } returns SystemColorContrast.LowContrast

        val result = getResolvedColorContrast()

        assertThat(result).isEqualTo(ResolvedColorContrast.LowContrast)
        verify(exactly = 1) { settingsRepository.getSystemColorContrast() }
    }
}
