package com.roy.test;

import java.nio.file.Paths;

import com.roy.complications.ProcessDirectory;

public class Tests {

	public static void main(String[] args) {
// //		FileSystemHandler rc = new FileSystemHandler("./");
//		rc.createFolder("TestFolder");
//		rc.createFile("TestFolder.txt");
//		rc.deleteFile("TestFolder.txt");
//		rc.deleteFolder("TestFolder");
		
	/*	System.out.println(rc.getLanguage());
		System.out.println(rc.getRating());
		
		System.out.println(rc.getReleaseDate());
		System.out.println(rc.getSynopsis());
		System.out.println(rc.getTitle());*/
//		rc.getFolderList();
//		rc.getFileList();
		
		ProcessDirectory addRating= new ProcessDirectory(Paths.get("./MovieRatingTester/").toString());
		System.out.println(addRating.rateIt());
		System.out.println("Ran");
	}

}
