package com.sottti.roller.coasters.data.settings.datasource

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ActivityLifecycleEmitter @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val _activityCreatedFlow = MutableSharedFlow<Unit>()
    val activityCreatedFlow: Flow<Unit> = _activityCreatedFlow

    init {
        (context as Application).registerActivityLifecycleCallbacks(
            object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    _activityCreatedFlow.tryEmit(Unit)
                }

                override fun onActivityStarted(activity: Activity) {
                    // No specific action required on activity started for this emitter.
                }

                override fun onActivityResumed(activity: Activity) {
                    // No specific action required on activity resumed for this emitter.
                }

                override fun onActivityPaused(activity: Activity) {
                    // No specific action required on activity paused for this emitter.
                }

                override fun onActivityStopped(activity: Activity) {
                    // No specific action required on activity stopped for this emitter.
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                    // No specific action required on activity save instance state for this emitter.
                }

                override fun onActivityDestroyed(activity: Activity) {
                    // No specific action required on activity destroyed for this emitter.
                }
            },
        )

        _activityCreatedFlow.tryEmit(Unit)
    }
}