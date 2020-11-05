package com.adematici.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private var adapter : TimeAdapter? = null

    private lateinit var timeArrayList: ArrayList<String>
    private var time: String? = ""

    var runnable: Runnable = Runnable {  }
    var handler: Handler = Handler()

    private var second: Int = 0
    private var minute: Int = 0
    private var hour: Int = 0

    private var text: String = "$hour:$minute:$second"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timeArrayList = ArrayList()
        timeArrayList.clear()

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager

        adapter = TimeAdapter(timeArrayList)
        recyclerView.adapter = adapter

    }


    fun buttonStart(view: View){
        timeArrayList.clear()
        second = 0
        minute = 0
        hour = 0

        runnable = object : Runnable{
            override fun run() {
                if(second == 60){
                    second = 0
                    minute++
                    if(minute == 60){
                        minute = 0
                        hour++
                    }
                }
                second++
                text = "$hour:$minute:$second"
                textViewMeter.text = text
                handler.postDelayed(this,1000)
            }
        }
        handler.post(runnable)
        button.setEnabled(false)
    } // start

    fun buttonLap(view: View){
        if(time != null){
            time = textViewMeter.text.toString()
            timeArrayList.add(time!!)
            adapter!!.notifyDataSetChanged()
        }
    }

    fun buttonStop(view: View){
        button.setEnabled(true)
        second = 0
        minute = 0
        hour = 0
        text = "$hour:$minute:$second"
        textViewMeter.text = text
        handler.removeCallbacks(runnable)
    }

}