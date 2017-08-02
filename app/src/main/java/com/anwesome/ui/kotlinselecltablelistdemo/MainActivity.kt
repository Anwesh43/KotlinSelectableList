package com.anwesome.ui.kotlinselecltablelistdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.anwesome.ui.selectablelistkotlin.SelectableList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SelectableList.create(this)
        SelectableList.addSelectableItem("Hello")
        SelectableList.addSelectableItem("Iron Man")
        SelectableList.addSelectableItem("Spider Man")
        SelectableList.addSelectableItem("Hello")
        SelectableList.addSelectableItem("Iron Man")
        SelectableList.addSelectableItem("Spider Man")
        SelectableList.addSelectableItem("Hello")
        SelectableList.addSelectableItem("Iron Man")
        SelectableList.addSelectableItem("Spider Man")
        SelectableList.addSelectableItem("Super Man")
        //addContentView(list, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT))
    }
}
