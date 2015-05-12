 insert into game(team_id, opponent_name) values (1, "other guys");
 update game set q1_suc_clears = 3 where game_id = 1;
 update game set q2_suc_clears = 4 where game_id = 1;
 update game set q3_suc_clears = 2 where game_id = 1;
 update game set q4_suc_clears = 1 where game_id = 1;
 select game_id, team_id, opponent_name, Q1_suc_clears,Q2_suc_clears,Q3_suc_clears,Q4_suc_clears, tot_suc_clears from game;
 update game set q1_fail_clears = 3 where game_id = 1;
 update game set q2_fail_clears = 4 where game_id = 1;
 update game set q3_fail_clears = 2 where game_id = 1;
 update game set q4_fail_clears = 1 where game_id = 1;
 select game_id, team_id, opponent_name, Q1_fail_clears,Q2_fail_clears,Q3_fail_clears,Q4_fail_clears, tot_fail_clears from game;
 update game set q1_suc_rides = 3 where game_id = 1;
 update game set q2_suc_rides = 4 where game_id = 1;
 update game set q3_suc_rides = 2 where game_id = 1;
 update game set q4_suc_rides = 1 where game_id = 1;
 select game_id, team_id, opponent_name, Q1_suc_rides,Q2_suc_rides,Q3_suc_rides,Q4_suc_rides, tot_suc_rides from game;
  update game set q1_fail_rides = 3 where game_id = 1;
 update game set q2_fail_rides = 4 where game_id = 1;
 update game set q3_fail_rides = 2 where game_id = 1;
 update game set q4_fail_rides = 1 where game_id = 1;
 select game_id, team_id, opponent_name, Q1_fail_rides,Q2_fail_rides,Q3_fail_rides,Q4_fail_rides, tot_fail_rides from game;
 
 select game_id, player_id from IndividualStats;
 
 update IndividualStats set tot_goals = 1 where player_id = 1;
 select team_score, game_id from game;
 update IndividualStats set tot_goals = 3 where player_id = 2;
 select team_score, game_id from game;