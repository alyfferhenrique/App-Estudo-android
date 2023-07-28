package com.example.testemvvm.di
import com.example.testemvvm.BuildConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    fun build() : Retrofit {
        val lInterceptor = HttpLoggingInterceptor()
        lInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level
            .NONE

        val lBuild = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(lInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://app-cargas-brasil-2c01d74fd783.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(lBuild)
            .build()
    }

    companion object {
        fun getParams(aRequest: Any?): Map<String, String> {
            val lJson = Gson().toJson(aRequest)
            val lType = object :
                TypeToken<Map<String?, String?>?>() {}.type
            return Gson().fromJson(
                lJson,
                lType
            )
        }
    }
}

