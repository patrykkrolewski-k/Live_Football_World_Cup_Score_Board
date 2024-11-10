package group.id.football;

import group.id.api.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardImpl implements Scoreboard {


    private final List<Match> matches;

    public ScoreBoardImpl() {
        this.matches = new ArrayList<>();
    }

    public void startNewMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        matches.add(new Match());
    }

    List<Match> getMatches() {
        return matches;
    }

}
