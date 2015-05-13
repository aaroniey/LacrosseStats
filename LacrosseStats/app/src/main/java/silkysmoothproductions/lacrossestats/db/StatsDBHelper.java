package silkysmoothproductions.lacrossestats.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatsDBHelper
        extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "color.db";
    private static final int DATABASE_VERSION = 1;

    private static final String GameTeamStatsUpdateTrigger = "create trigger UpdateTeamStats after update on "
            + GameTable.TABLE_GAME + "begin "
            //final score
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.FINAL_TEAM_SCORE
            + " = new." + GameTable.Q1_TEAM_SCORE + " + new." + GameTable.Q2_TEAM_SCORE + " + new." + GameTable.Q3_TEAM_SCORE + " + new." + GameTable.Q4_TEAM_SCORE
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //oppenet score
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.FINAL_OPPONENT_SCORE
            + " = new." + GameTable.Q1_OPPONENT_SCORE + " + new." + GameTable.Q2_OPPONENT_SCORE + " + new." + GameTable.Q3_OPPONENT_SCORE + " + new." + GameTable.Q4_OPPONENT_SCORE
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //successful clears
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_CLEARS
            + " = new." + GameTable.Q1_SUCCESSFUL_CLEARS + " + new." + GameTable.Q2_SUCCESSFUL_CLEARS + " + new." + GameTable.Q3_SUCCESSFUL_CLEARS + " + new." + GameTable.Q4_SUCCESSFUL_CLEARS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //failed clears
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_CLEARS
            + " = new." + GameTable.Q1_FAILED_CLEARS + " + new." + GameTable.Q2_FAILED_CLEARS + " + new." + GameTable.Q3_FAILED_CLEARS + " + new." + GameTable.Q4_FAILED_CLEARS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //successful rides
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_RIDES
            + " = new." + GameTable.Q1_SUCCESSFUL_RIDES + " + new." + GameTable.Q2_SUCCESSFUL_RIDES + " + new." + GameTable.Q3_SUCCESSFUL_RIDES + " + new." + GameTable.Q4_SUCCESSFUL_RIDES
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //failed rides
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_RIDES
            + " = new." + GameTable.Q1_FAILED_RIDES + " + new." + GameTable.Q2_FAILED_RIDES + " + new." + GameTable.Q3_FAILED_RIDES + " + new." + GameTable.Q4_FAILED_RIDES
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //successful manups
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_MANUPS
            + " = new." + GameTable.Q1_SUCCESSFUL_MANUPS + " + new." + GameTable.Q2_SUCCESSFUL_MANUPS + " + new." + GameTable.Q3_SUCCESSFUL_MANUPS + " + new." + GameTable.Q4_SUCCESSFUL_MANUPS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //failed manups
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_MANUPS
            + " = new." + GameTable.Q1_FAILED_MANUPS + " + new." + GameTable.Q2_FAILED_MANUPS + " + new." + GameTable.Q3_FAILED_MANUPS + " + new." + GameTable.Q4_FAILED_MANUPS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //SUCCESSFUL MANDOWNS
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_MANDOWNS
            + " = new." + GameTable.Q1_SUCCESSFUL_MANDOWNS + " + new." + GameTable.Q2_SUCCESSFUL_MANDOWNS + " + new." + GameTable.Q3_SUCCESSFUL_MANDOWNS + " + new." + GameTable.Q4_SUCCESSFUL_MANDOWNS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //FAILED MANDOWNS
            + "update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_MANDOWNS
            + " = new." + GameTable.Q1_FAILED_MANDOWNS + " + new." + GameTable.Q2_FAILED_MANDOWNS + " + new." + GameTable.Q3_FAILED_MANDOWNS + " + new." + GameTable.Q4_FAILED_MANDOWNS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"
            + "end;";
    private static final String CreateIndividualStats = "create trigger CreateIndividualStats after insert on " + GameTable.TABLE_GAME
            + "  begin insert into " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS
            + "(" + IndividualStatsTable.COLUMN_PLAYER_ID + ", " + IndividualStatsTable.COLUMN_GAME_ID + ") "
            + "select " + RosterTable.TABLE_ROSTER + "." + RosterTable.COLUMN_PLAYER_ID + ", "+ GameTable.TABLE_GAME + "." + GameTable.COLUMN_GAME_ID
            + " from " + GameTable.TABLE_GAME + ", " + RosterTable.TABLE_ROSTER
            + " where " + GameTable.TABLE_GAME + "." + GameTable.COLUMN_TEAM_ID + " = " + RosterTable.TABLE_ROSTER + " . " + RosterTable.COLUMN_TEAM_ID + ";"
            + " end;";

    public StatsDBHelper( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase database ) {
        PlayerTable.onCreate(database);
        TeamTable.onCreate(database);
        RosterTable.onCreate(database);
        GameTable.onCreate(database);
        IndividualStatsTable.onCreate(database);
        database.execSQL(GameTeamStatsUpdateTrigger);
        database.execSQL(CreateIndividualStats);
    }

    @Override
    public void onUpgrade( SQLiteDatabase database,
                           int oldVersion,
                           int newVersion) {
        PlayerTable.onUpgrade( database, oldVersion, newVersion );
        TeamTable.onUpgrade( database, oldVersion, newVersion );
        RosterTable.onUpgrade( database, oldVersion, newVersion );
        GameTable.onUpgrade( database, oldVersion, newVersion );
        IndividualStatsTable.onUpgrade( database, oldVersion, newVersion );
    }

}

