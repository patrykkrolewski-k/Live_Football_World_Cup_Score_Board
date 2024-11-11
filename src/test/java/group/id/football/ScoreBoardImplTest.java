package group.id.football;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardImplTest {
    @Test
    void matchIsInitialisedWithZeroScore() {
        ScoreBoardImpl scoreboard = new ScoreBoardImpl();
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
        ScoreBoardImpl scoreboard = new ScoreBoardImpl();
        scoreboard.startNewMatch(null, "Canada");
        assertThat(listAppender.list.get(0)).isEqualTo("Home team name cannot be null");
    }

    @Test
    void nawayTeamNameCannotBeNull() {
        Logger logger = (Logger) LoggerFactory.getLogger(ScoreBoardImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        ScoreBoardImpl scoreboard = new ScoreBoardImpl();
        scoreboard.startNewMatch("Mexico", null);
        assertThat(listAppender.list.get(0)).isEqualTo("Away team name cannot be null");
    }

    @Test
    void startTheSameMatchParallelly() {
        Logger logger = (Logger) LoggerFactory.getLogger(ScoreBoardImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        ScoreBoardImpl scoreboard = new ScoreBoardImpl();
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Mexico", "Canada");
        assertThat(listAppender.list.get(0).getMessage()).contains("Mexico - Canada match has already started");

    }
    @Test
    void updateScoreOfMatches() {
        ScoreBoardImpl scoreboard = new ScoreBoardImpl();
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
        ScoreBoardImpl scoreboard = new ScoreBoardImpl();
        scoreboard.updateMatchScore("Mexico",1, "Belgium", 3);
        assertThat(listAppender.list.get(0)).isEqualTo("There is no Mexico - Belgium match now");

    }





}
