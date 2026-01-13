package com.example.mywebserviceapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mywebserviceapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Emulator: 10.0.2.2 -> host komputer (Windows/macOS/Linux)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") // untuk emulator
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        binding.btnSendData.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            if (name.isEmpty()) {
                binding.tvResponse.text = "Nama tidak boleh kosong"
                return@setOnClickListener
            }

            val request = PostRequest(name)

            apiService.sendData(request).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        binding.tvResponse.text = response.body()?.message ?: "OK"
                        binding.etName.setText("")
                    } else {
                        binding.tvResponse.text = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    binding.tvResponse.text = "Failure: ${t.message}"
                }
            })
        }

        binding.btnFetchData.setOnClickListener {
            apiService.getData().enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val dataList = response.body()?.data ?: emptyList()
                        val message = response.body()?.message ?: ""
                        binding.tvResponse.text = "$message\nData:\n${dataList.joinToString("\n")}"
                    } else {
                        binding.tvResponse.text = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    binding.tvResponse.text = "Failure: ${t.message}"
                }
            })
        }
    }
}
