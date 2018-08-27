package cn.icbc.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: asus
 * @Date: 2018/8/27 18:11
 */
@Service
public class SignService {

    private static Map<String,String> PUBLIC_KEYS = new HashMap<>();
    static {
        PUBLIC_KEYS.put("1001","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC34KfY0fn04Z+LStMIOVKg1w4M\n" +
                "8YMd37wfUgyvgTsal/HqP71MRH+9PPOqkBF4Nhx4909POZivhWYNgZYAn67uB1SD\n" +
                "HKMMD/D9IrHg+zIvOuGFNW3Z23vbcrQo/hHnNQhwnVRfP1l3FWrnTACc8Xo9EvtO\n" +
                "F1Dl8np2hKoweWTOxQIDAQAB");
    }


    public String getPublicKeyByAuthId(String authId){
        return PUBLIC_KEYS.get(authId);
    }

}
