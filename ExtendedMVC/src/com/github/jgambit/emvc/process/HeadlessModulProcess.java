package com.github.jgambit.emvc.process;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class HeadlessModulProcess {
	
	protected static final Logger _log = Logger.getLogger(HeadlessModulProcess.class.getName());
	protected static final String LOG_RESOURCE_PATH = "resource/log.txt";
	
	public HeadlessModulProcess() {
		Handler handler = null;
		try {
			handler = new FileHandler( LOG_RESOURCE_PATH );
		} catch (SecurityException | IOException e) {
			throw new IllegalStateException(e.getMessage());
		}
		handler.setFormatter(new SimpleFormatter());
		_log.setLevel(Level.INFO);
		_log.addHandler(handler);
	}

}
