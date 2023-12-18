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
import com.example.investbuddy.data.network.UserAPI
import com.example.investbuddy.data.repository.StockRepository
import com.example.investbuddy.data.repository.UserRepository
import com.example.investbuddy.data.responses.StockResponseItem
import com.example.investbuddy.databinding.FragmentBalanceBinding
import com.example.investbuddy.databinding.FragmentBuyBinding
import com.example.investbuddy.ui.base.BaseFragment
import com.example.investbuddy.ui.enable
import com.example.investbuddy.ui.home.HomeActivity
import com.example.investbuddy.ui.home.HomeViewModel
import com.example.investbuddy.ui.home.StockViewModel
import com.example.investbuddy.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BuyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BalanceFragment : BaseFragment<HomeViewModel, FragmentBalanceBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.balance.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }

                is Resource.Failure -> {

                }
            }
        })

        binding.idBalanceBtn.setOnClickListener {
            val money = binding.quantityText.text.toString().toDouble()
            viewModel.balanceAdd(money)
        }



    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBalanceBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildAPI(UserAPI::class.java, token)
        return UserRepository(api)
    }

}