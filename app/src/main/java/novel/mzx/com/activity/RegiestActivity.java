package novel.mzx.com.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import novel.mzx.com.R;
import novel.mzx.com.base.BaseActivity;
import novel.mzx.com.db.MyDatabaseHelper;
import novel.mzx.com.utils.StringUtils;
import novel.mzx.com.utils.ToastUtils;


/**
 * Created by Administrator on 2019/5/16 0016.
 */

public class RegiestActivity extends BaseActivity {

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;

    @BindView(R.id.et_password)
    EditText etPassword;


    @BindView(R.id.iv_close)
    ImageView iv_back;

    MyDatabaseHelper databaseHelper;

    @Override
    protected int getResourceId() {
        return R.layout.activity_regiest;
    }

    @Override
    protected void initView() {
        super.initView();
        ImmersionBar.with(this).titleBar(R.id.toolbar).keyboardEnable(true).init();
        ImmersionBar.with(RegiestActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
        databaseHelper = new MyDatabaseHelper(this,"user.db",null,1);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (StringUtils.getEditTextData(etPhoneNum).isEmpty() && StringUtils.getEditTextData(etPassword).isEmpty()){
                    ToastUtils.showToast(RegiestActivity.this,"Please enter username or password");
                    return;
                }
                if(StringUtils.getEditTextData(etPhoneNum).isEmpty()){
                    ToastUtils.showToast(RegiestActivity.this,"Please enter username");
                    return;
                }

                if(StringUtils.getEditTextData(etPassword).isEmpty()){
                    ToastUtils.showToast(RegiestActivity.this,"Please enter password");
                    return;
                }
                String uname = StringUtils.getEditTextData(etPhoneNum);
                String pwd = StringUtils.getEditTextData(etPassword);

                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from user where name = ?", new String[]{uname});
                if(cursor.moveToNext()){
                    Toast.makeText(RegiestActivity.this,"registered",Toast.LENGTH_SHORT).show();
                }else {
                    ContentValues values = new ContentValues();
                    values.put("name", uname);
                    values.put("password", pwd);
                    db.insert("user",null,values);
                    Toast.makeText(RegiestActivity.this,"registration success",Toast.LENGTH_SHORT).show();


                    finish();
                }

            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





    }



}
