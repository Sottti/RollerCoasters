package com.sottti.roller.coasters.utils.lifecycle

import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged

public fun <T> Context.observeConfigurationChanges(
    getValue: () -> T,
): Flow<T> = callbackFlow {
    trySend(getValue())

    val callbacks = object : ComponentCallbacks {
        override fun onConfigurationChanged(newConfig: Configuration) {
            trySend(getValue())
        }

        @Deprecated("Deprecated in Java")
        override fun onLowMemory() = Unit
    }

    registerComponentCallbacks(callbacks)
    awaitClose { unregisterComponentCallbacks(callbacks) }
}
    .distinctUntilChanged()
    .conflate()
