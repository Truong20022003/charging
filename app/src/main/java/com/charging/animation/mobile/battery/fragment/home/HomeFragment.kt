package com.charging.animation.mobile.battery.fragment.home

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity
import com.charging.animation.mobile.battery.activity.base.BaseFragment
import com.charging.animation.mobile.battery.activity.down.DownActivity
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.api.Results
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.FragmentHomeUpdateBinding
import com.charging.animation.mobile.battery.fragment.home.adapter.AnimContentAndAdsAdapter
import com.charging.animation.mobile.battery.fragment.home.adapter.CategoryAdapter
import com.charging.animation.mobile.battery.fragment.home.model.CategoryModel
import com.charging.animation.mobile.battery.fragment.home.repository.HomeRepository
import com.charging.animation.mobile.battery.listener.Listener
import com.charging.animation.mobile.battery.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class HomeFragment : BaseFragment<FragmentHomeUpdateBinding, HomeViewModel>() {
    private var list: MutableList<Results> = mutableListOf()
    private var adapter: AnimContentAndAdsAdapter? = null
    private var adapterCategory: CategoryAdapter? = null
    private var nameCategory = ""
    override fun getViewBinding(): FragmentHomeUpdateBinding = FragmentHomeUpdateBinding.inflate(layoutInflater)

    override fun setViewModel(): HomeViewModel = viewModels<HomeViewModel>().value


    override fun init() {
        binding.imgSave.loadImage(requireActivity(), R.drawable.language_bg_btn, binding.imgSave)
        recycleViewAnimation()
        recycleViewCategory()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.ACTION_DOWN)
        LocalBroadcastManager.getInstance(requireActivity())
            .registerReceiver(broadcastReceiver, intentFilter)
        CommonAds.logEvent(requireActivity(), "home_view")
    }


    override fun listener() {
        binding.imgSave.tap {
            try {
                CommonAds.logEvent(requireActivity(), "home_custom_click")
                (requireActivity() as MainActivity).select()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun viewModel() {
        viewModel.success.observe(this) {
            if (it) {
                list.addAll(HomeRepository.listAll)
                adapter?.addAll(list, false)
                binding.progress.visibility = View.GONE
            } else {
                binding.progress.visibility = View.GONE
            }
        }

        viewModel.api(requireActivity())
    }

    private fun recycleViewCategory() {
        adapterCategory = CategoryAdapter(requireActivity(), object : Listener() {
            override fun onClickCategory(categoryModel: CategoryModel) {
                super.onClickCategory(categoryModel)
                list.clear()
                list = mutableListOf()
                nameCategory = categoryModel.category

                when (categoryModel.category) {
                    Constants.All -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "All")
                        list.addAll(HomeRepository.listAll)
                    }

                    Constants.Recommend -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Recommend")
                        list.addAll(HomeRepository.listRecommend)
                    }

                    Constants.Black -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Black")
                        list.addAll(HomeRepository.listBlack)
                    }

                    Constants.Cat -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Cat")
                        list.addAll(HomeRepository.listCat)
                    }

                    Constants.Chill -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Chill")
                        list.addAll(HomeRepository.listChill)
                    }

                    Constants.Fantasy -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Fantasy")
                        list.addAll(HomeRepository.listFantasy)
                    }

                    Constants.Fire -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Fire")
                        list.addAll(HomeRepository.listFire)
                    }

                    Constants.Flower -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Flower")
                        list.addAll(HomeRepository.listFlower)
                    }

                    Constants.Handdrawing -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Handdrawing")
                        list.addAll(HomeRepository.listHanddrawing)
                    }

                    Constants.Horror -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Horror")
                        list.addAll(HomeRepository.listHorror)
                    }

                    Constants.Lofi -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Lofi")
                        list.addAll(HomeRepository.listLofi)
                    }

                    Constants.Neon -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Neon")
                        list.addAll(HomeRepository.listNeon)
                    }

                    Constants.Animal -> {
                        CommonAds.logEvent(requireActivity(), "home_category_click", "category_name", "Animal")
                        list.addAll(HomeRepository.listAnimal)
                    }
                }

                loadData()
            }
        })

        binding.rcvCategory.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        binding.rcvCategory.setItemViewCacheSize(10)
        binding.rcvCategory.setHasFixedSize(true)
        binding.rcvCategory.adapter = adapterCategory
    }


    private fun recycleViewAnimation() {
        adapter = AnimContentAndAdsAdapter(list, requireActivity(), false, object : Listener() {
            override fun onClick(results: Results) {
                super.onClick(results)
                if (results.type == 1 && !results.free) {

                    intent(results)
                } else {
                    intent(results)
                }
            }
        })
        binding.rcvAnimation.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.rcvAnimation.setHasFixedSize(true)
        binding.rcvAnimation.adapter = adapter
    }


    fun reset() {
        try {
            Log.e("kh45", list.size.toString())
            CoroutineScope(Dispatchers.Main).launch {
                adapter =
                    AnimContentAndAdsAdapter(list, requireActivity(), false, object : Listener() {
                        override fun onClick(results: Results) {
                            super.onClick(results)
                            if (results.type == 1 && !results.free) {
                                CommonAds.logEvent(requireActivity(), "home_item_choose", "category_name_choose", nameCategory)
                                intent(results)
                            } else {
                                CommonAds.logEvent(requireActivity(), "home_item_choose", "category_name_choose", nameCategory)
                                intent(results)
                            }
                        }
                    })
                binding.rcvAnimation.layoutManager = GridLayoutManager(requireActivity(), 3)
                binding.rcvAnimation.setHasFixedSize(true)
                binding.rcvAnimation.adapter = adapter
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData() {
        reset()
    }

    private var resultReload: ActivityResultLauncher<Intent> =
        registerForActivityResult<Intent, ActivityResult>(ActivityResultContracts.StartActivityForResult()) {

        }

    fun intent(results: Results) {
        when (results.type) {
            0 -> {
                resultReload.launch(
                    Intent(requireActivity(), ApplyAnimationActivity::class.java)
                        .putExtra(Constants.BUNDLE_LINK, results.link)
                        .putExtra(Constants.BUNDLE_TYPE, 0)
                )
            }

            1 -> {
                val intent = Intent(requireActivity(), DownActivity::class.java)
                intent.putExtra(Constants.BUNDLE_LINK, results)
                resultReload.launch(intent)
            }

            2 -> {
                resultReload.launch(
                    Intent(requireActivity(), ApplyAnimationActivity::class.java)
                        .putExtra(Constants.BUNDLE_LINK, results.link)
                        .putExtra(Constants.BUNDLE_TYPE, 2)
                )
            }
        }
    }

    fun check(listAll: List<Results>, results: Results) {
        for (i in listAll.indices) {
            val arr: Array<String> =
                listAll[i].link.split("/".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val arr2: Array<String> =
                results.link.split("/".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            if (arr2[arr2.size - 1] == arr[arr.size - 1]) {
                listAll[i].folder = results.folder
                listAll[i].type = results.type
                listAll[i].name = results.name
                listAll[i].link = results.link
                listAll[i].free = true
                break
            }
        }
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                Log.e("kh45", "broadcastReceiver")
                if (intent.action == Constants.ACTION_DOWN) {
                    val results: Results = intent.getSerializableExtra(Constants.ACTION_DOWN) as Results
                    check(HomeRepository.listAll, results)
                    check(HomeRepository.listRecommend, results)
                    when (results.folder) {
                        Constants.Black -> {
                            check(HomeRepository.listBlack, results)
                        }

                        Constants.Cat -> {
                            check(HomeRepository.listCat, results)
                        }

                        Constants.Chill -> {
                            check(HomeRepository.listChill, results)
                        }

                        Constants.Fantasy -> {
                            check(HomeRepository.listFantasy, results)
                        }

                        Constants.Fire -> {
                            check(HomeRepository.listFire, results)
                        }

                        Constants.Flower -> {
                            check(HomeRepository.listFlower, results)
                        }

                        Constants.Handdrawing -> {
                            check(HomeRepository.listHanddrawing, results)
                        }

                        Constants.Horror -> {
                            check(HomeRepository.listHorror, results)
                        }

                        Constants.Lofi -> {
                            check(HomeRepository.listLofi, results)
                        }

                        Constants.Neon -> {
                            check(HomeRepository.listNeon, results)
                        }

                        Constants.Animal -> {
                            check(HomeRepository.listAnimal, results)
                        }
                    }
                    Log.e("kh45", list.size.toString())
                    check(list, results)
                    reset()
                    LocalBroadcastManager.getInstance(requireActivity()).sendBroadcast(
                        Intent(Constants.ACTION_DOWN_SUCCESS)
                            .putExtra(Constants.ACTION_DOWN_SUCCESS, results)
                    )
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