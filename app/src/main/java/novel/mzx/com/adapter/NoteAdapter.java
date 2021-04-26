package novel.mzx.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import novel.mzx.com.R;
import novel.mzx.com.bean.NoteBean;
import novel.mzx.com.db.MyDatabaseHelper;


/**
 * Created by Administrator on 2019/4/17 0017.
 */

public class NoteAdapter extends CommonAdapter<NoteBean> {
    Context mContext;
    MyDatabaseHelper databaseHelper;
    public NoteAdapter(Context context, int layoutId, List<NoteBean> datas) {
        super(context, layoutId, datas);

        mContext = context;
        databaseHelper = new MyDatabaseHelper(context,"note.db",null,1);
    }

    @Override
    protected void convert(ViewHolder holder, final NoteBean s, int position) {

        holder.setText(R.id.tv_name,s.getTitle());
        holder.setText(R.id.tv_content,s.getContent());
        holder.setText(R.id.tv_time,s.getTime());
        holder.setOnClickListener(R.id.button_delete, new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.delete("note","id = ?",new String[]{s.getId()+""});
                Intent intent = new Intent();
                intent.setAction("refresh");
                mContext.sendBroadcast(intent);
            }
        });

    }

}
