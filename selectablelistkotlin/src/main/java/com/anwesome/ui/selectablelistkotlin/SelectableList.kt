package com.anwesome.ui.selectablelistkotlin

import android.content.Context
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.ViewGroup

/**
 * Created by anweshmishra on 02/08/17.
 */
class SelectableList(context:Context,var deviceW:Int = 0,var deviceH:Int = 0,var animHandler:AnimHandler = AnimHandler()):ViewGroup(context) {
    init {
        getDimension(context)
    }
    private fun getDimension(context: Context) {
        var displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        var display = displayManager.getDisplay(0)
        var size = Point()
        display.getRealSize(size)
        deviceW = size.x
        deviceH = size.y
    }
    fun addSelectableItem(text:String) {
        var view = SelectableView(context,text,animHandler)
        addView(view,LayoutParams(deviceW,deviceH/9))
        requestLayout()
    }
    override fun onMeasure(wspec:Int,hspec:Int) {
        var hView = deviceH/20
        for(i in 0..childCount-1) {
            var view = getChildAt(i)
            measureChild(view,wspec,hspec)
            hView += view.measuredHeight + deviceH/20
        }
        setMeasuredDimension(deviceW,hView)
    }
    override fun onLayout(reloaded:Boolean,a:Int,b:Int,w:Int,h:Int) {
        var y = deviceH/20
        for(i in 0..childCount-1) {
            var view = getChildAt(i)
            view.layout(0,y,w,y+view.measuredHeight)
            y+=view.measuredHeight
        }
    }
}