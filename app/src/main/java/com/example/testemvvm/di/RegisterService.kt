package com.example.testemvvm.di

import com.example.testemvvm.data.models.RegisterModel
import com.example.testemvvm.data.models.ResponseBase

class RegisterService {

    private  val api : APIUser
        get() {
            return RetrofitConfig().build().create(APIUser::class.java)
        }

    fun register(request: RegisterModel.Request) : ResponseBase<RegisterModel.Response>{
        val execute = api.register(request).execute()
        if (execute.body()!= null){
            return execute.body()!!
        } else {
            throw Exception ("Erro ao cadastrar")
        }
    }
}