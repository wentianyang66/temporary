package novel.mzx.com.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;


import novel.mzx.com.http.Constants;
import novel.mzx.com.utils.Cockroach;
import novel.mzx.com.utils.SPUtil;


/**
 * Created by admin on 2019/11/18.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        SPUtil.init(this, Constants.SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);

        //noCrashMethod();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }


    /**
     * f防止程序崩溃闪退
     */
    private void noCrashMethod() {

        Cockroach.install(new Cockroach.ExceptionHandler() {

            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException

            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {

                           /* Log.e("AndroidRuntime","--->CockroachException:"+thread+"<---",throwable);
                            Toast.makeText(App.this, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();*/

                        } catch (Throwable e) {

                        }
                    }
                });
            }
        });
    }
}
