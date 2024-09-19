package com.charging.animation.mobile.battery.activity.info.fragment.mobile

import android.annotation.SuppressLint
import android.os.Build
import android.provider.Settings
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseFragment
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.databinding.FragmentMobileBinding
import com.charging.animation.mobile.battery.model.Mobile

class MobileFragment : BaseFragment<FragmentMobileBinding, MobileViewModel>() {
    private var mobileAdapter: MobileAdapter? = null
    var list: MutableList<Mobile> = mutableListOf()
    override fun getViewBinding(): FragmentMobileBinding= FragmentMobileBinding.inflate(layoutInflater)

    override fun setViewModel(): MobileViewModel =  viewModels<MobileViewModel>().value

    override fun init() {

        binding.imgCycle.loadImage(requireActivity(), R.drawable.img_mobile_info, binding.imgCycle)

        list.add(Mobile(R.drawable.ic_language, getString(R.string.device), Build.DEVICE))
        @SuppressLint("HardwareIds") val androidId = Settings.Secure.getString(requireActivity().contentResolver, Settings.Secure.ANDROID_ID)
        list.add(Mobile(R.drawable.ic_language, getString(R.string.device) + " " + getString(R.string.id), androidId))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.model), Build.MODEL))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.id), Build.ID))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.sdk), Build.VERSION.SDK_INT.toString() + ""))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.manufacture), Build.MANUFACTURER))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.User), Build.USER))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.Type), Build.TYPE))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.product), Build.PRODUCT))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.broad), Build.BOARD))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.boot_loader), Build.BOOTLOADER))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.cpu), Build.CPU_ABI))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.display), Build.DISPLAY))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.Hardware), Build.HARDWARE))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.host), Build.HOST))
        list.add(Mobile(R.drawable.ic_language, getString(R.string.version), Build.VERSION.SDK_INT.toString() + ""))
        mobileAdapter = MobileAdapter(list, requireActivity())
        binding.rcvMobile.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rcvMobile.adapter = mobileAdapter
    }

    override fun listener() {

    }

    override fun viewModel() {

    }
}