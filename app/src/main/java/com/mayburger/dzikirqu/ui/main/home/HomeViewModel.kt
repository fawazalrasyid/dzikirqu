package com.mayburger.dzikirqu.ui.main.home

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.mayburger.dzikirqu.constants.LocaleConstants
import com.mayburger.dzikirqu.data.DataManager
import com.mayburger.dzikirqu.model.PrayerTime
import com.mayburger.dzikirqu.model.events.QuranLastReadEvent
import com.mayburger.dzikirqu.ui.adapters.viewmodels.ItemBookViewModel
import com.mayburger.dzikirqu.ui.base.BaseViewModel
import com.mayburger.dzikirqu.util.StringProvider
import com.mayburger.dzikirqu.util.praytimes.PrayerTimeHelper
import com.mayburger.dzikirqu.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.text.SimpleDateFormat
import java.util.*


class HomeViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<HomeNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
        when (obj) {
            is QuranLastReadEvent -> {
                buildLastRead()
            }
        }
    }

    val lastReadSurah = ObservableField("")
    val lastReadVerse = ObservableField("")

    init {
        buildLastRead()
    }

    fun buildLastRead() {
        dataManager.quranLastRead?.let {
            lastReadSurah.set(it.surah.name)
            lastReadVerse.set(
                String.format(
                    StringProvider.getInstance().getString(LocaleConstants.VERSE_NO_N),
                    it.verse.id.toString()
                )
            )
        }
    }

    val _refreshBooks = MutableLiveData(false)
    val books = _refreshBooks.switchMap {
        liveData(IO) {
            try {
                if (dataManager.getAllBooks().isNotEmpty()) {
                    emit(dataManager.getAllBooks().filter { it.language == dataManager.language }
                        .map { ItemBookViewModel(it) }.toList())
                }
                emit(dataManager.getBooks().filter { it.data.language == dataManager.language })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    val prayerTime = liveData(Main) {
        try {
            val hawk = PrayerTimeHelper.getPrayerTimeFromHawk()
            emit(hawk)
            val new = dataManager.getPrayerTime()
            emit(new)
        } catch (e: java.lang.Exception) {
            println("EXACT ${e.message}")
        }
    }

    var newtimer: CountDownTimer? = null
    var prayerTimeText = ObservableField("Loading..")
    var prayerUntilTime = ObservableField("Loading...")
    fun buildPrayerTime(prayer: PrayerTime) {
        newtimer?.cancel()
        newtimer = object : CountDownTimer(864000000, 1000) {
            override fun onFinish() {}

            @SuppressLint("SimpleDateFormat")
            override fun onTick(millisUntilFinished: Long) {
                try {
                    val cal = Calendar.getInstance()
                    val date = cal.time
                    val dateFormat = SimpleDateFormat("HH:mm")
                    val now = dateFormat.format(date)
                    if (prayer.address != "Loading...") {
                        prayer.let { PrayerTimeHelper.buildPrayerTime(prayerTimeText, now, it) }
                        prayer.let { PrayerTimeHelper.buildPrayerUntil(prayerUntilTime, now, it) }
                    }
                } catch (e: Exception) {
                }
            }
        }
        newtimer?.start()
    }

    fun onClickPrayTime() {
        navigator?.onClickPrayTime()
    }

    fun onClickReadQuran() {
        navigator?.onClickReadQuran()
    }

    fun onClickLastRead(){
        navigator?.onClickLastRead()
    }

    override fun onCleared() {
        super.onCleared()
        if (newtimer != null) {
            (newtimer as CountDownTimer).cancel()
        }
    }

//    private var _prayerTime:MutableLiveData<PrayerTime> = MutableLiveData()
//    val prayerTime : LiveData<PrayerTime> = _prayerTime
//
//    fun getPrayers(context: Context){
//        _prayerTime.postValue(PrayerTimeHelper.getPrayerTime(context).value)
//    }
}