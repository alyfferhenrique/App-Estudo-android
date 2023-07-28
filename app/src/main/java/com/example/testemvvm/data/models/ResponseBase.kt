package com.example.testemvvm.data.models

import com.google.gson.annotations.SerializedName

class ResponseBase <T> {
    @SerializedName("Sucesso")
    var success: Boolean? = false

    @SerializedName("Mensagem")
    var message: String? = ""

    @SerializedName("Data")
    var data: T? = null
}