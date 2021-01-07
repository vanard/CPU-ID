package com.vanard.cpuid

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.vanard.cpuid.databinding.FragmentDeviceBinding


class DeviceFragment : Fragment() {
    private val TAG = "DeviceFragment"

    private var _binding: FragmentDeviceBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var telephonyManager: TelephonyManager

    private var phoneType: String? = null
    private var dataNetworkType: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDeviceBinding.inflate(inflater, container, false)
        telephonyManager =
            requireActivity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        setData()
        requestPermission()

        return mBinding.root
    }

    private fun requestPermission() {
        val singlePermission = object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse) {
                configData()
                setData()
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse) {
                requireActivity().createToast("Phone permission is needed to get network type data")
                setData()
            }

            override fun onPermissionRationaleShouldBeShown(
                permission: PermissionRequest?,
                token: PermissionToken?
            ) {
                setData()
            }
        }

        val multiPermission = object: BaseMultiplePermissionsListener() {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                super.onPermissionsChecked(report)
                Log.d(TAG, "onPermissionRationaleShouldBeShown: check")
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                super.onPermissionRationaleShouldBeShown(permissions, token)
                Log.d(TAG, "onPermissionRationaleShouldBeShown: shouldbe")
            }
        }

        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.READ_PHONE_STATE)
            .withListener(singlePermission).check()
    }

    @SuppressLint("MissingPermission")
    private fun configData() {
        dataNetworkType =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                when (telephonyManager.dataNetworkType) {
                    NETWORK_TYPE_EDGE,
                    NETWORK_TYPE_GPRS,
                    NETWORK_TYPE_CDMA,
                    NETWORK_TYPE_IDEN,
                    NETWORK_TYPE_1xRTT -> "2G"
                    NETWORK_TYPE_UMTS,
                    NETWORK_TYPE_HSDPA,
                    NETWORK_TYPE_HSPA,
                    NETWORK_TYPE_HSPAP,
                    NETWORK_TYPE_EVDO_0,
                    NETWORK_TYPE_EVDO_A,
                    NETWORK_TYPE_EVDO_B -> "3G"
                    NETWORK_TYPE_LTE -> "4G"
                    NETWORK_TYPE_NR -> "5G"
                    else -> "Unknown"
                }
            } else {
                "Unknown"
            }

        phoneType = when(telephonyManager.phoneType) {
            PHONE_TYPE_CDMA -> "CDMA"
            PHONE_TYPE_GSM -> "GSM"
            PHONE_TYPE_SIP -> "SIP"
            else -> "No phone radio"
        }

        val multiSim = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            telephonyManager.isMultiSimSupported
        }else {
            -1
        }
    }

    private fun setData() {
        mBinding.deviceModelText.text = Build.MODEL
        mBinding.manufacturerText.text = Build.MANUFACTURER
        mBinding.brandText.text = Build.BRAND
        mBinding.deviceText.text = Build.DEVICE
        mBinding.boardText.text = Build.BOARD
        mBinding.hardwareText.text = Build.HARDWARE
        mBinding.fingerprintText.text = Build.FINGERPRINT

        if (!phoneType.isNullOrEmpty())
            mBinding.deviceTypeText.text = phoneType
        if (!dataNetworkType.isNullOrEmpty())
            mBinding.networkTypeText.text = dataNetworkType
        mBinding.operatorNameText.text = telephonyManager.simOperatorName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}