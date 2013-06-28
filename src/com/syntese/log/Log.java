package com.syntese.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	static {
		System.out.println("log");
		// BasicConfigurator replaced with PropertyConfigurator.
	    PropertyConfigurator.configure("etc/log4j.properties");
	    syntese = Logger.getLogger("syntese");
	}
	public static Logger syntese;
	

}
