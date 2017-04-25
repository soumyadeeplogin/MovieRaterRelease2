package com.roy.complications;

import java.io.File;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.roy.apiHandler.APIHandler;
import com.roy.fileSystemHandler.FileSystemHandler;




public class ProcessDirectory {

	String path;
	Logger log = Logger.getLogger(this.getClass());

	public ProcessDirectory(String path) {
		this.path = path;
		PropertyConfigurator.configure("log4j.properties");
	}

	public boolean rateIt(){
		
		FileSystemHandler fileSystemHandler = new FileSystemHandler(this.path);
		log.info("Working on directory : " + this.path);
		
		moveToFolder();
		
		List<File> fileList = fileSystemHandler.getFolderList();
		ListIterator<File> iterator = fileList.listIterator();
		while (iterator.hasNext()) {
			String folderNameCache = iterator.next().toString();
			String movieName = nameCleaner(folderNameCache);
			log.info("Movie name : " + movieName);
			APIHandler apiHandler = new APIHandler(movieName);
			fileSystemHandler.renameFolder('\\'+folderNameCache.lastIndexOf('\\'), '\\'+apiHandler.getCleanRating() + " " + apiHandler.getTitle());			
		}
		return true; 
	}
	
	private void moveToFolder(){
		
		FileSystemHandler fileSystemHandler = new FileSystemHandler(this.path);
		List<File> fileList = fileSystemHandler.getFileList();
		ListIterator<File> iterator = fileList.listIterator();
		while (iterator.hasNext()) {
			String fileName = iterator.next().toString();
			fileName = fileName.substring(fileName.lastIndexOf('\\')+1);
			String folderName = fileName.substring(0, fileName.lastIndexOf('.'));
			log.info("Moving " + fileName + " to " + folderName );
			
			if(fileSystemHandler.createFolder(folderName)){
				if(fileSystemHandler.moveFile(fileName, '\\'+folderName+'\\'))
				{
					log.info("Moved");
				} else {
					log.error("Move failed");
				}
			} else {
				log.error("Folder creation failed");
			}
		}
	}
	

	private String nameCleaner(String name) {
		String cleanedName = name.toLowerCase().replace("hd", "");
		cleanedName = cleanedName.replace("hdrip", "");
		cleanedName = cleanedName.replace("brrip", "");
		cleanedName = cleanedName.replace("ts", "");
		cleanedName = cleanedName.substring(cleanedName.lastIndexOf('\\')+1, cleanedName.length());
		return cleanedName;
	}
	
	
}
