package com.example.investbuddy.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.investbuddy.R
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.network.StockAPI
import com.example.investbuddy.data.repository.StockRepository
import com.example.investbuddy.data.responses.StockResponseItem
import com.example.investbuddy.databinding.FragmentBuyBinding
import com.example.investbuddy.databinding.FragmentSellBinding
import com.example.investbuddy.ui.base.BaseFragment
import com.example.investbuddy.ui.enable
import com.example.investbuddy.ui.home.HomeActivity
import com.example.investbuddy.ui.home.StockViewModel
import com.example.investbuddy.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SellFragment : BaseFragment<StockViewModel, FragmentSellBinding, StockRepository>() {

    private var price: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stockID = activity?.intent?.getIntExtra("stockExtra", -1)
        if (stockID != null) {
            viewModel.getStock(stockID)
        }

        viewModel.stock_item.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    updateUIStock(it.value)
                }

                is Resource.Failure -> {

                }
            }
        })

        viewModel.stock_result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }

                is Resource.Failure -> {

                }
            }
        })

        binding.idSellBtn.setOnClickListener {
            val quantity = Integer.parseInt(binding.quantityText.text.toString())
            if (stockID != null) {
                viewModel.sellStocks(stockID, quantity)
            }
        }

        binding.quantityText.addTextChangedListener {
            val quantity = Integer.parseInt(binding.quantityText.text.toString())
            binding.totalSum.text = (price * quantity).toString()
        }


    }

    private fun updateUIStock(stock_item: StockResponseItem) {
        val quantity = Integer.parseInt(binding.quantityText.text.toString())
        binding.totalSum.text = (stock_item.price * quantity).toString()
        price = stock_item.price
    }


    override fun getViewModel() = StockViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSellBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): StockRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildAPI(StockAPI::class.java, token)
        return StockRepository(api)
    }

}