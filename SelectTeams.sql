SELECT teamname as team, playername as players from player, team, roster 
where roster.team_id = team.team_id AND roster.player_id = player.player_id;