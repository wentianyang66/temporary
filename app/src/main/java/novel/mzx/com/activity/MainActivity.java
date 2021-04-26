package novel.mzx.com.activity;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import novel.mzx.com.R;
import novel.mzx.com.base.BaseActivity;
import novel.mzx.com.fragment.Exercice2Fragment;
import novel.mzx.com.fragment.ExerciceFragment;
import novel.mzx.com.fragment.HomeFragment;
import novel.mzx.com.fragment.MeFragment;
import novel.mzx.com.fragment.SecondFragment;
import novel.mzx.com.fragment.ThreeFragment;
import novel.mzx.com.utils.BroadCastReceiverUtil;


public class MainActivity extends BaseActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.main_radioGroup)
    RadioGroup mainRadioGroup;

    @BindView(R.id.rb_recommend)
    RadioButton rb_recommend;

    HomeFragment homeFragment;

    SecondFragment secondFragment;

    ThreeFragment threeFragment;

    Exercice2Fragment  exercice2Fragment;

    ExerciceFragment  exerciceFragment;

    MeFragment meFragment;
    BroadcastReceiver broadcastReceiver;
    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();


        requestPermissions();
        String[] a = {"jumptoSencondFragment"};
        rb_recommend.setChecked(true);

        broadcastReceiver = BroadCastReceiverUtil.registerBroadcastReceiver(this, a, new BroadCastReceiverUtil.OnReceiveBroadcast() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity.this, "sadsa", Toast.LENGTH_SHORT).show();
                rb_recommend.setChecked(true);
            }
        });

    }


    @Override
    protected void initData() {
        super.initData();
        showFragment(0);
    }

    @Override
    protected void initListener() {
        super.initListener();

        mainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_recommend:
                        showFragment(0);
                        ImmersionBar.with(MainActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                        break;
                    case R.id.rb_three:
                        showFragment(1);
                        ImmersionBar.with(MainActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                        break;
                    case R.id.rb_home_page:
                        showFragment(2);
                        ImmersionBar.with(MainActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                        break;
                    case R.id.rb_Exercice2:
                        showFragment(3);
                        ImmersionBar.with(MainActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                        break;
                        case R.id.rb_Exercice:
                        showFragment(4);
                        ImmersionBar.with(MainActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                        break;
                        case R.id.rb_my:
                        showFragment(5);
                        ImmersionBar.with(MainActivity.this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                        break;
                }
            }
        });
    }

    public void showFragment(int i) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragments(ft);
        switch (i) {
            case 0:
                if (threeFragment != null)
                    ft.show(threeFragment);
                else {
                    threeFragment = new ThreeFragment();
                    ft.add(R.id.frameLayout, threeFragment);
                }

                break;
            case 1:
                if (secondFragment != null)
                    ft.show(secondFragment);
                else {
                    secondFragment = new SecondFragment();
                    ft.add(R.id.frameLayout, secondFragment);
                }

                break;
            case 2:
                if (homeFragment != null)
                    ft.show(homeFragment);
                else {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.frameLayout, homeFragment);
                }

                break;
            case 3:
                if (exercice2Fragment != null)
                    ft.show(exercice2Fragment);
                else {
                    exercice2Fragment = new Exercice2Fragment();
                    ft.add(R.id.frameLayout, exercice2Fragment);
                }

                break;
            case 4:
                if (exerciceFragment != null)
                    ft.show(exerciceFragment);
                else {
                    exerciceFragment = new ExerciceFragment();
                    ft.add(R.id.frameLayout, exerciceFragment);
                }

                break;
                case 5:
                if (meFragment != null)
                    ft.show(meFragment);
                else {
                    meFragment = new MeFragment();
                    ft.add(R.id.frameLayout, meFragment);
                }
                ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(false).navigationBarColor(R.color.colorPrimary).init();
                break;

        }
        ft.commit();
    }

    private void hideFragments(FragmentTransaction ft2) {
        if (homeFragment != null)
            ft2.hide(homeFragment);
        if (secondFragment != null)
            ft2.hide(secondFragment);
        if (threeFragment != null)
            ft2.hide(threeFragment);
        if (meFragment != null)
            ft2.hide(meFragment);
        if (exercice2Fragment != null)
            ft2.hide(exercice2Fragment);
        if (exerciceFragment != null)
            ft2.hide(exerciceFragment);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        BroadCastReceiverUtil.unRegisterBroadcastReceiver(this, broadcastReceiver);
    }


    private void requestPermissions(){
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA);
                if(permission!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE,
                                    Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS},0x0010);
                }

                if(permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0x0010);
                }

                if(permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {
                            Manifest.permission.CAMERA},0x0010);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
