package com.example.uielementschallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import com.example.uielementschallenge.handlers.AlbumsTableHandler
import com.example.uielementschallenge.models.Album

class AlbumsActivity : AppCompatActivity() {

    var albums = arrayOf("Hufflepuff" , "Gryffindor" , "Ravenclaw", "Slytherin")
    var covers = intArrayOf(R.drawable.hufflepuff , R.drawable.gryffindor,
            R.drawable.ravenclaw, R.drawable.slyttherin)
    var titlesArray: ArrayList<String> = ArrayList()
    var albumsTableHandler = AlbumsTableHandler(this)
    lateinit var album: MutableList<Album>
    lateinit var adapter: ArrayAdapter<String>
    lateinit var titles : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        
        title = "ALBUMS"

        albums = albumsTableHandler.read()
        titles = albumsTableHandler.getTitles()

        val gridView = findViewById<GridView>(R.id.imageGridView)
        val mainAdapter = ImageAdapter(this, albums, covers)
        gridView.adapter = mainAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, AlbumDetailsActivity::class.java)
            intent.putExtra("position", albums[position])
            startActivity(intent)
        }


        for(i in albums){
            titlesArray.add(i)
        }

        for (string in titles){
            titlesArray.add(string)
        }

    }
}

