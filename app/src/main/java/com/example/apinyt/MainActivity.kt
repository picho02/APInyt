package com.example.apinyt

import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private val articulos2 = mutableListOf<Resultado>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        getEmailPopular()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    private fun getEmailPopular(){
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<Respuesta> = getRetrofit().create(APIService::class.java).getResponse("emailed/7.json?api-key=QAZ1Wvu3QIlniUfYfo7tvGAmGB8r4lui")
            val nytResponse: Respuesta? = call.body()
            val articulos: List<Resultado> = nytResponse?.results ?: emptyList()
            runOnUiThread {
                if(call.isSuccessful()) {
                    if (nytResponse != null) {
                        articulos2.clear()
                        articulos2.addAll(articulos)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    /*fun obtenerTitulo(view: View) {
        getEmailPopular()
    }*/
    fun initRecycler(){
        adapter= Adapter(articulos2)
        findViewById<RecyclerView>(R.id.rvMain).layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        findViewById<RecyclerView>(R.id.rvMain).adapter = adapter

    }
}