package com.example.test.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.OkHttp.MessageEvent;
import com.example.test.R;
import com.example.test.Bean.UserBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private Context context;
    private List<UserBean.DataBean> mData;

    public RecycleAdapter(Context context) {
        this.context = context;
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = mData.get(i).getImages();
        String replace = images.split("\\|")[0].replace("https", "http");
        viewHolder.imageView.setImageURI(Uri.parse(replace));
        viewHolder.text_price.setText(mData.get(i).getTitle());
        viewHolder.text_title.setText(mData.get(i).getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setDatas(List<UserBean.DataBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addDatas(List<UserBean.DataBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        SimpleDraweeView imageView;
        @BindView(R.id.text_title)
        TextView text_title;
        @BindView(R.id.text_price)
        TextView text_price;
        @BindView(R.id.layout)
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
