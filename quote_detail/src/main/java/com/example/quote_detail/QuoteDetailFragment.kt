package com.example.quote_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.coreapi.mediator.AppWithFacade
import com.example.coreapi.network.MoexApi
import com.example.quote_detail.databinding.FragmentQuoteDetailBinding
import com.example.quote_detail.di.QuoteDetailComponent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class QuoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentQuoteDetailBinding

    @Inject
    lateinit var api: MoexApi

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
            lifecycleScope.launch {
                api.getLastQuotes(listOf("SBER"))
            }
        }
        return binding.root
    }
}