package fr.isen.berton.myandroidtoolbox

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.ContactsContract
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_informations.*
import android.content.Intent
import androidx.core.content.ContextCompat
import android.annotation.SuppressLint
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.pm.PackageManager
import android.location.LocationManager
import android.content.Context
import android.location.Location
import android.location.LocationListener



class InformationsActivity : AppCompatActivity(), LocationListener{


    companion object {
        val permission_request_code = 3
        val gpsPermissionRequestCode = 2
    }

    lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informations)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        requestPermission(android.Manifest.permission.READ_CONTACTS, permission_request_code) {
            readContacts()
        }

        requestPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, gpsPermissionRequestCode) {
            startGPS()
        }

        pickimagebutton.setOnClickListener{
            val imagefromgalleryIntent = Intent(Intent.ACTION_PICK)
            imagefromgalleryIntent.setType(getString(R.string.img))

            val imagefromcameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            val chooseIntent= Intent.createChooser(imagefromgalleryIntent, getString(R.string.gallery))
            chooseIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(imagefromcameraIntent))
            startActivityForResult(chooseIntent,11)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK ){
            if(data?.data == null){
                val bitmap = data?.extras?.get(getString(R.string.data)) as? Bitmap
                bitmap?.let{
                    pickimagebutton.setImageBitmap(it)
                }
            }else{
                pickimagebutton.setImageURI(data?.data)

            }

        }
    }

    fun requestPermission(permissionToRequest: String, requestCode: Int, handler: ()-> Unit) {
        if(ContextCompat.checkSelfPermission(this, permissionToRequest) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissionToRequest)) {
                //display toast
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permissionToRequest), requestCode)
            }
        } else {
            handler()
        }
    }

    fun readContacts() {
        val listContacts = ArrayList<ContactModel>()
        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        while(contacts?.moveToNext() ?: false) {
            val displayName = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val displayPhoneNumber = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts._ID))

            val contactModel = ContactModel()

            contactModel.displayPhoneNumber = displayPhoneNumber.toString()
            contactModel.displayName = displayName.toString()
            listContacts.add(contactModel)
        }
        contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactRecyclerView.adapter = ContactAdapter(listContacts)
    }

    @SuppressLint("MissingPermission")
    fun startGPS() {
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null)
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        location?.let {
            refreshPositionuser(it)
        }
    }

    fun refreshPositionuser(location: Location){
        latitudeedittext.text = "Latitude: ${location.latitude}"
        longitudeedittext.text = "Longitude: ${location.longitude}"
    }

    override fun onLocationChanged(location: Location?) {
        location?.let{
            refreshPositionuser(it)
        }
    }

    private fun LocationManager.requestSingleUpdate(networkProvider: String, permissionsActivity: InformationsActivity, nothing: Nothing?) {

    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
