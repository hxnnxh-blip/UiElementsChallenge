package com.example.uielementschallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get

class QueuedSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        title = "QUEUED SONGS"

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumSongs)
        val songsListView = findViewById<ListView>(R.id.songsQueued)
        songsListView.adapter = adapter
    }
}