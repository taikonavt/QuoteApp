package com.example.quote_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.base.BaseFragment
import com.example.base.BaseViewModel
import com.example.coreapi.mediator.AppWithFacade
import com.example.coreapi.network.MoexApi
import com.example.quote_detail.databinding.FragmentQuoteDetailBinding
import com.example.quote_detail.di.QuoteDetailComponent
import com.example.quote_detail.model.QuoteDetailRouteEvent
import com.example.quote_detail.model.QuoteDetailUIEvent
import com.example.quote_detail.model.QuoteDetailUIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuoteDetailFragment : BaseFragment<QuoteDetailUIEvent,
    QuoteDetailUIState, QuoteDetailRouteEvent, FragmentQuoteDetailBinding>() {

    @Inject lateinit var api: MoexApi

    @Inject override lateinit var viewModel: BaseViewModel<QuoteDetailUIEvent, QuoteDetailUIState, QuoteDetailRouteEvent, *>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QuoteDetailComponent.create(
            (requireActivity().application as AppWithFacade).getFacade()
        )
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuoteDetailBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
//            lifecycleScope.launch {
//                api.getLastQuotes(listOf("SBER"))
//            }
        }
        return binding.root
    }

    override fun setViewBinding(inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentQuoteDetailBinding =
        FragmentQuoteDetailBinding.inflate(inflater)

    override fun onFirstShow() {
        super.onFirstShow()
        viewModel.postEvent(QuoteDetailUIEvent.Init)
    }

    override fun handleUiData(data: QuoteDetailUIState) {
        data.value?.let {
            binding.tv.text = it
        }
    }
}