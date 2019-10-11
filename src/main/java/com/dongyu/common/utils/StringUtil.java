/**
 * @Title: StringUtil.java
 * @Package com.bawei.common.utils
 * @Description: (描述该文件做什么)
 * @author Alex Lu
 * @date 2019年8月7日 下午5:37:23
 * @version V1.0
 */
package com.dongyu.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtil
 * @Description: 字符串工具类
 * @author Alex Lu
 * @date 2019年8月7日 下午5:37:23
 *
 */
public class StringUtil {

	public static final String PLACE_REGEX = "(\\d{7,8})";

	public static final String NUMBER_REGEX = "^[0-9]*$";

	public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

	public static final String MOBILE_REGEX = "^1(3|4|5|7|8)\\d{9}$";

	// 判断多个空格的正则表达式
	public static final String BLANK_REGEX = "\\s*";

	private StringUtil() {

	}

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 验证字符串是否为空
	 * @param content
	 *            被验证的字符串
	 * @return
	 * @return boolean 符合为true，不符合为false
	 */
	public static boolean isEmpty(String content) {
		boolean flag = false;

		// 判断字符串是否为null和""
		if (content == null || "".equals(content)) {
			return flag;
		}
		if (content.length() <= 0) {
			return flag;
		}
		// 根据正则表达式进行匹配
		Pattern pattern = Pattern.compile(BLANK_REGEX);
		Matcher matcher = pattern.matcher(content);
		// 首先判断字符串是否为空格（1-n个空格）
		if (matcher.matches()) {
			return flag;
		}
		flag = true;
		return flag;
	}

	/**
	 * 
	 * @Title: validString
	 * @Description: 验证字符串是否为空
	 * @param str
	 * @return
	 * @return Boolean
	 */
	public static boolean validString(String str) {
		return str != null && str.length() > 0;

	}

	/**
	 * 
	 * @Title: validPhoneNum
	 * @Description: 验证手机号
	 * 
	 *               运营商号段如下：
	 *               中国联通号码：130、131、132、145（无线上网卡）、155、156、185（iPhone5上市后开放
	 *               ）、186、176（4G号段）、 175（2015年9月10日正式启用，暂只对北京、上海和广东投放办理）
	 *               中国移动号码：
	 *               134、135、136、137、138、139、147（无线上网卡）、150、151、152、157、158
	 *               、159、182、183、187、188、178
	 *               中国电信号码：133、153、180、181、189、177、173、149 虚拟运营商：170、1718、1719
	 *               手机号前3位的数字包括： 1 :1 2 :3,4,5,7,8 3 :0,1,2,3,4,5,6,7,8,9
	 * 
	 * @param phoneNum
	 * @return
	 * @return Boolean
	 */
	public static boolean isMobile(String phoneNum) {
		Boolean flag = false;
		String reg = MOBILE_REGEX;
		if (!isEmpty(phoneNum)) {
			return flag;
		}
		if (phoneNum.length() == 11) {
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(phoneNum);
			flag = matcher.matches();
		}
		return flag;

	}

	/**
	 * 
	 * @Title: isEmail
	 * @Description: 验证邮箱
	 * @param email
	 * @return
	 * @return Boolean
	 */
	public static boolean isEmail(String email) {
		Boolean flag = false;
		if (!isEmpty(email)) {
			return flag;
		}
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		flag = matcher.matches();
		return flag;

	}

	/**
	 * 
	 * @Title: isCharacter
	 * @Description: 判断是否全部为字母
	 * @param target
	 * @return
	 * @return boolean
	 */
	public static boolean isCharacter(String target) {
		String reg = "^[a-zA-Z]+$";
		if (target != null && !"".equals(target)) {
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(target);
			return matcher.matches();
		}
		return false;

	}

	/**
	 * 
	 * @Title: getRandomString
	 * @Description: 获取n位随机英文字符串:小写字母的ASC码97-122，大写字母的ASCII码65-90
	 * @param n
	 * @return
	 * @return String
	 */
	public static String getRandomString(int n) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			int a = (int) (Math.random() * 57 + 65);
			// 90-97是非英文字母，所以需要去掉
			if (a > 90 && a < 97) {
				int b = a - 90;
				a = a - b;
			}
			char c = (char) a;
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: getRandomNumberAndCharacter
	 * @Description: 获取n位随机英文和数字字符串:小写字母的ASCII码97-122，大写字母的ASCII码65-90
	 *               数字ASCII码为48-57
	 * @param n
	 * @return
	 * @return String
	 */
	public static String getRandomNumberAndCharacter(int n) {
		StringBuffer sb = new StringBuffer();
		int b = 0;
		for (int i = 0; i < n; i++) {
			int a = (int) (Math.random() * 74 + 48);
			// 90-97是非英文字母，所以需要去掉
			if (a > 90 && a < 97) {
				b = a - 90;
				a = a - b;
			}
			// 57-65是非数字，所以需要过滤
			if (a > 57 && a < 65) {
				b = a - 57;
				a = a - b;
			}
			char c = (char) a;
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: getChinese
	 * @Description: 获取n个随机中文字符串 提示：Unicode字符中文编码从十六进制数4e00到十六进制数9fa5之间。
	 * @param str
	 * @return
	 * @return Boolean
	 * @throws UnsupportedEncodingException
	 */
	public static String getChinese(int n) {
		// 先把Unicode字符中文编码转换十六进制
		int start = Integer.valueOf("4e00", 16);
		System.out.println(start);
		int end = Integer.valueOf("9fa5", 16);
		System.out.println(end);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			// 随机生成一个char，然后转成字符串
			char c = (char) (int) (Math.random() * (end - start) + start);
			sb.append(c);
		}
		return sb.toString();

	}

	/**
	 * 获取链接的后缀名
	 * 
	 * @return
	 */
	public static String parseSuffix(String url) {
		Pattern pattern = Pattern.compile("\\S*[?]\\S*");
		Matcher matcher = pattern.matcher(url);
		String[] spUrl = url.toString().split("/");
		int len = spUrl.length;
		String endUrl = spUrl[len - 1];
		if (matcher.find()) {
			String[] spEndUrl = endUrl.split("\\?");
			return spEndUrl[0].split("\\.")[1];
		}
		return endUrl.split("\\.")[1];
	}

	/**
	 * 
	 * @Title: readTxtFile
	 * @Description: 读取文本文件
	 * @param filePath
	 * @param encoding
	 * @return
	 * @throws IOException
	 * @return String
	 */
	public static String readTxtFile(String filePath, String encoding) throws IOException {
		long end = 0l;
		long start = System.currentTimeMillis();
		System.out.println("运行开始时间：" + start);
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				StringBuffer sb = new StringBuffer();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					sb.append(lineTxt + "\n");
				}
				bufferedReader.close();
				read.close();
				end = System.currentTimeMillis();
				System.out.println("结束开始时间：" + end);
				System.out.println("花费时间：" + (end - start));
				return sb.toString();
			} else {
				throw new IOException("文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("文件不存在");
		}

	}

