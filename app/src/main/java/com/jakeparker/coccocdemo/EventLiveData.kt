package com.jakeparker.coccocdemo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Long Tran
 * @since 02/01/2021
 */
class EventLiveData<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(
                owner,
                Observer {
                    if (pending.compareAndSet(true, false)) observer.onChanged(it)
                }
        )
    }

    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }
}