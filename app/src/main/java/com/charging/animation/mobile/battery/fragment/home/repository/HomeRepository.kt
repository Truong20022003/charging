package com.charging.animation.mobile.battery.fragment.home.repository

import android.content.Context
import com.charging.animation.mobile.battery.api.Api
import com.charging.animation.mobile.battery.api.Results
import com.charging.animation.mobile.battery.api.RetrofitClient
import com.charging.animation.mobile.battery.database.Database
import com.charging.animation.mobile.battery.fragment.home.listener.Listener
import com.charging.animation.mobile.battery.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object HomeRepository {
    var listAll: MutableList<Results> = ArrayList()
    var listRecommend: MutableList<Results> = ArrayList()
    var listBlack: MutableList<Results> = ArrayList()
    var listCat: MutableList<Results> = ArrayList()
    var listChill: MutableList<Results> = ArrayList()
    var listFantasy: MutableList<Results> = ArrayList()
    var listFire: MutableList<Results> = ArrayList()
    var listFlower: MutableList<Results> = ArrayList()
    var listHanddrawing: MutableList<Results> = ArrayList()
    var listHorror: MutableList<Results> = ArrayList()
    var listLofi: MutableList<Results> = ArrayList()
    var listNeon: MutableList<Results> = ArrayList()
    var listAnimal: MutableList<Results> = ArrayList()
    fun getAllApi(context: Context?, listener: Listener) {
        val call = RetrofitClient.getInstance().api.all
        call.enqueue(object : Callback<List<Results>?> {
            override fun onResponse(
                call: Call<List<Results>?>,
                response: Response<List<Results>?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    clear()
                    for (result in response.body()!!) {
                        when (result.folder) {
                            Constants.Black -> listBlack.add(result)
                            Constants.Cat -> listCat.add(result)
                            Constants.Chill -> listChill.add(result)
                            Constants.Fantasy -> listFantasy.add(result)
                            Constants.Fire -> listFire.add(result)
                            Constants.Flower -> listFlower.add(result)
                            Constants.Handdrawing -> listHanddrawing.add(result)
                            Constants.Horror -> listHorror.add(result)
                            Constants.Lofi -> listLofi.add(result)
                            Constants.Neon -> listNeon.add(result)
                            Constants.Animal -> listAnimal.add(result)
                            else -> {}
                        }
                    }
                    checkDownSuccess(context, listBlack, Constants.Black)
                    checkDownSuccess(context, listCat, Constants.Cat)
                    checkDownSuccess(context, listChill, Constants.Chill)
                    checkDownSuccess(context, listFantasy, Constants.Fantasy)
                    checkDownSuccess(context, listFire, Constants.Fire)
                    checkDownSuccess(context, listFlower, Constants.Flower)
                    checkDownSuccess(context, listHanddrawing, Constants.Handdrawing)
                    checkDownSuccess(context, listHorror, Constants.Horror)
                    checkDownSuccess(context, listLofi, Constants.Lofi)
                    checkDownSuccess(context, listNeon, Constants.Neon)
                    checkDownSuccess(context, listAnimal, Constants.Animal)
                    listRecommend.addAll(listAll)
                    listRecommend.shuffle()
                    listener.onSuccess()
                } else {
                    clear()
                    listener.onFail()
                }
            }

            override fun onFailure(call: Call<List<Results>?>, t: Throwable) {
                clear()
                listener.onFail()
            }
        })
    }

    fun checkDownSuccess(context: Context?, data: MutableList<Results>, folder: String?) {
        val list = Database.getInstance(context).resultsDao().getAll(folder)
        for (results in data) {
            results.free = true
        }
        listAll.addAll(list)
        for (i in data.indices) {
            val results = data[i]
            results.name = results.link
            val link = Api.BASE_URL + "app/V020ChargingAnimation/" + results.link
            results.link = link
            results.type = 1
            results.folder = folder
            if (i == 0 || i == 1) {
                results.free = true
            }
            val arr = link.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (check(arr[arr.size - 1])) {
                listAll.add(results)
                list.add(results)
            }
        }
        data.clear()
        data.addAll(list)
        data.shuffle()
    }

    fun check(link: String): Boolean {
        for (i in listAll.indices) {
            val arr =
                listAll[i].link.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (arr[arr.size - 1] == link) {
                return false
            }
        }
        return true
    }

    fun clear() {
        listAll.clear()
        listRecommend.clear()
        listBlack.clear()
        listCat.clear()
        listChill.clear()
        listFantasy.clear()
        listFire.clear()
        listFlower.clear()
        listHanddrawing.clear()
        listHorror.clear()
        listLofi.clear()
        listNeon.clear()
        listAnimal.clear()
    }
}