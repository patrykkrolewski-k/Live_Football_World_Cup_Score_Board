package group.id.api;

public interface Scoreboard {
        void startNewMatch(String homeTeamName, String awayTeamName);
        void updateMatchScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore);
        void finishMatch(String homeTeamName, String awayTeamName);
}
