package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class ScheduleRollerCoastersSyncTest {

    private lateinit var rollerCoastersRepository: RollerCoastersRepository
    private lateinit var scheduleRollerCoastersSync: ScheduleRollerCoastersSync

    @Before
    fun setUp() {
        rollerCoastersRepository = mockk<RollerCoastersRepository> {
            every { scheduleRollerCoastersSync() } just runs
        }
        scheduleRollerCoastersSync = ScheduleRollerCoastersSync(rollerCoastersRepository)
    }

    @Test
    fun `schedules roller coasters sync when invoked`() {
        scheduleRollerCoastersSync()
        verify(exactly = 1) { rollerCoastersRepository.scheduleRollerCoastersSync() }
    }
}