package group.id.football;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Match {
    private static final String NEGATIVE_SCORE = "The home team score and the away team score cannot be negative";
    static final Logger LOGGER = LoggerFactory.getLogger(Match.class);

    private final FootballTeam homeTeam;
    private final FootballTeam awayTeam;
    private int homeTeamPoints;
    private int awayTeamPoints;
    private final int matchIndex;

    Match(FootballTeam homeTeam, FootballTeam awayTeam, int matchIndex) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchIndex = matchIndex;
    }

    void updateScore(int homeTeamPoints, int awayTeamPoints) {
        if(homeTeamPoints < 0 || awayTeamPoints < 0){
            LOGGER.warn(NEGATIVE_SCORE);
        } else {
            this.homeTeamPoints = homeTeamPoints;
            this.awayTeamPoints = awayTeamPoints;
        }
    }

    @Override
    public String toString() {
        return homeTeam.getName() + " " + homeTeamPoints + " - " + awayTeam.getName() + " " + awayTeamPoints;
    }

    int getHomeTeamPoints() {
        return homeTeamPoints;
    }

    int getAwayTeamPoints() {
        return awayTeamPoints;
    }

    public int getMatchIndex() {
        return matchIndex;
    }
}
