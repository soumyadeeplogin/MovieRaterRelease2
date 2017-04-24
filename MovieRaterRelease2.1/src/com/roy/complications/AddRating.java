package com.roy.complications;

import java.io.File;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.roy.apiHandler.APIHandler;
import com.roy.fileSystemHandler.FileSystemHandler;




public class AddRating {

	String path;
	Logger log = Logger.getLogger(this.getClass());

	public AddRating(String path) {
		this.path = path;
		PropertyConfigurator.configure("log4j.properties");
	}

	public boolean rateIt(){
		
		FileSystemHandler fileSystemHandler = new FileSystemHandler(this.path);
		log.info("Working on directory : " + this.path);
		List<File> fileList = fileSystemHandler.getFolderList();
		ListIterator<File> iterator = fileList.listIterator();
		while (iterator.hasNext()) {
			String movieName = nameCleaner(iterator.next().toString());
			log.info("Movie name : " + movieName);
			APIHandler apiHandler = new APIHandler(movieName);
			System.out.println(apiHandler.getTitle());
			
		}
		return true; 
	}
	

	private String nameCleaner(String name) {
		String cleanedName = name.toLowerCase().replace("hd", "");
		cleanedName = cleanedName.replace("hd", "");
		cleanedName = cleanedName.replace("brrip", "");
		cleanedName = cleanedName.replace("ts", "");
		cleanedName = cleanedName.substring(cleanedName.lastIndexOf('\\')+1, cleanedName.length());
		return cleanedName;
	}
}
