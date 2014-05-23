package CoreGame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Map {
	
	private static Logger logger = LogManager.getRootLogger();
	private Environment env;
	
	public Map(){
		env = loadEnv();
	}
	
	private Environment loadEnv(){
		// TODO Environment laden
		return null;
	}

}
