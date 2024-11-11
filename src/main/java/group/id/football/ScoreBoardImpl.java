package group.id.football;

import group.id.api.Scoreboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ScoreBoardImpl implements Scoreboard {
    static final Logger LOGGER = LoggerFactory.getLogger(ScoreBoardImpl.class);
    private final Map<String, Match> matchMap;

    public ScoreBoardImpl() {
        this.matchMap = new HashMap();
    }

    public void startNewMatch(String homeTeamName, String awayTeamName) {
         if (verifyNewMatch(homeTeamName, awayTeamName)) {
                matchMap.put(
                        homeTeamName + awayTeamName,
                        new Match(
                                new FootballTeam(homeTeamName),
                                new FootballTeam(awayTeamName)));
        }
    }

    @Override
    public void updateMatchScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) {
        if (matchMap.containsKey(homeTeamName + awayTeamName)) {
            matchMap.get(homeTeamName + awayTeamName).updateScore(homeTeamScore, awayTeamScore);
        } else {
            LOGGER.warn("There is no " + homeTeamName + " - " + awayTeamName + " match now");
        }
    }

    public Map<String, Match> getMatchMap() {
        return matchMap;
    }

    private boolean verifyNewMatch(String homeTeamName, String awayTeamName) {
        if (matchMap.containsKey(homeTeamName + awayTeamName)) {
            LOGGER.warn(homeTeamName + " - " + awayTeamName + " match has already started");
            return false;
        }
        if (Objects.isNull(homeTeamName)) {
            LOGGER.warn("Home team name cannot be null");
            return false;
        }
        if (Objects.isNull(awayTeamName)) {
            LOGGER.warn("Away team name cannot be null");
            return false;
        }
        return true;
    }
}
