package com.example.testemvvm.data.repositories

import com.example.testemvvm.data.models.Login
import com.example.testemvvm.data.models.RegisterModel
import com.example.testemvvm.data.models.ResponseBase
import com.example.testemvvm.di.RegisterService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlin.math.E

class RegisterRepository {

    private val service: RegisterService get() {return RegisterService() }

    private fun register (request: RegisterModel.Request) : Single<ResponseBase<RegisterModel.Response>>{
        return Single.create {
            try {
                val response =  service.register(request)
                when{
                    response!!.success!! ->{
                        it.onSuccess(response)
                    }else ->{
                        it.onError(Exception(response.message))

                    }
                }
            }catch (ec : Exception){
                it.onError(Exception("Vai trabalhar meu truta"))
            }
        }
    }


    fun register(request: RegisterModel.Request, observer: DisposableObserver<ResponseBase<RegisterModel.Response>>){
        register(request)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}