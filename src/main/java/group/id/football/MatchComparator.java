package group.id.football;

import java.util.Comparator;

class MatchComparator implements Comparator<Match> {
    @Override
    public int compare(Match o1, Match o2) {
    int pointsDifference = o2.getHomeTeamPoints() + o2.getAwayTeamPoints()
            - o1.getHomeTeamPoints() - o1.getAwayTeamPoints();

    if (pointsDifference == 0) {
        return o2.getMatchIndex() - o1.getMatchIndex();
    }
        return pointsDifference;
    }
}
