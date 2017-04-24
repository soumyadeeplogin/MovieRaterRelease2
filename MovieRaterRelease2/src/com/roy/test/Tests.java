package com.roy.test;

import com.roy.apiHandler.RESTClient;

public class Tests {

	public static void main(String[] args) {
		RESTClient rc = new RESTClient("Harry Potter");
		rc.get();
	}

}
