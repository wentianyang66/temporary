package novel.mzx.com.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;


import butterknife.ButterKnife;
import novel.mzx.com.R;


public abstract class BaseFragment extends Fragment {
    protected View statusBarView;
    protected Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getLayoutResID(), null);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        initListener();
        //StatusBarUtil.setTransparentForWindow(getActivity());
        statusBarView = view.findViewById(R.id.status_bar_view);
        toolbar = view.findViewById(R.id.toolbar);
        fitsLayoutOverlap();
        return view;
    }


    public abstract int getLayoutResID();


    public void initView(View view){

    }


    public void initListener() {

    }

    public void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();



    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    private void fitsLayoutOverlap() {
        if (statusBarView != null) {
            ImmersionBar.setStatusBarView(this, statusBarView);
        } else {
            ImmersionBar.setTitleBar(this, toolbar);
        }
    }

}
