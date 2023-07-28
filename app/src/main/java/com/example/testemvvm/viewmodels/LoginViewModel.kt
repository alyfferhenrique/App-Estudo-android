package com.example.testemvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testemvvm.data.models.BaseView
import com.example.testemvvm.data.models.ResponseBase
import com.example.testemvvm.data.models.Login
import com.example.testemvvm.data.repositories.UserRepository
import io.reactivex.observers.DisposableObserver

class LoginViewModel (var userRepository : UserRepository): ViewModel() {

    private val _loginview = MutableLiveData<BaseView<ResponseBase<Login.UserResponse>>>()
    var loginview : LiveData<BaseView<ResponseBase<Login.UserResponse>>> = _loginview


    private val _getAllview = MutableLiveData<BaseView<ResponseBase<ArrayList<Login.UserResponse>>>>()
    var getAllView : LiveData<BaseView<ResponseBase<ArrayList<Login.UserResponse>>>> = _getAllview

    //private val _registerview = MutableLiveData<BaseView<List<Login.Response>>>()
   // var registerview : LiveData<BaseView<List<Login.Response>>> = _registerview

    fun login(request : Login.Request){

        userRepository.login(request, object : DisposableObserver<ResponseBase<Login.UserResponse>>(){
                override fun onNext(data: ResponseBase<Login.UserResponse>) {
                    _loginview.value = BaseView(_object = data, message = "Autenticacao realizada com sucesso", success = true)
                }








                override fun onError(e: Throwable) {
                    if(e.message.isNullOrEmpty()) {
                        _loginview.value = BaseView(null, "Nao foi possivel realizar o login, meu trutão", false)
                    } else {
                        _loginview.value = BaseView(null, e.message!!, false)
                    }
                }

                override fun onComplete() {

                }
            })
        }


    fun getAll(){

        userRepository.getAll(object : DisposableObserver<ResponseBase<ArrayList<Login.UserResponse>>>(){
            override fun onNext(data: ResponseBase<ArrayList<Login.UserResponse>>) {
                _getAllview.value = BaseView(_object = data, message = "Usuarios carregados com sucesso", success = true)
            }

            override fun onError(e: Throwable) {
                if(e.message.isNullOrEmpty()) {
                    _getAllview.value = BaseView(null, "Nao foi possivel buscar os usuarios, meu trutão", false)
                } else {
                    _getAllview.value = BaseView(null, e.message!!, false)
                }
            }

            override fun onComplete() {

            }
        })
    }

    }

