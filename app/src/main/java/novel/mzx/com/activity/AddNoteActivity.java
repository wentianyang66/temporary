package novel.mzx.com.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

import butterknife.BindView;
import novel.mzx.com.R;
import novel.mzx.com.base.BaseActivity;
import novel.mzx.com.db.MyDatabaseHelper;
import novel.mzx.com.utils.SPUtils;
import novel.mzx.com.utils.TimeUtils2;
import novel.mzx.com.utils.ToastUtils;


public class AddNoteActivity extends BaseActivity {
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mIv)
    ImageView mIv;


    @BindView(R.id.app_bar)
    AppBarLayout app_bar;

    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.et_weight)
    EditText et_weight;
    @BindView(R.id.et_mubiao)
    EditText et_mubiao;
    @BindView(R.id.cb_01)
    ImageView cb_01;
    @BindView(R.id.cb_02)
    ImageView cb_02;
    @BindView(R.id.button_add)
    Button button_add;
    MyDatabaseHelper databaseHelper;
    int sex = 0;



    @Override
    protected int getResourceId() {
        return R.layout.activity_add_note;
    }


    String update;


    @Override
    protected void initView() {
        super.initView();


        mSwipeRefreshLayout.setProgressViewOffset(true, -20, 100);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.btn1, R.color.cool_green_shade);

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("verticalOffset",verticalOffset+"");
                if (verticalOffset >= 0) {
                    mSwipeRefreshLayout.setEnabled(true);

                }else {
                    mSwipeRefreshLayout.setEnabled(false);
                    //      mSwipeRefreshLayout.setNestedScrollingEnabled(false);
                }
            }

        });
        Random random = new Random();
        String url = "http://106.14.135.179/ImmersionBar/" + random.nextInt(40) + ".jpg";
        Glide.with(this).asBitmap().load(url)
                .apply(new RequestOptions().placeholder(R.mipmap.icon_defoat))
                .into(mIv);



        SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Random random = new Random();
                String url = "http://106.14.135.179/ImmersionBar/" + random.nextInt(40) + ".jpg";
                Glide.with(AddNoteActivity.this).asBitmap().load(url)
                        .apply(new RequestOptions().placeholder(R.mipmap.icon_defoat))
                        .into(mIv);

                //getData2();
            }


        };


        databaseHelper = new MyDatabaseHelper(this,"note.db",null,1);

    }


    @Override
    public void initData() {
        super.initData();
        cb_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb_01.setBackground(getResources().getDrawable(R.mipmap.radiobuttonon));
                cb_02.setBackground(getResources().getDrawable(R.mipmap.radiobuttonoff));
                sex = 0;
            }
        });

        cb_02.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                cb_01.setBackground(getResources().getDrawable(R.mipmap.radiobuttonoff));
                cb_02.setBackground(getResources().getDrawable(R.mipmap.radiobuttonon));
                sex = 1;
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString().trim();
                String content = et_content.getText().toString().trim();
                String nowTime = TimeUtils2.getNowTime();
                if(title.equals("") || content.equals("")){
                    ToastUtils.showToast(AddNoteActivity.this,"Please enter complete information!");
                    return;
                }

                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("title", title);
                values.put("content", content);
                values.put("time",nowTime);
                values.put("userId", (String)SPUtils.get(AddNoteActivity.this,"userId",""));
                db.insert("note",null,values);

                Intent intent = new Intent();
                intent.setAction("refresh");
                sendBroadcast(intent);

                finish();
            }
        });
    }

}
