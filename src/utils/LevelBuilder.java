package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {
    private int[][] level;

    public LevelBuilder(String filename) {
        readLevelFromFile(filename);
    }

    private void readLevelFromFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numRows = lines.size();
        int numCols = lines.get(0).split(",\\s*").length; // Assuming elements are separated by commas and optional whitespace

        level = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] elements = lines.get(i).split(",\\s*");
            for (int j = 0; j < numCols; j++) {
                level[i][j] = Integer.parseInt(elements[j]);
            }
        }
    }

    public int[][] getLevel() {
        return level;
    }
}
