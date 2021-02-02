package com.td.challenge.tdchallenge.aggregator.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileHandlerService {

    @Setter
    @Value("${file.prefix.name}")
    private String filePath;

    Set<String> files;

    @PostConstruct
    public void loadFile() {
        if (files == null){
            files = readFile(filePath);
        }
    }

    private Set<String> readFile(String filePath) {
        InputStream in = getClass().getResourceAsStream(filePath);
        return new Scanner(in)
                .useDelimiter("\n")
                .tokens()
                .collect(Collectors.toSet());
    }

    public Set<String> getFiles() {
        return files;
    }

}
