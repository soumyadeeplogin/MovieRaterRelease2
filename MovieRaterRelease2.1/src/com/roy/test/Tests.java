package com.roy.test;

import com.roy.apiHandler.APIHandler;
import com.roy.apiHandler.RESTClient;
import com.roy.fileSystemHandler.FileSystemHandler;

public class Tests {

	public static void main(String[] args) {
		FileSystemHandler rc = new FileSystemHandler("./");
		//rc.createFolder("TestFolder");
		rc.createFile("TestFolder.txt");
		rc.renameFile("TestFolder.txt", "Test.txt");
		
	/*	System.out.println(rc.getLanguage());
		System.out.println(rc.getRating());
		
		System.out.println(rc.getReleaseDate());
		System.out.println(rc.getSynopsis());
		System.out.println(rc.getTitle());*/
		
	}

}
