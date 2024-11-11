package group.id.football;

import java.util.Objects;

class Match {

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
        this.homeTeamPoints = homeTeamPoints;
        this.awayTeamPoints = awayTeamPoints;
    }

    @Override
    public String toString() {
        return homeTeam.getName() + " " + homeTeamPoints + " - " + awayTeam.getName() + " " + awayTeamPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return homeTeam.equals(match.homeTeam) && awayTeam.equals(match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
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
