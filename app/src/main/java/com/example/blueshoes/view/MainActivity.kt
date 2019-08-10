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
import android.view.View
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blueshoes.R
import com.example.blueshoes.data.NavMenuItemsDataBase
import com.example.blueshoes.domain.NavMenuItem
import com.example.blueshoes.domain.User
import com.example.blueshoes.util.NavMenuItemDetailsLookup
import com.example.blueshoes.util.NavMenuItemKeyProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_user_logged.*
import kotlinx.android.synthetic.main.nav_header_user_not_logged.*
import kotlinx.android.synthetic.main.nav_menu.*

class MainActivity : AppCompatActivity() {

    val user = User("Leonardo", R.drawable.user, false)

    lateinit var rv_menu_items : RecyclerView

    lateinit var navMenuItems : List<NavMenuItem>
    lateinit var selectNavMenuItems: SelectionTracker<Long>

    lateinit var navMenuItemsLogged : List<NavMenuItem>
    lateinit var selectNavMenuItemsLogged: SelectionTracker<Long>

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

    /*
 * Método responsável por esconder itens do menu gaveta de
 * acordo com o status do usuário (conectado ou não).
 * */
    private fun showHideNavMenuViews(){
        if( user.status ){ /* Conectado */
            rl_header_user_not_logged.visibility = View.GONE
            fillUserHeaderNavMenu()
        }
        else{  /* Não conectado */
            rl_header_user_logged.visibility = View.GONE
            v_nav_vertical_line.visibility = View.GONE
            rv_menu_items_logged.visibility = View.GONE
        }
    }

    private fun fillUserHeaderNavMenu(){
        if( user.status ) { /* Conectado */
            iv_user.setImageResource(user.image)
            tv_user.text = user.name
        }
    }

    private fun initNavMenu(savedInstanceState: Bundle?){

        val navMenu = NavMenuItemsDataBase(this)
        navMenuItems = navMenu.items
        navMenuItemsLogged = navMenu.itemsLogged

        initNavMenuItems()
        initNavMenuItemsLogged()

        if(savedInstanceState != null) {
            selectNavMenuItems.onRestoreInstanceState(savedInstanceState)
            selectNavMenuItemsLogged.onRestoreInstanceState(savedInstanceState)
        }else{
            selectNavMenuItems.select(R.id.item_all_shoes.toLong())
        }
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

        selectNavMenuItems.addObserver(
            SelectObserverNavMenuItems({
                selectNavMenuItemsLogged.selection.filter {
                    selectNavMenuItemsLogged.deselect(it)
                }
            })
        )

        (rv_menu_items.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItems
    }

    private fun initNavMenuItemsLogged() {

        val rv_menu_items_logged: RecyclerView = findViewById(R.id.rv_menu_items_logged);
        rv_menu_items_logged.setHasFixedSize(false)
        rv_menu_items_logged.layoutManager = LinearLayoutManager(this)
        rv_menu_items_logged.adapter = NavMenuItemsAdapter(navMenuItemsLogged)

        initNavMenuItemsLoggedSelection()
    }

    private fun initNavMenuItemsLoggedSelection() {

        var rv_menu_items_logged = rv_menu_items_logged

        selectNavMenuItemsLogged = SelectionTracker.Builder<Long>(
            "id-selected-items-logged",
            rv_menu_items_logged as RecyclerView,
            NavMenuItemKeyProvider(navMenuItemsLogged),
            NavMenuItemDetailsLookup(rv_menu_items_logged as RecyclerView),
            StorageStrategy.createLongStorage()
        )
            .build()

        selectNavMenuItemsLogged.addObserver(
            SelectObserverNavMenuItems({
                selectNavMenuItems.selection.filter {
                    selectNavMenuItems.deselect(it)
                }
            })
        )

        (rv_menu_items_logged.adapter as NavMenuItemsAdapter).selectionTracker =  selectNavMenuItemsLogged
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        selectNavMenuItems.onRestoreInstanceState(outState!!)
        selectNavMenuItemsLogged.onRestoreInstanceState(outState!!)
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

    inner class SelectObserverNavMenuItems(
        val callbackRemoveSelection: ()->Unit
    ) : SelectionTracker.SelectionObserver<Long>(){

        /*
         * Método responsável por permitir que seja possível
         * disparar alguma ação de acordo com a mudança de
         * status de algum item em algum dos objetos de seleção
         * de itens de menu gaveta. Aqui vamos proceder com
         * alguma ação somente em caso de item obtendo seleção,
         * para item perdendo seleção não haverá processamento,
         * pois este status não importa na lógica de negócio
         * deste método.
         * */
        override fun onItemStateChanged(
            key: Long,
            selected: Boolean ) {
            super.onItemStateChanged( key, selected )

            /*
             * Padrão Cláusula de Guarda para não seguirmos
             * com o processamento em caso de item perdendo
             * seleção. O processamento posterior ao condicional
             * abaixo é somente para itens obtendo a seleção,
             * selected = true.
             * */
            if( !selected ){
                return
            }

            /*
             * Para garantir que somente um item de lista se
             * manterá selecionado, é preciso acessar o objeto
             * de seleção da lista de itens de usuário conectado
             * para então remover qualquer possível seleção
             * ainda presente nela. Sempre haverá somente um
             * item selecionado, mas infelizmente o método
             * clearSelection() não estava respondendo como
             * esperado, por isso a estratégia a seguir.
             * */
            callbackRemoveSelection()

            /*
             * TODO: Mudança de Fragment
             * */

            /*
             * Fechando o menu gaveta.
             * */
            drawer_layout.closeDrawer( GravityCompat.START )
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
