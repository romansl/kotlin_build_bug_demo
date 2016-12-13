package com.romansl.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.romansl.MyEventBusIndex
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe



/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {
    private val eventBus = EventBus.builder().addIndex(MyEventBusIndex()).build()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById(R.id.text).setOnClickListener {
            eventBus.post(Event())
        }
    }

    override fun onResume() {
        super.onResume()
        eventBus.register(this)
    }

    override fun onPause() {
        super.onPause()
        eventBus.unregister(this)
    }

    @Subscribe
    fun onEvent(event: Event) {
        Log.d("Bus", "OnEvent")
    }

    class Event
}
