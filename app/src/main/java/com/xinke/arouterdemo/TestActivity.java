package com.xinke.arouterdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

// Add annotations on pages that support routing (required)
// The path here needs to pay attention to need at least two levels : /xx/xx    至少2级
@Route(path = "/test/testActivity")
public class TestActivity extends AppCompatActivity {

    @Autowired
    public String userName;
    @Autowired
    public int age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ARouter.getInstance().inject(this);   //注入

        Log.d("TAG", userName + age);


        findViewById(R.id.toHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/homeActivity").navigation();
            }
        });
    }
}