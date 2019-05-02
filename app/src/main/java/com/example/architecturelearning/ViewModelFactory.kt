package com.example.architecturelearning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory
    @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]

        if (creator == null) {
            creators.entries.forEach {
                if (modelClass.isAssignableFrom(it.key)) {
                    creator = it.value
                    return@forEach
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("ViewModel creator isn't found")
        }

        return creator!!.get() as T
    }
}