package com.example.testemvvm.data.models

class BaseView<T> (
    var _object: T? = null,
    var message: String = "",
    var success: Boolean = false
)