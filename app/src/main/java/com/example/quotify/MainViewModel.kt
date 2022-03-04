package com.example.quotify

import android.content.Context
import android.media.session.MediaSession
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(private val context: Context) : ViewModel() {

    private var quotesList : Array<Quote> = emptyArray()


    private var index = 0

    init{
        quotesList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {

        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)  // store i/p stream in buffer
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java) // pass format of file and type of data

    }

    fun getQuote() = quotesList[index]

    fun nextQuote() = quotesList[++index % quotesList.size]

    fun previousQuote() = quotesList[(--index + quotesList.size) % quotesList.size]


}