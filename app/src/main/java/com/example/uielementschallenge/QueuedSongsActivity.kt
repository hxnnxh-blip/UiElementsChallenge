package com.example.uielementschallenge

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class QueuedSongsActivity : AppCompatActivity() {
    lateinit var notificationManage: NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        title = "QUEUED SONGS"

        notificationManage = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        adapter = ArrayAdapter(this, R.layout.text_color, albumSongs)
        val songsListView = findViewById<ListView>(R.id.songsQueued)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)

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
                val listItem: String = albumSongs[info.position]

                AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure...")
                        .setMessage("Do you want to delete the selected item..?")
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                            albumSongs.removeAt(info.position)
                            adapter?.notifyDataSetChanged()
                            Toast.makeText(this, "Successfully removed: $listItem",Toast.LENGTH_LONG).show()
                            if (albumSongs.isEmpty()){
                                setNotif()
                            }
                        })
                        .setNegativeButton("No", null).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun setNotif(){

        val intent = Intent(applicationContext, NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext,0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                notificationChannel = NotificationChannel(
                        channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManage.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this,channelId)
                        .setContentTitle("ALERT")
                        .setContentText("Queued songs empty!")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
            } else{

                builder = Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
            }
            notificationManage.notify(1234,builder.build())
        }
}