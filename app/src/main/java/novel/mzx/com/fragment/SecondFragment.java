package novel.mzx.com.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import novel.mzx.com.R;
import novel.mzx.com.adapter.NoteAdapter;
import novel.mzx.com.base.BaseFragment;
import novel.mzx.com.bean.NoteBean;
import novel.mzx.com.db.MyDatabaseHelper;
import novel.mzx.com.utils.SPUtils;


public class SecondFragment extends BaseFragment {




    @BindView(R.id.rv_msg_list)
    RecyclerView rvMsgList;
    @BindView(R.id.rl_nodata)
    RelativeLayout rl_nodata;

    private LinearLayoutManager mLinearLayoutManager;
    private NoteAdapter adapter;
    MyDatabaseHelper databaseHelper;
    private List<NoteBean> list = new ArrayList<>();


    @Override
    public int getLayoutResID() {
        return R.layout.fragment_second;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        rvMsgList.setLayoutManager(mLinearLayoutManager);

        databaseHelper = new MyDatabaseHelper(getActivity(),"note.db",null,1);


        registerBoradcastReceiver();

    }

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("refresh");

        getActivity().registerReceiver(broadcastReceiver, myIntentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("refresh")){
                getData();
            }
        }
    };

    @Override
    public void initData() {
        super.initData();

        getData();
    }

    private void getData() {
        /*private int id;
        private int userId;
        private String title;
        private String content;
        private String time;*/
        list.clear();
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from note where userId = ? ", new String[]{(String) SPUtils.get(getActivity(),"userId","")});
            while (cursor.moveToNext()){
                NoteBean noteBean = new NoteBean();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time = cursor.getString(cursor.getColumnIndex("time"));

                noteBean.setId(id);
                noteBean.setUserId(userId);
                noteBean.setTitle(title);
                noteBean.setContent(content);
                noteBean.setTime(time);
                list.add(noteBean);
            }
            if(list.size() >0){
                adapter = new NoteAdapter(getActivity(),R.layout.list_item_note,list);
                rvMsgList.setAdapter(adapter);
                rl_nodata.setVisibility(View.GONE);
            }else {
                rl_nodata.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){

            e.getMessage();
        }
    }


    @Override
    public void initListener() {
        super.initListener();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }
}
