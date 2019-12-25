package com.changyu.celo

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.changyu.celo.manager.timber.CrashReportingTree
import com.changyu.celo.service.ServiceFactory

import timber.log.Timber
import timber.log.Timber.DebugTree
import java.lang.ref.WeakReference


/**
 * ClassName:   App
 *
 * Author:      leeeyou
 * Date:        2018/2/24 14:27
 */
class KotlinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initServiceFactory()
    }

    private fun initServiceFactory() {
        ServiceFactory.DEFAULT_CONTEXT = WeakReference(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }


}