package com.ehsansetayesh.core.common.extension

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import kotlin.system.exitProcess

fun Context.closeApplication() {
    // Finish all activities
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (activity in activityManager.appTasks) {
        activity.finishAndRemoveTask()
    }

    // Terminate the process
    Process.killProcess(Process.myPid())
    exitProcess(0)
}
