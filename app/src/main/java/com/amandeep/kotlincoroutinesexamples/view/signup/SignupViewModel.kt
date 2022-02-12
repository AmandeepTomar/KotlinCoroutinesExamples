package com.amandeep.kotlincoroutinesexamples.view.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amandeep.kotlincoroutinesexamples.db.UserDataBase
import com.amandeep.kotlincoroutinesexamples.model.LoginState
import com.amandeep.kotlincoroutinesexamples.model.User
import kotlinx.coroutines.*

class SignupViewModel(application: Application) : AndroidViewModel(application) {
private val TAG="SignupViewModel"
    private val exceptionhandler= CoroutineExceptionHandler{coroutineContext, throwable ->

    }
    private val coroutineScope= CoroutineScope(Dispatchers.IO+exceptionhandler)
    private val db by lazy { UserDataBase(getApplication()).userDao() }

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun signup(username: String, password: String, info: String) {
        Log.e(TAG, "signup: $username password $password" )
        coroutineScope.launch {
            val user=db.getUserByName(username)
            if (user!= null){
                withContext(Dispatchers.Main) {
                    error.value = "User is already exists"
                }
            }else {
                val user=User(username,password.hashCode(),info)
                val userid=db.insertUser(user)
                user.id=userid
                LoginState.login(user)
                withContext(Dispatchers.Main){
                    signupComplete.value=true
                }
            }
        }
    }
}