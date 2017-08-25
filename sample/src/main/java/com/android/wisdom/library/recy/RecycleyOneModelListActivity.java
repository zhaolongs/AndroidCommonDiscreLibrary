package com.android.wisdom.library.recy;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wisdom.library.R;
import com.androidlongs.pullrefreshrecyclerylib.common.PullRecyclerViewUtils;
import com.androidlongs.pullrefreshrecyclerylib.inter.PullRecyclerViewLinserner;
import com.androidlongs.pullrefreshrecyclerylib.inter.PullRecyclerViewOnItemClickLinserner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidlongs on 2017/8/24.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 */

public class RecycleyOneModelListActivity extends Activity {

    private PullRecyclerViewUtils mPullRecyclerViewUtils;
    private List<Object> mStringList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recy_main_layout);

        LinearLayout mainLinLayout = findViewById(R.id.ll_recy_main_content);

        //加载RecyclerView

        mStringList = new ArrayList<>();
        for (int lI = 0; lI < 20; lI++) {
            mStringList.add("test");
        }

        //初始化
        mPullRecyclerViewUtils = PullRecyclerViewUtils.getInstance();

        //设置一开始显示布局为加载中
        RelativeLayout lRelativeLayout = mPullRecyclerViewUtils.setRecyclerViewFunction(
                this.getApplicationContext(),
                R.layout.item_recy_once_model_layout,//条目布局
                mPullRecyclerViewLinserner,//设置监听回调
                PullRecyclerViewUtils.SHOW_DEFAUTLE_PAGE_TYPE.LOADING
        );


        //添加布局
        mainLinLayout.addView(lRelativeLayout);


        //设置刷新布局条目背景
        mPullRecyclerViewUtils.setPullRefshBackGroundColor(Color.WHITE);
        //刷新字体
        mPullRecyclerViewUtils.setPullRefshTextColorFunction(Color.BLUE);

        //模拟网络加载数据
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullRecyclerViewUtils.setLoadingDataList(null);
            }
        },3000);

    }


    private PullRecyclerViewLinserner mPullRecyclerViewLinserner = new PullRecyclerViewLinserner() {
        @Override
        public void loadMoreData() {

            //加载更多
            final List<Object> lStringList = new ArrayList<>();
            for (int lI = 0; lI < 11; lI++) {
                lStringList.add("test " + lI);
            }

            mStringList.addAll(lStringList);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPullRecyclerViewUtils.setLoadingDataList(null);
                }
            }, 2000);


        }

        @Override
        public void loadingRefresDataFunction() {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    final List<Object> lStringList = new ArrayList<>();
                    for (int lI = 0; lI < 11; lI++) {
                        lStringList.add("loading more test");
                    }
                    mStringList.clear();
                    mPullRecyclerViewUtils.setLoadingDataList(mStringList);
                }
            }, 2000);
        }

        @Override
        public void setViewDatas(View itemView, int position, int itemType, Object object) {

            TextView titleTextView = itemView.findViewById(R.id.tv_item_header_title);

            titleTextView.setText("大秦帝国 " + position + " " + object);
        }
    };

    private PullRecyclerViewOnItemClickLinserner mPullRecyclerViewOnItemClickLinserner = new PullRecyclerViewOnItemClickLinserner() {
        @Override
        public void setonItemClick(int position, int itemType, Object object) {
            Toast.makeText(RecycleyOneModelListActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
        }
    };


    private RelativeLayout getRelativeLayout1() {
        //获取布局
        return mPullRecyclerViewUtils.setRecyclerViewFunction(
                this.getApplicationContext(),
                R.layout.item_recy_once_model_layout,//条目布局
                mStringList, //数据集合
                mPullRecyclerViewLinserner,//设置监听回调
                mPullRecyclerViewOnItemClickLinserner//条目事件点击监听
        );
    }


    private RelativeLayout getRelativeLayout2() {
        //获取布局
        return mPullRecyclerViewUtils.setRecyclerViewFunction(
                this.getApplicationContext(),
                R.layout.item_recy_once_model_layout,//条目布局
                mPullRecyclerViewLinserner,//设置监听回调
                PullRecyclerViewUtils.SHOW_DEFAUTLE_PAGE_TYPE.LOADING
        );
    }
}
