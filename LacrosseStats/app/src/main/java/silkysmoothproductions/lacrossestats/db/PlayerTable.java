package silkysmoothproductions.lacrossestats.db;

/**
 * Created by Asilcott on 5/12/2015.
 */


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class PlayerTable {
    // Column names
    public static final String TABLE_PLAYER = "player";
    public static final String COLUMN_PLAYER_ID = "player_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_JERSEY_NUMBER = "jersey_number";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_GRADE = "grade";

    // SQL statement to create the table
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PLAYER
            + "("
            + COLUMN_PLAYER_ID + " integer primary key autoincrement, "
            + COLUMN_FIRST_NAME + " text not null, "
            + COLUMN_LAST_NAME + " text not null, "
            + COLUMN_JERSEY_NUMBER + " integer not null, "
            + COLUMN_POSITION + " TEXT default 'none' check(position = 'fos' or position = 'lsm' or position = 'smd' or position = 'att' or position = 'mid' or position = 'gol' or position = 'def' or position = 'none' ), "
            + COLUMN_GRADE + " TEXT default 'unknown' check(grade = 'so' or grade = 'fr' or grade = 'jr' or grade = 'sr' or grade = 'unknown') "
            + ");";

    public static void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database,
                                 int oldVersion,
                                 int newVersion) {
        Log.d(PlayerTable.class.getName(),
                "Upgrading database from version "
                        + oldVersion + " to " + newVersion
                        + ", which destroyed all existing data");

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(database);

        Log.d("TableTask.onUpgrade()", "complete");
    }
}