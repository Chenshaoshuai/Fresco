package com.bawei.www.day1dome.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.www.day1dome.Apis;
import com.bawei.www.day1dome.R;
import com.bawei.www.day1dome.adpter.RviewAdapeter;
import com.bawei.www.day1dome.bean.BannerBean;
import com.bawei.www.day1dome.bean.FoodBean;
import com.bawei.www.day1dome.okhttputil.Httputil;
import com.bawei.www.day1dome.okhttputil.ICallBack;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class HomeFragment extends Fragment {

    private Banner baner;
    private RecyclerView rview;
    private RviewAdapeter rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        baner = view.findViewById(R.id.baner);
        rview = view.findViewById(R.id.rview);

        setRequest();

        setRecyclView();
        return view;
    }

    private void setRecyclView() {

        rv = new RviewAdapeter(getActivity());
        rview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rview.setAdapter(rv);

        Httputil.getInstance().getEnqueue(Apis.URL, FoodBean.class, new ICallBack() {
            @Override
            public void success(Object obj) {
                FoodBean foodBean = (FoodBean) obj;
                List<FoodBean.DataBean.TuijianBean.ListBeanX> listBeanXES = foodBean.getData().getTuijian().getList();
                rv.setData(listBeanXES);
            }

            @Override
            public void failed(Exception e) {

            }
        });

    }


    private void setRequest() {
        Httputil.getInstance().getEnqueue(Apis.BANNERURL, BannerBean.class, new ICallBack() {
            @Override
            public void success(Object obj) {
                final BannerBean bannerBean = (BannerBean) obj;
                baner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                baner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        BannerBean.NewslistBean bannerBean1 = (BannerBean.NewslistBean) path;
                        imageView.setImageURI(Uri.parse(bannerBean1.getPicUrl()));
                    }

                    @Override
                    public ImageView createImageView(Context context) {
                        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                        GenericDraweeHierarchyBuilder builder= new GenericDraweeHierarchyBuilder(getResources());
                        RoundingParams roundingParams =RoundingParams.fromCornersRadii(20.0f,20.0f,20.0f,20.0f);
                        GenericDraweeHierarchy builder1= builder.setRoundingParams(roundingParams).build();
                        simpleDraweeView.setHierarchy(builder1);
                        return simpleDraweeView;
                    }
                });

                baner.setImages(bannerBean.getNewslist());
                baner.start();

            }



            @Override
            public void failed(Exception e) {

            }
        });
    }

}
