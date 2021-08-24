package com.test.mvvmlearn

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.test.mvvmlearn.network.Character

class MainActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(CharacterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.characterLiveData.observe(this, {
            processCharacterResponse(it)
        })

    }

    private fun processCharacterResponse(state: ScreenState<List<Character>?>) {

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        when (state) {
            is ScreenState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                progressBar.visibility = View.GONE
                if (state.data != null) {
                    val adapter = CharacterAdapter(state.data)
                    val recyclerView = findViewById<RecyclerView>(R.id.rv_character)
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                    recyclerView.adapter = adapter
                }
            }
            is ScreenState.Error -> {
                progressBar.visibility = View.GONE

                val view = progressBar.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}