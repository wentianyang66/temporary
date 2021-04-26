package novel.mzx.com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils2 {

    public static String getNowTime(){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        return time;

    }
}
