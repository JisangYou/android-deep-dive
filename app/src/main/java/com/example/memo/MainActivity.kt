package com.example.memo

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.memo.R


class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName
    private val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Timber.e("mainActivity")
        if (ContextCompat.checkSelfPermission(
                this,
                permissions[0]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    permissions[0]
                )
            ) {
//                Timber.e("ActivityCompat.shouldShowRequestPermissionRationale")
            } else {
//                Timber.e("ActivityCompat.requestPermissions")
                ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    REQUEST_WRITE_CODE
                )
            }
        } else {
//            Timber.e("Permission has already been granted")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_WRITE_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    Timber.e("Permission was granted")
                } else {
//                    Timber.e("Permission Denied")
                }
                return
            }
            else -> {
//                Timber.e("Ignore all other requests")
            }
        }
    }

    companion object {
        private val REQUEST_WRITE_CODE = 1989
    }
}


