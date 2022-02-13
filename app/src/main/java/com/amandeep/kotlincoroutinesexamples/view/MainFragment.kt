package com.amandeep.kotlincoroutinesexamples.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.amandeep.kotlincoroutinesexamples.R
import com.amandeep.kotlincoroutinesexamples.extensions.showToast
import com.amandeep.kotlincoroutinesexamples.model.LoginState
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by lazy {

        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signoutBtn.setOnClickListener { onSignout() }
        deleteUserBtn.setOnClickListener { onDelete() }
        showNewsBtn.setOnClickListener {
           val action= MainFragmentDirections.actionMainFragmentToNewsFeedsFragment()
            Navigation.findNavController(showNewsBtn).navigate(action)
        }
        usernameTV.text= LoginState.user?.username


        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(viewLifecycleOwner, Observer {
            if (it) {
                requireContext().showToast("User Signed Out")
                signOutDirection()
            }

        })
        viewModel.userDeleted.observe(viewLifecycleOwner, Observer {
            signOutDirection()
        })
    }

    private fun onSignout() {
        viewModel.onSignout()
    }

    private fun onDelete() {
        requireActivity().also {
            AlertDialog.Builder(it)
                .setTitle("Delete User")
                .setMessage("Are you sure want to delete the user")
                .setPositiveButton("Yes") { dialog, a ->
                    viewModel.onDeleteUser()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, a ->
                    dialog.dismiss()
                }.show()
        }
    }

    private fun signOutDirection() {
        val action = MainFragmentDirections.actionMainFragmentToSignupFragment()
        Navigation.findNavController(signoutBtn).navigate(action)
    }

}