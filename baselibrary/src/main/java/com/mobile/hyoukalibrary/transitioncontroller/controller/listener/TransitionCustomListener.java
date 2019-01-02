package com.mobile.hyoukalibrary.transitioncontroller.controller.listener;

import android.animation.Animator;

/**
 */
public interface TransitionCustomListener {

    public void onTransitionStart(Animator animator);

    public void onTransitionEnd(Animator animator);

    public void onTransitionCancel(Animator animator);

}
