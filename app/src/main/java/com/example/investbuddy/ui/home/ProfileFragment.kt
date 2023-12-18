package com.example.investbuddy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.network.UserAPI
import com.example.investbuddy.data.repository.UserRepository
import com.example.investbuddy.data.responses.StockResponse
import com.example.investbuddy.data.responses.StockResponseItem
import com.example.investbuddy.data.responses.UserResponse
import com.example.investbuddy.databinding.FragmentProfileBinding
import com.example.investbuddy.ui.base.BaseFragment
import com.example.investbuddy.ui.detail.DetailActivity
import com.example.investbuddy.ui.home.balance.BalanceActivity
import com.example.investbuddy.ui.home.card.CardAdapter
import com.example.investbuddy.ui.home.card.StockClickListener
import com.example.investbuddy.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class ProfileFragment : BaseFragment<HomeViewModel, FragmentProfileBinding, UserRepository>(), StockClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser()
        viewModel.getMyStocks()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    updateUIUser(it.value)
                }
                is Resource.Failure -> {

                }
            }
        })

        viewModel.stocks.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    updateUIStocks(it.value, this)
                }
                is Resource.Failure -> {

                }
            }
        })

        binding.balanceBtn.setOnClickListener {
            val intent = Intent(context, BalanceActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    private fun updateUIUser(user: UserResponse) {
        with(binding) {
            idEmail.text = user.email
            idUsername.text = user.username
            idRegistered.text = user.registered_at
            idBalance.text = user.balance.toString() + " ₽"
        }
    }

    private fun updateUIStocks(stocks: StockResponse, activity: ProfileFragment) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CardAdapter(stocks, activity)
        }
    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildAPI(UserAPI::class.java, token)
        return UserRepository(api)
    }

    override fun onClick(stock: StockResponseItem) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("stockExtra", stock.id)
        startActivity(intent)
    }

}