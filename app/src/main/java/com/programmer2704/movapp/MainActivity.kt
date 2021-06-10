package com.programmer2704.movapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.programmer2704.movapp.forfragment.DefaultFragmentFactory
import com.programmer2704.movapp.forfragment.DefaultFragmentFactoryEntryPoint
import com.programmer2704.movapp.view.viewmodel.PopularViewmodel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    private lateinit var b: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val popularVM: PopularViewmodel by viewModels()

    @Inject
    lateinit var fragmentFactory: DefaultFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        val entryPoint = EntryPointAccessors.fromActivity(
            this,
            DefaultFragmentFactoryEntryPoint::class.java)
        supportFragmentManager.fragmentFactory = entryPoint.getFragmentFactory()



        super.onCreate(savedInstanceState)
        /*b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)*/
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
//        setSupportActionBar(b.toAppBarMain.toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_upcoming,
                R.id.nav_popular,
                R.id.nav_favorites),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        popularVM.popularMoviesLiveData.observe(this, {

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}