package com.xinke.arouterdemo.animation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import androidx.core.app.ActivityOptionsCompat;

import com.xinke.arouterdemo.R;

public class AnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);


//        ActivityOptionsCompat.makeCustomAnimation()
//        ActivityOptionsCompat.makeClipRevealAnimation()
//        ActivityOptionsCompat.makeScaleUpAnimation()
//        ActivityOptionsCompat.makeSceneTransitionAnimation()
//        ActivityOptionsCompat.makeThumbnailScaleUpAnimation()

    }


    public void normal(View view) {
        Fade fade = new Fade();//渐隐
        fade.setDuration(1000);
        Slide slide = new Slide(Gravity.END);//右边平移
        slide.setDuration(1000);
        Explode explode = new Explode();//展开回收
        explode.setDuration(1000);
//        getWindow().setEnterTransition(slide);
        getWindow().setReenterTransition(explode);
//        getWindow().setExitTransition(explode);
//        getWindow().setAllowEnterTransitionOverlap(false);
//        getWindow().setAllowReturnTransitionOverlap(false);


        Intent i = new Intent(this, Normal.class);
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this);
//      两种方法都能用，不要问我源码怎么实现，没空研究了
//      ActivityOptionsCompat transitionActivityOptions =ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(i, transitionActivityOptions.toBundle());
//        startActivity(new Intent(this,Normal.class));
    }

    public void makeClipRevealAnimation(View view) {

        startActivity(new Intent(this, ClipRevealAnimation.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    public void makeCustomAnimation(View view) {
    }

    public void makeScaleUpAnimation(View view) {
    }

    public void makeSceneTransitionAnimation(View view) {
    }

    public void makeThumbnailScaleUpAnimation(View view) {

    }

}
