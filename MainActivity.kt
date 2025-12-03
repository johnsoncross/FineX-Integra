package com.example.financeapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.network.ApiService
import com.example.financeapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchMarketData()
    }

    private fun fetchMarketData() {
        val apiService = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val call = apiService.getMarketData()

        call.enqueue(object : Callback<MarketData> {
            override fun onResponse(call: Call<MarketData>, response: Response<MarketData>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    val marketData = response.body()
                    // Update UI with market data
                } else {
                    // Handle API error
                    showToast("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MarketData>, t: Throwable) {
                // Handle network error
                showToast("Network Error: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
