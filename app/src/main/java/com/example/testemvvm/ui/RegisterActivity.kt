package com.example.testemvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testemvvm.data.models.RegisterModel
import com.example.testemvvm.data.repositories.RegisterRepository
import com.example.testemvvm.databinding.ActivityRegisterBinding
import com.example.testemvvm.utilities.MaskUtil
import com.example.testemvvm.utilities.showToastLong
import com.example.testemvvm.viewmodels.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = RegisterViewModel(RegisterRepository())

        binding.usernamePhone.addTextChangedListener(
            MaskUtil.insert(
                MaskUtil.CELLPHONE,
                binding.usernamePhone
            )
        )

        binding.usernameDocumentRegister.addTextChangedListener(
            MaskUtil.insert(
                MaskUtil.CPF,
                binding.usernameDocumentRegister
            )
        )

        binding.ButtonCadastar.setOnClickListener {
            val request = RegisterModel.Request(

                document = binding.usernameDocumentRegister.text.toString(),
                name = binding.usernameName.text.toString(),
                email = binding.usernameEmail.text.toString(),
                phone = binding.usernamePhone.text.toString(),
                birthday = binding.usernameBirthdate.text.toString(),
                password = binding.editPassword.text.toString()


            )
            if (request.document.isNullOrEmpty()) {
                this@RegisterActivity.showToastLong("Informe o documento")
            } else if (request.email.isNullOrEmpty()) {
                this@RegisterActivity.showToastLong("Informe o email")
            } else if (request.name.isNullOrEmpty()) {
                this@RegisterActivity.showToastLong("Imforme o nome")
            } else if (request.password.isNullOrEmpty()) {
                this@RegisterActivity.showToastLong("Informe um senha")
            } else if (request.phone.isNullOrEmpty()) {
                this@RegisterActivity.showToastLong("Informe o telefone")
            } else if (request.birthday.isNullOrEmpty()) {
                this@RegisterActivity.showToastLong("Informe a data de nascimento")
            } else {
                viewModel.register(request)
            }
        }


        viewModel.registerview.observe(this) {
            if (it.success) {
                this@RegisterActivity.showToastLong(it.message)
            } else {
                this@RegisterActivity.showToastLong(it.message)
            }
        }
    }
}