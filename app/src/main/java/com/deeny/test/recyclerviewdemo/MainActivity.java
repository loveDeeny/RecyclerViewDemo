package com.deeny.test.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.rv_recycler)
    RecyclerView rv_recycler;
    private List<String> list;//数据源
    private MyAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initList();

        //listview的效果
        /*rv_recycler.setLayoutManager(new LinearLayoutManager(this));
        rv_recycler.setAdapter(myadapter = new MyAdapter(this,list));
        rv_recycler.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));*/
        //gridview的效果
        /*rv_recycler.setLayoutManager(new GridLayoutManager(this,2));
        rv_recycler.setAdapter(myadapter = new MyAdapter(this,list));
        rv_recycler.addItemDecoration(new DividerGridItemDecoration(this));*/
        //瀑布流的样式：
        rv_recycler.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        rv_recycler.setAdapter(myadapter = new MyAdapter(this,list));
        rv_recycler.addItemDecoration(new DividerGridItemDecoration(this));

    }

    private void initList(){
        list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add("第"+i+"条");
        }
    }
}
