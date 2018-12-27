package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test.Adapter.RecycleAdapter;
import com.example.test.Bean.UserBean;
import com.example.test.OkHttp.MessageEvent;
import com.example.test.OkHttp.OkHttp;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    XRecyclerView xRecyclerView;
    private RecycleAdapter adapter;
    private int mPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        xRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter(this);
        xRecyclerView.setAdapter(adapter);
        mPage=1;
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                mPage++;
                loadData();
            }
        });

        loadData();
    }

    private void loadData() {
        Map<String,String> map=new HashMap<>();
        map.put("keywords","笔记本");
        map.put("page",mPage+"");
        OkHttp.getInstance().getQueue(Apis.DATA,map,UserBean.class);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent messageEvent){
        if(messageEvent.getName().equals("1")){
            UserBean userBean= (UserBean) messageEvent.getObject();
            if(mPage==1){
                adapter.setDatas(userBean.getData());
            }else{
                adapter.addDatas(userBean.getData());
            }
            xRecyclerView.loadMoreComplete();
            xRecyclerView.refreshComplete();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
