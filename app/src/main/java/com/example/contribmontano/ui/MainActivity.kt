package com.example.contribmontano.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contribmontano.R
import com.example.contribmontano.adapter.ReposAdapter
import com.example.contribmontano.databinding.ActivityMainBinding
import com.example.contribmontano.viewmodel.MontanoViewModel
import com.jarvis.ca.Mark

class MainActivity : AppCompatActivity() {
    private lateinit var model: MontanoViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        this.model = ViewModelProvider(this).get(MontanoViewModel::class.java)

        this.model.getRepositories().observe(this, Observer {
            if (it.isEmpty()) {
                Mark.showAlertError(this, getString(R.string.type_repository_name))
            } else {
                binding.recycler.adapter = ReposAdapter(it)
            }
        })

        binding.btSearch.setOnClickListener { getRepositories() }

        val layoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.addItemDecoration(
            DividerItemDecoration(this, layoutManager.orientation)
        )
    }

    private fun getRepositories() {
        if (binding.etName.text.isNotEmpty()) {
            this.model.requestRepositories(binding.etName.text.toString())
        } else {
            Mark.showAlertError(this, getString(R.string.type_repository_name))
        }
    }
}