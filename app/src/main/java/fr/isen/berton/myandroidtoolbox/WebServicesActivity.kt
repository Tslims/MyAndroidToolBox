package fr.isen.berton.myandroidtoolbox

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity() {

    val urlAPI = "https://randomuser.me/api/?results=20&nat=gb"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)

        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, urlAPI, Response.Listener {
                response ->

            var gson = Gson()
            var result= gson.fromJson(response, RandomUserResult::class.java)

            result.results?.let{
                RecyclerViewWS.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
                RecyclerViewWS.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                RecyclerViewWS.adapter = UserWebServicesAdapter(it,{ userItem : UserWebServicesModel-> profilItemClicked(userItem) })
            }

        }, Response.ErrorListener {
            Toast.makeText(this, getString(R.string.err), Toast.LENGTH_LONG)
        })

        queue.add(request)


    }

    private fun profilItemClicked(userItem : UserWebServicesModel) {
        val alertDialogB = Dialog(this)
        alertDialogB.setContentView(R.layout.ws_layout)

        alertDialogB.findViewById<TextView>(R.id.nominfos).text = "Nom : " + userItem.name?.last
        alertDialogB.findViewById<TextView>(R.id.prenominfos).text = "Prénom : " + userItem.name?.first
        alertDialogB.findViewById<TextView>(R.id.genderinfos).text = "Sexe : " + userItem.gender
        alertDialogB.findViewById<TextView>(R.id.emailinfos).text = "Email : " + userItem.email

        alertDialogB.show()
    }
}


