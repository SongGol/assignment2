package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //액티비티 연결, 리스너 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var dataSet: ArrayList<ViewItem> = arrayListOf(ViewItem("t1","c1","1-1-1"), ViewItem("t2","c2","1-1-1"))

        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainRecyclerview.adapter = MainAdapter(dataSet)
        binding.mainRecyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    //리스너, 브로드캐스트 리시버 등록
    override fun onStart() {
        super.onStart()
    }

    //DB에서 값 읽어오기
    override fun onResume() {
        super.onResume()
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