create table Player(
	player_id integer primary key autoincrement,
	first_name TEXT not null,
	last_name TEXT not null,
	jersey_number integer not null check(jersey_number > 0),
	grade TEXT, 
	position TEXT check(position = 'att' or position = 'mid' or position = 'gol' or position = 'def')
);
create table Team(
	team_id integer primary key autoincrement,
	teamname text
);
create table Roster(
	team_id integer,
	player_id integer,
	Foreign key(team_id) references Team(team_id),
	Foreign key(player_id) references Player(player_id)
);
create table Game(
	game_id integer primary key autoincrement,
	team_id integer,
	opponent_name text,
	
	final_team_score integer default 0,
	final_opponent_score integer default 0,
	
	
	
	tot_team_GBs integer default 0;
	tot_team_Assists integer default 0;
	tot_team_shots integer default 0;
	
	Q1_suc_clears integer default 0,
	Q1_fail_clears integer default 0,
	Q2_suc_clears integer default 0,
	Q2_fail_clears integer default 0,
	Q3_suc_clears integer default 0,
	Q3_fail_clears integer default 0,
	Q4_suc_clears integer default 0,
	Q4_fail_clears integer default 0,
	tot_suc_clears integer default 0,
	tot_fail_clears integer default 0,
	
	Q1_suc_rides integer default 0,
	Q1_fail_rides integer default 0,
	Q2_suc_rides integer default 0,
	Q2_fail_rides integer default 0,
	Q3_suc_rides integer default 0,
	Q3_fail_rides integer default 0,
	Q4_suc_rides integer default 0,
	Q4_fail_rides integer default 0,
	tot_suc_rides integer default 0,
	tot_fail_rides integer default 0,
		
	Foreign key(team_id) references Team(team_id)
);
create table IndividualStats(
	stats_id integer primary key autoincrement,
	player_id integer, 
	game_id integer,
	tot_shots integer default 0,
	tot_goals integer default 0,
	tot_GBs integer default 0,
	tot_Assists integer default 0, 
	Foreign key(player_id) references Player(player_id),
	Foreign key(game_id) references Game(game_id)
);