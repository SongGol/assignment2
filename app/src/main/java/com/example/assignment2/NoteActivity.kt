package com.example.assignment2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignment2.databinding.ActivityNoteBinding
import kotlin.math.log

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private var data: RecyclerItem? = null
    private var id: String = ""
    private var modified: String? = null

    private lateinit var model: NoteViewModel

    //액티비티 연결, 리스너 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("NoteActivity onCreate()", "test")
        binding = ActivityNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
/*
        //live data observer등록
        model = ViewModelProvider(this).get(NoteViewModel::class.java)
        val titleObserver = Observer<String> { newTitle ->
            binding.noteTitle.setText(newTitle)
        }
        val contentObserver = Observer<String> { newContent ->
            binding.noteContent.setText(newContent)
        }
        model.currentTitle.observe(this, titleObserver)
        model.currentContent.observe(this, contentObserver)
*/

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                binding.noteTitle.setText(getString(STATE_TITLE))
                binding.noteContent.setText(getString(STATE_CONTENT))
            }
        } else {
            data = intent?.getParcelableExtra<RecyclerItem>("data")
            modified = data?.modified
            //model.setValues(data?.title, data?.content)

            binding.noteTitle.setText(data?.title)
            binding.noteContent.setText(data?.content)
            binding.noteModified.setText(modified)
        }
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
        //binding.noteTitle.setText(data?.title)
        //binding.noteContent.setText(data?.content)
        binding.noteModified.setText(modified)
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
        SharedPreferenceManager.putObject(applicationContext, id, data)

        //model.setValues(binding.noteTitle.text.toString(), binding.noteContent.text.toString())
    }

    //DB에 데이터 수정 및 저장
    override fun onStop() {
        super.onStop()
        Log.d("NoteActivity onStop()", "test")

        modified = getCurrentTime()
        Toast.makeText(binding.root.context, "저장되었습니다", Toast.LENGTH_SHORT).show()
    }

    //리스너 해제 및 자원정리, 마지막으로 notification띄우기
    override fun onDestroy() {
        super.onDestroy()
        Log.d("NoteActivity onDestroy()", "test")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("NoteActivity onSaveInstanceState()", "test")
        outState?.run {
            putString(STATE_TITLE, binding.noteTitle.text.toString())
            putString(STATE_CONTENT, binding.noteContent.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("NoteActivity onRestoreInstanceState()", "test")
        binding.noteTitle.setText(savedInstanceState.getString(STATE_TITLE))
        binding.noteContent.setText(savedInstanceState.getString(STATE_CONTENT))
    }

    companion object {
        val STATE_TITLE = "noteTitle"
        val STATE_CONTENT = "noteContent"
    }
}