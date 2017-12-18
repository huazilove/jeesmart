package cn.jeesmart.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * 
 * @author Joe
 */
@SuppressWarnings("static-access")
public class ValidateUtils {
	/**
	 * 是否是整数
	 * 
	 * @param value
	 */
	public static boolean isInteger(String value) {
		String regEx = "^-?[1-9]\\d*$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是正整数
	 * 
	 * @param value
	 */
	public static boolean isUnsignedInteger(String value) {
		String regEx = "^[1-9]\\d*$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是负整数
	 * 
	 * @param value
	 */
	public static boolean isIntege2(String value) {
		String regEx = "^-[1-9]\\d*$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是数字
	 * 
	 * @param value
	 */
	public static boolean isNum(String value) {
		String regEx = "^([+-]?)\\d*\\.?\\d+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是正数（正整数 + 0）
	 * 
	 * @param value
	 */
	public static boolean isNum1(String value) {
		String regEx = "^[1-9]\\d*|0$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是负数（负整数 + 0）
	 * 
	 * @param value
	 */
	public static boolean isNum2(String value) {
		String regEx = "^-[1-9]\\d*|0$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是浮点数
	 * 
	 * @param value
	 */
	public static boolean isDecmal(String value) {
		String regEx = "^([+-]?)\\d*\\.\\d+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是正浮点数
	 * 
	 * @param value
	 */
	public static boolean isDecmal1(String value) {
		String regEx = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 判断是否金额格式
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isMoney(String value) {
		String regEx = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,8})?$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是负浮点数
	 * 
	 * @param value
	 */
	public static boolean isDecmal2(String value) {
		String regEx = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是浮点数
	 * 
	 * @param value
	 */
	public static boolean isDecmal3(String value) {
		String regEx = "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是非负浮点数（正浮点数 + 0）
	 * 
	 * @param value
	 */
	public static boolean isDecmal4(String value) {
		String regEx = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是非正浮点数（负浮点数 + 0）
	 * 
	 * @param value
	 */
	public static boolean isDecmal5(String value) {
		String regEx = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是邮件
	 * 
	 * @param value
	 */
	public static boolean isEmail(String value) {
		String regEx = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是颜色
	 * 
	 * @param value
	 */
	public static boolean isColor(String value) {
		String regEx = "^[a-fA-F0-9]{6}$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是url
	 * 
	 * @param value
	 */
	public static boolean isUrl(String value) {
		String regEx = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是中文
	 * 
	 * @param value
	 */
	public static boolean isChinese(String value) {
		String regEx = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是ACSII字符
	 * 
	 * @param value
	 */
	public static boolean isAscii(String value) {
		String regEx = "^[\\x00-\\xFF]+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是邮编
	 * 
	 * @param value
	 */
	public static boolean isZipcode(String value) {
		String regEx = "^\\d{6}$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是手机
	 * 
	 * @param value
	 */
	public static boolean isMobile(String value) {
		String regEx = "^1[345678][0-9]{9}$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是ip地址
	 * 
	 * @param value
	 */
	public static boolean isIp(String value) {
		String regEx = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是非空
	 * 
	 * @param value
	 */
	public static boolean isNotempty(String value) {
		String regEx = "^\\S+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是图片
	 * 
	 * @param value
	 */
	public static boolean isPicture(String value) {
		String regEx = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是压缩文件
	 * 
	 * @param value
	 */
	public static boolean isRar(String value) {
		String regEx = "(.*)\\.(rar|zip|7zip|tgz)$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是日期
	 * 
	 * @param value
	 */
	public static boolean isDate(String value) {
		String regEx = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是QQ号码
	 * 
	 * @param value
	 */
	public static boolean isQq(String value) {
		String regEx = "^[1-9]*[1-9][0-9]*$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是电话号码的函数(包括验证国内区号,国际区号,分机号)
	 * 
	 * @param value
	 */
	public static boolean isTel(String value) {
		String regEx = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 用来用户注册。只能包含英文字母、数字、"-"和"_"
	 * 
	 * @param value
	 */
	public static boolean isUsername(String value) {
		String regEx = "^[a-zA-Z][\u4e00-\u9fa5A-Za-z0-9-_]+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是字母
	 * 
	 * @param value
	 */
	public static boolean isLetter(String value) {
		String regEx = "^[A-Za-z]+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是大写字母
	 * 
	 * @param value
	 */
	public static boolean isLetteru(String value) {
		String regEx = "^[A-Z]+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是大写字母
	 * 
	 * @param value
	 */
	public static boolean isLetterl(String value) {
		String regEx = "^[a-z]+$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是价格
	 * 
	 * @param value
	 */
	public static boolean isPrice(String value) {
		String regEx = "^([1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|\\.[0-9]{1,2})$";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否是身份证号
	 * 
	 * @param value
	 */
	public static boolean isIdNo(String value) {
		String regEx = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
		String regEx1 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
		return Pattern.compile(regEx).matcher(value).matches() || Pattern.compile(regEx1).matcher(value).matches();
	}

	/**
	 * 是否是中国人姓名
	 * 
	 * @param value
	 */
	public static boolean isPersonName(String value) {
		String regEx = "[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*";
		return Pattern.compile(regEx).matcher(value).matches();
	}

	/**
	 * 是否符合密码格式
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isPassword(String value) {
		String regEx = "^(?!\\W*[0-9]+\\W*$)(?!\\W*[a-zA-Z]+\\W*$)[^\\s'\"]{6,16}$";
		return Pattern.compile(regEx).matcher(value).matches();
	}
}
