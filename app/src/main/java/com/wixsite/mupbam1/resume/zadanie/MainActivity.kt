package com.wixsite.mupbam1.resume.zadanie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.wixsite.mupbam1.resume.zadanie.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = PlantAdapter()
    private val imageIdList = listOf(
        R.drawable.kblueberry,
        R.drawable.klyukva,
        R.drawable.kmalina,
        R.drawable.kryejovnik,
        R.drawable.kvictory,
    )
    private var index = 0
    var string="somString"

//    private var doc: Document? = null
//    private var secThread: Thread? = null
//    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val url="https://dev-tasks.alef.im/task-m-001/list.php"
       urlTread(url)
        init()
//        initThread()
    }

     fun urlTread(url: String) {
        var arr = ""

        thread {
           val apiResponse = URL(url).readText()
            val dropStart = apiResponse.drop(1)
            val dropEnd = dropStart.dropLast(1)
            var http=dropEnd.split(",")
            val size=http.size
            arr=arr+http[10].toString()
            Log.d("MyLog","url-${http[10]}, position[$size]")
            Log.d("MyLog","url*$arr}, position[$size]")
            string=http[10]
       }
        Log.d("MyLog","url+${arr}")
    }

    fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rcView.adapter = adapter
            buttonAdd.setOnClickListener {
                if(index > 4) index = 0
                val plant = Plant(imageIdList[index], "Plant $string")
                adapter.addPlant(plant)
                index++
            }
        }
    }
//    private fun initThread() {
//        runnable = Runnable { getWeb() }
//        secThread = Thread(runnable)
//        secThread!!.start()
//    }
//
//    private fun getWeb() {
//        try {
//            doc = Jsoup.connect("https://dev-tasks.alef.im/task-m-001/list.php").get()
//            Log.d("MyLog", "body- $doc")
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }

}