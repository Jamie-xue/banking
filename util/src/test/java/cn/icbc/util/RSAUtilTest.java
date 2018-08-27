package cn.icbc.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Auther: asus
 * @Date: 2018/8/27 13:15
 */
public class RSAUtilTest {

    private final String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC34KfY0fn04Z+LStMIOVKg1w4M\n" +
            "8YMd37wfUgyvgTsal/HqP71MRH+9PPOqkBF4Nhx4909POZivhWYNgZYAn67uB1SD\n" +
            "HKMMD/D9IrHg+zIvOuGFNW3Z23vbcrQo/hHnNQhwnVRfP1l3FWrnTACc8Xo9EvtO\n" +
            "F1Dl8np2hKoweWTOxQIDAQAB";
    private final String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALfgp9jR+fThn4tK\n" +
            "0wg5UqDXDgzxgx3fvB9SDK+BOxqX8eo/vUxEf70886qQEXg2HHj3T085mK+FZg2B\n" +
            "lgCfru4HVIMcowwP8P0iseD7Mi864YU1bdnbe9tytCj+Eec1CHCdVF8/WXcVaudM\n" +
            "AJzxej0S+04XUOXyenaEqjB5ZM7FAgMBAAECgYEAm+EhqZdoNQbIgAcwm7osqBln\n" +
            "2eUr6Dlxe4PNKUN+3W6OMK1URsGTINP5JVD43lXREWNmop0LKVEHUM/YYDL+0W4T\n" +
            "tO4uuLF3AvRkIHbnrRqbYMi2wur3rA35yT0QO7pNwSVofQ9XknuQO6l5MEBIbcXP\n" +
            "gvCyLBFEK9Odh46RTUECQQDp441KtLhOtE+DAC8K94ebnei7WfSsmAQCbFhdwGv4\n" +
            "Vl3xYbySd1HpXS2PE/ZFw1V7XP8dz1Qsw351GKRHFtRdAkEAyULCAFLpTfz4CIQ7\n" +
            "CLbczdQKdJGib5SsxaLXS18SHMG5Op24hrpYBxiFRAfDvTrtUGT01gCmIdUsNCE4\n" +
            "+q89iQJAb1Q0Hfo1vKjRLB77RXIbZ3RbokooT7swNWMTENUVz6h0oQAPvXv0Tmte\n" +
            "0zsCP6TB3K2gdbUJcSFA4UXwdDImtQJAJ2AuQI34I0/43wGv4xaTwJlZqlDL7PSz\n" +
            "kL85S/+/tWyZZsRw07slec3sVNEIFL+wqmf2FuBtoHTqfxB462LZsQJAaLGMM2/O\n" +
            "dZybr1WfJE5lICJbJGVFIQ/0AgTb00rJBqzvkAJQFiVm4DhesshetfN1dN9zLfbB\n" +
            "DLAJJ4t46vHyjw==";


    @Test
    public void fun() {
        String say = "I LOVE YOU";
        String sign = RSAUtil.sign(say, privateKey);
        System.out.println("签名："+sign.toString());
        boolean verify = RSAUtil.verify(say, sign, publicKey);

        System.out.println("验签结果："+verify);
    }


}