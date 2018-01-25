package com.sunny.renthouse.module.file;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IOUtils {

	private static Logger log = LogManager.getLogger(IOUtils.class);
	
	public static void copy(InputStream input, OutputStream output) throws IOException {
		copy(input, output, false);
	}
	
	public static void copy(InputStream input, OutputStream output, boolean isClose) throws IOException {
		int len = 0;
		byte[] buffer = new byte[8192];
		try {
			while ((len = input.read(buffer, 0, 8192)) != -1) {
				output.write(buffer, 0, len);
			}
			output.flush();
		} finally {
			if (isClose) {
				close(input);
				close(output);
			}
		}
	}
	
	public static void close(Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException exp) {
			log.error(exp.getMessage());
		}
	}
}
