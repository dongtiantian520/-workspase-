package com.dongyu.common.utils;

import java.io.File;
import java.math.BigDecimal;

/**
 * @ClassName: FileUtil
 * @Description: 文件处理工具类
 * @author Alex Lu
 * @date 2019年8月8日 下午7:34:43
 *
 */
public class FileUtil {
	private FileUtil() {
	}

	/**
	 * 
	 * @Title: getSuffix
	 * @Description: 获取文件扩展名
	 * @param filePath 文件的路径
	 * @return
	 * @return String
	 */
	public static String getSuffix(String filePath) {
		File file = new File(filePath);
		String fileName = file.getName();
		//获取最后一个"."的index
		int index = fileName.lastIndexOf(".");
		//截取字符串
		String suffix = fileName.substring(index + 1, fileName.length());
		return suffix;

	}

	/**
	 * 
	 * @Title: delFile
	 * @Description: 删除文件，如果是目录，则下面的文件和所有子目录中的文件都要删除
	 * @param file
	 * @return void
	 */
	public static void delFile(File file) {
		if (file == null) {
			return;
		}
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				// 如果是文件夹，则继续调用delFile方法，不是则删除文件
				if (f.isDirectory()) {
					delFile(f);
				} else {
					f.delete();
				}
			}
		}
		// 删除文件夹目录
		file.delete();
	}

	/**
	 * 
	 * @Title: getSystemInfo
	 * @Description: 获取操作系统用户目录 提示：全局属性user.dir和user.home
	 * @return
	 * @return File
	 */
	public static File getSystemInfo() {
		String usrDir = System.getProperty("user.dir");
		System.out.println(usrDir);
		String usrHome = System.getProperty("user.home");
		System.out.println(usrHome);
		File file = new File(usrDir);
		return file;
	}

	/**
	 * 
	 * @Title: getFileSize
	 * @Description: 返回文件以指定单位大小表示 单位：B,K,M,G,T
	 * @param file
	 * @return
	 * @return String
	 */
	public static String getFileSize(File file) {
		String type = "File ";
		String fileName = file.getName();
		long size = file.length();
		BigDecimal kiloByte = new BigDecimal(size).divide(new BigDecimal(1024));
		if (kiloByte.compareTo(new BigDecimal(1)) < 1) {
			return type + fileName + "=" + size + "B";
		}

		BigDecimal megaByte = kiloByte.divide(new BigDecimal(1024));
		if (megaByte.compareTo(new BigDecimal(1)) < 1) {
			BigDecimal result1 = kiloByte;
			return type + fileName + "=" + result1.setScale(0, BigDecimal.ROUND_HALF_DOWN).toPlainString() + "K";
		}

		BigDecimal gigaByte = megaByte.divide(new BigDecimal(1024));
		if (gigaByte.compareTo(new BigDecimal(1)) < 1) {
			BigDecimal result2 = megaByte;
			return type + fileName + "=" + result2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "M";
		}

		BigDecimal teraBytes = gigaByte.divide(new BigDecimal(1024));
		if (teraBytes.compareTo(new BigDecimal(1)) < 1) {
			BigDecimal result3 = gigaByte;
			return type + fileName + "=" + result3.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "G";
		}
		BigDecimal result4 = teraBytes;
		return type + fileName + "=" + result4.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "T";
	}

	public static void main(String[] args) {
		// System.out.println(getSuffix("C:\\Users\\mi\\Desktop\\20190806113318.jpg"));
		delFile(new File("D:\\testFile\\test.txt"));
	}
}
