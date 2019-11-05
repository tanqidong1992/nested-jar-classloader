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

public class TestSpringJarLoader {

	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		String path="./test-data/tar_res-0.0.1.jar";
		Logger logger=LoggerFactory.getLogger("XX");
		NestedJarClassLoader loader=new NestedJarClassLoader(TestSpringJarLoader.class.getClassLoader(),logger);
		File file=new File(path);
		loader.addURLs(file.toURL());
		List<String> classNames=loader.listAllClass("default");
		classNames
		.stream()
		.filter(clazz->clazz.contains("controller"))
		.filter(clazz->!clazz.contains("$"))
		.forEach(name->{
			Class<?> clazz = null;
			try {
				clazz = loader.loadClass(name, true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(clazz.getName());
			/**
			for(Method method:clazz.getDeclaredMethods()) {
				System.out.println(clazz.getName()+"."+method.getName());
			}
			*/
		});
		 
		
		List<String> allClass=loader.listAllClass("default");
		allClass.stream()
		.filter(name->name.contains("hngd"))
		.forEach(System.out::println);
		System.out.println("a");

	}

}
