package group.id.football;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardImplTest {

    @Test
    void matchIsInitialisedWithZeroScore() {
        ScoreBoardImpl scoreboard = new ScoreBoardImpl();
        scoreboard.startNewMatch(new FootballTeam(), new FootballTeam());

        assertThat(scoreboard.getMatches().get(0).getAwayTeamPoints()).isZero();
        assertThat(scoreboard.getMatches().get(0).getHomeTeamPoints()).isZero();
    }
}
