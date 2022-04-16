package com.fionicholas.moviecatalogue.core.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> singleScheduler(subscriberScheduler: Scheduler = Schedulers.io(), observerScheduler: Scheduler = AndroidSchedulers.mainThread()): SingleSchedulerTransformer<T> {
    return SingleSchedulerTransformer(
        subscriberScheduler,
        observerScheduler
    )
}

fun <T> completableScheduler(subscriberScheduler: Scheduler = Schedulers.io(), observerScheduler: Scheduler = AndroidSchedulers.mainThread()): CompletableSchedulerTransformer {
    return CompletableSchedulerTransformer(subscriberScheduler, observerScheduler)
}