package com.anwesome.ui.selectablelistkotlin

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.ViewGroup
import android.widget.ScrollView

/**
 * Created by anweshmishra on 02/08/17.
 */
class SelectableList(context:Context,var deviceW:Int = 0,var deviceH:Int = 0,var animHandler:AnimHandler = AnimHandler()):ViewGroup(context) {
    init {
        getDimension(context)
        setBackgroundColor(Color.parseColor("#212121"))
    }
    private fun getDimension(context: Context) {
        var displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        var display = displayManager.getDisplay(0)
        var size = Point()
        display.getRealSize(size)
        deviceW = size.x
        deviceH = size.y
    }
    fun addSelectableItem(text:String,vararg listeners: OnSelectedListener) {
        var view = SelectableView(context,text,animHandler)
        view.listeners = listeners
        addView(view, LayoutParams(deviceW,deviceH/9))
        requestLayout()
    }
    override fun onMeasure(wspec:Int,hspec:Int) {
        var hView = deviceH/20
        for(i in 0..childCount-1) {
            var view = getChildAt(i)
            measureChild(view,wspec,hspec)
            hView += view.measuredHeight + deviceH/20
        }
        setMeasuredDimension(deviceW,hView+deviceH/5)
    }
    override fun onLayout(reloaded:Boolean,a:Int,b:Int,w:Int,h:Int) {
        var y = deviceH/20
        for(i in 0..childCount-1) {
            var view = getChildAt(i)
            view.layout(0,y,w,y+view.measuredHeight)
            y+=view.measuredHeight+deviceH/10
        }
    }
    companion object {
        var view:SelectableList?=null
        var scrollView:ScrollView?=null
        fun create(activity:Activity) {
            if(view == null) {
                view = SelectableList(activity)
                var scrollView = ScrollView(activity)
                scrollView.addView(view,LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT))
                activity.addContentView(scrollView,LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT))
            }
        }
        fun addSelectableItem(text:String,vararg listeners: OnSelectedListener) {
            if(listeners.size == 1) {
                view?.addSelectableItem(text,listeners[0])
            }
            else {
                view?.addSelectableItem(text)
            }
            scrollView?.requestLayout()
        }
    }
}