package com.aplikasi.readinglistrevisi.activities

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.aplikasi.readinglist.data.Account
import com.aplikasi.readinglist.data.EXTRA_ACCOUNT_REGISTER
import com.aplikasi.readinglistrevisi.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.add_book_custom_dialog.*
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id-example_01"
    private val notificationId = 101


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //broadcast reciver
        var AirPlaneReceiver = MyAirPlaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirPlaneReceiver, filter)

        //notification
        creatNotificationChannel()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            var Mylayout = layoutInflater.inflate(R.layout.add_book_custom_dialog,null)
            val myDialogBuilder = AlertDialog.Builder(this).apply {
                setView(Mylayout)
                setTitle("TAMBAHKAN BUKU")
            }
            var judulBuku = Mylayout.findViewById<EditText>(R.id.judulBuku)
            var penulisBuku = Mylayout.findViewById<EditText>(R.id.penulisBuku)
            var deskripsiBuku = Mylayout.findViewById<EditText>(R.id.deskripsiBuku)
            var tombolSelesai = Mylayout.findViewById<Button>(R.id.tombolSelesai)
            val myDialog = myDialogBuilder.create()

            tombolSelesai.setOnClickListener {
                sendNotification()
                myDialog.hide()
            }
            myDialog.show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    private fun creatNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Tittle"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val bitMap = BitmapFactory.decodeResource(applicationContext. resources, R.drawable.header_background_login)
        val bitMapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.header_background_login)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.header_background_login)
            .setContentTitle("Reading List APP")
            .setContentText("Berhasil Menambahkan buku bacaan baru")
            .setLargeIcon(bitMapLargeIcon)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setStyle((NotificationCompat.BigPictureStyle().bigPicture(bitMap)))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val akun = intent.getParcelableExtra<Account>(EXTRA_ACCOUNT_REGISTER)
        userName.text = akun?.Email ?: ""
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_ACCOUNT_REGISTER, userName.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        userName?.text = savedInstanceState?.getString(EXTRA_ACCOUNT_REGISTER) ?: "Kosong"
    }

}