package com.vanard.cpuid

import android.opengl.GLES10.glGetString
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vanard.cpuid.databinding.FragmentCpuBinding
import com.vanard.cpuid.databinding.FragmentDeviceBinding
import java.util.*
import javax.microedition.khronos.opengles.GL10

class CpuFragment : Fragment() {

    private var _binding: FragmentCpuBinding? = null
    private val mBinding get() = _binding!!

    private var texto: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentCpuBinding.inflate(inflater, container, false)

        Build.SUPPORTED_ABIS.forEachIndexed { index, it ->
            texto += if (index < 2) {
                "$it, "
            }else {
                it
            }
        }

        mBinding.textCpu.text = "Processor Architecture: ${System.getProperty("os.arch")}\n" +
                "Supported ABIs: $texto\n" +
                "CPU Cores: ${Runtime.getRuntime().availableProcessors()}\n"
                Log.d("GL", "GL_RENDERER = "   + GL10.GL_RENDERER)
        Log.d("GL", "GL_VENDOR = "     + glGetString( GL10.GL_VENDOR     ));
        Log.d("GL", "GL_VERSION = "    + glGetString( GL10.GL_VERSION    ));
        Log.i("GL", "GL_EXTENSIONS = " + glGetString( GL10.GL_EXTENSIONS ));


        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}