package group.id.football;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardImplTest {

    private static final String EXPECTED_SUMMARY = "1. Uruguay 6 - Italy 6\n" +
            "2. Spain 10 - Brazil 2\n" +
            "3. Mexico 0 - Canada 5\n" +
            "4. Argentina 3 - Australia 1\n" +
            "5. Germany 2 - France 2";

    private ScoreBoardImpl scoreboard;

    @BeforeEach
    void init() {
        this.scoreboard = new ScoreBoardImpl();
    }

    @Test
    void matchIsInitialisedWithZeroScore() {
        scoreboard.startNewMatch("Mexico", "Canada");
        Match match = scoreboard.getMatchMap().values().iterator().next();
        assertThat(match.getHomeTeamPoints()).isZero();
        assertThat(match.getAwayTeamPoints()).isZero();
    }

    @Test
    void homeTeamNameCannotBeNull() {
        Logger logger = (Logger) LoggerFactory.getLogger(ScoreBoardImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        scoreboard.startNewMatch(null, "Canada");
        assertThat(listAppender.list.get(0).getMessage()).contains("Home team name cannot be null");
    }

    @Test
    void awayTeamNameCannotBeNull() {
        Logger logger = (Logger) LoggerFactory.getLogger(ScoreBoardImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        scoreboard.startNewMatch("Mexico", null);
        assertThat(listAppender.list.get(0).getMessage()).contains("Away team name cannot be null");
    }

    @Test
    void startTheSameMatchParallellyIsForbidenAndShouldNotOverwriteScore() {
        Logger logger = (Logger) LoggerFactory.getLogger(ScoreBoardImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.updateMatchScore("Mexico", 4, "Canada", 2);
        scoreboard.startNewMatch("Mexico", "Canada");
        assertThat(listAppender.list.get(0).getMessage()).contains("Mexico - Canada match has already started");
        Match match = scoreboard.getMatchMap().values().iterator().next();
        assertThat(match.getHomeTeamPoints()).isEqualTo(4);
        assertThat(match.getAwayTeamPoints()).isEqualTo(2);
    }

    @Test
    void updateScoreOfMatches() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.updateMatchScore("Mexico", 1, "Canada", 3);
        Match match = scoreboard.getMatchMap().values().iterator().next();
        assertThat(match.getHomeTeamPoints()).isEqualTo(1);
        assertThat(match.getAwayTeamPoints()).isEqualTo(3);
    }


    @Test
    void updateScoreOfNotExistingMatchIsNotPossible() {
        Logger logger = (Logger) LoggerFactory.getLogger(ScoreBoardImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        scoreboard.updateMatchScore("Mexico", 1, "Belgium", 3);
        assertThat(listAppender.list.get(0).getMessage()).contains("There is no Mexico - Belgium match now");
    }

    @Test
    void finishMatchInProgressShouldRemoveItFromScoreBoard() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.finishMatch("Mexico", "Canada");
        assertThat(scoreboard.getMatchMap().values()).isEmpty();
    }

    @Test
    void scoresShouldBePrintedFromTopTotalScoreAndThenFromOldestMatch() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.updateMatchScore("Mexico", 0, "Canada", 5);
        scoreboard.startNewMatch("Spain", "Brazil");
        scoreboard.updateMatchScore("Spain", 10, "Brazil", 2);
        scoreboard.startNewMatch("Germany", "France");
        scoreboard.updateMatchScore("Germany", 2, "France", 2);
        scoreboard.startNewMatch("Uruguay", "Uruguay");
        scoreboard.updateMatchScore("Uruguay", 6, "Uruguay", 6);
        scoreboard.startNewMatch("Argentina", "Australia");
        scoreboard.updateMatchScore("Argentina", 3, "Australia", 1);

        assertThat(scoreboard.toString()).isEqualTo(EXPECTED_SUMMARY);
    }

}
