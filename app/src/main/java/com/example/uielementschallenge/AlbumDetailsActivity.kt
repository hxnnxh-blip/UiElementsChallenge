package com.example.uielementschallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        title = "ALBUM DETAILS"

        val albumCover = findViewById<ImageView>(R.id.albumCover)
        val titleTxt = findViewById<TextView>(R.id.titleText)

        var songList: Array<String> = arrayOf()
        val songOrder = intent.extras!!.getString("position")


        if (songOrder.equals("Hufflepuff")) {

            titleTxt.text = songOrder
            albumCover.setImageResource(R.drawable.hufflepuff)
            songList = arrayOf("Robin Hood", "Get You The Moon", "Before You Go", "That's us")

        } else if (songOrder.equals("Gryffindor")) {

            titleTxt.text = songOrder
            albumCover.setImageResource(R.drawable.gryffindor)
            songList = arrayOf("Last Time", "Ocean", "Glad It's you", "Moral of the Story")

        } else if (songOrder.equals("Ravenclaw")) {

            titleTxt.text = songOrder
            albumCover.setImageResource(R.drawable.ravenclaw)
            songList = arrayOf("Life is a Lie", "Who I'm Meant to Be", "Bedroom Ceiling", "Malibu Nights")

        } else if (songOrder.equals("Slytherin")) {
            titleTxt.text = songOrder
            albumCover.setImageResource(R.drawable.slyttherin)
            songList = arrayOf("Daunted", "Wrong Direction", "Control", "Creep")
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songList)
        val productsListView = findViewById<ListView>(R.id.albumSongList)
        productsListView.adapter = adapter

    }
}