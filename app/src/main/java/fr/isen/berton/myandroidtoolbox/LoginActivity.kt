package fr.isen.berton.myandroidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val goodIdendifier = "admin"
    var goodPassword = "123"

    var userPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userPref = getSharedPreferences(Constants.UserPreferencesName, Context.MODE_PRIVATE)
        checkPreferences()

        validate.setOnClickListener {
            doLogin()
        }
    }

    fun doLogin() {
        if (canLog(usernameEditText.text.toString(), passwordEditText.text.toString())) {
            saveUser(usernameEditText.text.toString(), passwordEditText.text.toString())
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    fun canLog(identifier: String, password: String): Boolean {
        return identifier == goodIdendifier && password == goodPassword
    }

    fun saveUser(identifier: String, password: String) {
        val editor = userPref?.edit()
        editor?.putString(Constants.kIdentifier, identifier)
        editor?.putString(Constants.kPassword, password)
        editor?.apply()
    }

    fun checkPreferences() {
        val identifier = userPref?.getString(Constants.kIdentifier, null) ?: ""
        val password = userPref?.getString(Constants.kPassword, null) ?: ""
        usernameEditText.setText(identifier)
        passwordEditText.setText(password)
        doLogin()
    }
}