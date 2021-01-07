package com.vanard.cpuid

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scottyab.rootbeer.RootBeer
import com.vanard.cpuid.databinding.FragmentSystemBinding
import java.util.*


class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var rooted: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSystemBinding.inflate(inflater, container, false)

        val rootBeer = RootBeer(context)
        rooted = if (rootBeer.isRooted) {
            //we found indication of root
            "Yes"
        } else {
            //we didn't find indication of root
            "No"
        }

        mBinding.textViewSystem.text = "Android ${Build.VERSION.RELEASE}\n" +
                "SDK Level: ${Build.VERSION.SDK_INT}\n" +
                "Security Patch Update: ${Build.VERSION.SECURITY_PATCH}\n" +
                "Bootloader: ${Build.BOOTLOADER}\n" +
                "Build Number: ${Build.ID}\n" +
                "Baseband Version: ${Build.getRadioVersion()}\n" +
                "Root Access: $rooted\n" +
                "Kernel Version: ${System.getProperty("os.version")}\n" +
                "Java VM: ${System.getProperty("java.vm.version")}\n" +
                "Language: ${Locale.getDefault().displayLanguage}\n"

        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}