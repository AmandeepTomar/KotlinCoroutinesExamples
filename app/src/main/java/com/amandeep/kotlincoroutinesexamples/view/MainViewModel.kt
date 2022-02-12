package com.amandeep.kotlincoroutinesexamples.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amandeep.kotlincoroutinesexamples.db.UserDataBase
import com.amandeep.kotlincoroutinesexamples.model.LoginState
import kotlinx.coroutines.*

class MainViewModel(application: Application) :AndroidViewModel(application) {
    private val TAG="MainViewModel"
    private val exceptionhandler= CoroutineExceptionHandler{coroutineContext, throwable ->

    }
    private val coroutineScope= CoroutineScope(Dispatchers.IO+exceptionhandler)
    private val db by lazy { UserDataBase(getApplication()).userDao() }

    val userDeleted = MutableLiveData<Boolean>()
    val signout = MutableLiveData<Boolean>()

    fun onSignout() {
        LoginState.loggedOut()
        signout.value=true
    }

    fun onDeleteUser() {
        coroutineScope.launch {
            LoginState.user?.let {
                db.deleteUser(it)
                withContext(Dispatchers.Main){
                    LoginState.loggedOut()
                    signout.value=true
                }
            }
        }
    }
}