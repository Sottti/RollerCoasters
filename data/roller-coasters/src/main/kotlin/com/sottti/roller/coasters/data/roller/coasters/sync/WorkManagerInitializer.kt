package com.sottti.roller.coasters.data.roller.coasters.sync

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.sottti.roller.coasters.data.roller.coasters.di.WorkManagerEntryPoint
import dagger.hilt.android.EntryPointAccessors

internal class WorkManagerInitializer : ContentProvider() {

    override fun onCreate(): Boolean {
        val appContext =
            context?.applicationContext ?: throw IllegalStateException("Context cannot be null")

        val workerFactory: WorkerFactory =
            EntryPointAccessors
                .fromApplication(
                    context = appContext,
                    entryPoint = WorkManagerEntryPoint::class.java,
                ).getWorkerFactory()

        WorkManager.initialize(
            context = appContext,
            configuration = Configuration.Builder().setWorkerFactory(workerFactory).build(),
        )
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?,
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null
    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?,
    ): Int = 0
}
