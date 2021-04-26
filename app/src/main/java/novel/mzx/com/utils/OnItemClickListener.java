package novel.mzx.com.utils;

import android.view.View;

/**
 * Created by LJZ
 * on 2019-11-13
 * Describe:
 */
public interface OnItemClickListener {

    //单点监听
    void OnItemClick(View view, int position);
    //长点监听
    void OnItemLongClick(View view, int position);
}
