package com.example.investbuddy.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.investbuddy.R
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.network.StockAPI
import com.example.investbuddy.data.repository.StockRepository
import com.example.investbuddy.data.responses.StockResponseItem
import com.example.investbuddy.databinding.FragmentDetailBinding
import com.example.investbuddy.ui.base.BaseFragment
import com.example.investbuddy.ui.home.StockViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DetailFragment : BaseFragment<StockViewModel, FragmentDetailBinding, StockRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stockID = activity?.intent?.getIntExtra("stockExtra", -1)
        if (stockID != null) {
            viewModel.getStock(stockID)
        }

        viewModel.stock_item.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value)
                }

                is Resource.Failure -> {

                }
            }
        })

        binding.buyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_buyFragment)
        }

        binding.sellBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_sellFragment)
        }

    }

    private fun updateUI(stock_item: StockResponseItem) {
        with(binding) {
            priceText.text = stock_item.price.toString() + " ₽"
            companyText.text = stock_item.company
            ticketText.text = stock_item.ticket
            descriptionText.text = stock_item.description

        }
        val image = remoteDataSource.BASE_URL_ALL + stock_item.image
        Glide.with(binding.root).load(image).circleCrop()
            .into(binding.imageView)
    }


    override fun getViewModel() = StockViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): StockRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildAPI(StockAPI::class.java, token)
        return StockRepository(api)
    }

}