package com.roy.fileSystemHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FileSystemHandler {
	Logger log = Logger.getLogger(this.getClass());
	Path path;

	public FileSystemHandler(String path) {
		PropertyConfigurator.configure("log4j.properties");
		this.path = Paths.get(path);
		log.info("Path : " + this.path);
	}

	public boolean createFolder(String name) {
		File dir = new File(path.toString() + '/' + name);
		if (dir.exists()) {
			log.info("Folder exists at " + path.toString() + '/' + name);
			return true;
		}
		if (dir.mkdirs()) {
			log.info("Folder created at " + path.toString() + '/' + name);
			return true;
		} else {
			log.error("Failed to create folder at " + path.toString() + '/' + name);
			return false;
		}
	}

	public boolean createFile(String name) {
		File file = new File(path.toString() + '/' + name);
		if (file.exists()) {
			log.info("File exists at " + path.toString() + '/' + name);
			return true;
		}
		try {
			if (file.createNewFile()) {
				log.info("File created at " + path.toString() + '/' + name);
				return true;
			} else {
				log.error("Failed to create file at " + path.toString() + '/' + name);
				return false;
			}
		} catch (IOException e) {
			log.error("Failed to create file" + e.getMessage());
			return false;
		}
	}

	public boolean renameFolder(String from, String to) {
		File fromFolder = new File(path.toString() + '/' + from);
		File toFolder = new File(path.toString() + '/' + to);
		if (fromFolder.exists() && fromFolder.isDirectory()) {
			if (fromFolder.renameTo(toFolder)) {
				log.info("Folder renamed from " + from + " to " + to);
				return true;
			} else {
				log.error("Cannot rename folder.");
				return false;
			}
		} else {
			log.error("Cannot rename folder. Folder doesn't exists");
			return false;
		}

	}

	public boolean renameFile(String from, String to) {
		File fromfile = new File(path.toString() + '/' + from);
		File tofile = new File(path.toString() + '/' + to);
		if (fromfile.exists() && !fromfile.isDirectory()) {
			if (fromfile.renameTo(tofile)) {
				log.info("File renamed from " + from + " to " + to);
				return true;
			} else {
				log.error("Cannot rename file.");
				return false;
			}
		} else {
			log.error("Cannot rename file. File doesn't exists");
			return false;
		}
	}

	
	public boolean deleteFolder(String name) {
		return true;
	}

	public boolean deleteFile(String name) {
		return true;
	}

	public List<String> getFileList() {
		return null;
	}

	public List<String> getFolderList() {
		return null;
	}

	public boolean moveFile(String from, String to) {
		return true;
	}

	public boolean moveFolder(String from, String to) {
		return true;
	}

}
