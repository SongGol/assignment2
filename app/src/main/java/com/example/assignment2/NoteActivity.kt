package com.example.assignment2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2.databinding.ActivityNoteBinding
import kotlin.math.log

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private var data: RecyclerItem? = null
    private var id: String = ""
    private var modified: String? = null

    //액티비티 연결, 리스너 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("NoteActivity onCreate()", "test")
        binding = ActivityNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        data = intent?.getParcelableExtra<RecyclerItem>("data")
        modified = data?.modified
    }

    //리스너, 브로드캐스트 리시버 등록
    override fun onStart() {
        super.onStart()
        Log.d("NoteActivity onStart()", "test")

    }

    //intent에서 값 받은것을 반영
    override fun onResume() {
        super.onResume()
        Log.d("NoteActivity onResume()", "test")
        //id는 항상 있음
        id = (data?.id)?:""
        //Parcelable로 값 받아오기
        binding.noteTitle.setText(data?.title)
        binding.noteContent.setText(data?.content)
        binding.noteModified.setText(data?.modified)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("NoteActivity onRestart()", "test")
    }

    //저장 되었습니다 토스트 메시지
    override fun onPause() {
        super.onPause()
        Log.d("NoteActivity onPause()", "test")

        val data = RecyclerItem(id, binding.noteTitle.text.toString(), binding.noteContent.text.toString(), getCurrentTime())
        intent.putExtra("data", data)

        SharedPreferenceManager.putObject(applicationContext, id, data)
        Log.i("NoteActivity onStop()", "putObject success, id: "+id)
    }

    //DB에 데이터 수정 및 저장
    override fun onStop() {
        super.onStop()
        Log.d("NoteActivity onStop()", "test")

    }

    //리스너 해제 및 자원정리, 마지막으로 notification띄우기
    override fun onDestroy() {
        super.onDestroy()
        Log.d("NoteActivity onDestroy()", "test")
    }
}