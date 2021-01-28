package com.td.challenge.tdchallenge.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileHandler {

    public List<String> readFile(String filePath) throws IOException, URISyntaxException {

        Path path = Paths.get(getClass().getClassLoader()
                .getResource(filePath).toURI());

        return Files.lines(path).sorted().collect(Collectors.toList());
    }

}
