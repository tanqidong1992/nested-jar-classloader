package io.squark.nestedjarclassloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.squark.nestedjarclassloader.NestedJarClassLoader;

public class SpringJarLoader {

	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		String path="E:\\workspaces\\hnvmns-code-modules\\inspection-system\\target\\inspection-system-0.0.1-SNAPSHOT.jar";
		String path1="E:\\workspaces\\hnvmns-code-modules\\inspection-system\\target\\inspection-system-0.0.1-SNAPSHOT.jar.origin";
		Logger logger=LoggerFactory.getLogger("XX");
		NestedJarClassLoader loader=new NestedJarClassLoader(SpringJarLoader.class.getClassLoader(),logger);
		File file=new File(path);
		loader.addURLs(file.toURL());
		Class<?> clazz=loader.loadClass("com.hngd.web.controller.InspectObjectController",true);
		for(Method method:clazz.getDeclaredMethods()) {
			System.out.println(method.getName());
		}
		List<String> allClass=loader.listAllClass("default");
		allClass.stream()
		.filter(name->name.contains("hngd"))
		.forEach(System.out::println);
		System.out.println("a");

	}

}
