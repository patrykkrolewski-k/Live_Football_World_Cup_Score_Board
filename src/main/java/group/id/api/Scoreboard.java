package group.id.api;

import group.id.football.FootballTeam;

public interface Scoreboard {
    void startNewMatch(FootballTeam homeTeam, FootballTeam awayTeam);

}
