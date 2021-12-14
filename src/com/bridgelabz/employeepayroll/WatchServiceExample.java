package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class WatchServiceExample {

	private WatchService watcher;
	private Map<WatchKey, Path> keys;

	WatchServiceExample(Path dir) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();

		walkAndReisterDirectories(dir);
	}

	private void walkAndReisterDirectories(Path dir) throws IOException {
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				registerDirectory(dir);
				return FileVisitResult.CONTINUE;
			}
		});

	}

	private void registerDirectory(Path dir) throws IOException {
		WatchKey watchKey = dir.register(this.watcher, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		keys.put(watchKey, dir);
	}

	public void processEvents() {
		for (;;) {
			WatchKey key = null;
			try {
				key = watcher.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Path path = keys.get(key);
			if (path == null) {
				System.err.println("Watch key NotRecognized");
				continue;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				@SurpressWarnigs("rawtypes")
				WatchEvent.Kind kind = event.kind();

				Path name = ((WatchEvent<Path>) event).context();
				Path child = path.resolve(name);

				System.out.format("%s: %s\n", event.kind().name(), child);

				if (kind == StandardWatchEventKinds.ENTRY_CREATE) {

					try {
						if (Files.isDirectory(child))
							walkAndReisterDirectories(child);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			boolean valid = key.reset();
			if(!valid){
				keys.remove(key);
				if (keys.isEmpty()) {
					break;
				}
			}
		}
	}
}
