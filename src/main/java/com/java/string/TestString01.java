package com.java.string;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 用来写 String 的测试类
 * 字符串是常量；它们的值在创建之后不能更改。字符串缓冲区支持可变的字符串。因为 String 对象是不可变的，所以可以共享。
 *
 * @author wangning
 * @create 2020-12-09 10:57
 */
public class TestString01 {
    /**
     * 测试字符与字节转换，针对转码过程中的中文乱码
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        //获取系统中默认的编码
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
        String s1 = "你好！。？";
        System.out.println("s1 = " + s1);
        byte[] bytes = s1.getBytes("GBK");
        System.out.println("bytes = " + bytes.length);
        String newStr = new String(bytes, "UTF-8");
        System.out.println("newStr = " + newStr);
    }

    /**
     * String 中的常用方法
     * 常用方法
     * length()
     * toString()
     * equals()
     * substring()
     * contains()
     * getBytes()
     * indexOf()
     * matches()
     * replace()
     * trim()
     * toCharArray()
     * charAt()
     * compareTo
     * toUpperCase()
     * toLowerCase()
     */
    @Test
    public void test02() throws Exception {
        String s = "abcdefghijklmnopqrstuvwxyz";
        //1.根据下标获取字符
        System.out.println("s.charAt() = " + s.charAt(2));

        //2.返回指定索引处的字符（Unicode代码点）
        System.out.println("s.codePointAt(2) = " + s.codePointAt(2));

        //3.返回指定索引之前的字符（Unicode代码点）
        System.out.println("s.codePointBefore(2) = " + s.codePointBefore(2));

        //4.返回此 String 指定文本范围内的Unicode代码点数
        System.out.println("s.codePointCount(0,2) = " + s.codePointCount(0, 2));

        //5.按字典顺序比较两个字符串。 比较是基于字符串中每个字符的Unicode值。
        //由该String对象表示的字符序列按字典顺序与由参数字符串表示的字符序列进行比较。
        //如果String对象按字典顺序排列在参数字符串之前，结果为负整数。 结果是一个正整数，如果String对象按字典顺序跟随参数字符串。
        //如果字符串相等，结果为零; compareTo返回0 ，当equals(Object)方法将返回true 。
        //由源码可以看出：
        //首先取出两个字符串的长度，比较较小的长度内，两者是否相等。
        //若不相等，则直接返回该位置字符的ASCII码相减后的值。
        //若各位置都相等，则将两个字符串长度的差值返回。
        //    public int compareTo(String anotherString) {
        //        int len1 = value.length;
        //        int len2 = anotherString.value.length;
        //        int lim = Math.min(len1, len2);
        //        char v1[] = value;
        //        char v2[] = anotherString.value;
        //
        //        int k = 0;
        //        while (k < lim) {
        //            char c1 = v1[k];
        //            char c2 = v2[k];
        //            if (c1 != c2) {
        //                return c1 - c2;
        //            }
        //            k++;
        //        }
        //        return len1 - len2;
        //    }
        String s1 = "baaaa";
        String s2 = "aa";
        System.out.println("s.compareTo(\"\") = " + s1.compareTo(s2));

        //6.同compare，但是忽略大小写
        s1 = "aaa";
        s2 = "AAA";
        System.out.println("s1.compareToIgnoreCase(s2) = " + s1.compareToIgnoreCase(s2));

        //7.将指定的字符串连接到该字符串的末尾
        System.out.println("s1.concat(\"bcd\") = " + s1.concat("sss"));

        //8.当且仅当此字符串包含指定的char值序列时才返回true
        System.out.println("s1.contains(\"aa\") = " + s1.contains("ba"));

        //9.copyValueOf(char[] data)相当于valueOf(char[] data)
        char[] data = {'a','b'};
        System.out.println("s1.copyValueOf(data) = " + String.copyValueOf(data));
        System.out.println("s1.valueOf(data) = " + String.valueOf(data));

        //10.返回char数组参数的特定子阵列的字符串char形式。
        //offset参数是子阵列的第一个字符的索引。 count参数指定子数组的长度。 副本的内容被复制; 字符数组的后续修改不会影响返回的字符串
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        System.out.println("String.copyValueOf(chars, 3,3) = " + String.copyValueOf(chars, 3, 3));
        System.out.println("String.valueOf(chars, 3, 3) = " + String.valueOf(chars, 3, 3));

        //11.测试字符串否是以指定的字符结尾或者开头
        s1 = "abcd";
        System.out.println("s1.endsWith(\"d\") = " + s1.endsWith("d"));
        System.out.println("s1.startsWith(\"a\") = " + s1.startsWith("a"));

        //12.equals,比较字符串的值是否相等
        s1 = "abc";
        s2 = "abc";
        System.out.println("s1.equals(s2) = " + s1.equals(s2));
        s1 = "ABC";
        System.out.println("s1.equalsIgnoreCase(s2) = " + s1.equalsIgnoreCase(s2));

        //13.format
        //
        String str = String.format("Hi, %s", "林计钦");
        System.out.println("String.format = " + str);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
        System.out.printf("3>7的结果是：%b %n", 3>7);
        Date date = new Date();
        System.out.printf("全部日期和时间信息：%tc%n", date);//格式化输出日期或时间
        System.out.printf("年-月-日格式：%tF%n", date);
        System.out.printf("月/日/年格式：%tD%n", date);
        System.out.printf("HH:MM:SS PM格式(12时制)：%tr%n", date);
        System.out.printf("HH:MM:SS格式(24时制)：%tT%n", date);
        System.out.printf("HH:MM格式(24时制)：%tR%n", date);

        //14.getBytes()
        str = "asdfg";
        System.out.println("str.getBytes() = " + str.getBytes());//使用平台的默认字符集将此 String编码为字节序列，将结果存储到新的字节数组中。
        System.out.println("str.getBytes(\"UTF-8\") = " + str.getBytes("UTF8"));
//        str.getBytes(Charset char);

        //15.getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
        //将此字符串中的字符复制到目标字符数组中。
        char[] c = new char[str.length()];
        str.getChars(0, str.length()-1, c, 0);
        System.out.println("c = " + c.toString());

        //16.hashCode()
        System.out.println("str.hashCode() = " + str.hashCode());

        //17.indexOf(),返回的是查找字符串的下标,此方法用法与lastIndexOf相同
        str = "abcdebcaabc";
        System.out.println("str.indexOf('a') = " + str.indexOf('a'));
        System.out.println("str.indexOf(\"ab\") = " + str.indexOf("ab"));
        System.out.println("str.indexOf(97) = " + str.indexOf(97));//括号内为一个字符的Unicode代码点
        System.out.println("str.indexOf(98,3) = " + str.indexOf(98, 3));////从索引为3的位置开始查询Unicode为98的，返回第一个字符串的下标
        System.out.println("str.indexOf(\"bc\",3) = " + str.indexOf("bc", 3));//从索引为3的位置开始查询字符串"bc"，返回第一个字符串的下标

        //18.isEmpty()返回 true如果，且仅当 length()为 0 ,当字符串为null是不能这样用，会抛出异常
        str = "";
        System.out.println("str.isEmpty() = " + str.isEmpty());

        //19.intern() 返回字符串对象的规范表示。
        //返回字符串对象的规范表示。
        //最初为空的字符串池由String类String 。
        //当调用intern方法时，如果池已经包含与equals(Object)方法确定的相当于此String对象的字符串，则返回来自池的字符串。 否则，此String对象将添加到池中，并返回对此String对象的引用。
        //由此可见，对于任何两个字符串s和t ， s.intern() == t.intern()是true当且仅当s.equals(t)是true 。
        //所有文字字符串和字符串值常量表达式都被实体化。 字符串文字在The Java™ Language Specification的 3.10.5节中定义。
        //结果
        //一个字符串与该字符串具有相同的内容，但保证来自一个唯一的字符串池。
        str = "ab c";
        System.out.println("str.intern() = " + str.intern());

        //20.join
        System.out.println("String.join(\" \",\"I\",\"am\",\"iron\", \"man\") = " + String.join("-", "I", "am", "iron", "man"));

        //21.lastIndexOf() 返回值为int，字符的下标
        System.out.println("str.lastIndexOf() = " + str.lastIndexOf('c'));

        //22.length()方法，注意区分数组中的length，数组中不带括号
        System.out.println("str.length() = " + str.length());

        //23.matches()
        String reg = "[0-9]+";
        str = "123";
        System.out.println("str.matches(reg) = " + str.matches(reg));

        //23.replace()
        str = "abcdcc";
        System.out.println("str.replace(\"c\",\"\") = " + str.replace("c", ""));

        //24.split()
        System.out.println("str.split('c') = " + str.split("c"));

        //25.toCharArray()
        System.out.println("str.toCharArray() = " + str.toCharArray().length);

        //26.toLowerCase() toUpperCase() 大小写转换

        //27.trim(),此方法只能去除两端的空格
        str = " ab cd ";
        System.out.println("str.trim() = " + str.trim());

        //28.valueOf()，能够把其他类型的转化为字符串，返回值为字符串的形式
        boolean b = true;
        System.out.println("String.valueOf(b) = " + String.valueOf(b));
        int i = 123456;
        System.out.println("String.valueOf(i) = " + String.valueOf(i));

    }

