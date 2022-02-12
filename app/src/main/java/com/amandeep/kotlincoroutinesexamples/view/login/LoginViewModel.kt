package com.amandeep.kotlincoroutinesexamples.view.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amandeep.kotlincoroutinesexamples.db.UserDataBase
import com.amandeep.kotlincoroutinesexamples.model.LoginState
import kotlinx.coroutines.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG="LoginViewModel"
    private val exceptionhandler= CoroutineExceptionHandler{coroutineContext, throwable ->

    }
    private val coroutineScope= CoroutineScope(Dispatchers.IO+exceptionhandler)
    private val db by lazy { UserDataBase(getApplication()).userDao() }

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(username: String, password: String) {
        coroutineScope.launch {
           val user= db.getUserByName(username)
            if (user!=null){
                withContext(Dispatchers.Main){
                    if (user.passwordHash==password.hashCode()) {
                        LoginState.login(user)
                        loginComplete.value = true
                    }else{
                        LoginState.loggedOut()
                        error.value="Password is not correct"
                        loginComplete.value = false
                    }
                }
            }else{
                withContext(Dispatchers.Main){
                    LoginState.loggedOut()
                    error.value="User is not registered"
                    loginComplete.value=false
                }
            }
        }
    }
}