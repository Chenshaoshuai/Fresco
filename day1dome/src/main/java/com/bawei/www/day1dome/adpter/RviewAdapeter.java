package com.bawei.www.day1dome.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.www.day1dome.R;
import com.bawei.www.day1dome.bean.FoodBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RviewAdapeter extends RecyclerView.Adapter<RviewAdapeter.ViewHolder> {

    private Context context;
    private List<FoodBean.DataBean.TuijianBean.ListBeanX> mlist;

    public RviewAdapeter(Context context) {
        this.context = context;
        mlist=new ArrayList<>();
    }


    @NonNull
    @Override
    public RviewAdapeter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.rview_item, null);
        ViewHolder vh =new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RviewAdapeter.ViewHolder viewHolder, int i) {
        ViewHolder vh1= viewHolder;
        vh1.item_title.setText(mlist.get(i).getTitle());

        Pattern pen = Pattern.compile("\\|");

        String images = mlist.get(i).getImages();

        String[] split = pen.split(images);

        vh1.item_img.setImageURI(split[0]);



    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<FoodBean.DataBean.TuijianBean.ListBeanX> listBeanXES) {
        this.mlist = listBeanXES;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView item_img;
        private final TextView item_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_title = itemView.findViewById(R.id.item_title);
        }
    }
}
