package novel.mzx.com.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;


import butterknife.ButterKnife;
import novel.mzx.com.R;
import novel.mzx.com.utils.ActivityUtils;


/**
 * Activity的基类
 *
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceId());
        ButterKnife.bind(this);
        initView();
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
        initData();
        initListener();

        ActivityUtils.getActivityManager().addActivity(this);

    }



    /**
     * 初始化监听
     */
    protected void initListener() {};

    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int getResourceId();

    /**
     * 初始化控件
     */
    protected void initView() {}

    /**
     * 初始化数据
     */
    protected void initData() {}


    /**
     * 显示dialog
     */
    protected void showDialog(String s) {
        dialog = new ProgressDialog(this);
        dialog.setMessage(s);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }



    /**
     * dialog停止
     */
    protected void stopDialog() {
        if (null != dialog ) {
            if (dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopDialog();
        ActivityUtils.getActivityManager().finishActivity(this);


    }

    public void onBack(View view){
        finish();
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
        return ;
    }


}
