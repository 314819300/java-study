package com.java.baseconversion;

/**
 * @author wangning
 * @create 2020-11-30 17:12
 */
public class BytesToHexString {

    public static void main(String[] args) {
        String s = "abcd";
        byte[] bytesArr = s.getBytes();
        BytesToHexString bytesToHexString = new BytesToHexString();
        bytesToHexString.toHexString(bytesArr);

    }
    public void toHexString(byte[] bytesArr) {
        StringBuffer stringBuffer = new StringBuffer(bytesArr.length);
        String hexStr;
        for (int i = 0; i < bytesArr.length; i++) {
            hexStr = Integer.toHexString(0xFF & bytesArr[i]);
            stringBuffer.append(hexStr);
        }
        System.out.println("stringBuffer = " + stringBuffer);
    }
}
