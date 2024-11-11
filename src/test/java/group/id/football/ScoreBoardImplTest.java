package group.id.football;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardImplTest {

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
        scoreboard.updateMatchScore("Mexico",4, "Canada", 2);
        scoreboard.startNewMatch("Mexico", "Canada");
        assertThat(listAppender.list.get(0).getMessage()).contains("Mexico - Canada match has already started");
        Match match = scoreboard.getMatchMap().values().iterator().next();
        assertThat(match.getHomeTeamPoints()).isEqualTo(4);
        assertThat(match.getAwayTeamPoints()).isEqualTo(2);
    }
    @Test
    void updateScoreOfMatches() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.updateMatchScore("Mexico",1, "Canada", 3);
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
        scoreboard.updateMatchScore("Mexico",1, "Belgium", 3);
        assertThat(listAppender.list.get(0).getMessage()).contains("There is no Mexico - Belgium match now");
    }

    @Test
    void finishMatchInProgressShouldRemoveItFromScoreBoard() {
        scoreboard.startNewMatch("Mexico", "Canada");
        assertThat(scoreboard.getMatchMap().values()).isEmpty();
    }

}
