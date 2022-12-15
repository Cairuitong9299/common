package com.bdp.idmapping.core;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/2 - 11 - 02 - 22:02
 * @Description: com.bdp.idmapping.core
 * @version: 1.0
 */
public enum IdCodeEnum {

    HID("00"),
    IMEI("01"),
    IMEI2("02"),
    UDID("03"),
    PCBA("04"),
    VAID("05"),
    OAID("06"),
    GAID("07"),
    CID("08"),
    UUID("09"),
    SSOID("10"),
    MEID("11"),
    ICCID("12"),
    FINGER("13"),
    ANDROIDID("14"),
    BUUID("15"),
    MOBILE("16"),
    UNIONID("17"),
    USERNAME("18"),
    VX_OPENID("19"),
    EMAIL("20"),
    IMEI_MD5("21"),
    GUID_MD5("22"),
    BITMAPID("23"),
    UNIQUE_SSOID("98"),
    ONEID("99"),
    ALL("100"),
    UNKONW("");

    String code;
    static Set<String> codeSet = new HashSet();

    private IdCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    //把IdCodeEnum中的数值都添加到codeSet中去
    static {
        IdCodeEnum[] var0 = values();
        int var1 = var0.length;
        for (int var2 = 0; var2 < var1; var2++) {
            IdCodeEnum idCodeEnum = var0[var2];
            codeSet.add(idCodeEnum.getCode());
        }
    }

    //判断传入的参数是否在IdCodeEnum中

    public  static  boolean isValidCode(String code){
        if (codeSet.isEmpty()){
            IdCodeEnum[] var0 = values();
            int var1 = var0.length;
            for (int var2 = 0; var2 < var1; var2++) {
                IdCodeEnum idCodeEnum = var0[var2];
                codeSet.add(idCodeEnum.getCode());
            }
        }
        return  codeSet.contains(code);
    }
}
