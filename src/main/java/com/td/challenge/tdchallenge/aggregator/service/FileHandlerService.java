package com.td.challenge.tdchallenge.aggregator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileHandlerService {

    @Value("${file.prefix.name}")
    private String filePath;

    Set<String> files;

    @PostConstruct
    public void loadFile() throws IOException, URISyntaxException {
        if (files == null){
            files = readFile(filePath);
        }
    }

    private Set<String> readFile(String filePath) throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(filePath).toURI());
        return Files.lines(path).sorted().collect(Collectors.toSet());
    }

    public Set<String> getFiles() {
        return files;
    }

}
