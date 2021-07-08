package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //startActivityForResult를 대체
    private lateinit var requestActivity: ActivityResultLauncher<Intent>

    var dataSet: ArrayList<RecyclerItem> = arrayListOf(RecyclerItem("1","t1","c1","1-1-1"), RecyclerItem("2","t2","c2","1-1-1"))

    //액티비티 연결, 리스너 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //layout manager설정
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainRecyclerview.adapter = MainAdapter(dataSet)
        binding.mainRecyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        //btn listener 선언

    }

    //리스너, 브로드캐스트 리시버 등록
    override fun onStart() {
        super.onStart()

        //노트 추가버튼
        binding.addItemBtn.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            val data = RecyclerItem(getCurrentTime(), "", "", getCurrentTime())
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
        }
    }

    //DB에서 값 읽어오기
    override fun onResume() {
        super.onResume()
        dataSet.add(RecyclerItem(getCurrentTime(),"t3", "c3", getCurrentTime()))
    }

    override fun onRestart() {
        super.onRestart()
    }

    //저장 되었습니다 토스트 메시지
    override fun onPause() {
        super.onPause()
    }

    //DB에 데이터 삭제 및 수정
    override fun onStop() {
        super.onStop()
    }

    //리스너 해제 및 자원정리, 마지막으로 notification띄우기
    override fun onDestroy() {
        super.onDestroy()
    }
}

fun getCurrentTime(): String {
    val date = Date()
    val format = SimpleDateFormat("yy-MM-dd HH:mm:ss")
    return format.format(date)
}