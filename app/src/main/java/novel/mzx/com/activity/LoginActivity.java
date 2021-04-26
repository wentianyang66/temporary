package novel.mzx.com.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;
import novel.mzx.com.R;
import novel.mzx.com.base.BaseActivity;
import novel.mzx.com.db.MyDatabaseHelper;
import novel.mzx.com.utils.FinishActivity;
import novel.mzx.com.utils.SPUtils;
import novel.mzx.com.utils.StringUtils;
import novel.mzx.com.utils.ToastUtils;
import novel.mzx.com.views.InputMethodLayout;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_regiest)
    TextView tv_regiest;


    @BindView(R.id.root)
    ScrollView scrollView;

    @BindView(R.id.rl)
    InputMethodLayout rl;

    int scrollViewHeight;


    @Override
    protected int getResourceId() {
        return R.layout.login_activity;
    }


    String update;
    MyDatabaseHelper databaseHelper;

    @Override
    protected void initView() {
        super.initView();
        ImmersionBar.with(this).titleBar(R.id.toolbar).keyboardEnable(true).init();
        ImmersionBar.with(LoginActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();


        rl.setOnkeyboarddStateListener(new InputMethodLayout.onKeyboardsChangeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case InputMethodLayout.KEYBOARD_STATE_HIDE:

                        break;
                    case InputMethodLayout.KEYBOARD_STATE_SHOW:

                        scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollViewHeight = scrollView.getMeasuredWidth();

                                scrollView.scrollTo(0, 500);
                            }
                        });

                        break;

                    default:
                        break;
                }
            }
        });

        tv_regiest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegiestActivity.class);
                startActivity(intent);
                FinishActivity.addActivity(LoginActivity.this);
            }
        });

        databaseHelper = new MyDatabaseHelper(this, "user.db", null, 1);

        findViewById(R.id.test_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //
                Intent it = new Intent(LoginActivity.this,Main2Activity.class);
                startActivity(it);
            }
        });
    }


    @OnClick({R.id.btn_login, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                if (StringUtils.getEditTextData(etPhoneNum).isEmpty() && StringUtils.getEditTextData(etPassword).isEmpty()) {
                    ToastUtils.showToast(this, "Please enter username or password");
                    return;
                }
                if (StringUtils.getEditTextData(etPhoneNum).isEmpty()) {
                    ToastUtils.showToast(this, "Please enter username");
                    return;
                }

                if (StringUtils.getEditTextData(etPassword).isEmpty()) {
                    ToastUtils.showToast(this, "Please enter password");
                    return;
                }
                String uname = StringUtils.getEditTextData(etPhoneNum);
                String pwd = StringUtils.getEditTextData(etPassword);
                netWorkLogin(uname, pwd);


                break;


        }
    }


    private void netWorkLogin(String uname, String pwd) {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where name = ? and password = ?", new String[]{uname, pwd});
        if (cursor.moveToNext()) {
            int userId = cursor.getInt(cursor.getColumnIndex("id"));
            Toast.makeText(LoginActivity.this, "login successful!", Toast.LENGTH_SHORT).show();
            SPUtils.put(LoginActivity.this, "name", uname);
            SPUtils.put(LoginActivity.this, "userId", userId + "");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Incorrect account or password, please log in again!", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (update != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);

    }


}
