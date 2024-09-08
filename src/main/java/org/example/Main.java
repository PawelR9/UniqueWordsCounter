package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource("inputFile.txt");

        if (resource == null) {
            throw new IllegalArgumentException("file not found");
        }

        File file = new File(resource.getFile());

        String text = FileUtils.readFileToString(file).toLowerCase().replaceAll("[^a-z0-9\\s]", "");
        String[] words = text.split(" ");

        Map<String, Integer> countMap = new HashMap<>();
        for(String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        StringBuilder outputText = new StringBuilder();

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            outputText.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        FileUtils.writeStringToFile(new File("outputFile.txt"),outputText.toString());

    }
}
