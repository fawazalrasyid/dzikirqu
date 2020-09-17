package com.mayburger.dzikirqu.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.mayburger.dzikirqu.R
import com.mayburger.dzikirqu.BR
import com.mayburger.dzikirqu.databinding.ActivitySplashBinding
import com.mayburger.dzikirqu.ui.base.BaseActivity
import com.mayburger.dzikirqu.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(),SplashNavigator{
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel:SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycleScope.launchWhenCreated {
//            viewModel.getBookData()
//        }
//
        startActivity(Intent(this,MainActivity::class.java))
        finishActivity()
    }
}