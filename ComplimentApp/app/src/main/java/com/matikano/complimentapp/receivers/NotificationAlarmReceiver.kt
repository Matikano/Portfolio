package com.matikano.complimentapp.receivers

import android.content.Context
import android.content.Intent
import com.matikano.complimentapp.di.receivers.HiltBroadcastReceiver
import com.matikano.complimentapp.domain.notification.ComplimentNotificationService
import com.matikano.complimentapp.domain.repository.ComplimentRepository
import com.matikano.complimentapp.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationAlarmReceiver: HiltBroadcastReceiver() {

    @Inject
    lateinit var complimentNotificationService: ComplimentNotificationService

    @Inject
    lateinit var repository: ComplimentRepository

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        CoroutineScope(Dispatchers.IO).launch {
            when(val result = repository.getCompliment()){
                is Resource.Success -> {
                    val content = result.data?.content
                    content?.let { compliment ->
                        complimentNotificationService.showNotification(compliment)
                    }
                }
                is Resource.Error -> return@launch
            }

        }

    }

}