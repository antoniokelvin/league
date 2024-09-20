package com.spandigital;

public class Main {

    public static void main(String[] args) {
        PointsServiceImpl service = new PointsServiceImpl();
        service.processFile(args[0]);
    }
}