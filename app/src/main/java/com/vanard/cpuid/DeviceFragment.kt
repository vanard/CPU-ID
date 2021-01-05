package com.vanard.cpuid

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vanard.cpuid.databinding.FragmentDeviceBinding

class DeviceFragment : Fragment() {

    private var _binding: FragmentDeviceBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDeviceBinding.inflate(inflater, container, false)

        val detail = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            "Version Release: ${Build.VERSION.RELEASE}\n" +
                    "Version Incremental: ${Build.VERSION.INCREMENTAL}\n" +
                    "BASE OS: ${Build.VERSION.BASE_OS}\n" +
                    "Codename: ${Build.VERSION.CODENAME}\n" +
                    "SECURITY PATCH: ${Build.VERSION.SECURITY_PATCH}\n" +
                    "SDK Number: ${Build.VERSION.SDK_INT}\n" +
                    "Board: ${Build.BOARD}\n" +
                    "Bootloader: ${Build.BOOTLOADER}\n" +
                    "Brand: ${Build.BRAND}\n" +
                    "Device: ${Build.DEVICE}\n" +
                    "Display: ${Build.DISPLAY}\n" +
                    "Model: ${Build.MODEL}\n" +
                    "Manufacturer: ${Build.MANUFACTURER}\n" +
                    "Hardware: ${Build.HARDWARE}\n" +
                    "Product: ${Build.PRODUCT}\n" +
                    "ID: ${Build.ID}\n" +
                    "Fingerprint: ${Build.FINGERPRINT}\n" +
                    "Host: ${Build.HOST}\n" +
                    "Type: ${Build.TYPE}\n" +
                    "Tags: ${Build.TAGS}\n" +
                    "Time: ${Build.TIME}\n" +
                    "Unknown: ${Build.UNKNOWN}\n" +
                    "User: ${Build.USER}\n"
        } else {
            "Version Release: ${Build.VERSION.RELEASE}\n" +
                    "Version Incremental: ${Build.VERSION.INCREMENTAL}\n" +
                    "Codename: ${Build.VERSION.CODENAME}\n" +
                    "SDK Number: ${Build.VERSION.SDK_INT}\n" +
                    "Board: ${Build.BOARD}\n" +
                    "Bootloader: ${Build.BOOTLOADER}\n" +
                    "Brand: ${Build.BRAND}\n" +
                    "Device: ${Build.DEVICE}\n" +
                    "Display: ${Build.DISPLAY}\n" +
                    "Model: ${Build.MODEL}\n" +
                    "Manufacturer: ${Build.MANUFACTURER}\n" +
                    "Hardware: ${Build.HARDWARE}\n" +
                    "Product: ${Build.PRODUCT}\n" +
                    "ID: ${Build.ID}\n" +
                    "Fingerprint: ${Build.FINGERPRINT}\n" +
                    "Host: ${Build.HOST}\n" +
                    "Type: ${Build.TYPE}\n" +
                    "Tags: ${Build.TAGS}\n" +
                    "Time: ${Build.TIME}\n" +
                    "Unknown: ${Build.UNKNOWN}\n" +
                    "User: ${Build.USER}\n"
        }

        mBinding.textView.text = detail

        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}