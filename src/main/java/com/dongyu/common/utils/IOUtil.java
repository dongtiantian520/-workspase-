package com.dongyu.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName: IOUtil
 * @Description: 流处理工具
 * @author Alex Lu
 * @date 2019年8月9日 下午3:26:19
 *
 */
public class IOUtil {
	private IOUtil() {
	}

	/**
	 * 
	 * @Title: closeStream
	 * @Description: 关闭多个io流
	 * @param closeables
	 * @return void
	 */
	public static void closeStream(Closeable... closeables) {
		try {
			for (Closeable c : closeables) {
				c.close();
			}
		} catch (IOException e) {

		}
	}

	/**
	 * 
	 * @Title: readFile
	 * @Description: 用scanner扫描文件
	 * @param filePath
	 * @return
	 * @return List<String>
	 */
	public static List<String> readFile(String filePath) {
		List<String> list = new ArrayList<String>();
		try {
			//根据文件路径进行扫描
			Scanner scanner = new Scanner(new File(filePath));
			//保存到list中
			while (scanner.hasNextLine()) {
				list.add(scanner.nextLine());

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;

	}
	/**
	 * 
	* @Title: readText
	* @Description: 读取文本文件
	* @param filePath 文件路径
	* @param charset 编码
	* @return void
	 */
	public static void readText(String filePath, String charset) {
		try {
			File file = new File(filePath);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), charset);
			BufferedReader br = new BufferedReader(isr);
			String textLine = null;
			textLine = br.readLine();
			while (textLine != null) {
				System.out.println(textLine = br.readLine());
			}
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: writeText
	* @Description: 写入文本文件
	* @param filePath 文件路径
	* @param content	需要写入的内容
	* @return void
	 */
	public static void writeText(String filePath, String content) {

		File file = new File(filePath);
		try {
			// 文本路径如果不存在需要创建一个
			file.createNewFile();
			// 写文件操作
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			// \r\n换行
			bw.write(content + "\r\n");
			// 把缓存区内容压入文件
			bw.flush();
			// 关闭文件
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	* @Title: readFileByColumn
	* @Description: 按行读取文本文件,保存到list
	* @param file
	* @return
	* @return List<String>
	 */
	public static List<String> readFileByColumn(File file) {
		List<String> arrayList = new ArrayList<>();
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
			BufferedReader bf = new BufferedReader(inputReader);
			// 按行读取字符串
			String str;
			while ((str = bf.readLine()) != null) {
				arrayList.add(str);
			}
			bf.close();
			inputReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return arrayList;

	}
	/**
	 * 
	* @Title: downloadFileFromUrl
	* @Description: 网络文件下载
	* @param fileUrl
	* @param fileName
	* @param savePath
	* @throws Exception
	* @return void
	 */
	public static void downloadFileFromUrl(String fileUrl, String fileName, String savePath) throws Exception {
		// 获取连接
		URL url = new URL(fileUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(3 * 1000);
		// 设置请求头
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
		// 获取输入流
		InputStream in = connection.getInputStream();
		//获取文件保存路径
		File saveDir = new File(savePath);
		//如果不存在，新建一个
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		//定义新的文件
		File file = new File(savePath + fileName);
		
		OutputStream out = new FileOutputStream(file);
		//写入文件
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = in.read(bytes)) != -1) {
			out.write(bytes, 0, len);
		}
		//io流的关闭，每次调用结束后必须关闭
		out.close();
		in.close();
	}

	public static void main(String[] args) {
		// String url = "http://localhost/news,ye-201809-44198239.html";
		// String reg = "(\\d{6,8})";
		// Pattern pattern = Pattern.compile(reg);
		// Matcher matcher = pattern.matcher(url);
		// if(matcher.find()){
		// System.out.println(matcher.group(0));
		// }
		try {
			List<String> list = readFileByColumn(new File("d:\\test.txt"));
			System.out.println(list.size());
			downloadFileFromUrl("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png", "baidu.png", "E:\\");
		} catch (Exception e) {

		}
	}
}
