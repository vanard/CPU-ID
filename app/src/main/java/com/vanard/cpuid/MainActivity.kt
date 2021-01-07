package com.vanard.cpuid

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.vanard.cpuid.Util.createToast
import com.vanard.cpuid.adapter.PagerAdapter
import com.vanard.cpuid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)

        mBinding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        val fragments = ArrayList<Fragment>()
        fragments.add(DeviceFragment())
        fragments.add(CpuFragment())
        fragments.add(SystemFragment())
        fragments.add(CameraFragment())
        fragments.add(BatteryFragment())
        fragments.add(SensorFragment())

        val titles = ArrayList<String>()
        titles.add("Device")
        titles.add("CPU")
        titles.add("System")
        titles.add("Camera")
        titles.add("Battery")
        titles.add("Sensor")

        val adapter = PagerAdapter(
            fragments, titles, supportFragmentManager
        )

        mBinding.viewPager.adapter = adapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)

//        requestPermission()
    }

    private fun requestPermission() {
        val multiPermission = object: BaseMultiplePermissionsListener() {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                super.onPermissionsChecked(report)
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                super.onPermissionRationaleShouldBeShown(permissions, token)
            }
        }

        Dexter.withContext(applicationContext)
            .withPermissions(Manifest.permission.READ_PHONE_STATE)
            .withListener(multiPermission).check()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_setting -> createToast("Settings Clicked")
            R.id.menu_close -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}