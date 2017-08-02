package com.anwesome.ui.kotlinselecltablelistdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import com.anwesome.ui.selectablelistkotlin.OnSelectedListener
import com.anwesome.ui.selectablelistkotlin.SelectableList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SelectableList.create(this)
        var items:Array<String> = arrayOf("Wonder Woman","Iron Man","Spider Man","Super Man","Ant Man","Bat Man","Aqua Man","Green Arrow","Flash")
        items.forEach { item ->
            SelectableList.addSelectableItem(item,CustomSelectedListener(this,item))
        }
        //addContentView(list, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT))
    }
    class CustomSelectedListener(var activity: MainActivity,var text:String):OnSelectedListener {
        override fun onSelect(text: String) {
            Toast.makeText(activity,"selected $text",Toast.LENGTH_SHORT).show()
        }

        override fun onUnSelect(text: String) {
            Toast.makeText(activity,"unselected $text",Toast.LENGTH_SHORT).show()
        }
    }
}
