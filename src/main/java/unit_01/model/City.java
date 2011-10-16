package unit_01.model;
import lombok.Data;


public @Data class City {
    private final String name;


    @Override public String toString() {
        return name;
    }
}