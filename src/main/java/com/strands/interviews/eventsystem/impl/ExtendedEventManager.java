package com.strands.interviews.eventsystem.impl;

import com.strands.interviews.eventsystem.InterviewEventListener;

import java.util.*;

/**
 * Created by ivanmolera on 19/5/17.
 */
public class ExtendedEventManager extends DefaultEventManager {

    @Override
    public void registerListener(String listenerKey, InterviewEventListener listener)
    {
        super.registerListener(listenerKey, listener);

        Class[] classes = listener.getHandledEventClasses();
        if(classes == null || classes.length == 0)
        {
            Map listeners = super.getListeners();
            if(listeners.values() != null)
            {
                Iterator it = listeners.values().iterator();
                while(it.hasNext())
                {
                    InterviewEventListener eventListener = (InterviewEventListener) it.next();
                    Class[] handledClasses = eventListener.getHandledEventClasses();
                    for (int i = 0; i < handledClasses.length; i++)
                        addToListenerList(handledClasses[i], listener);
                }
            }
        }
    }
}
