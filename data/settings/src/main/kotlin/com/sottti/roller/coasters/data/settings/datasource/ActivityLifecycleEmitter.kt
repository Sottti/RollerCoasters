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

                override fun onActivityStarted(activity: Activity) {}
                override fun onActivityResumed(activity: Activity) {}
                override fun onActivityPaused(activity: Activity) {}
                override fun onActivityStopped(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
                override fun onActivityDestroyed(activity: Activity) {}
            },
        )

        _activityCreatedFlow.tryEmit(Unit)
    }
}
