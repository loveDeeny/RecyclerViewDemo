package com.deeny.test.recyclerviewdemo.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.deeny.test.recyclerviewdemo.R;
import com.deeny.test.recyclerviewdemo.adapter.MyAdapter;
import com.deeny.test.recyclerviewdemo.driver.DividerGridItemDecoration;
import com.deeny.test.recyclerviewdemo.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddOrRemoveActivity extends AppCompatActivity implements MyAdapter.RecylerListener {

    @InjectView(R.id.rv_recycler)
    RecyclerView rv_recycler;
    private List<String> list;
    private MyAdapter myadapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(myadapter != null){
                        myadapter.notifyDataSetChanged();
                    }else {
                        myadapter = new MyAdapter(AddOrRemoveActivity.this,list);
                        myadapter.setRecylerListener(AddOrRemoveActivity.this);
                        rv_recycler.setAdapter(myadapter);
                    }

                    break;
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_remove);
        ButterKnife.inject(this);

        list = new ArrayList<>();

        //initList();
        AppUtils.getData(0,list,handler);
        rv_recycler.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        rv_recycler.addItemDecoration(new DividerGridItemDecoration(this));

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e("单击事件",position+"");
        //单击在此位置添加一项
        if(myadapter != null){
            myadapter.addData(position);
        }

    }

    @Override
    public void onItemLongClick(View view, final int position) {
        Log.e("长单击事件",position+"");
        //长点击删除
        //弹出一个框，选择是否删除
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddOrRemoveActivity.this);
        builder.setTitle(null);
        builder.setMessage("确定删除该消息吗");
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.create().dismiss();

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(myadapter != null){
                    myadapter.removeData(position);
                }
            }
        });
        builder.show();
    }
}
