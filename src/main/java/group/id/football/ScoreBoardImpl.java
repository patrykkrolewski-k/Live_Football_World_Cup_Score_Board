package group.id.football;

import group.id.api.Scoreboard;

import java.util.List;

public class ScoreBoardImpl implements Scoreboard {


    private List<Match> matches;
    public void startNewMatch(FootballTeam homeTeam, FootballTeam awayTeam) {

    }

    List<Match> getMatches() {
        return matches;
    }

}
