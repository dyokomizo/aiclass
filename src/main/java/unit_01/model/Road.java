package unit_01.model;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;



public @Data class Road {
    private static volatile AtomicInteger ORDINAL = new AtomicInteger();
    private final int ordinal = ORDINAL.incrementAndGet();
    private final int cost;


    @Override public String toString() {
        return String.valueOf(cost);
    }
}