public class PlayerStats implements Comparable<PlayerStats> {
    private String player;
    private String position;
    private String team;
    private int gamesPlayed;
    private double points;

    // Constructor
    public PlayerStats(String player, String position, String team, int gamesPlayed, double points) {
        this.player = player;
        this.position = position;
        this.team = team;
        this.gamesPlayed = gamesPlayed;
        this.points = points;
    }

    // Getters
    public String getPlayer() {
        return player;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getPoints() {
        return points;
    }

    // ToString to display PlayerStats data
    @Override
    public String toString() {
        return "Player: " + player + ", Position: " + position + ", Team: " + team +
                ", Games Played: " + gamesPlayed + ", Points: " + points;
    }

    // compareTo method: compare based on points (or change as per requirement)
    @Override
    public int compareTo(PlayerStats other) {
        return Double.compare(this.points, other.points); // compare based on points
    }

    // equals method to compare players by name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PlayerStats other = (PlayerStats) obj;
        return player.equals(other.player); // compare based on player name
    }
}