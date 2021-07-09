package com.example.assignment2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    //startActivityForResult를 대체
    private lateinit var requestActivity: ActivityResultLauncher<Intent>

    var dataSet: ArrayList<RecyclerItem> = arrayListOf(RecyclerItem("1","t1","c1","1-1-1"), RecyclerItem("2","t2","c2","1-1-1"))

    //액티비티 연결, 리스너 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity onCreate()", "test")

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        mainAdapter = MainAdapter(dataSet)
        //layout manager설정
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainRecyclerview.adapter = mainAdapter
        binding.mainRecyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        //btn listener 선언


        //itemTouchHelper선언
        val itemTouchHelper = ItemTouchHelper(RecyclerItemTouchHelper(mainAdapter))
        itemTouchHelper.attachToRecyclerView(binding.mainRecyclerview)

        //여기사 리스너 선언
    }

    //리스너, 브로드캐스트 리시버 등록
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity onStart()", "test")
        //노트 추가버튼
        binding.addItemBtn.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            val data = RecyclerItem(getCurrentTime(), "", "", "")
            intent.putExtra("data", data)
            requestActivity.launch(intent)
        }
        //노트 삭제 버튼
        binding.deleteItemBtn.setOnClickListener {

        }
        //노트 탐색 버튼
        binding.searchItemBtn.setOnClickListener {

        }

        requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                activityResult ->

            if (activityResult.resultCode == Activity.RESULT_OK) {
                Log.i("requestActivity", "in here")
                val dataIntent = activityResult.data
                val data: RecyclerItem? = dataIntent?.getParcelableExtra<RecyclerItem>("data")
                if (data != null) {
                    dataSet.add(data)
                    binding.mainRecyclerview.adapter?.notifyDataSetChanged()
                }
            }

        }
    }

    //DB에서 값 읽어오기
    override fun onResume() {
        super.onResume()
        Log.d("MainActivity onResume()", "test")
        //dataSet.add(RecyclerItem(getCurrentTime(),"t3", "c3", ""))

        val allEntries: Map<String, *> = getSharedPreferences("note_pref", Context.MODE_PRIVATE).all
        for ((key, value) in allEntries) {
            Log.d("map key: ", key)
            Log.d("map value: ", (SharedPreferenceManager.getObject(applicationContext, key, RecyclerItem(key,"","",""))).toString())
            //Log.d("map values", key + ": " + SharedPreferenceManager.getObject(applicationContext, key, RecyclerItem()).title)
            dataSet.add(SharedPreferenceManager.getObject(applicationContext, key, RecyclerItem(key,"","","")))
        }
        Log.i("MainActivity onResume()", "end")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity onRestart()", "test")
        //
        dataSet.clear()
        binding.mainRecyclerview.adapter?.notifyDataSetChanged()
    }

    //저장 되었습니다 토스트 메시지
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity onPause()", "test")
    }

    //DB에 데이터 삭제 및 수정
    override fun onStop() {
        super.onStop()
        Log.d("MainActivity onStop()", "test")
    }

    //리스너 해제 및 자원정리, 마지막으로 notification띄우기
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity onDestroy()", "test")
    }
}

fun getCurrentTime(): String {
    val date = Date()
    val format = SimpleDateFormat("yy-MM-dd HH:mm:ss")
    return format.format(date).toString()
}