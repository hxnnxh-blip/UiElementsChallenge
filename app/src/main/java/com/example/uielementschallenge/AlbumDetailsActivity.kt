package com.example.uielementschallenge

import android.app.AlertDialog
import android.app.ListActivity
import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList


class AlbumDetailsActivity : AppCompatActivity() {

    private var adapter: ArrayAdapter<String>? = null
    private var songList: Array<String> = arrayOf()
    private var listSong = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        title = "ALBUM DETAILS"

        val albumCover = findViewById<ImageView>(R.id.albumCover)
        val titleTxt = findViewById<TextView>(R.id.titleText)



        val songOrder = intent.extras!!.getString("position")


        when {
            songOrder.equals("Hufflepuff") -> {

                titleTxt.text = songOrder
                albumCover.setImageResource(R.drawable.hufflepuff)
                songList = arrayOf("Robin Hood", "Get You The Moon", "Before You Go", "That's us")

            }
            songOrder.equals("Gryffindor") -> {

                titleTxt.text = songOrder
                albumCover.setImageResource(R.drawable.gryffindor)
                songList = arrayOf("Last Time", "Ocean", "Glad It's you", "Moral of the Story")

            }
            songOrder.equals("Ravenclaw") -> {

                titleTxt.text = songOrder
                albumCover.setImageResource(R.drawable.ravenclaw)
                songList = arrayOf("Life is a Lie", "Who I'm Meant to Be", "Bedroom Ceiling", "Malibu Nights")

            }
            songOrder.equals("Slytherin") -> {
                titleTxt.text = songOrder
                albumCover.setImageResource(R.drawable.slyttherin)
                songList = arrayOf("Daunted", "Wrong Direction", "Control", "Creep")
            }
        }
        listSong = ArrayList()
        Collections.addAll(listSong, *songList)
        adapter = ArrayAdapter(this, R.layout.text_color, listSong)
        val productsListView = findViewById<ListView>(R.id.albumSongList)
        productsListView.adapter = adapter
        registerForContextMenu(productsListView)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        menuInflater.inflate(R.menu.delete_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.deleteSong -> {

                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo

                AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure...")
                        .setMessage("Do you want to delete the selected item..?")
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                            listSong.removeAt(info.position)
                            adapter?.notifyDataSetChanged()
                        })
                        .setNegativeButton("No", null).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}