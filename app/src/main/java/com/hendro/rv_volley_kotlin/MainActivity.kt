package com.hendro.rv_volley_kotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.hendro.rv_volley_kotlin.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mealList = ArrayList<Meal>()
    private lateinit var mealAdapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setRV()

        binding.swipeContainer.setOnRefreshListener(OnRefreshListener {
            getData()

            Handler(Looper.getMainLooper()).postDelayed(
                { binding.swipeContainer.setRefreshing(false) },
                3000
            ) // Delay in millis
        })

        // Configure the refreshing colors
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
    }

    public fun setRV(){
        mealAdapter = MealAdapter(mealList)
        val layoutManager = GridLayoutManager(applicationContext, 2)
        binding.rv.layoutManager = layoutManager
        binding.rv.itemAnimator = DefaultItemAnimator()
        binding.rv.adapter = mealAdapter

        getData()
    }

    fun getData(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.optJSONArray("meals")
                var id: String
                var meal: String
                var image: String

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    id = jsonObject.optString("idMeal")
                    meal = jsonObject.optString("strMeal")
                    image = jsonObject.optString("strMealThumb")

                    mealList.add(Meal(id, meal, image))
                }

                mealAdapter.notifyDataSetChanged()
            },
            {
                Toast.makeText(this@MainActivity, "Koneksi: Gagal terhubung", Toast.LENGTH_SHORT).show()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}