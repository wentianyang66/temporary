package novel.mzx.com.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import butterknife.BindView;
import novel.mzx.com.R;
import novel.mzx.com.activity.AddNoteActivity;
import novel.mzx.com.activity.AddWeightActivity;
import novel.mzx.com.activity.LoginActivity;
import novel.mzx.com.base.BaseFragment;

import novel.mzx.com.utils.SPUtil;
import novel.mzx.com.utils.SPUtils;



public class MeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rv_user_data)
    RelativeLayout rv_user_data;

    @BindView(R.id.ll_account)
    LinearLayout ll_account;

    @BindView(R.id.ll_vip)
    LinearLayout ll_vip;

    @BindView(R.id.ll_order)
    LinearLayout ll_order;



    @BindView(R.id.ll_location)
    LinearLayout ll_location;

    @BindView(R.id.ll_out)
    LinearLayout ll_out;


    @BindView(R.id.tv_zhuan_ye)
    TextView tv_zhuan_ye;

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.ll_system)
    LinearLayout ll_system;



    @Override
    public int getLayoutResID() {

        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        tv_user_name.setText("Logged in");
        tv_zhuan_ye.setText((String)SPUtils.get(getActivity(),"name",""));
    }

    @Override
    public void initListener() {
        super.initListener();
        rv_user_data.setOnClickListener(this);
        ll_account.setOnClickListener(this);
        ll_vip.setOnClickListener(this);
        ll_order.setOnClickListener(this);
        ll_out.setOnClickListener(this);
        ll_system.setOnClickListener(this);
        ll_location.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rv_user_data:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_vip:
                Intent ll_vip = new Intent(getActivity(), AddWeightActivity.class);
                startActivity(ll_vip);
                break;

            case R.id.ll_location:
                Intent ll_location = new Intent(getActivity(), AddNoteActivity.class);
                startActivity(ll_location);
                break;

            case R.id.ll_out:
                SPUtil.getInstance().putString("name","");
                Intent intent1 = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent1);
                //getActivity().finish();
                break;

        }
    }


}
