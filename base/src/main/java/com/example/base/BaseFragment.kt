package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.base.delegates.ErrorDelegate
import com.example.base.delegates.ErrorDelegateImpl
import com.example.coreapi.types.ResultWrapper
import com.example.coreapi.types.unwrap
import kotlinx.coroutines.flow.collect

abstract class BaseFragment<UIEvent, UIState, RouteEvent, VB : ViewBinding> : Fragment(),
    ErrorDelegate by ErrorDelegateImpl() {

    abstract var viewModel: BaseViewModel<UIEvent, UIState, RouteEvent, *>

    protected lateinit var binding: VB

    init {
        lifecycleScope.launchWhenResumed {
            viewModel.observeScreenState().collect(::handleUiState)
        }
        lifecycleScope.launchWhenResumed {
            viewModel.observeLoadingState().collect {
                it?.run {
                    handleUiState(it)
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.observeErrorState().collect {
                it.run {
                    handleUiState(it)
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.observeRouteEventsState().collect {
                it?.run {
                    handleRouteEvent(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setViewBinding(inflater, container, false)
        activity?.let { bindErrorActivity(it) }
        initViews()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onFirstShow(::onFirstShow)
        onShow()
    }

    override fun onStop() {
        super.onStop()
        onHide()
    }

    private fun handleUiState(state: ResultWrapper<UIState>) {
        state.unwrap(
            onData = ::handleUiData,
            onLoading = ::handleLoading,
            onError = ::handleError
        )
    }

    abstract fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): VB

    open fun initViews() {
        // Default empty
    }

    open fun onFirstShow() {
        // Default empty
    }

    open fun onShow() {
        // Default empty
    }

    open fun onHide() {
        // Default empty
    }

    open fun handleUiData(data: UIState) {
        // Default empty
    }

    open fun handleLoading(show: Boolean) {

    }

    open fun handleError(error: Throwable) {
        showError(error)
    }

    open fun handleRouteEvent(event: RouteEvent) {
        // Default empty
    }

    protected fun postEvent(event: UIEvent) = viewModel.postEvent(event)

}