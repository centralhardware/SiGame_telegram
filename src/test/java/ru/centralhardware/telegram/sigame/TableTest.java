package ru.centralhardware.telegram.sigame;

import org.junit.Test;

import java.io.IOException;

public class TableTest {

    private static final String[] headers = {"first", "second", "third"};
    private static final String[][] data = {
            {"10", "20", "30"},
            {"40", "50", "60"},
            {"70", "80", "90"}
    };

    @Test
    public void render() {
        Table table = new Table(headers, data);
    }
}