package com.example.assignment2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    //액티비티 연결, 리스너 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    //리스너, 브로드캐스트 리시버 등록
    override fun onStart() {
        super.onStart()

    }

    //DB에서 값 읽어오기
    override fun onResume() {
        super.onResume()

        //현재 시간 값 읽어오기

        //이미 생성된 항목에서 intent로 값을 보내면 그 값을 받아 editText에 넣어줌
        binding.noteTitle.setText(intent.getStringExtra("title"))
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