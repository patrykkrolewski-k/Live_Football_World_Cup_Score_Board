package group.id.football;

import group.id.api.Scoreboard;

import java.util.*;

public class ScoreBoardImpl implements Scoreboard {

    private final Map<String, Match> matchMap;

    public ScoreBoardImpl() {
        this.matchMap = new HashMap();
    }

    public void startNewMatch(String homeTeamName, String awayTeamName) {
        matchMap.put(
                homeTeamName + awayTeamName,
                new Match(
                        new FootballTeam(homeTeamName),
                        new FootballTeam(awayTeamName)));
    }

    @Override
    public void updateMatchScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) {
        if (matchMap.containsKey(homeTeamName + awayTeamName)) {
            matchMap.get(homeTeamName + awayTeamName).updateScore(homeTeamScore, awayTeamScore);
        }
    }

    public Map<String, Match> getMatchMap() {
        return matchMap;
    }
}