	/**
	 * 
	 * @Title: getPlaceholderValue
	 * @Description: 字符串截取，在指定的字符串中截取需要的字符串
	 * @param src
	 *            需要操作的字符串
	 * @param regex
	 *            正则表达式
	 * @return
	 * @return String
	 */
	public static String getPlaceholderValue(String src, String regex) {
		src = src.trim();
		String id = "";
		// 验证字符串是否为空，空的就不操作
		if (!isEmpty(src)) {
			return "字符串为空";
		}
		// 根据正则表达式匹配字符串
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(src);
		// 获取需要的字符串
		if (matcher.find()) {
			id = matcher.group(0);
		}
		return id;
	}

	/**
	 * 
	 * @Title: isNumber
	 * @Description: 判断字符串是否是数字，是的话转成int
	 * @param src
	 *            需要操作的字符串
	 * @param regex
	 *            正则表达式
	 * @return
	 * @return String
	 */
	public static int isNumber(String src, String regex) {
		src = src.trim();
		int score = 0;
		String str = "";
		// 验证字符串是否为空，空的就不操作
		if (!isEmpty(src.trim())) {
			return score;
		}
		// 根据正则表达式匹配字符串
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(src);
		// 获取需要的字符串
		if (matcher.find()) {
			str = matcher.group(0);
		}
		score = Integer.parseInt(str);
		return score;
	}

	/**
	 * 
	 * @Title: hasText
	 * @Description: 验证字符串是否为空
	 * @param str
	 * @return
	 * @return Boolean
	 */
	public static boolean hasText(String str) {
		boolean flag = false;

		// 判断字符串是否为null或""
		if (str == null || "".equals(str)) {
			return flag;
		}
		if (str.length() <= 0) {
			return flag;
		}
		// 根据正则表达式进行匹配
		Pattern pattern = Pattern.compile(BLANK_REGEX);
		Matcher matcher = pattern.matcher(str);
		// 首先判断字符串是否为空格（1-n个空格）
		if (matcher.matches()) {
			return flag;
		}
		flag = true;
		return flag;
	}

	/**
	 * 
	 * @Title: checkNameEnAndNo
	 * @Description: 判断是否有数字和字母
	 * @param name
	 *            被校验的字符
	 * @return
	 * @return boolean true代表符合
	 */
	public static boolean checkNameEnAndNo(String name) {
		boolean res = false;
		char[] cTemp = name.toCharArray();
		for (int i = 0; i < name.length(); i++) {
			if (isWord(cTemp[i])) {
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * 
	 * @Title: isWord
	 * @Description: 校验某个字符是否是a-z、A-Z、_、0-9
	 * @param c
	 *            被校验的字符
	 * @return boolean true代表符合条件
	 */
	public static boolean isWord(char c) {
		String regEx = "[\\w]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher("" + c);
		return m.matches();
	}
	public static String stringToHtml(String content) {
		if (isEmpty(content)) {
			System.out.println("替换前：" + content);
			try {
				if (content.contains("\n\r")) {
					content = content.replaceAll("\\\\n\\\\r", "\n");
				}
				if (content.contains("\r\n")) {
					content = content.replaceAll("\\\\r\\\\n", "\n");
				}
				if (content.contains("\r")) {
					content = content.replaceAll("\\\\r", "<br/>");
				}
				BufferedReader e = new BufferedReader(new InputStreamReader(
						new ByteArrayInputStream(content.getBytes(Charset
								.forName("utf8"))), Charset.forName("utf8")));
				StringBuffer strbuf = new StringBuffer();
				String line;
				while ((line = e.readLine()) != null) {
					line = "<p>" + line + "</p>";
					strbuf.append(line + "\r\n");
				}
				System.out.println("替换后：" + strbuf.toString());
			} catch (IOException arg3) {
				arg3.printStackTrace();
			}
		}

		return content;
	}
	public static String toHtml(String content) {
		if (content.contains("\n\r")) {
			content = content.replaceAll("\\\\n\\\\r", "\n");
		}

		if (content.contains("\r\n")) {
			content = content.replaceAll("\\\\r\\\\n", "\n");
		}

		if (content.contains("\r")) {
			content = content.replaceAll("\\\\r", "<br/>");
		}

		content = "<p>" + content.replaceAll("\\n", "") + "</p>";
		return content;
	}

	public static void main(String[] args) {
		String str = "123123.com";
		System.out.println(isEmail(str));
	}
}
