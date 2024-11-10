package group.id.api;

import group.id.football.FootballTeam;

public interface Scoreboard {
    public void startNewMatch(FootballTeam homeTeam, FootballTeam awayTeam);
}
