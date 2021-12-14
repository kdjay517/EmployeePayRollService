package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperations {
	
	private static final String HOME = "Data/abc/test.txt" ;
	
	public static void main(String[] args) throws IOException {
		Path homePath = Paths.get(HOME);
		System.out.println(Files.exists(homePath));
		Files.createDirectories(homePath);
		System.out.println(Files.exists(homePath));
		Files.createFile(homePath);
		DirectoryStream<Path> stream = Files.newDirectoryStream(homePath);
		
		for (Path path:stream) {
			System.out.println(path.getFileName());
		}
	}
}
