package com.roy.fileSystemHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
		File folder = new File(path.toString() + '/' + name);
		if (folder.exists() && folder.isDirectory()) {
			if(folder.delete()){
				log.info("Folder deleted.");
				return true;
			} else {
				log.error("Folder delete failed.");
				return false;
			}
		} else {
			log.error("Cannot delete folder. Folder doesn't exists");
			return false;
		}
	}

	public boolean deleteFile(String name) {
		File file = new File(path.toString() + '/' + name);
		if (file.exists() && !file.isDirectory()) {
			if(file.delete()){
				log.info("File deleted.");
				return true;
			} else {
				log.error("File delete failed.");
				return false;
			}
		} else {
			log.error("Cannot delete File. File doesn't exists");
			return false;
		}
	}

	public List<File> getFileList() {
		File[] directories = new File(path.toString()).listFiles(File::isFile);
		List<File> file = Arrays.asList(directories);
		log.debug("getFileList : " + Arrays.toString(directories));
//		System.out.println(Arrays.toString(directories));
		return file;
	}

	public List<File> getFolderList() {
		File[] directories = new File(path.toString()).listFiles(File::isDirectory);
		List<File> folder = Arrays.asList(directories);
		log.debug("getFolderList : " + Arrays.toString(directories));
//		System.out.println(Arrays.toString(directories));
		return folder;
	}

	public boolean moveFile(String from, String to) {
		to = to+from;
		if(renameFile(from, to)){
			log.info("File moved");
			return true;
		} else {
			log.info("Failed to move file");
			return false;
		}
	}

	public boolean moveFolder(String from, String to) {
		to = to+from;
		if(renameFolder(from, to)){
			log.info("Folder moved");
			return true;
		} else {
			log.info("Failed to move folder");
			return false;
		}
	}

}
