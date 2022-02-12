package com.amandeep.kotlincoroutinesexamples.view.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.amandeep.kotlincoroutinesexamples.R
import com.amandeep.kotlincoroutinesexamples.extensions.showToast
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment: Fragment(R.layout.fragment_signup) {
    private val TAG="SignupFragment"

    private val viewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupBtn.setOnClickListener { onSignup(it) }
        gotoLoginBtn.setOnClickListener { onGotoLogin(it) }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signupComplete.observe(viewLifecycleOwner) { isComplete ->
            if (isComplete) {
                requireContext().showToast("Signup is complete")
                //SignupFragment
               val action= SignupFragmentDirections.actionSignupFragmentToMainFragment()
                Navigation.findNavController(signupBtn).navigate(action)
            }

        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Log.e(TAG, "observeViewModel: error")
            requireContext().showToast(error)
        }
    }

    private fun onSignup(v: View){
        val useName=signupUsername?.text.toString()
        val usePassword=signupPassword?.text.toString()
        val useInfo=otherInfo?.text.toString()

        if (useName.isNotEmpty() && usePassword.isNotEmpty() && useInfo.isNotEmpty()){
            viewModel.signup(useName,usePassword,useInfo)
        }else{
            requireContext().showToast("Please provide all information")
        }

    }

    private fun onGotoLogin(v: View) {
        val action=SignupFragmentDirections.actionSignupFragmentToLoginFragment()
        Navigation.findNavController(v).navigate(action)
    }
}