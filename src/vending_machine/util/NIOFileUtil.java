package vending_machine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import vending_machine.Product;
import vending_machine.constants.AppendType;

/**
 * Java 1.8 이상버전의 파일 유틸리티
 */
public class NIOFileUtil {
	
	public static void writeFile(String parent, String filename, String description, AppendType appendType) {
		
		File file = new File(parent, filename);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if(appendType == AppendType.OVER_WRITE) {
		int index = 2; 
		while (file.exists()) {
			file = new File(file.getParent(),filename+" (" + (index++) + ").txt");
		}
	}
		
		// 파일에 쓸 내용
		List<String> fileDesc = new ArrayList<>();
		fileDesc.add(description);
		
		// 파일을 쓴다.
		try{
					
			if( appendType == AppendType.APPEND) {
				Files.write(file.toPath(), fileDesc, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
							
			} else {
				Files.write(file.toPath(), fileDesc, Charset.forName("UTF-8"));			
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(file.getAbsolutePath());
	}
	
	public static File findFile(String fileName, File from) {
		if ( from == null) {
			from = new File("C:\\");
		}
		
		if(from.exists() && from.isDirectory()) {
			File[] itemInDir = from.listFiles();
			
//			System.out.println(from.getAbsolutePath());
			
			if (itemInDir != null) {
			for (File item : itemInDir) {
				if (item.isDirectory()) {
					File result = findFile(fileName, item);
					if (result != null) {
						return result;
					}
				} else if (item.getName().equals(fileName)) {
					System.out.println(item.getAbsolutePath());
					return item;
				}
			}
		} else if (from.getName().equals(fileName)) {
			System.out.println(from.getAbsolutePath());
			return from;
			}
		} 
		return null;
	
		
	}
	
	
	public static List<Product> readCSVFile(String filename) {
		
		File file = NIOFileUtil.findFile(filename, null);
		
		if(file == null) {
			return new ArrayList<>();
		}
		
		if (file.exists() && file.isFile()) {
			List<String> fileLine = new ArrayList<>();
			
			try {
				fileLine.addAll(Files.readAllLines(file.toPath(), Charset.forName("UTF-8")));
			}
			catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
			
			List<Product> csvList = new ArrayList<>();
			Product product = null;
			String[] splittedCsvLine;
			
			for (String line : fileLine) {
				
				product = new Product();
				
				splittedCsvLine = line.trim().split(","); 
				product.setName(splittedCsvLine[0].trim());
				product.setPrice(Integer.parseInt(splittedCsvLine[1].trim()) );
				product.setQuantity(Integer.parseInt(splittedCsvLine[2].trim()) );
				
				csvList.add(product);
			}
			
			return csvList;
		}
		
		return new ArrayList<>();
	}

}
