package com.coupon.api.utils;

import java.util.Date;

public class Redeem {

    static String stringTable = "abcdefghijkmnpqrstuvwxyz23456789";
    final static String password = "dak3le2";

    //从byte转为字符表索引所需要的位数
    final static int convertByteCount = 5;

    /*public static void main( String[] args ) throws Exception
    {
        ShowTime();
        System.out.println("=======================");
        create((byte)1,10000,12,password);

        VerifyCode("c8dksqjamaba");
        VerifyCode("4a36g5npamna");
        VerifyCode("4a36g5naamna");
        VerifyCode("dafe33234g435");
        VerifyCode("ga8ehxsq6dja");
    }*/

    /**
     * 生成兑换码
     * 这里每一次生成兑换码的最大数量为int的最大值即2147483647
     * @return
     */
    public static byte[] create(byte groupId,int codeCount,int codeLength,String password) {
        //8位的数据总长度
        int fullCodeLength = codeLength * convertByteCount / 8;
        //随机码对时间和id同时做异或处理
        //类型1，id4，随机码n,校验码1
        int randCount = fullCodeLength - 6;//随机码有多少个

        //如果随机码小于0 不生成
        if(randCount <= 0 ) {
            return null;
        }
        for(int i = 0 ; i < codeCount ; i ++) {
            //这里使用i作为code的id
            //生成n位随机码
            byte[] randBytes = new byte[randCount];
            for(int j = 0 ; j  < randCount ; j ++) {
                randBytes[j] = (byte)(Math.random() * Byte.MAX_VALUE);
            }

            //存储所有数据
            ByteHapper byteHapper = ByteHapper.CreateBytes(fullCodeLength);
            byteHapper.AppendNumber(groupId).AppendNumber(i).AppendBytes(randBytes);

            //计算校验码 这里使用所有数据相加的总和与byte.max 取余
            byte verify = (byte) (byteHapper.GetSum() % Byte.MAX_VALUE);
            byteHapper.AppendNumber(verify);

            //使用随机码与时间和ID进行异或
            for(int j = 0 ; j < 5 ; j ++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ (byteHapper.bytes[5 + j % randCount]));
            }

            //使用密码与所有数据进行异或来加密数据
            byte[] passwordBytes = password.getBytes();
            for(int j = 0 ; j < byteHapper.bytes.length ; j++){
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ passwordBytes[j % passwordBytes.length]);
            }

            //这里存储最终的数据
            byte[] Bytes = new byte[codeLength];

            //按6位一组复制给最终数组
            for(int j = 0 ; j < byteHapper.bytes.length ; j ++) {
                for(int k = 0 ; k < 8 ; k ++) {
                    int sourceIndex = j*8+k;
                    int targetIndex_x = sourceIndex / convertByteCount;
                    int targetIndex_y = sourceIndex % convertByteCount;
                    byte placeval = (byte)Math.pow(2, k);
                    byte val = (byte)((byteHapper.bytes[j] & placeval) == placeval ? 1:0);
                    //复制每一个bit
                    Bytes[targetIndex_x] = (byte)(Bytes[targetIndex_x] | (val << targetIndex_y));
                }
            }

            StringBuilder result = new StringBuilder();
            //编辑最终数组生成字符串
            for(int j = 0 ; j < Bytes.length ; j ++) {
                result.append(stringTable.charAt(Bytes[j]));
            }
            System.out.println("out string : " + result.toString());
        }
        ShowTime();
        return null;
    }

    /**
     * 验证兑换码
     * @param code
     */
    public static void VerifyCode(String code ){
        byte[] Bytes = new byte[code.length()];

        //首先遍历字符串从字符表中获取相应的二进制数据
        for(int i=0;i<code.length();i++){
            byte Index = (byte) stringTable.indexOf(code.charAt(i));
            Bytes[i] = Index;
        }

        //还原数组
        int fullCodeLength = code.length() * convertByteCount / 8;
        int randCount = fullCodeLength - 6;//随机码有多少个

        byte[] fullBytes = new byte[fullCodeLength];
        for(int j = 0 ; j < fullBytes.length ; j ++) {
            for(int k = 0 ; k < 8 ; k ++) {
                int sourceIndex = j*8+k;
                int targetIndex_x = sourceIndex / convertByteCount;
                int targetIndex_y = sourceIndex % convertByteCount;

                byte placeval = (byte)Math.pow(2, targetIndex_y);
                byte val = (byte)((Bytes[targetIndex_x] & placeval) == placeval ? 1:0);

                fullBytes[j] = (byte) (fullBytes[j] | (val << k));
            }
        }

        //解密，使用密码与所有数据进行异或来加密数据
        byte[] passwordBytes = password.getBytes();
        for(int j = 0 ; j < fullBytes.length ; j++){
            fullBytes[j] = (byte) (fullBytes[j] ^ passwordBytes[j % passwordBytes.length]);
        }

        //使用随机码与时间和ID进行异或
        for(int j = 0 ; j < 5 ; j ++) {
            fullBytes[j] = (byte) (fullBytes[j] ^ (fullBytes[5 + j % randCount]));
        }

        //获取校验码 计算除校验码位以外所有位的总和
        int sum = 0;
        for(int i = 0 ;i < fullBytes.length - 1; i ++){
            sum += fullBytes[i];
        }
        byte verify = (byte) (sum % Byte.MAX_VALUE);

        //校验
        if(verify == fullBytes[fullBytes.length - 1]){
            System.out.println(code + " : verify success!");
        }else {
            System.out.println(code + " : verify failed!");
        }

    }



    public static void ShowTime(){
        Date date = new Date();
        long times = date.getTime();//时间戳
        System.out.println("time  : " + times);
    }
}

