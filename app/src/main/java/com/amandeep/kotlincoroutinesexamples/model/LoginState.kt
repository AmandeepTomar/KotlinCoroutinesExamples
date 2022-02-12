package com.amandeep.kotlincoroutinesexamples.model

object LoginState {
    var isLoggedIn=false
    var user:User?=null

    fun loggedOut(){
        isLoggedIn=false
        user=null
    }

    fun login(user: User){
        isLoggedIn=true
        this.user=user
    }
}