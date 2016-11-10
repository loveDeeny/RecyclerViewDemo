package com.deeny.test.recyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by deeny on 2016/10/26.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<String> list;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv)
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            ViewGroup.LayoutParams params = tv.getLayoutParams();
            //设置图片的相对于屏幕的宽高比
            params.width = AppUtils.getScreenDispaly(context)[0]/3;
            params.height =  (int) (200 + Math.random() * 400) ;
            tv.setLayoutParams(params);
        }
    }
}
