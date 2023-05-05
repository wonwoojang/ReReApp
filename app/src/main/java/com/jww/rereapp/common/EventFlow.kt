package com.jww.rereapp.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.concurrent.atomic.AtomicBoolean


/**
 * @author 박상권<br>https://medium.com/prnd/mvvm의-viewmodel에서-이벤트를-처리하는-방법-6가지-31bb183a88ce
 */

interface EventFlow<out T> : Flow<T> {
    companion object {
        const val DEFAULT_REPLAY: Int = 3
    }
}

interface MutableEventFlow<T> : EventFlow<T>, FlowCollector<T>

fun <T> mutableEventFlow(replay: Int = EventFlow.DEFAULT_REPLAY): MutableEventFlow<T> =
    EventFlowImpl(replay)

fun <T> MutableEventFlow<T>.asEventFlow(): EventFlow<T> = ReadOnlyEventFlow(this)

private class ReadOnlyEventFlow<T>(flow: EventFlow<T>) : EventFlow<T> by flow

private class EventFlowImpl<T>(replay: Int) : MutableEventFlow<T> {
    private val flow: MutableSharedFlow<EventFlowSlot<T>> = MutableSharedFlow(replay = replay)

    override suspend fun collect(collector: FlowCollector<T>) = flow.collect { slot ->
        if (!slot.markConsumed()) {
            collector.emit(slot.value)
        }
    }

    override suspend fun emit(value: T) {
        flow.emit(EventFlowSlot(value))
    }
}

private class EventFlowSlot<T>(val value: T) {
    private val consumed: AtomicBoolean = AtomicBoolean(false)

    fun markConsumed(): Boolean = consumed.getAndSet(true)
}