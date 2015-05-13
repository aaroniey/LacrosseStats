package silkysmoothproductions.lacrossestats.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Asilcott on 5/13/2015.
 */
public class IndividualStatsTable {
    // Column names
    public static final String TABLE_INDIVIDUAL_STATS = "individualstats";
    public static final String COLUMN_STATS_ID = "stats_id";
    public static final String COLUMN_GAME_ID = "game_id";
    public static final String COLUMN_PLAYER_ID = "player_id";

    public static final String Q1_GOALS = "q1_goals";
    public static final String Q2_GOALS = "q2_goals";
    public static final String Q3_GOALS = "q3_goals";
    public static final String Q4_GOALS = "q4_goals";
    public static final String TOTAL_GOALS = "total_goals";
    public static final String Q1_ASSISTS = "q1_assists";
    public static final String Q2_ASSISTS = "q2_assists";
    public static final String Q3_ASSISTS = "q3_assists";
    public static final String Q4_ASSISTS = "q4_assists";
    public static final String TOTAL_ASSISTS = "total_assists";
    public static final String Q1_SHOTS = "q1_shots";
    public static final String Q2_SHOTS = "q2_shots";
    public static final String Q3_SHOTS = "q3_shots";
    public static final String Q4_SHOTS = "q4_shots";
    public static final String TOTAL_SHOTS = "total_shots";
    public static final String Q1_GBS = "q1_gbs";
    public static final String Q2_GBS = "q2_gbs";
    public static final String Q3_GBS = "q3_gbs";
    public static final String Q4_GBS = "q4_gbs";
    public static final String TOTAL_GBS = "total_gbs";
    public static final String Q1_CAUSED_TURNOVERS = "q1_caused_turnovers";
    public static final String Q2_CAUSED_TURNOVERS = "q2_caused_turnovers";
    public static final String Q3_CAUSED_TURNOVERS = "q3_caused_turnovers";
    public static final String Q4_CAUSED_TURNOVERS = "q4_caused_turnovers";
    public static final String TOTAL_CAUSED_TURNOVERS = "total_caused_turnovers";
    public static final String Q1_FO_WINS = "q1_fo_wins";
    public static final String Q2_FO_WINS = "q2_fo_wins";
    public static final String Q3_FO_WINS = "q3_fo_wins";
    public static final String Q4_FO_WINS = "q4_fo_wins";
    public static final String TOTAL_FO_WINS = "total_fo_wins";
    public static final String Q1_FO_LOSSES = "q1_fo_losses";
    public static final String Q2_FO_LOSSES = "q2_fo_losses";
    public static final String Q3_FO_LOSSES = "q3_fo_losses";
    public static final String Q4_FO_LOSSES = "q4_fo_losses";
    public static final String TOTAL_FO_LOSSES = "total_fo_losses";
    public static final String Q1_SAVES = "q1_saves";
    public static final String Q2_SAVES = "q2_saves";
    public static final String Q3_SAVES = "q3_saves";
    public static final String Q4_SAVES = "q4_saves";
    public static final String TOTAL_SAVES = "total_saves";
    public static final String Q1_GOALS_ALLOWED = "q1_goals_allowed";
    public static final String Q2_GOALS_ALLOWED = "q2_goals_allowed";
    public static final String Q3_GOALS_ALLOWED = "q3_goals_allowed";
    public static final String Q4_GOALS_ALLOWED = "q4_goals_allowed";
    public static final String TOTAL_GOALS_ALLOWED = "total_goals_allowed";


    private static final String idf = " integer default 0, ";
    // SQL statement to create the table
    private static final String DATABASE_CREATE = "create table "
            + TABLE_INDIVIDUAL_STATS
            + "("
            + COLUMN_STATS_ID + " integer primary key autoincrement, "
            + COLUMN_GAME_ID + " integer, "
            + COLUMN_PLAYER_ID + " integer, "
            + Q1_GOALS + idf
            + Q2_GOALS + idf
            + Q3_GOALS + idf
            + Q4_GOALS + idf
            + TOTAL_GOALS + idf
            + Q1_ASSISTS + idf
            + Q2_ASSISTS + idf
            + Q3_ASSISTS + idf
            + Q4_ASSISTS + idf
            + TOTAL_ASSISTS + idf
            + Q1_SHOTS + idf
            + Q2_SHOTS + idf
            + Q3_SHOTS + idf
            + Q4_SHOTS + idf
            + TOTAL_SHOTS + idf
            + Q1_GBS + idf
            + Q2_GBS + idf
            + Q3_GBS + idf
            + Q4_GBS + idf
            + TOTAL_GBS + idf
            + Q1_CAUSED_TURNOVERS + idf
            + Q2_CAUSED_TURNOVERS + idf
            + Q3_CAUSED_TURNOVERS + idf
            + Q4_CAUSED_TURNOVERS + idf
            + TOTAL_CAUSED_TURNOVERS + idf
            + Q1_FO_WINS + idf
            + Q2_FO_WINS + idf
            + Q3_FO_WINS + idf
            + Q4_FO_WINS + idf
            + TOTAL_FO_WINS + idf
            + Q1_FO_LOSSES + idf
            + Q2_FO_LOSSES + idf
            + Q3_FO_LOSSES + idf
            + Q4_FO_LOSSES + idf
            + TOTAL_FO_LOSSES + idf
            + Q1_SAVES + idf
            + Q2_SAVES + idf
            + Q3_SAVES + idf
            + Q4_SAVES + idf
            + TOTAL_SAVES + idf
            + Q1_GOALS_ALLOWED + idf
            + Q2_GOALS_ALLOWED + idf
            + Q3_GOALS_ALLOWED + idf
            + Q4_GOALS_ALLOWED + idf
            + TOTAL_GOALS_ALLOWED + idf
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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_INDIVIDUAL_STATS);
        onCreate(database);

        Log.d("TableTask.onUpgrade()", "complete");
    }
}
