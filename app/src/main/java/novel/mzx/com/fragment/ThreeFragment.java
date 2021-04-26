package novel.mzx.com.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import novel.mzx.com.R;
import novel.mzx.com.adapter.WeightAdapter;
import novel.mzx.com.base.BaseFragment;

import novel.mzx.com.bean.WeightBean;
import novel.mzx.com.db.MyDatabaseHelper;
import novel.mzx.com.utils.SPUtils;
import novel.mzx.com.views.ImageBannerFramLayout;


public class ThreeFragment extends BaseFragment implements ImageBannerFramLayout.FramLayoutLisenner {

    @BindView(R.id.rv_msg_list)
    RecyclerView rvMsgList;

    @BindView(R.id.image_group)
    ImageBannerFramLayout mGroup;

    private MyLinearLayoutManager mLinearLayoutManager;

    private int[] ids = new int[] {
            R.mipmap.banner01,//图片资源1
            R.mipmap.banner02,//图片资源2
            R.mipmap.banner03
    };

    MyDatabaseHelper databaseHelper;
    private List<WeightBean> list = new ArrayList<>();
    private WeightAdapter homeWorkAdapter;

    @Override
    public int getLayoutResID() {

        return R.layout.fragment_three;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        mLinearLayoutManager = new MyLinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        rvMsgList.setLayoutManager(mLinearLayoutManager);

        databaseHelper = new MyDatabaseHelper(getActivity(),"weightInfo.db",null,1);
        list.clear();


        mGroup.setLisenner(this);
        List<Bitmap> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),ids[i]);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);

        registerBoradcastReceiver();
    }


    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("freshHome");
        //注册广播
        getActivity().registerReceiver(broadcastReceiver, myIntentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("freshHome")){
                getDatas();
            }
        }
    };


    @Override
    public void initListener() {
        super.initListener();

    }



    @Override
    public void initData() {


        getDatas();


    }

    private void getDatas() {
        list.clear();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from weightInfo where userId = ?", new String[]{(String)SPUtils.get(getActivity(),"userId","")});

        while (cursor.moveToNext()){
            WeightBean weightBean = new WeightBean();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int userId = cursor.getInt(cursor.getColumnIndex("userId"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String height = cursor.getString(cursor.getColumnIndex("height"));
            String weight = cursor.getString(cursor.getColumnIndex("weight"));
            String mubiaoWeight = cursor.getString(cursor.getColumnIndex("mubiaoWeight"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            weightBean.setId(id);
            weightBean.setUserId(userId);
            weightBean.setAge(age);
            weightBean.setHeight(height);
            weightBean.setWeight(weight);
            weightBean.setMubiaoWeight(mubiaoWeight);
            weightBean.setTime(time);
            weightBean.setSex(sex);
            list.add(weightBean);
        }
        homeWorkAdapter = new WeightAdapter(getActivity(),R.layout.list_item_homework,list);
        rvMsgList.setAdapter(homeWorkAdapter);
    }

    @Override
    public void chickImageIndex(int pos) {

    }

    public class MyLinearLayoutManager extends LinearLayoutManager{


        public MyLinearLayoutManager(Context context) {
            super(context);
        }

        public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        /**
         * 禁止RecyclerView滑动
         * @return
         */
        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }
}
