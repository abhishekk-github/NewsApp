package com.doubtnut.amateur.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelProviderFactory @Inject constructor(private val providerMap: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {
    /**
     * Creates a new instance of the given `Class`.
     *
     *
     *
     * @param modelClass a `Class` whose instance is requested
     * @param <T>        The type parameter for the ViewModel.
     * @return a newly created ViewModel
    </T> */
    override fun <T : ViewModel> create(modelClass: Class<T>): T = (providerMap[modelClass]
        ?: error("ViewModel instance for $modelClass is not present in the given map")).get() as T

}