package com.matikano.complimentapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(context: Context, params: WorkerParameters): CoroutineWorker
}