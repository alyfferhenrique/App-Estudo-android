package com.example.testemvvm.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testemvvm.data.models.Login
import com.example.testemvvm.viewmodels.LoginViewModel
import com.example.testemvvm.data.repositories.UserRepository
import com.example.testemvvm.databinding.ActivityMainBinding
import com.example.testemvvm.utilities.MaskUtil
import com.example.testemvvm.utilities.showToastLong

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.editDocument.addTextChangedListener(MaskUtil.insert(MaskUtil.CPF, binding.editDocument))

        viewModel = LoginViewModel(UserRepository())


        viewModel.getAll()

        binding.ButtonCadastar.setOnClickListener{

            val i = Intent(this, RegisterActivity :: class.java)
            startActivity(i)
            finish()
        }

        binding.buttonEntrar.setOnClickListener{
            val request = Login.Request(
                binding.editDocument.text.toString(),
                binding.editPassword.text.toString()
            )
            if(request.checkedValues()) {
                viewModel.login(request)
            } else {
                this@LoginActivity.showToastLong( "Informe os campos corretamente, meu truta")
            }


        }

        /*viewModel.getAllView.observe(this) {
            if (it.success) {
                //this@LoginActivity.showToastLong("${it.message} Total usuarios: ${it._object!!.data!!.size}")

                var usuarios : String = "Usuários \n \n"
                for(user in it._object!!.data!!) {
                    usuarios += "Nome: ${user.name}\nEmail: ${user.email} \n --------------------- \n"
                }

               // this@LoginActivity.showToastLong(usuarios)
                binding.txtname.text = usuarios
                // ir outra tela
            } else {
                this@LoginActivity.showToastLong(it.message)
                //Toast.makeText(this@LoginActivity, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }*/

        viewModel.loginview.observe(this) {
            if (it.success) {
                this@LoginActivity.showToastLong(it.message)
                // ir outra tela
            } else {
                this@LoginActivity.showToastLong(it.message)
                //Toast.makeText(this@LoginActivity, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



