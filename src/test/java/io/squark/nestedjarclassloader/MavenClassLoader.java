package io.squark.nestedjarclassloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MavenClassLoader {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		Logger logger=LoggerFactory.getLogger("XX");
		NestedJarClassLoader loader=new NestedJarClassLoader(MavenClassLoader.class.getClassLoader(),logger);
		File file=new File("F:\\workspaces\\commons\\hnvmns-java-sample\\target\\classes");
		loader.addURLs(file.toURL());
		List<String> clazzes=loader.listAllClass("default");
		System.out.println(clazzes.size());
		clazzes.stream().forEach(c->{
			System.out.println(c);
			try {
				loader.loadClass(c);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
