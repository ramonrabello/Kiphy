package com.github.ramonrabello.kiphy.common.arch.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = Main

    protected val jobs = mutableListOf<Job>()

    infix fun MutableList<Job>.add(job: Job) {
        this.add(job)
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { if (!it.isCancelled) it.cancel() }
    }
}
