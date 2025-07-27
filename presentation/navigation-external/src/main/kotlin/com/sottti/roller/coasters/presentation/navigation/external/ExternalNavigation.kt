package com.sottti.roller.coasters.presentation.navigation.external

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class ExternalNavigation @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    public fun openUrl(
        @StringRes urlResId: Int,
        toolbarColor: Int,
    ) {
        CustomTabsIntent.Builder().apply {
            setShowTitle(true)
            setShareState(CustomTabsIntent.SHARE_STATE_ON)
            setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(toolbarColor)
                    .build(),
            )
        }
            .build()
            .apply { intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            .launchUrl(context, context.getString(urlResId).toUri())
    }
}
