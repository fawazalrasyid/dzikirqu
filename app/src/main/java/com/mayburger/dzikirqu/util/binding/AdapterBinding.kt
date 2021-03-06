package com.mayburger.dzikirqu.util.binding

import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.dzikirqu.R
import com.mayburger.dzikirqu.ui.adapters.*
import com.mayburger.dzikirqu.ui.adapters.viewmodels.*


object AdapterBinding {
//    @BindingAdapter("checkoutAdapter")
//    @JvmStatic
//    fun addCheckoutItems(
//        recyclerView: RecyclerView,
//        items: MutableLiveData<ArrayList<ItemCheckoutViewModel>>
//    ) {
//        val adapter = recyclerView.adapter as CheckoutAdapter?
//        if (adapter != null) {
//            items.value?.let {
//                adapter.clearItems()
//                adapter.addItems(it)
//            }
//        }
//    }


    @BindingAdapter("booksAdapter")
    @JvmStatic
    fun addBooksItems(
        recyclerView: RecyclerView,
        items: LiveData<List<ItemBookViewModel>>
    ) {
        val adapter = recyclerView.adapter as BookAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
                if (!adapter.isLoaded) {
                    val context = recyclerView.context;
                    val controller =
                        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fade);
                    recyclerView.layoutAnimation = controller;
                    recyclerView.scheduleLayoutAnimation()
                    adapter.isLoaded = true
                }
            }
        }
    }

    @BindingAdapter("surahAdapter")
    @JvmStatic
    fun addSurahItems(
        recyclerView: RecyclerView,
        items: LiveData<List<ItemSurahViewModel>>
    ) {
        val adapter = recyclerView.adapter as SurahAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
            }
            if (!adapter.isLoaded) {
                val context = recyclerView.context;
                val controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fade);
                recyclerView.layoutAnimation = controller;
                recyclerView.scheduleLayoutAnimation()
                adapter.isLoaded = true
            }
        }
    }
    @BindingAdapter("juzAdapter")
    @JvmStatic
    fun addJuzItems(
        recyclerView: RecyclerView,
        items: LiveData<List<ItemJuzViewModel>>
    ) {
        val adapter = recyclerView.adapter as JuzAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
            }
            if (!adapter.isLoaded) {
                val context = recyclerView.context;
                val controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fade);
                recyclerView.layoutAnimation = controller;
                recyclerView.scheduleLayoutAnimation()
                adapter.isLoaded = true
            }
        }
    }

    @BindingAdapter("quranBookmarkAdapter")
    @JvmStatic
    fun addQuranBookmarkItems(
        recyclerView: RecyclerView,
        items: LiveData<List<ItemQuranBookmarkViewModel>>
    ) {
        val adapter = recyclerView.adapter as QuranBookmarkAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
            }
            if (!adapter.isLoaded) {
                val context = recyclerView.context;
                val controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fade);
                recyclerView.layoutAnimation = controller;
                recyclerView.scheduleLayoutAnimation()
                adapter.isLoaded = true
            }
        }
    }

    @BindingAdapter("ayahAdapter")
    @JvmStatic
    fun addAyahs(
        recyclerView: RecyclerView,
        items: LiveData<List<Any>>
    ) {
        val adapter = recyclerView.adapter as AyahAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
            }
        }
    }

    @BindingAdapter("searchAyahAdapter")
    @JvmStatic
    fun addSearchAyahs(
        recyclerView: RecyclerView,
        items: LiveData<List<ItemSearchAyahViewModel>>
    ) {
        val adapter = recyclerView.adapter as SearchAyahAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
            }
        }
    }

    @BindingAdapter("prayerAdapter")
    @JvmStatic
    fun addBookListItems(
        recyclerView: RecyclerView,
        items: LiveData<List<ItemPrayerViewModel>>
    ) {
        val adapter = recyclerView.adapter as PrayerAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
//                if (!adapter.isLoaded) {
//                    val context = recyclerView.context;
//                    val controller =
//                        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fade);
//                    recyclerView.layoutAnimation = controller;
//                    recyclerView.scheduleLayoutAnimation()
//                    adapter.isLoaded = true
//                }
            }
        }
    }
    @BindingAdapter("prayerPageAdapter")
    @JvmStatic
    fun addPrayerPageItems(
        recyclerView: RecyclerView,
        items: LiveData<List<PagePrayerViewModel>>
    ) {
        val adapter = recyclerView.adapter as PrayerPagerAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
            }
        }
    }
}