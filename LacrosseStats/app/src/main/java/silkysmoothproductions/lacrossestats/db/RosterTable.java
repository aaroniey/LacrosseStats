package silkysmoothproductions.lacrossestats.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Asilcott on 5/12/2015.
 */
public class RosterTable {
    // Column names
    public static final String TABLE_ROSTER = "roster";
    public static final String COLUMN_PLAYER_ID = "player_id";
    public static final String COLUMN_TEAM_ID = "team_id";

    // SQL statement to create the table
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ROSTER
            + "("
            + COLUMN_PLAYER_ID + " integer, "
            + COLUMN_TEAM_ID + " integer, "
            + "Foreign key(team_id) references Team(team_id), " +
            " Foreign key(player_id) references Player(player_id));";

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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_ROSTER);
        onCreate(database);

        Log.d("TableTask.onUpgrade()", "complete");
    }
}
