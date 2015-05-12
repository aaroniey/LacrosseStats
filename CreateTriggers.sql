create trigger UpdateTeamStats after update on Game 
begin
	UPDATE Game SET tot_suc_clears = new.Q1_suc_clears + new.Q2_suc_clears + new.Q3_suc_clears + new.Q4_suc_clears
    WHERE game_id = new.game_id;
	UPDATE Game SET tot_fail_clears = new.Q1_fail_clears + new.Q2_fail_clears + new.Q3_fail_clears + new.Q4_fail_clears
    WHERE game_id = new.game_id;
	UPDATE Game SET tot_suc_rides = new.Q1_suc_rides + new.Q2_suc_rides + new.Q3_suc_rides + new.Q4_suc_rides
    WHERE game_id = new.game_id;
	UPDATE Game SET tot_fail_rides = new.Q1_fail_rides + new.Q2_fail_rides + new.Q3_fail_rides + new.Q4_fail_rides
    WHERE game_id = new.game_id;
end;

create trigger CreateIndividualStats after insert on Game
begin
	insert into IndividualStats(player_id, game_id) select roster.player_id, game.game_id from game, roster where game.team_id = roster.team_id;
end;

create trigger UpdateGameStats after update on IndividualStats
begin
	update Game set team_score = team_score + new.tot_goals - old.tot_goals where game_id = new.game_id;
end;