package group.id.football;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {
    private static final String NEGATIVE_SCORE = "The home team score and the away team score cannot be negative";

    @Test
    void updateScoreWithNegativeValueIsNotPossible() {
        Logger logger = (Logger) LoggerFactory.getLogger(Match.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        Match match = new Match(new FootballTeam("Mexico"), new FootballTeam("Belgium"), 0);

        match.updateScore(-1,3);
        assertThat(listAppender.list.get(0).getMessage()).contains(NEGATIVE_SCORE);
        assertThat(match.getHomeTeamPoints()).isZero();
        assertThat(match.getAwayTeamPoints()).isZero();

        match.updateScore(1,-3);
        assertThat(listAppender.list.get(0).getMessage()).contains(NEGATIVE_SCORE);
        assertThat(match.getHomeTeamPoints()).isZero();
        assertThat(match.getAwayTeamPoints()).isZero();

        match.updateScore(-1,-3);
        assertThat(listAppender.list.get(0).getMessage()).contains(NEGATIVE_SCORE);
        assertThat(match.getHomeTeamPoints()).isZero();
        assertThat(match.getAwayTeamPoints()).isZero();
    }
}
