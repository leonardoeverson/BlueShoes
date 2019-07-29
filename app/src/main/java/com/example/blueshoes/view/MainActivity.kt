package com.example.blueshoes.view

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blueshoes.R
import com.example.blueshoes.data.NavMenuItemsDataBase
import com.example.blueshoes.domain.NavMenuItem
import com.example.blueshoes.util.NavMenuItemDetailsLookup
import com.example.blueshoes.util.NavMenuItemKeyProvider
import kotlinx.android.synthetic.main.nav_menu.*

class MainActivity : AppCompatActivity() {

    val navMenuItems = NavMenuItemsDataBase(this).items
    lateinit var rv_menu_items : RecyclerView
    lateinit var selectNavMenuItems: SelectionTracker<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //navView.setNavigationItemSelectedListener(this)
    }

    private fun initNavMenuItems() {

        rv_menu_items = findViewById(R.id.rv_menu_items);
        rv_menu_items.setHasFixedSize(false)
        rv_menu_items.layoutManager = LinearLayoutManager(this)
        rv_menu_items.adapter = NavMenuItemsAdapter(navMenuItems)

        initNavMenuItemsSelection()
    }

    private fun initNavMenuItemsSelection() {

        selectNavMenuItems = SelectionTracker.Builder<Long>(
            "id-selected-items",
            rv_menu_items,
            NavMenuItemKeyProvider(navMenuItems),
            NavMenuItemDetailsLookup(rv_menu_items),
            StorageStrategy.createLongStorage()
        )
            .build()

        (rv_menu_items.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItems
    }

    private fun initNavMenuItemsLogged() {

        val rv_menu_items_logged: RecyclerView = findViewById(R.id.rv_menu_items_logged);
        rv_menu_items_logged.setHasFixedSize(false)
        rv_menu_items_logged.layoutManager = LinearLayoutManager(this)
        rv_menu_items_logged.adapter = NavMenuItemsAdapter(NavMenuItemsDataBase(this).items)

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//        when (item.itemId) {
//            R.id.nav_home -> {
//                // Handle the camera action
//            }
//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_tools -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
//        }
//
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
}
