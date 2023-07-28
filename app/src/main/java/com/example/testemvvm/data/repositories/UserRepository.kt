package com.example.testemvvm.data.repositories

import com.example.testemvvm.data.models.Login
import com.example.testemvvm.data.models.ResponseBase
import com.example.testemvvm.di.UserService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class UserRepository {

    private val service: UserService get() {return UserService() }

    private fun login(request: Login.Request) : Single<ResponseBase<Login.UserResponse>>{
        return Single.create {
            try {
                val response = service.login(request)
                when{
                    response!!.success!! ->{
                        it.onSuccess(response)
                    } else -> {
                        it.onError(Exception(response.message))
                    }
                }
            } catch (ex: Exception){
                it.onError(Exception("Não foi possivel autenticar o usuário meu truta"))
            }
        }
    }

    fun login(request: Login.Request, observer: DisposableObserver<ResponseBase<Login.UserResponse>>){
        login(request)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }


    private fun getAll() : Single<ResponseBase<ArrayList<Login.UserResponse>>>{
        return Single.create {
            try {
                val response = service.getAll()
                when{
                    response!!.success!! ->{
                        it.onSuccess(response)
                    } else -> {
                    it.onError(Exception(response.message))
                }
                }
            } catch (ex: Exception){
                it.onError(Exception("Não foi possivel autenticar o usuário meu truta"))
            }
        }
    }

    fun getAll(observer: DisposableObserver<ResponseBase<ArrayList<Login.UserResponse>>>){
        getAll()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}