package com.aplikasi.readinglistrevisi.activities

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
import com.aplikasi.readinglist.data.Account
import com.aplikasi.readinglist.data.EXTRA_ACCOUNT_REGISTER
import com.aplikasi.readinglistrevisi.R
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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