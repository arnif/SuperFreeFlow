package ru.andr.SuperFreeFlow;

/**
 * Created by arnif on 9/16/14.
 */
public class Puzzle {

    private String size;
    private String flows;

    public Puzzle(String size, String flows) {
        this.size = size;
        this.flows = flows;
    }

    public String getSize() {
        return size;
    }

    public String getFlows() {
        return flows;
    }
}
