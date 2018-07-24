package com.sahajamit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
    public static void main(String[] args) throws IOException {
        String path =  new MainClass().getFilePath("/Users/amitrawat/Downloads/tmp","abc");
        System.out.println(path);
    }

    public String getFilePath(String folder, String partialFileName) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(folder))) {
            Optional<Path> matchedFilePath =  paths
                    .filter(Files::isRegularFile)
                    .filter(f->f.getFileName().toString().toLowerCase().contains(partialFileName.toLowerCase()))
                    .findFirst();
            if(matchedFilePath.isPresent())
                return matchedFilePath.get().toAbsolutePath().toString();
            else
                throw new IllegalStateException("No file found in the folder with this name: " + partialFileName);
        }catch (Exception e){
            throw e;
        }
    }

}
