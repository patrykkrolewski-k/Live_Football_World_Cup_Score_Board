package group.id.football;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardImplTest {

    private static ScoreBoardImpl scoreboard;

    @BeforeAll
    static void init() {
        scoreboard = new ScoreBoardImpl();
        scoreboard.startNewMatch(new FootballTeam(), new FootballTeam());
    }

    @Test
    void matchIsInitialisedWithZeroScore() {
        assertThat(scoreboard.getMatches().get(0).getHomeTeamPoints()).isZero();
        assertThat(scoreboard.getMatches().get(0).getAwayTeamPoints()).isZero();
    }

    @Test
    void updateScoreOfMatches() {
        scoreboard.updateMatchScore(1, 3);
        assertThat(scoreboard.getMatches().get(0).getHomeTeamPoints()).isEqualTo(1);
        assertThat(scoreboard.getMatches().get(0).getAwayTeamPoints()).isEqualTo(3);
    }



}
