package com.spandigital;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class PointsServiceImpl {

    private static final int WIN_POINTS = 3;
    private static final int DRAW_POINTS = 1;
    private static final int LOSS_POINTS = 0;

    public void processFile(String path) {

        Map<String, Integer> computedScores = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.map(Match::new
            ).forEach((match) -> {
                int currentTeam1Score = computedScores.get(match.getTeam1()) != null ? computedScores.get(match.getTeam1()) : 0;
                int currentTeam2Score = computedScores.get(match.getTeam2()) != null ? computedScores.get(match.getTeam2()) : 0;
                if (!match.getWinningTeam().isPresent()) {
                    computedScores.put(match.getTeam1(), currentTeam1Score + DRAW_POINTS);
                    computedScores.put(match.getTeam2(), currentTeam2Score + DRAW_POINTS);
                } else {
                    int currentWinnerScore = computedScores.get(match.getWinningTeam().get()) != null ? computedScores.get(match.getWinningTeam().get()) : 0;
                    computedScores.put(match.getWinningTeam().get(), currentWinnerScore + WIN_POINTS);
                    if(match.getLosingTeam().isPresent()) {
                        int currentLoserScore = computedScores.get(match.getLosingTeam().get()) != null ? computedScores.get(match.getLosingTeam().get()) : 0;
                        computedScores.put(match.getLosingTeam().get(), currentLoserScore + LOSS_POINTS);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        createTable(computedScores).forEach(System.out::println);
    }

    public Set<Team> createTable(Map<String, Integer> logTable) {
        Comparator<Team> comp = Team::compareTo;
        Set<Team> table = new TreeSet<>(comp);

        logTable.forEach((name, value) -> table.add(new Team(name, value)));
        AtomicInteger rank = new AtomicInteger(0);
        AtomicReference<Team> previous = new AtomicReference<>();
        table.forEach(team -> {
            if (previous.get() == null) {
                team.setRank(1);
                rank.getAndIncrement();
            } else {
                if (previous.get().getPoints() == team.getPoints()) {
                    team.setRank(previous.get().getRank());
                    rank.getAndIncrement();
                } else {
                    rank.incrementAndGet();
                    team.setRank(rank.get());
                }
            }
            previous.set(team);
        });
        return table;
    }
}
