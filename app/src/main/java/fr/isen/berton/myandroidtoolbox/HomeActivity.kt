package fr.isen.berton.myandroidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPreferences = getSharedPreferences(Constants.UserPreferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        lifeCycleButton.setOnClickListener {
            startActivity(Intent(this, LifeCycleActivity::class.java))
        }

        logoutbutton.setOnClickListener {
            editor.clear().commit()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        saveButton.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }

        permissionButton.setOnClickListener {
            startActivity(Intent(this, InformationsActivity::class.java))
        }

        logoutButton.setOnClickListener {
            startActivity(Intent(this, WebServicesActivity::class.java))
        }
    }


}
