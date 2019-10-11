/**
* @Title: NIOUtil.java
* @Package com.bawei.common.utils
* @Description: (描述该文件做什么)
* @author Alex Lu
* @date 2019年9月4日 上午8:41:22
* @version V1.0
*/
package com.dongyu.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NIOUtil
 * @Description: (描述这个类的作用)
 * @author Alex Lu
 * @date 2019年9月4日 上午8:41:22
 *
 */
public class NIOUtil {
	
	public static String readFile(String filePath,String encoding){
		//用FileInputStream获取文件
		FileInputStream fis = null;
		String strs = "";
		long end = 0l;
		long start = System.currentTimeMillis();
		System.out.println("运行开始时间："+start);
		try {
			fis = new FileInputStream(new File(filePath));
			//获取通道
			FileChannel channel = fis.getChannel();
			//设置容量为1000字节（自己定义）
			int capacity =1000;
			ByteBuffer bb = ByteBuffer.allocate(capacity);
			System.out.println("限制是："+bb.limit()+"；容量为："+bb.capacity()+";位置是："+bb.position());
			int length=-1;
			while ((length=channel.read(bb))!=-1) {
				bb.clear();
				byte[] bytes = bb.array();
				System.out.println("*************start reading*************");
				strs = new String(bytes,0,length, encoding);
				System.out.println("文件内容："+strs);
				end = System.currentTimeMillis();
				System.out.println("结束开始时间："+end);
				System.out.println("+++++ending++++++");
				System.out.println("花费时间："+(end-start));
			}
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strs;
		
	}
	public static void main(String[] args) {
		readFile("D:\\systems\\eclipse\\hs_err_pid7552.log","utf-8");
	}
}
