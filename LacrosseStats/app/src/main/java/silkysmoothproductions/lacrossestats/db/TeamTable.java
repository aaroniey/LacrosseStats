package silkysmoothproductions.lacrossestats.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Asilcott on 5/12/2015.
 */
public class TeamTable {
    // Column names
    public static final String TABLE_TEAM = "Team";
    public static final String COLUMN_TEAM_ID = "team_id";
    public static final String COLUMN_TEAM_NAME = "team_name";

    // SQL statement to create the table
    private static final String DATABASE_CREATE = "create table "
            + TABLE_TEAM
            + "("
            + COLUMN_TEAM_ID + " integer primary key autoincrement, "
            + COLUMN_TEAM_NAME + " text not null "
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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        onCreate(database);

        Log.d("TableTask.onUpgrade()", "complete");
    }
}