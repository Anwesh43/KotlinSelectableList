package com.anwesome.ui.selectablelistkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View

/**
 * Created by anweshmishra on 02/08/17.
 */
class SelectableView(ctx:Context,var text:String):View(ctx) {
    var time = 0
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var selectableItem:SelectableItem?=null
    override fun onDraw(canvas:Canvas) {
        if(time == 0) {
            selectableItem = SelectableItem(text,canvas.width.toFloat(),canvas.height.toFloat())
        }
        selectableItem?.draw(canvas,paint)
        time++
    }
    fun update() {
        selectableItem?.update()
        postInvalidate()
    }
    fun stopped():Boolean = selectableItem?.stoppedUpdating()?:true

    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                selectableItem?.startUpdating()
            }
        }
        return true
    }
    data class SelectableItem(var text:String,var w:Float,var h:Float,var scale:Float = 0.0f,var dir:Int = 0) {
        fun update() {
            scale += 0.2f*dir
            if(scale > 1) {
                scale = 1.0f
                dir = 0
            }
            if(scale < 0) {
                scale = 0.0f
                dir = 0
            }
        }
        fun stoppedUpdating():Boolean = dir == 0
        fun draw(canvas:Canvas,paint:Paint) {
            paint.style = Paint.Style.STROKE
            paint.color = Color.parseColor("#4caf50")
            paint.strokeWidth = w/60
            paint.strokeCap = Paint.Cap.ROUND
            canvas.drawRect(0.0f,0.0f,w,h,paint)
            paint.style = Paint.Style.FILL
            canvas.save()
            canvas.translate(w/2,h/2)
            canvas.save()
            canvas.scale(scale,scale)
            canvas.drawRect(RectF(-w/2,-h/2,w/2,h/2),paint)
            canvas.restore()
            paint.color = Color.WHITE
            paint.textSize = w/20
            canvas.drawText(text,-paint.measureText(text)/2,paint.textSize/2,paint)
            canvas.restore()
        }
        fun startUpdating() {
            dir = when(scale) {
                0.0f -> 1
                1.0f -> -1
                else -> dir
            }
        }
    }
}