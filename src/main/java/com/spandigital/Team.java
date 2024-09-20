package com.spandigital;

public class Team implements Comparable<Team> {
    String name;
    int points;
    int rank;

    public Team(final String name, final int points){
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Team obj) {
        if (obj.points == points) {
            return name.compareTo(obj.name);
        } else if (points > obj.points) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(rank + ". " + name + ", ").append(points).append(" pt");
        if (points != 1) {
            sb.append("s");
        }
        return sb.toString();
    }
}
