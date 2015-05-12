SELECT teamname as team, first_name, last_name, jersey_number, grade, position from player, team, roster 
where roster.team_id = team.team_id AND roster.player_id = player.player_id;