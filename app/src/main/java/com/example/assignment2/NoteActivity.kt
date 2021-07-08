package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

        //Parcelable로 값 받아오기
        val data = intent?.getParcelableExtra<RecyclerItem>("data")
        binding.noteTitle.setText(data?.title)
        binding.noteContent.setText(data?.content)
        binding.noteModified.setText(data?.modified)
    }

    override fun onRestart() {
        super.onRestart()
    }

    //저장 되었습니다 토스트 메시지
    override fun onPause() {
        super.onPause()
    }

    //DB에 데이터 수정 및 저장
    override fun onStop() {
        super.onStop()
    }

    //리스너 해제 및 자원정리, 마지막으로 notification띄우기
    override fun onDestroy() {
        super.onDestroy()
    }
}