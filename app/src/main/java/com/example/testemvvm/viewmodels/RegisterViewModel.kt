package com.example.testemvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testemvvm.data.models.BaseView
import com.example.testemvvm.data.models.RegisterModel
import com.example.testemvvm.data.models.ResponseBase
import com.example.testemvvm.data.repositories.RegisterRepository
import io.reactivex.observers.DisposableObserver

class RegisterViewModel (var registerRepository: RegisterRepository) : ViewModel() {

    private val _registerview = MutableLiveData<BaseView<ResponseBase<RegisterModel.Response>>>()
    val registerview: LiveData<BaseView<ResponseBase<RegisterModel.Response>>> = _registerview

    fun register(request: RegisterModel.Request) {
        registerRepository.register(request, object : DisposableObserver<ResponseBase<RegisterModel.Response>>() {
            override fun onNext(data: ResponseBase<RegisterModel.Response>) {
                _registerview.value =
                    BaseView(_object = data, message = "Cadastro realizado com sucesso", success = true)
            }

            override fun onError(e: Throwable) {
                if (e.message.isNullOrEmpty()) {
                    _registerview.value = BaseView(null, "Não foi possivel realizar o login", false)
                } else {
                    _registerview.value = BaseView(null, e.message!!, false)
                }
            }

            override fun onComplete() {

            }
        })
    }
}

