create table Player(
	player_id integer primary key,
	playername TEXT
);
create table Team(
	team_id integer primary key,
	teamname text
);
create table Roster(
	team_id integer,
	player_id integer,
	Foreign key(team_id) references Team(team_id),
	Foreign key(player_id) references Player(player_id)
);