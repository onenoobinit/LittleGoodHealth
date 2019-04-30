package com.youyijia.hyoukalibrary.transitioncontroller.controller;

/**
 * 描述:
 * <p>
 * 工程:过场动画使用
 *
 * https://github.com/zhangke3016/TranslationCompat
 */
public class Test {
    //参数一：当前Activity
//参数二：跳转意图
//参数三：当前页面跳转至下一页面的View
//参数四：下一页面关联的View id
//    TransitionController.getInstance().startActivity(this,new Intent(this, RegisterActivity.class),fab,R.id.fab);
//
////跳转后页面调用：
//    TransitionController.getInstance().show(this,getIntent());
//    可在show方法调用之前设置监听：
//            TransitionController.getInstance().setEnterListener(new TransitionCustomListener() {
//        @Override
//        public void onTransitionStart(Animator animator) {
//        }
//        @Override
//        public void onTransitionEnd(Animator animator) {
//        }
//        @Override
//        public void onTransitionCancel(Animator animator) {
//        }
//    });
//
////界面退出的时候调用
//    TransitionController.getInstance().exitActivity(PageDetailActivity.this);
//
////增加界面圆形转换动画
//// 用法及参数和ViewAnimationUtils一致
//    ViewAnimationCompatUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
//
//    //增加界面矩形转换动画
//    Animator mAnimator = ViewAnimationCompatUtils.createRectReveal( nsv, 0, nsv.getHeight(),ViewAnimationCompatUtils.RECT_TOP);

}
