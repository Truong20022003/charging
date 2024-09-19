package com.charging.animation.mobile.battery.fragment.setting

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseFragment
import com.charging.animation.mobile.battery.activity.language.LanguageSettingActivity
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.activity.policy.PolicyActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.FragmentSettingBinding
import com.charging.animation.mobile.battery.dialog.RatingDialog
import com.charging.animation.mobile.battery.dialog.RatingDialog.OnPress
import com.charging.animation.mobile.battery.fragment.alarm.AlarmFragment
import com.charging.animation.mobile.battery.service.BatteryService
import com.charging.animation.mobile.battery.util.Data
import com.charging.animation.mobile.battery.util.SharePrefUtils
import com.charging.animation.mobile.battery.util.Util
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import java.util.Locale

@Suppress("DEPRECATION")
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    private var isService = false
    private var isPercent = false
    private var isLive = false
    private var intent: Intent? = null
    private var isShare = false

    override fun getViewBinding(): FragmentSettingBinding =
        FragmentSettingBinding.inflate(layoutInflater)

    override fun setViewModel(): SettingViewModel = viewModels<SettingViewModel>().value

    override fun init() {
        CommonAds.logEvent(requireActivity(), "setting_view")

        clearFragmentContainer()

        isService = Data.getServiceBatter(requireContext())
        isPercent = Data.getBatteryPercent(requireContext())
        isLive = Data.getServiceLive(requireContext())
        intent = Intent(requireContext(), BatteryService::class.java)

        if (!isService) {
            binding.imgEndAnim.setImageResource(R.drawable.ic_switch_off)
        } else {
            if (!Util.isMyServiceRunning(
                    BatteryService::class.java,
                    requireContext()
                )
            ) if (Build.VERSION.SDK_INT >= 26) {
                requireContext().startForegroundService(intent)
            } else {
                requireContext().startService(intent)
            }
        }
        if (!isPercent) {
            binding.imgPercentage.setImageResource(R.drawable.ic_switch_off)
        }
        if (!isLive) {
            binding.imgKeepService.setImageResource(R.drawable.ic_switch_off)
        }
        binding.imgKeepService.tap {
            permissionLive()
        }

        binding.tv1.isSelected = true
        binding.tv2.isSelected = true
        binding.tv3.isSelected = true
        binding.tv4.isSelected = true
        binding.tv5.isSelected = true
        binding.tv6.isSelected = true
        binding.tv7.isSelected = true



        val intentFilter = IntentFilter()
        intentFilter.addAction("alarm")
        intentFilter.addAction("setting")
        LocalBroadcastManager.getInstance(requireActivity())
            .registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun listener() {
        binding.imgPercentage.tap {
            if (isPercent) {
                CommonAds.logEvent(requireActivity(), "setting_percentage_click", "value", "off")
                binding.imgPercentage.setImageResource(R.drawable.ic_switch_off)
                Data.setBatteryPercent(requireContext(), false)
            } else {
                CommonAds.logEvent(requireActivity(), "setting_percentage_click", "value", "on")
                binding.imgPercentage.setImageResource(R.drawable.ic_switch_on)
                Data.setBatteryPercent(requireContext(), true)
            }
            isPercent = !isPercent
        }
        binding.imgEndAnim.tap {
            CommonAds.logEvent(requireActivity(), "setting_enable_animation_click")
            permissionService()
        }
        binding.grAlarm4.tap {
            CommonAds.logEvent(requireActivity(), "setting_language_click")
            startActivity(Intent(context, LanguageSettingActivity::class.java))
        }
        binding.grAlarm5.tap {
            CommonAds.logEvent(requireActivity(), "setting_share_click")
            if (!isShare) {
                isShare = true
                share()
            }
        }
        binding.clRate.tap {
            CommonAds.logEvent(requireActivity(), "setting_rate_click")
            showRateDialog()
        }
        binding.grAlarm7.tap {
            CommonAds.logEvent(requireActivity(), "setting_feedback_click")
            feedback()
        }
        binding.grAlarm8.tap {
            CommonAds.logEvent(requireActivity(), "setting_privacy_policy_click")
            val intent = Intent(context, PolicyActivity::class.java)
            startActivity(intent)
        }

        binding.clAlarm.tap {
            CommonAds.logEvent(requireActivity(), "setting_alarm_click")
            binding.settingView.visibility = View.GONE
            LocalBroadcastManager.getInstance(requireActivity()).sendBroadcast(Intent("alarm"))
            try {
                (requireActivity() as MainActivity).startAlarm()
            }catch (e : Exception){
                e.printStackTrace()
            }


        }

    }

    override fun viewModel() {

    }

    override fun onResume() {
        super.onResume()
        isShare = false
        isService = Data.getServiceBatter(requireContext())
        if (!isService) {
            binding.imgEndAnim.setImageResource(R.drawable.ic_switch_off)
        } else {
            if (!Util.isMyServiceRunning(
                    BatteryService::class.java,
                    requireContext()
                )
            ) if (Build.VERSION.SDK_INT >= 26) {
                requireContext().startForegroundService(intent)
            } else {
                requireContext().startService(intent)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val packageName = requireContext().packageName
            val pm = requireContext().getSystemService(Context.POWER_SERVICE) as PowerManager
            isLive = if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                binding.imgKeepService.setImageResource(R.drawable.ic_switch_off)
                true
            } else {
                binding.imgKeepService.setImageResource(R.drawable.ic_switch_on)
                false
            }
            isLive = !isLive
        }
        if (SharePrefUtils.isRated(requireActivity())) {
            binding.clRate.visibility = View.GONE
        }
    }

    private fun permissionLive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val packageName = requireContext().packageName
            val pm = requireContext().getSystemService(Context.POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                val intent = Intent()
                intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.data = Uri.parse("package:$packageName")
                Data.setServiceLive(requireContext(), false)
                startActivity(intent)
            } else {
                Data.setServiceLive(requireContext(), true)
                if (isLive) {
                    CommonAds.logEvent(requireActivity(), "setting_disable")

                    binding.imgKeepService.setImageResource(R.drawable.ic_switch_off)
                    Data.setServiceLive(requireContext(), false)
                } else {
                    CommonAds.logEvent(requireActivity(), "setting_enabled")

                    binding.imgKeepService.setImageResource(R.drawable.ic_switch_on)
                    Data.setServiceLive(requireContext(), true)
                }
                isLive = !isLive
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001) {
            Data.setServiceBatter(requireContext(), Settings.canDrawOverlays(requireContext()))
            isService = Data.getServiceBatter(requireContext())
            if (isService) {
                binding.imgEndAnim.setImageResource(R.drawable.ic_switch_on)
                Data.setServiceBatter(requireContext(), true)
                if (Build.VERSION.SDK_INT >= 26) {
                    requireContext().startForegroundService(intent)
                } else {
                    requireContext().startService(intent)
                }
            } else {
                binding.imgEndAnim.setImageResource(R.drawable.ic_switch_off)
                Data.setServiceBatter(requireContext(), false)
                requireContext().stopService(Intent(requireContext(), BatteryService::class.java))
            }
            isService = !isService
        }
    }

    private fun permissionService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(requireContext())) {
                try {
                    if ("xiaomi" == Build.MANUFACTURER.toLowerCase(Locale.ROOT)) {
                        val intent = Intent("miui.intent.action.APP_PERM_EDITOR").apply {
                            setClassName(
                                "com.miui.securitycenter",
                                "com.miui.permcenter.permissions.PermissionsEditorActivity"
                            )
                            putExtra("extra_pkgname", requireActivity().packageName)
                        }
                        startActivityForResult(intent, 1001)
                    } else {
                        startActivityForResult(
                            Intent(
                                "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                                Uri.parse("package:${requireActivity().packageName}")
                            ),
                            1001
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                if (isService) {
                    CommonAds.logEvent(
                        requireActivity(),
                        "setting_enable_animation_click",
                        "value",
                        "off"
                    )
                    binding.imgEndAnim.setImageResource(R.drawable.ic_switch_off)
                    Data.setServiceBatter(requireContext(), false)
                    requireContext().stopService(
                        Intent(
                            requireContext(),
                            BatteryService::class.java
                        )
                    )
                } else {
                    CommonAds.logEvent(
                        requireActivity(),
                        "setting_enable_animation_click",
                        "value",
                        "on"
                    )
                    binding.imgEndAnim.setImageResource(R.drawable.ic_switch_on)
                    Data.setServiceBatter(requireContext(), true)
                    if (Build.VERSION.SDK_INT >= 26) {
                        requireContext().startForegroundService(intent)
                    } else {
                        requireContext().startService(intent)
                    }
                }
                isService = !isService
            }
        }else{
            if (isService) {
                CommonAds.logEvent(
                    requireActivity(),
                    "setting_enable_animation_click",
                    "value",
                    "off"
                )
                binding.imgEndAnim.setImageResource(R.drawable.ic_switch_off)
                Data.setServiceBatter(requireContext(), false)
                requireContext().stopService(
                    Intent(
                        requireContext(),
                        BatteryService::class.java
                    )
                )
            } else {
                CommonAds.logEvent(
                    requireActivity(),
                    "setting_enable_animation_click",
                    "value",
                    "on"
                )
                binding.imgEndAnim.setImageResource(R.drawable.ic_switch_on)
                Data.setServiceBatter(requireContext(), true)
                if (Build.VERSION.SDK_INT >= 26) {
                    requireContext().startForegroundService(intent)
                } else {
                    requireContext().startService(intent)
                }
            }
            isService = !isService
        }
    }


    private fun showRateDialog() {
        CommonAds.logEvent(requireActivity(), "rate_show", "position", "setting")
        val manager = ReviewManagerFactory.create(requireContext())
        val ratingDialog = RatingDialog(requireContext())
        ratingDialog.init(requireContext(), object : OnPress {
            override fun send(s: Int) {
                CommonAds.logEvent(
                    requireActivity(),
                    "rate_submit",
                    "rate_star",
                    s.toString() + "_star"
                )
                SharePrefUtils.forceRated(requireActivity())
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.thank_you_for_rating),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun rating(s: Int) {
                if (SharePrefUtils.isRated(requireActivity())) {
                    binding.clRate.visibility = View.GONE
                }
                CommonAds.logEvent(
                    requireActivity(),
                    "rate_submit",
                    "rate_star",
                    s.toString() + "_star"
                )
                val request = manager.requestReviewFlow()
                request.addOnCompleteListener { task: Task<ReviewInfo?> ->
                    if (task.isSuccessful) {
                        SharePrefUtils.forceRated(requireContext())
                    }
                }
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + requireActivity().packageName)
                    )
                )
            }

            override fun cancel() {
                SharePrefUtils.increaseCountOpenApp(requireContext())
                ratingDialog.dismiss()
            }

            override fun later() {
                SharePrefUtils.increaseCountOpenApp(requireContext())
                ratingDialog.dismiss()
            }
        })
        ratingDialog.show()
        ratingDialog.setOnDismissListener {
            if (SharePrefUtils.isRated(requireActivity())) {
                binding.clRate.visibility = View.GONE
            }
        }
    }

    private fun share() {
        val intentShare = Intent(Intent.ACTION_SEND)
        intentShare.type = "text/plain"
        intentShare.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        intentShare.putExtra(
            Intent.EXTRA_TEXT, """
     ${getString(R.string.app_name)}
     https://play.google.com/store/apps/details?id=${requireContext().packageName}
     """.trimIndent()
        )
        startActivity(Intent.createChooser(intentShare, getString(R.string.share_with)))
    }

    fun feedback() {
        val dialog = Dialog(requireContext(), R.style.full_screen_dialog2)
        @SuppressLint("InflateParams") val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_feedback, null, false)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(false)
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val w = (displayMetrics.widthPixels * 0.9f).toInt()
        val h = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window?.setLayout(w, h)
        dialog.show()
        val etFeedBack2 = view.findViewById<EditText>(R.id.et_feedback_2)
        val tvFeedBack = view.findViewById<Button>(R.id.btn_feedback)
        view.findViewById<View>(R.id.btnApply).tap { dialog.dismiss() }
        tvFeedBack.tap {
            val text2 = etFeedBack2.getText().toString().trim { it <= ' ' }
            val text1 = "FeedBack"
            if (text2.isNotEmpty()) {
                val uriText = "mailto:.com?subject=$text1&body=$text2"
                val uri = Uri.parse(uriText)
                val sendIntent = Intent(Intent.ACTION_SENDTO)
                sendIntent.data = uri
                startActivity(Intent.createChooser(sendIntent, "Send Email"))
                dialog.dismiss()
            }
        }
    }

    private fun clearFragmentContainer() {
        val fragmentManager = childFragmentManager
        val fragments = fragmentManager.fragments
        if (fragments.isNotEmpty()) {
            val transaction = fragmentManager.beginTransaction()
            for (fragment in fragments) {
                transaction.remove(fragment)
            }
            transaction.commit()
        }
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "alarm") {
                alarm()
            }else{
                setting()
            }
        }
    }

    fun alarm(){
        try {
            binding.settingView.visibility = View.GONE
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, AlarmFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }catch (e: Exception){}
    }

    fun setting(){
        try {
            binding.settingView.visibility = View.VISIBLE
            binding.fragmentContainer.removeAllViews()
            clearFragmentContainer()
        } catch (e:Exception){}
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(requireActivity())
            .unregisterReceiver(broadcastReceiver)
    }
}