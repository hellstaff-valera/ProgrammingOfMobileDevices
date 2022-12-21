package com.hellstaff.threadpoolexecutor

import android.app.Activity
import android.widget.ProgressBar

public class CounterThread(progressBar: ProgressBar,activity:Activity):Thread()
{
    var progressBar=progressBar
    var activity=activity

    override fun run() {
        super.run()
        for (progress in 0..100 step 10) // step is use to increase count by 10 (it shows jump)
        {

            (activity as ExecutorActivity).runOnUiThread {
               progressBar.progress = progress // here we set progress android
             }
            Thread.sleep(1000)
        }
    }
}