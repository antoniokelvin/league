import java.util.Optional;

public class Match {

    private String team1;
    private String team2;
    private Integer score1;
    private Integer score2;
    private Optional<String> winningTeam = Optional.empty();
    private Optional<String> losingTeam = Optional.empty();

    public Match(String line) {
        String[] arr = line.split(",");
        String team1Results = arr[0].trim();
        int lastSpaceIndex = team1Results.lastIndexOf(" ");
        team1 = team1Results.substring(0, lastSpaceIndex);
        score1 = Integer.parseInt(team1Results.substring(lastSpaceIndex + 1));
        String team2Results = arr[1].trim();

        lastSpaceIndex = team2Results.lastIndexOf(" ");
        team2 = team2Results.substring(0, lastSpaceIndex);
        score2 = Integer.parseInt(team2Results.substring(lastSpaceIndex + 1));
        processWinnerAndLoser();
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public Optional<String> getWinningTeam() {
        return this.winningTeam;
    }

    public Optional<String> getLosingTeam() {
        return this.losingTeam;
    }

    private void processWinnerAndLoser() {
        if (score1 > score2) {
            winningTeam = Optional.of(team1);
            losingTeam = Optional.of(team2);
        } else if (score2 > score1) {
            winningTeam = Optional.of(team2);
            losingTeam = Optional.of(team1);
        }
    }

}
