package com.charging.animation.mobile.battery.fragment.gallery

import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseFragment
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.FragmentGalleryBinding
import com.charging.animation.mobile.battery.fragment.gallery.adapter.TopAdapter

class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>() {

    private var homeAdapter: TopAdapter? = null
    override fun init() {
        homeAdapter = TopAdapter(childFragmentManager, 1)
        binding.viewpager.adapter = homeAdapter
        binding.viewpager.offscreenPageLimit = 2
        binding.btnCustom.isSelected = true
        binding.btnDown.isSelected = true
    }

    override fun listener() {
        binding.btnDown.tap {
            CommonAds.logEvent(requireActivity(), "gallery_downloaded_view")
            binding.btnDown.setBackgroundResource(R.drawable.gallery_btn_select)
            binding.btnCustom.setBackgroundResource(android.R.color.transparent)
            binding.viewpager.setCurrentItem(0, true)
        }
        binding.btnCustom.tap {
            CommonAds.logEvent(requireActivity(), "gallery_customed_view")
            binding.btnCustom.setBackgroundResource(R.drawable.gallery_btn_select)
            binding.btnDown.setBackgroundResource(android.R.color.transparent)
            binding.viewpager.setCurrentItem(1, true)
        }

        binding.viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.btnDown.setBackgroundResource(R.drawable.gallery_btn_select)
                    binding.btnCustom.setBackgroundResource(android.R.color.transparent)
                } else {
                    binding.btnCustom.setBackgroundResource(R.drawable.gallery_btn_select)
                    binding.btnDown.setBackgroundResource(android.R.color.transparent)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun viewModel() {

    }

    override fun getViewBinding(): FragmentGalleryBinding = FragmentGalleryBinding.inflate(layoutInflater)

    override fun setViewModel(): GalleryViewModel =  viewModels<GalleryViewModel>().value

}