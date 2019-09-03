package com.wildanka.pagingstackexchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wildanka.pagingstackexchange.model.entity.Items

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.fetchStackAnswer().observe(this, Observer<List<Items?>>{ response ->
            if (response != null) {
                Toast.makeText(this@MainActivity, response.size.toString(), Toast.LENGTH_LONG ).show()
            }
        })
    }
}
