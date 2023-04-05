package com.rivvana.simplenote_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rivvana.simplenote_app.databinding.ActivityEditBinding
import com.rivvana.simplenote_app.room.Note
import com.rivvana.simplenote_app.room.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy {
        NoteDB(this)
    }
    private lateinit var binding : ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
    }

    private fun setupListener() {
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(
                        0, binding.editTitle.text.toString(), binding.editNote.text.toString()
                    )
                )
                Log.d("Sukses", "Data berhasil ditambahkan")
                Log.d("Input", binding.editTitle.text.toString())
                finish()
            }
        }
    }
}