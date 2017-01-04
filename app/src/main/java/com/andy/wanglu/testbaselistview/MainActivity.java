package com.andy.wanglu.testbaselistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mMultiType, mLoadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMultiType = (Button) findViewById(R.id.multitype);
        mLoadMore = (Button) findViewById(R.id.loadmore);
        mLoadMore.setOnClickListener(this);
        mMultiType.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.multitype:
                Intent intent = new Intent(this, MultiTypeActivity.class);
                startActivity(intent);
                break;
            case R.id.loadmore:
                Intent intent1 = new Intent(this, LoadMoreActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
