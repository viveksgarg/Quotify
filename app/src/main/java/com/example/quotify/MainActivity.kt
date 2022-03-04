package com.example.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.quotify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this,ViewModelFactory(applicationContext))
            .get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())

        binding.next.setOnClickListener{
            setQuote(mainViewModel.nextQuote())
        }
        binding.previous.setOnClickListener{
            setQuote(mainViewModel.previousQuote())
        }

        binding.floatingActionButton.setOnClickListener{

            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
            startActivity(intent)
        }


    }

    private fun setQuote(quote: Quote){
        binding.quoteText.text = quote.text
        binding.quoteAuthor.text = quote.author
    }


}