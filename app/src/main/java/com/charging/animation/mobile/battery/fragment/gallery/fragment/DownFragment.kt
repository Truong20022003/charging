package com.charging.animation.mobile.battery.fragment.gallery.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity
import com.charging.animation.mobile.battery.activity.base.BaseFragment
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.api.Results
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.database.Database
import com.charging.animation.mobile.battery.databinding.FragmentDownUpdateBinding
import com.charging.animation.mobile.battery.fragment.gallery.GalleryViewModel
import com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter
import com.charging.animation.mobile.battery.listener.Listener

class DownFragment : BaseFragment<FragmentDownUpdateBinding, GalleryViewModel>() {
    var adapterGallery: GalleryAdapter? = null
    lateinit var listGallery: MutableList<Results>
    override fun getViewBinding(): FragmentDownUpdateBinding = FragmentDownUpdateBinding.inflate(layoutInflater)
    override fun setViewModel(): GalleryViewModel = viewModels<GalleryViewModel>().value

    override fun init() {
        binding.imgNodata.loadImage(requireActivity(), R.drawable.img_nodata, binding.imgNodata)
        binding.btnHome.loadImage(requireActivity(), R.drawable.language_bg_btn, binding.btnHome)

        listGallery = mutableListOf()
        listGallery = Database.getInstance(requireActivity()).resultsDao().getAll(2)
        adapterGallery = GalleryAdapter(listGallery, requireActivity(), object : Listener() {
            override fun onClickItem(results: Results, n: Int) {
                super.onClickItem(results, n)
                startActivity(
                    Intent(requireActivity(), ApplyAnimationActivity::class.java)
                        .putExtra("link", results.link)
                        .putExtra("type", 2)
                )
            }
        })
        binding.btnHome.isSelected = true
        binding.rcvGallery.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.rcvGallery.adapter = adapterGallery
        val intentFilter = IntentFilter()
        intentFilter.addAction("down_success")
        LocalBroadcastManager.getInstance(requireActivity())
            .registerReceiver(broadcastReceiver, intentFilter)
        if (listGallery.size == 0) {
            binding.rootNodata.visibility = View.VISIBLE
        }
        binding.btnHome.setOnClickListener {
            try {
                (requireActivity() as MainActivity).home()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    override fun listener() {

    }

    override fun viewModel() {

    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                if (intent.action == "down_success") {
                    val results = intent.getSerializableExtra("down_success") as Results?
                    if (results != null) {
                        listGallery.add(results)
                    }
                    adapterGallery?.notifyDataSetChanged()
                    binding.rootNodata.visibility = View.GONE
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(broadcastReceiver)
    }
}