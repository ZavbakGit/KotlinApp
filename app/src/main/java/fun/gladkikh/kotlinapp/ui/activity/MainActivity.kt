package `fun`.gladkikh.kotlinapp.ui.activity

import `fun`.gladkikh.kotlinapp.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mainFragment) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                supportActionBar!!.setDisplayShowHomeEnabled(false)
            } else {
                supportActionBar!!.setDisplayShowHomeEnabled(true)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    fun setTitleActionBar(title:String){
        supportActionBar?.title = title
    }


    fun getHostNavController() = navController
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
