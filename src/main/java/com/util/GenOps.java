package com.util;

import java.io.File;
import java.io.IOException;

public class GenOps {
	
public static GenOps instance;
	
	public static GenOps getInstance () {
		if(instance==null) {
			instance=new GenOps();
		}return instance;
	}
//#############################################################################################################################

	public void createFolder(String folderLocation) {
		File file = new File(folderLocation);
		file.mkdir();
	}
	
	public void createFile(String location, String fileName) throws IOException {
		File file = new File(location+File.separator+fileName);
		file.createNewFile();
	}

}
