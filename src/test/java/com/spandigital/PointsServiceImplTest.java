package com.spandigital;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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


}
