package com.example.uielementschallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val albumsArray = arrayOf("Robin Hood", "Get You The Moon", "Before You Go", "That's us",
                                "Last Time", "Ocean", "Glad It's you", "Moral of the Story",
                                "Life is a Lie", "Who I'm Meant to Be","Bedroom Ceiling",
                                "Malibu Nights","Daunted","Wrong Direction","Control","Creep")
        val adapter = ArrayAdapter<String>(this, R.layout.text_color, albumsArray)
        val albumListView = findViewById<ListView>(R.id.albumListView)
        albumListView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.go_to_albums -> {
                startActivity(Intent(this, AlbumbsActivity::class.java))
                true
            }
            R.id.go_to_songs -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.go_to_queue ->{
                startActivity(Intent(this, QueuedSongsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.addToQueue ->{

                Toast.makeText(this, "Queued", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }

    }
}