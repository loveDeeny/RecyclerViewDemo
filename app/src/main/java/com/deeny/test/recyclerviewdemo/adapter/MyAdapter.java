package com.deeny.test.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deeny.test.recyclerviewdemo.R;
import com.deeny.test.recyclerviewdemo.utils.AppUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by deeny on 2016/10/26.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<String> list;
    private RecylerListener recylerListener;

    public void setRecylerListener(RecylerListener recylerListener) {
        this.recylerListener = recylerListener;
    }

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        //如果设置了回掉
        if(recylerListener != null){
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    recylerListener.onItemClick(holder.ll,pos);
                }
            });

            holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    recylerListener.onItemLongClick(holder.ll,pos);
                    return true;//这里一定要记得返回true，不然事件没有被消耗掉，还会再次执行单击事件
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(int position) {
        list.add(position, "add this position");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv)
        TextView tv;
        @InjectView(R.id.ll)
        LinearLayout ll;
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


    public interface RecylerListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
