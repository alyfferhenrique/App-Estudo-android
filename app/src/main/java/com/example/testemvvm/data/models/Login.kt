package com.example.testemvvm.data.models

import com.google.gson.annotations.SerializedName

sealed class Login {

    class Request (
            @SerializedName("CPF")
            var document: String? = null,

            @SerializedName("Senha")
            var password: String? = null
        ) {

        fun checkedValues(): Boolean {
            return (document.isNullOrEmpty() || password.isNullOrEmpty()).not()
        }
    }


    class UserResponse {
        @SerializedName("IdUsuario")
        var _id: Long? = 0

        @SerializedName("CPF")
        var document: String? = ""

        @SerializedName("Nascimento")
        var birthDate: String? = ""

        @SerializedName("Nome")
        var name: String = ""

        @SerializedName("Email")
        var email: String = ""

        @SerializedName("Telefone")
        var telefone: String = ""

        @SerializedName("ImagemUsuario")
        var imageProfile: String = ""
    }

}