package novel.mzx.com.adapter;

import android.content.Context;


import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import novel.mzx.com.R;

import novel.mzx.com.bean.WeightBean;



/**
 * Created by Administrator on 2019/4/17 0017.
 */

public class WeightAdapter extends CommonAdapter<WeightBean> {
    Context mContext;

    public WeightAdapter(Context context, int layoutId, List<WeightBean> datas) {
        super(context, layoutId, datas);

        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, final WeightBean s, int position) {

        String sex = s.getSex();
        if(sex.equals("0")){
            holder.setText(R.id.tv_xuehao,"men");
        }else {
            holder.setText(R.id.tv_xuehao,"woman");
        }
        holder.setText(R.id.tv_name,s.getHeight());
        holder.setText(R.id.tv_xueyuan,s.getAge()+"");
        holder.setText(R.id.tv_banji,s.getWeight()+"");
        holder.setText(R.id.tv_type,s.getMubiaoWeight()+"");
        holder.setText(R.id.tv_time,s.getTime()+"");






    }

}
