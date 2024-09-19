package com.charging.animation.mobile.battery.fragment.alarm

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseFragment
import com.charging.animation.mobile.battery.activity.ring.RingtoneActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.FragmentAlarmBinding
import com.charging.animation.mobile.battery.receiver.AnimationReceiver
import com.charging.animation.mobile.battery.service.FullBatterService
import com.charging.animation.mobile.battery.service.HighService
import com.charging.animation.mobile.battery.service.LowService
import com.charging.animation.mobile.battery.util.Data

@Suppress("DEPRECATION")
class AlarmFragment : BaseFragment<FragmentAlarmBinding, AlarmViewModel>() {
    private var isHigh = false
    private var isService = false
    private var isLow = false
    override fun getViewBinding(): FragmentAlarmBinding = FragmentAlarmBinding.inflate(layoutInflater)

    override fun setViewModel(): AlarmViewModel = viewModels<AlarmViewModel>().value

    override fun init() {
        binding.imgCycle.loadImage(requireActivity(), R.drawable.battery_img_cycle, binding.imgCycle)
        binding.imageView12.loadImage(requireActivity(), R.drawable.battery_ic_pin_info, binding.imageView12)
        binding.connect.loadImage(requireActivity(), R.drawable.battery_ic_cc, binding.connect)
        binding.ivRingBattery.loadImage(requireActivity(), R.drawable.img_ring_battery, binding.ivRingBattery)


        CommonAds.logEvent(requireActivity(), "alarm_view")
        isService = Data.getServiceBatterFull(requireContext())
        isLow = Data.getLow(requireActivity())
        isHigh = Data.getHigh(requireActivity())
        Glide.with(requireActivity())
            .load(R.drawable.img_ring_battery)
            .into(binding.ivRingBattery)
        if (Data.getSelectRingtoneName(requireContext()) == null) {
            binding.tvNameRing.text = requireContext().getString(R.string.none)
        } else {
            binding.tvNameRing.text = Data.getSelectRingtoneName(requireContext())
        }
        if (!isService) {
            binding.imgServiceFull.setImageResource(R.drawable.ic_switch_off)
        }
        if (!isLow) {
            binding.imgLow.setImageResource(R.drawable.ic_switch_off)
        }
        if (!isHigh) {
            binding.imgHigh.setImageResource(R.drawable.ic_switch_off)
        }

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().registerReceiver(receiver, IntentFilter(filter), Context.RECEIVER_NOT_EXPORTED)
        }else{
            requireActivity().registerReceiver(receiver, IntentFilter(filter))
        }



        if (AnimationReceiver.check == 1) {
            binding.connect.visibility = View.VISIBLE
        } else {
            binding.connect.visibility = View.GONE
        }


        val batteryManager = requireActivity().getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        var status = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                val chargingStatus =
                    batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS)
                status = (chargingStatus == BatteryManager.BATTERY_STATUS_CHARGING
                        || chargingStatus == BatteryManager.BATTERY_STATUS_FULL)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }

        if (status) {
            binding.connect.visibility = View.VISIBLE
        } else {
            binding.connect.visibility = View.GONE
        }

    }

    override fun listener() {
        binding.imgServiceFull.tap {
            if (isService) {
                binding.imgServiceFull.setImageResource(R.drawable.ic_switch_off)
                Data.setServiceBatterFull(requireContext(), false)
                requireContext().stopService(
                    Intent(
                        requireContext(),
                        FullBatterService::class.java
                    )
                )
            } else {
                CommonAds.logEvent(requireActivity(), "alarm_enable_full_turn_on")
                binding.imgServiceFull.setImageResource(R.drawable.ic_switch_on)
                Data.setServiceBatterFull(requireContext(), true)
            }
            isService = !isService
        }
        binding.imgLow.tap {
            if (isLow) {
                binding.imgLow.setImageResource(R.drawable.ic_switch_off)
                Data.setLow(requireContext(), false)
                requireContext().stopService(Intent(requireContext(), LowService::class.java))
            } else {
                binding.imgLow.setImageResource(R.drawable.ic_switch_on)
                Data.setLow(requireContext(), true)
                CommonAds.logEvent(requireActivity(), "alarm_enable_low_turn_on")
            }
            isLow = !isLow
        }
        binding.imgHigh.tap {
            if (isHigh) {
                binding.imgHigh.setImageResource(R.drawable.ic_switch_off)
                Data.setHigh(requireContext(), false)
                requireContext().stopService(Intent(requireContext(), HighService::class.java))
            } else {
                CommonAds.logEvent(requireActivity(), "alarm_enable_temperature_turn_on")
                binding.imgHigh.setImageResource(R.drawable.ic_switch_on)
                Data.setHigh(requireContext(), true)
            }
            isHigh = !isHigh
        }
        binding.grAlarm4.tap {
            CommonAds.logEvent(requireActivity(), "alarm_ringtone_click")
            val intent = Intent(activity, RingtoneActivity::class.java)
            startActivity(intent)
        }
    }

    override fun viewModel() {

    }


    override fun onResume() {
        super.onResume()
        if (Data.getSelectRingtoneName(requireContext()) == null) {
            binding.tvNameRing.text = requireContext().getString(R.string.none)
        } else {
            binding.tvNameRing.text =
                Data.getSelectRingtoneName(requireContext())
        }

    }

    var receiver: BroadcastReceiver? = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == Intent.ACTION_BATTERY_CHANGED) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val batteryPct = (level * 100 / scale.toFloat()).toInt()
                binding.tvPin3.text = "$batteryPct%"
                status(batteryPct)
            }
            if (action == Intent.ACTION_POWER_CONNECTED) {
                AnimationReceiver.check = 1
                binding.connect.visibility = View.VISIBLE
            }
            if (action == Intent.ACTION_POWER_DISCONNECTED) {
                if (AnimationReceiver.check == 1) {
                    binding.connect.visibility = View.GONE
                    AnimationReceiver.check = 0
                }
                binding.connect.visibility = View.GONE
            }
        }
    }

    fun status(n: Int) {
        binding.tvPinInfo.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.tvPinInfo.viewTreeObserver.removeGlobalOnLayoutListener(this)
                val h = intArrayOf(binding.tvPinInfo.height)
                h[0] = n * h[0] / 100
                val layoutParams =
                    LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, h[0])
                layoutParams.gravity = Gravity.BOTTOM
                binding.imgPin.layoutParams = layoutParams
                binding.imgPin.requestLayout()
                if (n >= 72) {
                    binding.imgPin.setBackgroundResource(R.drawable.battery_bg_batter_info_1)
                } else if (n >= 21) {
                    binding.imgPin.setBackgroundResource(R.drawable.bg_batter_info_2)
                } else {
                    binding.imgPin.setBackgroundResource(R.drawable.bg_batter_info_3)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            if (receiver != null) {
                requireContext().unregisterReceiver(receiver)
            }
        } catch (e: Exception) {
            e.message
        }
    }
}