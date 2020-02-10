package fr.isen.berton.myandroidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_life_cycle.*


class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        lifeCycleText.text = getString(R.string.on_create)

        val firstFragment = LifeCycleFragment()
        val secondFragment = LifeCycleFragment2()
        supportFragmentManager.beginTransaction().add(R.id.LifeCycleLayout, firstFragment).commit()

        buttonFragment.setOnClickListener {
            if(firstFragment.isResumed){
                buttonFragment.text = getString(R.string.first_fragment)
                Log.d(getString(R.string.tag),getString(R.string.first_frag_resum))
                supportFragmentManager.beginTransaction().replace(R.id.LifeCycleLayout, secondFragment).commit()
            }
            else{
                buttonFragment.text = "Fragment 2"
                Log.d(getString(R.string.tag),getString(R.string.second_frag_resum))
                supportFragmentManager.beginTransaction().replace(R.id.LifeCycleLayout, firstFragment).commit()
            }
        }

    }
    override fun onStart() {
        super.onStart()
        lifeCycleText.text = getString(R.string.on_start)
        Log.d(getString(R.string.tag), getString(R.string.on_start))
    }
    override fun onResume() {
        super.onResume()
        lifeCycleText.text = getString(R.string.on_resume)
        Log.d(getString(R.string.tag), getString(R.string.on_resume))
    }
    override fun onPause() {
        super.onPause()
        lifeCycleText.text = getString(R.string.on_pause)
        Log.d(getString(R.string.on_pause), getString(R.string.on_pause))
    }
    override fun onStop() {
        super.onStop()
        lifeCycleText.text = getString(R.string.on_stop)
        Log.d(getString(R.string.on_stop), getString(R.string.on_stop))
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, getString(R.string.on_destroy), Toast.LENGTH_LONG)
        Log.d(getString(R.string.on_destroy), getString(R.string.on_destroy))
    }

}
