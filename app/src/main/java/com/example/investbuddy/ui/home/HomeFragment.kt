package com.example.investbuddy.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.network.StockAPI
import com.example.investbuddy.data.repository.StockRepository
import com.example.investbuddy.data.responses.StockResponse
import com.example.investbuddy.data.responses.StockResponseItem
import com.example.investbuddy.databinding.FragmentHomeBinding
import com.example.investbuddy.ui.base.BaseFragment
import com.example.investbuddy.ui.detail.DetailActivity
import com.example.investbuddy.ui.home.card.CardAdapter
import com.example.investbuddy.ui.home.card.StockClickListener
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<StockViewModel, FragmentHomeBinding, StockRepository>(), StockClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStocks()


        (activity as HomeActivity?)!!.setSupportActionBar(binding.toolbar)
        viewModel.stock.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value, this)
                }

                is Resource.Failure -> {

                }
            }
        })
    }

    override fun onClick(stock: StockResponseItem) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("stockExtra", stock.id)
        startActivity(intent)
    }

    private fun updateUI(stocks: StockResponse, activity: HomeFragment) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CardAdapter(stocks, activity)
        }
    }

    override fun getViewModel() = StockViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): StockRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildAPI(StockAPI::class.java, token)
        return StockRepository(api)
    }

}