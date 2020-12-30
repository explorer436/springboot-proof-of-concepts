/*
 * Copyright (c) 2016, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */

package io.redis.jedis.jedisdemo.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Various utility methods for working with the file system.
 * 
 * @author Jonathan Lemon
 * @author Jim Crume (adapted for PI)
 */
public final class FileUtils {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	/**
	 * Utility classes should not have a public or default constructor.
	 */
	private FileUtils() {
	}
	
	/**
	 * Deletes all files in the directory specified by the parameter <code>directory</code> within the default 'temp'
	 * directory.
	 *
	 * @param directory the directory
	 * @pre directory != null
	 * @pre directory.length() > 0
	 * @post All files in the directory specified by the parameter <code>directory</code> within the default 'temp'
	 * 	   directory have been deleted.
	 */
	public static void clearDirectory(String directory) {
		// Assert preconditions
		// TODO Validate.notEmpty(directory, "Directory cannot be empty");

		StringBuffer filePathBuffer = new StringBuffer(getDefaultTempDir());
		filePathBuffer.append(File.separatorChar + directory);

		File dir = new File(filePathBuffer.toString());
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
		}
	}

	/**
	 * Creates the directories specified by the parameter <code>filePath</code> within the default 'temp' directory.
	 *
	 * @param directory the directory
	 * @pre filePath != null
	 * @pre filePath.length() > 0
	 * @post The directory specified by the parameter <code>filePath</code> within the default 'temp' directory has been
	 * 	   created.
	 */
	public static void createDirectory(String directory) {
		// Assert preconditions
		// TODO Validate.notEmpty(directory, "Directory cannot be empty");

		File file = new File(directory);
		if (file != null && !file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * Return the full file path for an output file.
	 *
	 * @param directoryName the directory name
	 * @return the string
	 * @post The full file path for an output file has been returned.
	 */
	public static String createFullDirectoryPath(String directoryName) {
		StringBuffer filePathBuffer = new StringBuffer(getDefaultTempDir());
		if (directoryName != null && directoryName.length() > 0) {
			filePathBuffer.append(
				File.separatorChar + directoryName + File.separatorChar);
		}
		String returnString = filePathBuffer.toString();

		return returnString;
	}

	/**
	 * Returns the absolute path of the default temporary directory.
	 * <p>
	 * The default temporary directory is denoted by the system property <code>java.io.tmpdir</code>. However, if for
	 * some reason this is not defined, a default value will be returned as follows:
	 * <ul>
	 * <li>On WIN32, the value "D:\temp" is returned.</li>
	 * <li>On UNIX, the value "/tmp" is returned.</li>
	 * </ul>
	 * </p>
	 * <p>
	 *
	 * @return the default temp dir
	 * @pre None.
	 * @post A non-null non-empty String representing the default temporary directory has been returned.
	 */
	public static String getDefaultTempDir() {
		
		// System.out.println("printing system properties");
		// System.getProperties().list(System.out);
		
		// The default temp directory to be returned.
		String tempDir = null;
		
		String osName = System.getProperty("os.name");
		if (!StringUtils.isEmpty(osName) && osName.toLowerCase().startsWith("win")) {
			tempDir = "C:\\\\Temp";
		}

		if (StringUtils.isEmpty(tempDir)) {
			// Determine if this a UNIX platform by looking at the
			// system defined file separator.
			if (File.separatorChar == '/') {
				tempDir = "/tmp";
			}
		}

		// Assert preconditions
		// TODO Validate.notEmpty(tempDir, "Temp Directory cannot be empty");

		return tempDir;
	}

	/**
	 * Creates and returns a {@link FileOutputStream} that writes to a directory and file as defined by the parameters
	 * passed in. The output stream file name / location will take the format of
	 * "{OS temp directory}/{subDirectoyName}/{fileName}". If the parameter 'subDirectoyName' is null, the format will
	 * be "{OS temp directory}/{fileName}".
	 *
	 * @param fileName the file name
	 * @param subDirectoryName the sub directory name
	 * @return the file output stream
	 * @pre fileName != null
	 * @pre fileName.length() > 0
	 * @post A {@link FileOutputStream} that writes to a directory and file as defined by the parameters passed in has
	 * 	   been returned.
	 * @note If no {@link FileOutputStream} could be created, null will be returned.
	 */
	public static FileOutputStream getFileOutputStream(
		String fileName, String subDirectoryName) {
		// Assert preconditions
		// TODO Validate.notEmpty(fileName, "File Name cannot be empty");

		// Create the full file path.
		String filePath = createFullDirectoryPath(subDirectoryName);

		// Ensure the directory is created.
		createDirectory(filePath);

		// Create the file.
		File file = new File(filePath, fileName);

		// Create the output stream.
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
		}
		catch (FileNotFoundException e) {
			LOGGER.error("log - Error creating file for ["
				+ file.getAbsolutePath()
				+ "]");
		}

		return outputStream;
	}

	/**
	 * Creates and returns a {@link FileOutputStream} that writes to a directory and file as defined by the parameters
	 * passed in. The output stream file name / location will take the format of
	 * "{OS temp directory}/{subDirectoyName}/{fileName}". If the parameter 'subDirectoyName' is null, the format will
	 * be "{OS temp directory}/{fileName}".
	 *
	 * @param fileName the file name
	 * @param subDirectoryName the sub directory name
	 * @return the prints the writer
	 * @pre fileName != null
	 * @pre fileName.length() > 0
	 * @post A {@link FileOutputStream} that writes to a directory and file as defined by the parameters passed in has
	 * 	   been returned.
	 * @note If no {@link FileOutputStream} could be created, null will be returned.
	 */
	public static PrintWriter getPrintWriter(
		String fileName, String subDirectoryName) {
		// Assert preconditions
		// TODO Validate.notEmpty(fileName, "File Name cannot be empty");

		// Create the print writer.
		PrintWriter printWriter = null;
		printWriter = new PrintWriter(
			getFileOutputStream(fileName, subDirectoryName));

		return printWriter;
	}
}
