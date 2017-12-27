package cn.jeesmart.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author wjh
 */
public class StringHelper extends StringUtils {

    /**
     * 字符连接符
     */
    private static final char SEPARATOR = '_';
    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 转换为字节数组
     *
     * @param str 字符串
     * @return byte[] byte [ ]
     */
    public static byte[] getBytes(String str) {
        if (str == null) {
            throw new NullPointerException("str cannot be null");
        }
        return str.getBytes(DEFAULT_CHARSET);
    }

    /**
     * 转换为字节数组
     *
     * @param bytes 字节数组
     * @return String string
     */
    public static String toString(byte[] bytes) {
        return new String(bytes, DEFAULT_CHARSET);
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true boolean
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     *
     * @param html the html
     * @return the string
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        String ls = s.toLowerCase();

        StringBuilder sb = new StringBuilder(ls.length());
        boolean upperCase = false;
        for (int i = 0; i < ls.length(); i++) {
            char c = ls.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        String cs = toCamelCase(s);
        return cs.substring(0, 1).toUpperCase() + cs.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * Trim to default string.
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return String string
     */
    public static String trimToDefault(final String str, String defaultValue) {
        final String ts = trim(str);
        return isEmpty(ts) ? defaultValue : ts;
    }



    /**
     * 验证可能为空格或者""及null的字符串
     *
     * <pre>
     *   StringUtils.isBlank(null)      = true
     *   StringUtils.isBlank(&quot;&quot;)        = true
     *   StringUtils.isBlank(&quot; &quot;)       = true
     *   StringUtils.isBlank(&quot;bob&quot;)     = false
     *   StringUtils.isBlank(&quot;  bob  &quot;) = false
     * </pre>
     *
     * @param cs
     *            可能为空格或者""及null的字符序列
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(cs.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Description:验证字符串数组是否为空
     *
     * @author 唐海洋
     * @Version 1.0 2016-7-21下午5:34:41
     * @param css
     * @return
     */
    public static boolean isBlank(final CharSequence ... css) {
        if (css == null) {
            return true;
        }
        for (CharSequence cs : css) {
            if(isNotBlank(cs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证可能为""或者null的字符串
     *
     * @param cs
     *            可能为空的字符串
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 把数组内的对象通过分隔符连接为字符串
     *
     * @param array
     *            对象数组
     * @param separator
     *            分隔符
     * @return
     */
    public static String join(final Object[] array, final String separator) {
        return join(Arrays.asList(array), separator);
    }

    /**
     * 连接迭代器内对象为字符串
     *
     * @param it
     *            实现迭代接口的集合
     * @param separator
     *            分隔符
     * @return
     */
    public static String join(final Iterable<?> it, final String separator) {
        Iterator<?> iterator = null;
        if ((iterator = it.iterator()) == null || !iterator.hasNext()) {
            return null;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return String.valueOf(first);
        }

        StringBuilder buf = new StringBuilder().append(first);
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(separator).append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * 截取指定长度字符串，超长部分截取后加"..."
     *
     * @param str
     *            源字符串
     * @param length
     *            截取长度
     * @param dot
     *            是否为未显示内容添加"..."
     * @return
     */
    public static String substring(String str, final int length, final boolean dot) {
        return substring(str, 0, length, dot);
    }

    /**
     * 截取指定长度字符串，超长部分截取后加"..."
     *
     * @param str
     *            源字符串
     * @param beginIndex
     *            开始下标(包含本身)
     * @param endIndex
     *            结束下标(不包含本身)
     * @param dot
     *            是否为未显示内容添加"..."
     * @return
     */
    public static String substring(String str, int beginIndex, int endIndex, final boolean dot) {
        if (str != null) {
            if (str.length() >= (endIndex + 1)) {
                str = str.substring(beginIndex, endIndex);
                return dot ? str + "..." : str;
            }
        }
        return str;
    }

    /**
     * 以分隔标记分隔字符串为字符串数组
     *
     * @param str
     *            源字符串
     * @param separator
     *            分隔符,字符串或正则表达式对象，它标识了分隔字符串时使用的是一个还是多个字符。
     * @return
     */
    public static String[] split(final String str, final String separator) {
        return split(str, separator, false);
    }

    /**
     * 以分,隔标记分,隔字符串,为字符串数组
     *
     * @param str
     *            源字符串
     * @param separator
     *            分隔符
     * @param bool
     *            是否把分隔符添加到字符串尾部返回
     * @return
     */
    public static String[] split(String str, String separator, boolean bool) {
        String[] strs = null;
        if (str != null) {
            strs = str.split(separator);
            if (bool) {
                for (int i = 0, length = strs.length; i < length; i++) {
                    strs[i] += separator;
                }
            }
        }
        return strs;
    }

    /**
     * 将字符串以相同步长分隔生成List返回
     *
     * <pre>
     * 		StringUtil.split("我的世界因为有你才会美",3);   --> "[我的世, 界因为, 有你才, 会美]"
     * 		StringUtil.split("我的世界因为有你才会美",2);   --> "[我的, 世界, 因为, 有你, 才会, 美]"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param step
     *            拆分步长
     * @return
     */
    public static List<String> split(final String str, int step) {
        if (str != null && step > 0) {
            List<String> list;
            if (step > str.length()) {
                list = new ArrayList<String>(1);
                list.add(str);
            }
            else {
                list = new ArrayList<String>();

                int i = 0;
                do {
                    list.add(str.substring(i, i += step));
                }
                while ((i + step) < str.length());

                if (i < str.length()) {
                    list.add(str.substring(i, str.length()));
                }
            }
            return list;
        }
        return null;
    }
}
