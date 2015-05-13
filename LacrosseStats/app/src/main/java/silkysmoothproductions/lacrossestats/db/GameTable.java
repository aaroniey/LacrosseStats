package silkysmoothproductions.lacrossestats.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Asilcott on 5/12/2015.
 */
public class GameTable {
    // Column names
    public static final String TABLE_GAME = "game";
    public static final String COLUMN_GAME_ID = "game_id";
    public static final String COLUMN_TEAM_ID = "team_id";
    public static final String COLUMN_OPPONENT_NAME = "opponent_name";

    //Team score
    public static final String Q1_TEAM_SCORE = "q1_team_score";
    public static final String Q2_TEAM_SCORE = "q2_team_score";
    public static final String Q3_TEAM_SCORE = "q3_team_score";
    public static final String Q4_TEAM_SCORE = "q4_team_score";
    public static final String FINAL_TEAM_SCORE = "final_team_score";

    //opponent score
    public static final String Q1_OPPONENT_SCORE = "q1_opponent_score";
    public static final String Q2_OPPONENT_SCORE = "q2_opponent_score";
    public static final String Q3_OPPONENT_SCORE = "q3_opponent_score";
    public static final String Q4_OPPONENT_SCORE = "q4_opponent_score";
    public static final String FINAL_OPPONENT_SCORE = "final_opponent_score";

    //tallied stats
    public static final String TOTAL_TEAM_GBS = "total_team_gbs";
    public static final String TOTAL_TEAM_SHOTS = "total_team_shots";
    public static final String TOTAL_TEAM_ASSISTS = "total_team_assists";
    public static final String TOTAL_TEAM_CAUSED_TURNOVERS = "total_team_caused_turnovers";
    public static final String TOTAL_TEAM_SAVES = "total_team_saves";
    public static final String TOTAL_TEAM_FOWINS = "total_team_fowins";
    public static final String TOTAL_TEAM_FOLOSSES = "total_team_folosses";
    
    //successful clears
    public static final String Q1_SUCCESSFUL_CLEARS = "q1_successful_clears";
    public static final String Q2_SUCCESSFUL_CLEARS = "q2_successful_clears";
    public static final String Q3_SUCCESSFUL_CLEARS = "q3_successful_clears";
    public static final String Q4_SUCCESSFUL_CLEARS = "q4_successful_clears";
    public static final String TOTAL_SUCCESSFUL_CLEARS = "total_successful_clears";

    //failed clears
    public static final String Q1_FAILED_CLEARS = "q1_failed_clears";
    public static final String Q2_FAILED_CLEARS = "q2_failed_clears";
    public static final String Q3_FAILED_CLEARS = "q3_failed_clears";
    public static final String Q4_FAILED_CLEARS = "q4_failed_clears";
    public static final String TOTAL_FAILED_CLEARS = "total_failed_clears";

    //successful rides
    public static final String Q1_SUCCESSFUL_RIDES = "q1_successful_rides";
    public static final String Q2_SUCCESSFUL_RIDES = "q2_successful_rides";
    public static final String Q3_SUCCESSFUL_RIDES = "q3_successful_rides";
    public static final String Q4_SUCCESSFUL_RIDES = "q4_successful_rides";
    public static final String TOTAL_SUCCESSFUL_RIDES = "total_successful_rides";

    //failed rides
    public static final String Q1_FAILED_RIDES = "q1_failed_rides";
    public static final String Q2_FAILED_RIDES = "q2_failed_rides";
    public static final String Q3_FAILED_RIDES = "q3_failed_rides";
    public static final String Q4_FAILED_RIDES = "q4_failed_rides";
    public static final String TOTAL_FAILED_RIDES = "total_failed_rides";

    //successful manups
    public static final String Q1_SUCCESSFUL_MANUPS = "q1_successful_manups";
    public static final String Q2_SUCCESSFUL_MANUPS = "q2_successful_manups";
    public static final String Q3_SUCCESSFUL_MANUPS = "q3_successful_manups";
    public static final String Q4_SUCCESSFUL_MANUPS = "q4_successful_manups";
    public static final String TOTAL_SUCCESSFUL_MANUPS = "total_successful_manups";

    //failed manups
    public static final String Q1_FAILED_MANUPS = "q1_failed_manups";
    public static final String Q2_FAILED_MANUPS = "q2_failed_manups";
    public static final String Q3_FAILED_MANUPS = "q3_failed_manups";
    public static final String Q4_FAILED_MANUPS = "q4_failed_manups";
    public static final String TOTAL_FAILED_MANUPS = "total_failed_manups";

