package silkysmoothproductions.lacrossestats.db;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class StatsDBHelper
        extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "color.db";
    private static final int DATABASE_VERSION = 1;

    private static final String GameTeamStatsUpdateTrigger = "create trigger UpdateTeamStats after update on "
            + GameTable.TABLE_GAME + " begin "
            //final score
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.FINAL_TEAM_SCORE
            + " = new." + GameTable.Q1_TEAM_SCORE + " + new." + GameTable.Q2_TEAM_SCORE + " + new." + GameTable.Q3_TEAM_SCORE + " + new." + GameTable.Q4_TEAM_SCORE
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //oppenet score
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.FINAL_OPPONENT_SCORE
            + " = new." + GameTable.Q1_OPPONENT_SCORE + " + new." + GameTable.Q2_OPPONENT_SCORE + " + new." + GameTable.Q3_OPPONENT_SCORE + " + new." + GameTable.Q4_OPPONENT_SCORE
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //successful clears
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_CLEARS
            + " = new." + GameTable.Q1_SUCCESSFUL_CLEARS + " + new." + GameTable.Q2_SUCCESSFUL_CLEARS + " + new." + GameTable.Q3_SUCCESSFUL_CLEARS + " + new." + GameTable.Q4_SUCCESSFUL_CLEARS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //failed clears
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_CLEARS
            + " = new." + GameTable.Q1_FAILED_CLEARS + " + new." + GameTable.Q2_FAILED_CLEARS + " + new." + GameTable.Q3_FAILED_CLEARS + " + new." + GameTable.Q4_FAILED_CLEARS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //successful rides
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_RIDES
            + " = new." + GameTable.Q1_SUCCESSFUL_RIDES + " + new." + GameTable.Q2_SUCCESSFUL_RIDES + " + new." + GameTable.Q3_SUCCESSFUL_RIDES + " + new." + GameTable.Q4_SUCCESSFUL_RIDES
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //failed rides
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_RIDES
            + " = new." + GameTable.Q1_FAILED_RIDES + " + new." + GameTable.Q2_FAILED_RIDES + " + new." + GameTable.Q3_FAILED_RIDES + " + new." + GameTable.Q4_FAILED_RIDES
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //successful manups
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_MANUPS
            + " = new." + GameTable.Q1_SUCCESSFUL_MANUPS + " + new." + GameTable.Q2_SUCCESSFUL_MANUPS + " + new." + GameTable.Q3_SUCCESSFUL_MANUPS + " + new." + GameTable.Q4_SUCCESSFUL_MANUPS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //failed manups
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_MANUPS
            + " = new." + GameTable.Q1_FAILED_MANUPS + " + new." + GameTable.Q2_FAILED_MANUPS + " + new." + GameTable.Q3_FAILED_MANUPS + " + new." + GameTable.Q4_FAILED_MANUPS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //SUCCESSFUL MANDOWNS
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_SUCCESSFUL_MANDOWNS
            + " = new." + GameTable.Q1_SUCCESSFUL_MANDOWNS + " + new." + GameTable.Q2_SUCCESSFUL_MANDOWNS + " + new." + GameTable.Q3_SUCCESSFUL_MANDOWNS + " + new." + GameTable.Q4_SUCCESSFUL_MANDOWNS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + GameTable.COLUMN_GAME_ID + ";"

            //FAILED MANDOWNS
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_FAILED_MANDOWNS
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

    private static final String IndividualStatsUpdateTrigger = "create trigger UpdateIndividualStats after update on "
            + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " begin "
            //Goals
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_GOALS
            + " = new." + IndividualStatsTable.Q1_GOALS + " + new." + IndividualStatsTable.Q2_GOALS + " + new." + IndividualStatsTable.Q3_GOALS + " + new." + IndividualStatsTable.Q4_GOALS
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //ASSISTS
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_ASSISTS
            + " = new." + IndividualStatsTable.Q1_ASSISTS + " + new." + IndividualStatsTable.Q2_ASSISTS + " + new." + IndividualStatsTable.Q3_ASSISTS + " + new." + IndividualStatsTable.Q4_ASSISTS
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //gbs
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_GBS
            + " = new." + IndividualStatsTable.Q1_GBS + " + new." + IndividualStatsTable.Q2_GBS + " + new." + IndividualStatsTable.Q3_GBS + " + new." + IndividualStatsTable.Q4_GBS
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //caused turnovers
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_CAUSED_TURNOVERS
            + " = new." + IndividualStatsTable.Q1_CAUSED_TURNOVERS + " + new." + IndividualStatsTable.Q2_CAUSED_TURNOVERS + " + new." + IndividualStatsTable.Q3_CAUSED_TURNOVERS + " + new." + IndividualStatsTable.Q4_CAUSED_TURNOVERS
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //fo wins
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_FO_WINS
            + " = new." + IndividualStatsTable.Q1_FO_WINS + " + new." + IndividualStatsTable.Q2_FO_WINS + " + new." + IndividualStatsTable.Q3_FO_WINS + " + new." + IndividualStatsTable.Q4_FO_WINS
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //fo Losses
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_FO_LOSSES
            + " = new." + IndividualStatsTable.Q1_FO_LOSSES + " + new." + IndividualStatsTable.Q2_FO_LOSSES + " + new." + IndividualStatsTable.Q3_FO_LOSSES + " + new." + IndividualStatsTable.Q4_FO_LOSSES
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //saves
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_SAVES
            + " = new." + IndividualStatsTable.Q1_SAVES + " + new." + IndividualStatsTable.Q2_SAVES + " + new." + IndividualStatsTable.Q3_SAVES + " + new." + IndividualStatsTable.Q4_SAVES
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //goals allowed
            + " update " + IndividualStatsTable.TABLE_INDIVIDUAL_STATS + " set " + IndividualStatsTable.TOTAL_GOALS_ALLOWED
            + " = new." + IndividualStatsTable.Q1_GOALS_ALLOWED + " + new." + IndividualStatsTable.Q2_GOALS_ALLOWED + " + new." + IndividualStatsTable.Q3_GOALS_ALLOWED + " + new." + IndividualStatsTable.Q4_GOALS_ALLOWED
            + " where " + IndividualStatsTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update final score
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.FINAL_TEAM_SCORE
            + " = "+ GameTable.FINAL_TEAM_SCORE +  " + new." + IndividualStatsTable.TOTAL_GOALS + " - old." + IndividualStatsTable.TOTAL_GOALS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update q1 score
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q1_TEAM_SCORE
            + " = "+ GameTable.Q1_TEAM_SCORE +  " + new." + IndividualStatsTable.Q1_GOALS + " - old." + IndividualStatsTable.Q1_GOALS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update q2 score
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q2_TEAM_SCORE
            + " = "+ GameTable.Q2_TEAM_SCORE +  " + new." + IndividualStatsTable.Q2_GOALS + " - old." + IndividualStatsTable.Q2_GOALS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update q3 score
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q3_TEAM_SCORE
            + " = "+ GameTable.Q3_TEAM_SCORE +  " + new." + IndividualStatsTable.Q3_GOALS + " - old." + IndividualStatsTable.Q3_GOALS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"
            
            //update q4 score
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q4_TEAM_SCORE
            + " = "+ GameTable.Q4_TEAM_SCORE +  " + new." + IndividualStatsTable.Q4_GOALS + " - old." + IndividualStatsTable.Q4_GOALS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update total assists
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_TEAM_ASSISTS
            + " = "+ GameTable.TOTAL_TEAM_ASSISTS +  " + new." + IndividualStatsTable.TOTAL_ASSISTS + " - old." + IndividualStatsTable.TOTAL_ASSISTS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"
           
            //update total shots
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_TEAM_SHOTS
            + " = "+ GameTable.TOTAL_TEAM_SHOTS +  " + new." + IndividualStatsTable.TOTAL_SHOTS + " - old." + IndividualStatsTable.TOTAL_SHOTS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update total GBS
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_TEAM_GBS
            + " = "+ GameTable.TOTAL_TEAM_GBS +  " + new." + IndividualStatsTable.TOTAL_GBS + " - old." + IndividualStatsTable.TOTAL_GBS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update total CAUSED TURNOVERS
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_TEAM_CAUSED_TURNOVERS
            + " = "+ GameTable.TOTAL_TEAM_CAUSED_TURNOVERS +  " + new." + IndividualStatsTable.TOTAL_CAUSED_TURNOVERS + " - old." + IndividualStatsTable.TOTAL_CAUSED_TURNOVERS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update total FO_WINS
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_TEAM_FO_WINS
            + " = "+ GameTable.TOTAL_TEAM_FO_WINS +  " + new." + IndividualStatsTable.TOTAL_FO_WINS + " - old." + IndividualStatsTable.TOTAL_FO_WINS
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update total FO_LOSSES
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_TEAM_FO_LOSSES
            + " = "+ GameTable.TOTAL_TEAM_FO_LOSSES +  " + new." + IndividualStatsTable.TOTAL_FO_LOSSES + " - old." + IndividualStatsTable.TOTAL_FO_LOSSES
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update total assists
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.TOTAL_TEAM_SAVES
            + " = "+ GameTable.TOTAL_TEAM_SAVES +  " + new." + IndividualStatsTable.TOTAL_SAVES + " - old." + IndividualStatsTable.TOTAL_SAVES
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update final score OPP
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.FINAL_OPPONENT_SCORE
            + " = "+ GameTable.FINAL_OPPONENT_SCORE +  " + new." + IndividualStatsTable.TOTAL_GOALS_ALLOWED + " - old." + IndividualStatsTable.TOTAL_GOALS_ALLOWED
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update q1 score OPP
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q1_OPPONENT_SCORE
            + " = "+ GameTable.Q1_OPPONENT_SCORE +  " + new." + IndividualStatsTable.Q1_GOALS_ALLOWED + " - old." + IndividualStatsTable.Q1_GOALS_ALLOWED
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update q2 score OPP
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q2_OPPONENT_SCORE
            + " = "+ GameTable.Q2_OPPONENT_SCORE +  " + new." + IndividualStatsTable.Q2_GOALS_ALLOWED + " - old." + IndividualStatsTable.Q2_GOALS_ALLOWED
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update q3 score OPP
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q3_OPPONENT_SCORE
            + " = "+ GameTable.Q3_OPPONENT_SCORE +  " + new." + IndividualStatsTable.Q3_GOALS_ALLOWED + " - old." + IndividualStatsTable.Q3_GOALS_ALLOWED
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"

            //update q4 score OPP
            + " update " + GameTable.TABLE_GAME + " set " + GameTable.Q4_OPPONENT_SCORE
            + " = "+ GameTable.Q4_OPPONENT_SCORE +  " + new." + IndividualStatsTable.Q4_GOALS_ALLOWED + " - old." + IndividualStatsTable.Q4_GOALS_ALLOWED
            + " where " + GameTable.COLUMN_GAME_ID + " = new." + IndividualStatsTable.COLUMN_GAME_ID + ";"
            
            + "end;";

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
        database.execSQL("PRAGMA foreign_keys=1;");
        database.execSQL(GameTeamStatsUpdateTrigger);
        database.execSQL(CreateIndividualStats);
        database.execSQL(IndividualStatsUpdateTrigger);
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

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}

