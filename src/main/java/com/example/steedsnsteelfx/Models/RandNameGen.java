package com.example.steedsnsteelfx.Models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class RandNameGen {
    private static String getRandomLine(String path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Random random = new Random();
        return lines.get(random.nextInt(lines.size()));
    }

    public static String generateName() {
        String prefixPath = new File("").getAbsolutePath() + "/Data/prefix.txt";
        String suffixPath = new File("").getAbsolutePath() + "/Data/suffix.txt";

        String randomPrefix = getRandomLine(prefixPath);
        String randomSuffix = getRandomLine(suffixPath);

        return randomPrefix + " " + randomSuffix;
    }
}
