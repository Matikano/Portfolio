package com.matikano.complimentapp.data.receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import com.matikano.complimentapp.data.local.NotificationPreferences
import com.matikano.complimentapp.domain.notification.NotificationService
import com.matikano.complimentapp.di.receivers.HiltBroadcastReceiver
import com.matikano.complimentapp.domain.repository.ComplimentRepository
import com.matikano.complimentapp.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class NotificationAlarmReceiver: HiltBroadcastReceiver() {

    @Inject
    lateinit var notificationService: NotificationService

    @Inject
    lateinit var repository: ComplimentRepository

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        CoroutineScope(Dispatchers.IO).launch {
            when(val result = repository.getCompliment()){
                is Resource.Success -> {
                    val content = result.data?.content
                    content?.let { compliment ->
                        Log.d("NotificationService", "${this.javaClass.name} onReceive: content = $compliment")
                        notificationService.showNotification(compliment)
                    }
                }
                is Resource.Error -> return@launch
            }

        }

    }

}