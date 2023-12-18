package com.example.investbuddy.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.investbuddy.R
import com.example.investbuddy.databinding.FragmentLoginBinding
import com.example.investbuddy.data.network.AuthAPI
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.repository.AuthRepository
import com.example.investbuddy.databinding.FragmentRegisterBinding
import com.example.investbuddy.ui.base.BaseFragment
import com.example.investbuddy.ui.enable
import com.example.investbuddy.ui.home.HomeActivity
import com.example.investbuddy.ui.startNewActivity
import com.example.investbuddy.ui.visible
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.visible(false)
        binding.idBtnRegister.enable(false)

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(false)
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }

                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.idEdtPassword.addTextChangedListener {
            val email = binding.idEdtEmail.text.toString().trim()
            binding.idBtnRegister.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.idBtnRegister.setOnClickListener {
            val username = binding.idEdtUserName.text.toString().trim()
            val email = binding.idEdtEmail.text.toString().trim()
            val password = binding.idEdtPassword.text.toString().trim()
            binding.progressBar.visible(true)
            val registerBody: RegisterBody = RegisterBody(username, email, password)
            viewModel.register(registerBody)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildAPI(AuthAPI::class.java), userPreferences)

}