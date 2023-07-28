package com.example.testemvvm.di

import com.example.testemvvm.data.models.Login
import com.example.testemvvm.data.models.ResponseBase

class UserService {

    private val api: APIUser
        get() {
        return RetrofitConfig().build().create(APIUser::class.java)
    }

    fun login(request: Login.Request) : ResponseBase<Login.UserResponse>?{
        val execute = api.login(request).execute()
        if (execute.body() != null){
            return execute.body()
        } else{
            throw Exception("Não deu certo meu trutão, Aguarde....")
        }
    }


    fun getAll() : ResponseBase<ArrayList<Login.UserResponse>>?{
        val execute = api.getAll().execute()
        if (execute.body() != null){
            return execute.body()
        } else{
            throw Exception("Não deu certo meu trutão, Aguarde....")
        }
    }
}