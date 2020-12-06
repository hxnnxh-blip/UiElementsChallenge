package com.example.uielementschallenge

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uielementschallenge.CRUD.NewSong
import com.example.uielementschallenge.CRUD.UpdateSong
import com.example.uielementschallenge.handlers.SongsTableHandler
import com.example.uielementschallenge.models.Song
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var songsListView: ListView
    lateinit var songsTableHandler: SongsTableHandler
    lateinit var songs: MutableList<Song>
    lateinit var titles : MutableList<String>
    lateinit var adapter: ArrayAdapter<String>
    var songsArray: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsTableHandler = SongsTableHandler(this)
        songs = songsTableHandler.read()
        titles = songsTableHandler.getTitles()

        adapter = ArrayAdapter<String>(this, R.layout.text_color, songsArray)
        songsListView = findViewById<ListView>(R.id.albumListView)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)

        for (string in titles){
            songsArray.add(string)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.go_to_albums -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.go_to_songs -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.go_to_queue -> {
                startActivity(Intent(this, QueuedSongsActivity::class.java))
                true
            }
            R.id.add_a_song -> {
                startActivity(Intent(this, NewSong::class.java))
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

            R.id.addToQueue -> {

                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                albumSongs.add(songsArray[info.position])
                val snackBar = Snackbar.make(findViewById(R.id.albumListView), "Go to queued songs", Snackbar.LENGTH_SHORT)
                snackBar.setAction("Go",View.OnClickListener {
                    startActivity(Intent(this, QueuedSongsActivity::class.java))
                })
                snackBar.show()
                true
            }

            R.id.editSong -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val song_id = songs[info.position - 18].id
                val intent = Intent(applicationContext, UpdateSong::class.java)
                intent.putExtra("song_id", song_id)
                startActivity(intent)
                true
            }
            R.id.deleteSong -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val song = songs[info.position - 18]
                if (songsTableHandler.delete(song)) {
                    songsArray.removeAt(info.position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "Song was deleted.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Oops, something went wrong.", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}

val albumSongs = arrayListOf<String>()