package com.vincentui.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vincentui.studentapp.model.Movie
import com.vincentui.studentapp.model.Student

class ListViewModelMovie(application: Application): AndroidViewModel(application) {
    val movieLD = MutableLiveData<ArrayList<Movie>>()
    val movieLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTagMovie"
    private var queue: RequestQueue?= null

    fun refresh(){
        movieLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/movie.json"
        val stringRequest = StringRequest(Request.Method.GET, url,{
            loadingLD.value = false
            Log.d("showvoley", it)
            val sType = object : TypeToken<List<Movie>>() { }.type
            val result = Gson().fromJson<List<Movie>>(it, sType)
            movieLD.value = result as ArrayList<Movie>?
            loadingLD.value = false
        },{
            Log.e("showvoley", it.toString())
            movieLoadErrorLD.value = false
            loadingLD.value = false
        })
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}