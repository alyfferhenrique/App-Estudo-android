package com.example.testemvvm.data.models

import com.google.gson.annotations.SerializedName
import java.net.PasswordAuthentication

sealed class RegisterModel {

    class Request(

        @SerializedName("CPF")
        var document: String? = null,
        @SerializedName("Email")
        var email: String? = null,
        @SerializedName("ImagemUsuario")
        var imagemUsuario: String? = null,
        @SerializedName("Nascimento")
        var birthday: String? = null,
        @SerializedName("Nome")
        var name: String? = null,
        @SerializedName("Senha")
        var password: String? = null,
        @SerializedName("Telefone")
        var phone: String? = null
    )

    {

    }

    class Response (

        @SerializedName("IdUsuario")
        var _id: Long? = 0,

        @SerializedName("CPF")
        var document: String? = "",

        @SerializedName("Nascimento")
        var birthDate: String? = "",

        @SerializedName("Nome")
        var name: String = "",

        @SerializedName("Email")
        var email: String = "",

        @SerializedName("Telefone")
        var telefone: String = "",

        @SerializedName("ImagemUsuario")
        var imageProfile: String = "",
        )
}