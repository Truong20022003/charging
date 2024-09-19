package com.charging.animation.mobile.battery.activity.info.fragment.battery

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseFragment
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.databinding.FragmentBatteryUpdateBinding
import com.charging.animation.mobile.battery.model.Battery
import com.charging.animation.mobile.battery.receiver.AnimationReceiver
import com.charging.animation.mobile.battery.util.Util

class BatteryFragment : BaseFragment<FragmentBatteryUpdateBinding, BatteryViewModel>() {
    var battery: Battery? = null
    var isCharging = false
    override fun getViewBinding(): FragmentBatteryUpdateBinding = FragmentBatteryUpdateBinding.inflate(layoutInflater)

    override fun setViewModel(): BatteryViewModel = viewModels<BatteryViewModel>().value

    override fun init() {
        binding.imgCycle.loadImage(requireActivity(), R.drawable.battery_img_cycle, binding.imgCycle)
        binding.imageView12.loadImage(requireActivity(), R.drawable.battery_ic_pin_info, binding.imageView12)
        binding.connect.loadImage(requireActivity(), R.drawable.battery_ic_cc, binding.connect)

        binding.img1.loadImage(requireActivity(), R.drawable.ic_vol, binding.img1)
        binding.img2.loadImage(requireActivity(), R.drawable.ic_pin4, binding.img2)
        binding.img3.loadImage(requireActivity(), R.drawable.ic_temple, binding.img3)
        binding.img4.loadImage(requireActivity(), R.drawable.ic_capati, binding.img4)
        binding.img5.loadImage(requireActivity(), R.drawable.ic_charging_info, binding.img5)
        binding.img6.loadImage(requireActivity(), R.drawable.ic_lion, binding.img6)




        battery = Battery()
        receiver()

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
            isCharging = true
            binding.connect.visibility = View.VISIBLE
        } else {
            isCharging = false
            binding.connect.visibility = View.GONE
        }

    }

    fun receiver(){
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        }else{
            requireActivity().registerReceiver(receiver, filter)
        }

    }

    override fun listener() {

    }

    override fun viewModel() {

    }

    fun textGradient(textView: TextView) {
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader: Shader = LinearGradient(
            0f, 0f, width, textView.textSize, intArrayOf(
                Color.parseColor("#F16CFF"),
                Color.parseColor("#12D6F0"),
            ), null, Shader.TileMode.CLAMP
        )
        textView.paint.shader = textShader
    }

    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == Intent.ACTION_BATTERY_CHANGED) {
                battery?.level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                battery?.scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                battery?.temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1)
                battery?.voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
                battery?.technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
                battery?.status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

                binding.template.text = Util.getTemp(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0), true) + " / " + Util.getTemp(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0), false)
                binding.tvVol.text = Util.getVol(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)).toString() + " V"
                binding.tvType.text = Util.getChargeType(context)
                binding.tvCapacity.text = Util.getBatteryCapacity(context).toString() + "mah"
                binding.tvLion.text = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)

                textGradient(binding.template)
                textGradient(binding.tvVol)
                textGradient(binding.tvHeart)
                textGradient(binding.tvType)
                textGradient(binding.tvCapacity)
                textGradient(binding.tvLion)

                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val batteryPct = (level * 100 / scale.toFloat()).toInt()
                binding.tvPin3.text = "$batteryPct%"
                status(batteryPct)
                if (battery?.status == BatteryManager.BATTERY_STATUS_FULL) {
                    Log.e("Battery", "BATTERY_STATUS_FULL")
                }
                isCharging = battery?.status == BatteryManager.BATTERY_STATUS_CHARGING || battery?.status == BatteryManager.BATTERY_STATUS_FULL
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

                textGradient(binding.tvHeart)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(receiver)
    }
}