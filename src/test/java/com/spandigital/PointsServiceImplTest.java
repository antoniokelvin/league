package com.spandigital;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PointsServiceImplTest {

    PointsServiceImpl pointsService = new PointsServiceImpl();

    @Test
    public void testEmptyTable() {
        Map<String, Integer> logTable = new HashMap<>();
        Set<Team> table =  pointsService.createTable(logTable);
        Assertions.assertEquals(0, table.size());
    }

    @Test
    public void testSingleTable() {
        Map<String, Integer> logTable = new HashMap<>();
        logTable.put("Lions", 1);
        Set<Team> table =  pointsService.createTable(logTable);
        Assertions.assertEquals(1, table.size());
    }

    @Test
    public void testMultipleTeamsTable() {
        Map<String, Integer> logTable = new HashMap<>();
        logTable.put("Snakes", 1);
        logTable.put("Grouches", 0);
        logTable.put("Lions", 5);
        logTable.put("Tarantulas", 6);
        logTable.put("FC Awesome", 1);

        Set<Team> table =  pointsService.createTable(logTable);
        Assertions.assertEquals(5, table.size());
        List<Team> arr = new ArrayList<>(table);
        Team topTeam = arr.get(0);
        Assertions.assertEquals("Tarantulas", topTeam.getName());
        Assertions.assertEquals(6, topTeam.getPoints());
        Assertions.assertEquals(1, topTeam.getRank());


        Team secondTeam = arr.get(1);
        Assertions.assertEquals("Lions", secondTeam.getName());
        Assertions.assertEquals(5, secondTeam.getPoints());
        Assertions.assertEquals(2, secondTeam.getRank());

        // Check tie values
        Team thirdTeam = arr.get(3);
        Assertions.assertEquals("Snakes", thirdTeam.getName());
        Assertions.assertEquals(1, thirdTeam.getPoints());
        Assertions.assertEquals(3, thirdTeam.getRank());

        Team tiedOnThirdTeam = arr.get(2);
        Assertions.assertEquals("FC Awesome", tiedOnThirdTeam.getName());
        Assertions.assertEquals(1, tiedOnThirdTeam.getPoints());
        Assertions.assertEquals(3, tiedOnThirdTeam.getRank());
    }



}