    //successful mandowns
    public static final String Q1_SUCCESSFUL_MANDOWNS = "q1_successful_mandowns";
    public static final String Q2_SUCCESSFUL_MANDOWNS = "q2_successful_mandowns";
    public static final String Q3_SUCCESSFUL_MANDOWNS = "q3_successful_mandowns";
    public static final String Q4_SUCCESSFUL_MANDOWNS = "q4_successful_mandowns";
    public static final String TOTAL_SUCCESSFUL_MANDOWNS = "total_successful_mandowns";

    //failed mandowns
    public static final String Q1_FAILED_MANDOWNS = "q1_failed_mandowns";
    public static final String Q2_FAILED_MANDOWNS = "q2_failed_mandowns";
    public static final String Q3_FAILED_MANDOWNS = "q3_failed_mandowns";
    public static final String Q4_FAILED_MANDOWNS = "q4_failed_mandowns";
    public static final String TOTAL_FAILED_MANDOWNS = "total_failed_mandowns";

    private static final String idf = " integer default 0, ";
    // SQL statement to create the table
    private static final String DATABASE_CREATE = "create table "
            + TABLE_GAME
            + "("
            + COLUMN_GAME_ID + " integer primary key autoincrement, "
            + COLUMN_TEAM_ID + " integer, "
            + COLUMN_OPPONENT_NAME + " text, not null, "
            + Q1_TEAM_SCORE  + idf
            + Q2_TEAM_SCORE  + idf
            + Q3_TEAM_SCORE  + idf
            + Q4_TEAM_SCORE  + idf
            + FINAL_TEAM_SCORE  + idf
            + Q1_OPPONENT_SCORE  + idf
            + Q2_OPPONENT_SCORE  + idf
            + Q3_OPPONENT_SCORE  + idf
            + Q4_OPPONENT_SCORE  + idf
            + FINAL_OPPONENT_SCORE  + idf
            + TOTAL_TEAM_GBS  + idf
            + TOTAL_TEAM_SHOTS  + idf
            + TOTAL_TEAM_ASSISTS  + idf
            + TOTAL_TEAM_CAUSED_TURNOVERS  + idf
            + TOTAL_TEAM_SAVES  + idf
            + TOTAL_TEAM_FOWINS  + idf
            + TOTAL_TEAM_FOLOSSES  + idf
            + Q1_SUCCESSFUL_CLEARS  + idf
            + Q2_SUCCESSFUL_CLEARS  + idf
            + Q3_SUCCESSFUL_CLEARS  + idf
            + Q4_SUCCESSFUL_CLEARS  + idf
            + TOTAL_SUCCESSFUL_CLEARS  + idf
            + Q1_FAILED_CLEARS  + idf
            + Q2_FAILED_CLEARS  + idf
            + Q3_FAILED_CLEARS  + idf
            + Q4_FAILED_CLEARS  + idf
            + TOTAL_FAILED_CLEARS  + idf
            + Q1_SUCCESSFUL_RIDES  + idf
            + Q2_SUCCESSFUL_RIDES  + idf
            + Q3_SUCCESSFUL_RIDES  + idf
            + Q4_SUCCESSFUL_RIDES  + idf
            + TOTAL_SUCCESSFUL_RIDES  + idf
            + Q1_FAILED_RIDES  + idf
            + Q2_FAILED_RIDES  + idf
            + Q3_FAILED_RIDES  + idf
            + Q4_FAILED_RIDES  + idf
            + TOTAL_FAILED_RIDES  + idf
            + Q1_SUCCESSFUL_MANUPS  + idf
            + Q2_SUCCESSFUL_MANUPS  + idf
            + Q3_SUCCESSFUL_MANUPS  + idf
            + Q4_SUCCESSFUL_MANUPS  + idf
            + TOTAL_SUCCESSFUL_MANUPS  + idf
            + Q1_FAILED_MANUPS  + idf
            + Q2_FAILED_MANUPS  + idf
            + Q3_FAILED_MANUPS  + idf
            + Q4_FAILED_MANUPS  + idf
            + TOTAL_FAILED_MANUPS  + idf
            + Q1_SUCCESSFUL_MANDOWNS  + idf
            + Q2_SUCCESSFUL_MANDOWNS  + idf
            + Q3_SUCCESSFUL_MANDOWNS  + idf
            + Q4_SUCCESSFUL_MANDOWNS  + idf
            + TOTAL_SUCCESSFUL_MANDOWNS  + idf
            + Q1_FAILED_MANDOWNS  + idf
            + Q2_FAILED_MANDOWNS  + idf
            + Q3_FAILED_MANDOWNS  + idf
            + Q4_FAILED_MANDOWNS  + idf
            + TOTAL_FAILED_MANDOWNS  + idf
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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        onCreate(database);

        Log.d("TableTask.onUpgrade()", "complete");
    }
}
