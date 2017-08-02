package com.anwesome.ui.selectablelistkotlin

import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by anweshmishra on 02/08/17.
 */

class AnimationRunner(var views:ConcurrentLinkedQueue<SelectableView> = ConcurrentLinkedQueue()):Runnable{
    override fun run() {
        while(views.size > 0) {
            views.forEach { view ->
                if(view.stopped()) {
                    views.remove(view)
                }
                try {
                    Thread.sleep(75)
                }
                catch(ex:Exception) {

                }
                view.update()
            }
        }
    }
    fun addView(view:SelectableView) {
        views.add(view)
    }
}
class AnimHandler(var runner:AnimationRunner = AnimationRunner()) {
    fun addView(view:SelectableView) {
        runner.addView(view)
        if(runner.views.size == 1) {
            var t = Thread(runner)
            t.start()
        }
    }
}