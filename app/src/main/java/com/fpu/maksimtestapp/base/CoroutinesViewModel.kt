package com.fpu.maksimtestapp.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.coroutines.CoroutineContext

open class CoroutinesViewModel : ViewModel(), CoroutineScope {

    private val errorHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()
        launch { _errorEvents.emit(error) }
    }

    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Main.immediate + errorHandler

    private val _errorEvents: MutableSharedFlow<Throwable> = MutableSharedFlow()
    val errorEvents: SharedFlow<Throwable>
        get() = _errorEvents
}