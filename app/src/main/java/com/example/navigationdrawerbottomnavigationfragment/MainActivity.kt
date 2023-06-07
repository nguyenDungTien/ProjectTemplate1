package com.example.navigationdrawerbottomnavigationfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.navigationdrawerbottomnavigationfragment.Fragment.FavoriteFragment
import com.example.navigationdrawerbottomnavigationfragment.Fragment.HistoryFragment
import com.example.navigationdrawerbottomnavigationfragment.Fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var mDrawerLayout:DrawerLayout
    private val FRAGMENT_HOME=0
    private val FRAGMENT_FAVORITE=1
    private val FRAGMENT_HISTORY=2
    private var mCurrentFragment=FRAGMENT_HOME
    private lateinit var mBottomNavigationView:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerLayout=findViewById(R.id.draw_layout)
        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val toggle=ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.navigation_drawer_oper,R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        //bắt sụ kiên khí click items của navigationView
        val navigationView=findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)
        replaceFragment(HomeFragment())
        navigationView.menu.findItem(R.id.nav_home).isChecked = true
        mBottomNavigationView=findViewById(R.id.bottom_navigation)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id=item.itemId
        if (id==R.id.nav_home){
            if (mCurrentFragment!=FRAGMENT_HOME){
                replaceFragment(HomeFragment())
                mCurrentFragment=FRAGMENT_HOME
            }
        }else if (id==R.id.nav_favorite){
            if (mCurrentFragment!=FRAGMENT_FAVORITE) {
                replaceFragment(FavoriteFragment())
                mCurrentFragment = FRAGMENT_FAVORITE
            }
        }else if (id==R.id.nav_history){
                if (mCurrentFragment!=FRAGMENT_HISTORY) {
                    replaceFragment(HistoryFragment())
                    mCurrentFragment = FRAGMENT_HISTORY
                }
        }
        //logic dóng navigation_view
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
    fun replaceFragment(fragment:Fragment){
        val transition= supportFragmentManager.beginTransaction()
        transition.replace(R.id.content_frame,fragment)
        transition.commit()
    }
}