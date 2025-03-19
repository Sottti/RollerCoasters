package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import javax.inject.Inject

public class SyncAllRollerCoasters @Inject constructor(
    private val rollerCoastersRepository: RollerCoastersRepository,
) {
    public suspend operator fun invoke(): Result<Unit> =
        rollerCoastersRepository.syncAllRollerCoasters()
}