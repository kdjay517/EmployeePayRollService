package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WatchServiceMain {
	
	private static final String HOME = "Data/";
	
	public static void main(String[] args) throws IOException {
		Path dir = Paths.get(HOME);
		WatchServiceExample service = new WatchServiceExample(dir);
		service.processEvents();
	}

}
