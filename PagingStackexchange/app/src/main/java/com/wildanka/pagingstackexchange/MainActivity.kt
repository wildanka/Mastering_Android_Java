package com.wildanka.pagingstackexchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wildanka.pagingstackexchange.model.entity.Items
import com.wildanka.pagingstackexchange.view.StackItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        rv_stack_item.layoutManager = LinearLayoutManager(this)
        rv_stack_item.setHasFixedSize(true)

        val adapter: StackItemAdapter = StackItemAdapter(this)
        viewModel.itemPagedList.observe(this, Observer { items ->
            adapter.submitList(items)
        })

        rv_stack_item.adapter = adapter
    }
}
