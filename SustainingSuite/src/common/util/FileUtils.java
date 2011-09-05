package common.util;

import java.io.File;
import java.net.URI;

public class FileUtils {
	
    public static final long KiB = 1024;
	
	private static String sApplicationDirectoryPath;
	
	/**
	 * @return full path to directory where executable is located
	 */
	public synchronized static String getApplicationDirectoryPath() {
		if(sApplicationDirectoryPath == null) {
			Class<FileUtils> thisClass = FileUtils.class;
			
			URI uriToClass = URI.create(thisClass.getClassLoader().getResource(
					thisClass.getName().replace('.', '/') + ".class").getPath());
			
			File pathToDirectory = new File(uriToClass.getPath().split("!")[0]);
			
			if(!pathToDirectory.isDirectory())
				pathToDirectory = pathToDirectory.getParentFile();
			sApplicationDirectoryPath = pathToDirectory.toString();
		}
		
		return sApplicationDirectoryPath;
	}
}
