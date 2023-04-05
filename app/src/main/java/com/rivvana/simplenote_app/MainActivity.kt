package com.rivvana.simplenote_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.rivvana.simplenote_app.databinding.ActivityEditBinding
import com.rivvana.simplenote_app.databinding.ActivityMainBinding
import com.rivvana.simplenote_app.room.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy {
        NoteDB(this)
    }
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        setupRecycleView()
    }

    private fun setupRecycleView() {
        noteAdapter = NoteAdapter(arrayListOf())
        binding.listNote.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = noteAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            Log.d("Response", "$notes")
            withContext(Dispatchers.Main){
                noteAdapter.setData(notes)
            }
        }
    }

    private fun setupListener() {
        binding.buttonCreate.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }
}