    /**
     * 此方法用来测试 StringBuffer 与 StringBuilder
     * 两个都是可变字符串，但是 StringBuffer 线程安全
     * 常用的方法基本上同 string 相同
     * 自己的方法
     *  append()
     *  reverse()
     *  delete()
     *  insert()
     *  setCharAt(int index, char ch) 指定索引处的字符设置为 ch 。
     *  setLength(int newLength) 设置字符序列的长度。
     */
    @Test
    public void test03() {
        //append()
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("abc");
        stringBuffer.append("123");
        System.out.println("stringBuffer = " + stringBuffer);
        System.out.println("stringBuffer.reverse() = " + stringBuffer.reverse());
        stringBuffer.insert(0,"aa");
        stringBuffer.insert(1,"bb");
        System.out.println("stringBuffer = " + stringBuffer);
        stringBuffer.delete(0,2);
        System.out.println("stringBuffer = " + stringBuffer);
        stringBuffer.deleteCharAt(1);
        System.out.println("stringBuffer = " + stringBuffer);
    }

    @Test
    public void test04() {
        String s = "abcd";
        //等同于下面
        char[] chars = {'a','b','c','d'};
        String s1 = new String(chars);
        System.out.println("s = " + s);
        System.out.println("s1 = " + s1);
        System.out.println(s1.equals(s1));
        System.out.println(s1==s1);
        String s2 = new String("abcd");
        System.out.println(s1 == s2);

    }
}
