package common.params;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import common.util.FileUtils;

public class RepositoryUtils {

	private final static String REPOSITORY_PATH_ENVIRONMENT_VARIABLE =
		"sustaining.suite.repository.path";
	private static final String REPOSITORY_SUFFIX = ".repository.xml";
	private static final String REPOSITORY_TAG = "repository";

	private static String sRepositoryDirectoryPath;
	private static Map<String, Params> sRepositories = new HashMap<String, Params>();

	/**
	 * @return full path to directory where settings repository is located
	 */
	public synchronized static String getRepositoryDirectoryPath() {
		if(sRepositoryDirectoryPath == null) {
			// check presence of environment variable with repository path
			
			sRepositoryDirectoryPath = System.getenv(REPOSITORY_PATH_ENVIRONMENT_VARIABLE);
			
			if(sRepositoryDirectoryPath == null)
				sRepositoryDirectoryPath = FileUtils.getApplicationDirectoryPath();
		}
		
		return sRepositoryDirectoryPath;
	}
	
	public static Params getRepository(String repositoryName) {
		synchronized (sRepositories) {
			Params repository = sRepositories.get(repositoryName);
			
			if(repository != null)
				return repository;
			
			repository = loadRepositoryFromFile(repositoryName);
			if(repository == null)
				repository = new ParamsImpl();
			
			return repository;
		}
	}
	
	private static Params loadRepositoryFromFile(String repositoryName) {
		File repositoryFile = new File(getRepositoryDirectoryPath(), repositoryName + REPOSITORY_SUFFIX);
		
		if(!repositoryFile.exists())
			return null;
		
		DOMParser parser = new DOMParser();
		Document document = null;
		try {
			parser.parse(repositoryFile.getAbsolutePath());
			document = parser.getDocument();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(document != null) {
			NodeList repositoryTags = document.getElementsByTagName(REPOSITORY_TAG);
			if(repositoryTags.getLength() > 0)
				return new ParamsImpl(repositoryTags.item(0));

			System.out.println(document.toString());
		}
		
		return new ParamsImpl();
	}

	public static void main(String[] args) {
		
		System.out.println(FileUtils.getApplicationDirectoryPath());
		System.out.println(getRepositoryDirectoryPath());
		System.out.print(getRepository("clusters.connections"));

    }
}
