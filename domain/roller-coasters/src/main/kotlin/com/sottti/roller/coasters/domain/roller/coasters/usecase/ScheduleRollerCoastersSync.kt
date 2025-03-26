package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import javax.inject.Inject

public class ScheduleRollerCoastersSync @Inject constructor(
    private val rollerCoastersRepository: RollerCoastersRepository,
) {
    public operator fun invoke() {
        rollerCoastersRepository.scheduleRollerCoastersSync()
    }
}