package com.example.apinyt

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    private fun getEmailPopular(){
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<Respuesta> = getRetrofit().create(APIService::class.java).getResponse("emailed/7.json?api-key=QAZ1Wvu3QIlniUfYfo7tvGAmGB8r4lui")
            val nytResponse: Respuesta? = call.body()
            runOnUiThread {
                if (nytResponse != null) {
                    findViewById<TextView>(R.id.tvTitle).text = nytResponse.results.first().title
                }
            }
        }
    }

    fun obtenerTitulo(view: View) {
        getEmailPopular()
    }
}