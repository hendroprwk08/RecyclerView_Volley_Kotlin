package com.hendro.rv_volley_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.hendro.rv_volley_kotlin.databinding.ActivityDetailBinding
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val i = intent
        val id = i.getStringExtra("i_idMeal")

        load(id)

        //back button
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun load(id: String?) {
        val queue = Volley.newRequestQueue(this@DetailActivity)
        val url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i="+ id

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.optJSONArray("meals")

                binding.tvName.text = jsonArray.getJSONObject(0).getString("strMeal")
                binding.tvInstruction.text = jsonArray.getJSONObject(0).getString("strInstructions")

                Glide.with(this@DetailActivity)
                    .load(jsonArray.getJSONObject(0).getString("strMealThumb"))
                    .into(binding.ivImage)
            },
            Response.ErrorListener {
                Toast.makeText(this@DetailActivity, "Koneksi: Gagal terhubung", Toast.LENGTH_SHORT).show()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

}