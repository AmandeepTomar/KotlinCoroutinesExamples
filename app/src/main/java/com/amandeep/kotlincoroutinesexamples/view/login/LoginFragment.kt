package com.amandeep.kotlincoroutinesexamples.view.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.amandeep.kotlincoroutinesexamples.R
import com.amandeep.kotlincoroutinesexamples.extensions.showToast
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn.setOnClickListener { onLogin(it) }
        gotoSignupBtn.setOnClickListener { onGotoSignup(it) }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginComplete.observe(viewLifecycleOwner, Observer { isComplete ->
            if (isComplete){
                requireContext().showToast("Login successfully ")
                val action=LoginFragmentDirections.actionLoginFragmentToMainFragment()
                Navigation.findNavController(loginBtn).navigate(action)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            requireContext().showToast(error)

        })
    }

    private fun onLogin(v: View) {
        val useName=loginUsername.text.toString()
        val password=loginPassword.text.toString()
        if (useName.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(useName,password)
        }else{
            requireContext().showToast("Please fill all fields")
        }
    }

    private fun onGotoSignup(v: View){
        val action=LoginFragmentDirections.actionLoginFragmentToSignupFragment()
        Navigation.findNavController(v).navigate(action)
    }

}