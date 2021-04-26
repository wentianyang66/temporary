package novel.mzx.com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String create_user = "create table user("
            + "id integer primary key autoincrement,"
            + "name varchar(100),"
            + "password varchar(100))";



    public static final String create_weight = "create table weightInfo("
            + "id integer primary key autoincrement,"
            + "userId varchar(100),"
            + "height varchar(100),"
            + "weight varchar(100),"
            + "mubiaoWeight varchar(100),"
            + "sex varchar(100),"
            + "age integer,"
            + "time varchar(100))";

    public static final String create_note = "create table note("
            + "id integer primary key autoincrement,"
            + "userId varchar(100),"
            + "title varchar(100),"
            + "content varchar(100),"
            + "time varchar(100))";




    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_user);
        db.execSQL(create_weight);
        db.execSQL(create_note);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
