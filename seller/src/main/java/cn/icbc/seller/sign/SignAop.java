package cn.icbc.seller.sign;

import cn.icbc.seller.service.OrderService;
import cn.icbc.seller.service.SignService;
import cn.icbc.util.RSAUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Auther: asus
 * @Date: 2018/8/27 17:59
 */
@Aspect
@Component
public class SignAop {

    private static Logger LOG = LoggerFactory.getLogger(SignAop.class);


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

    @Autowired
    private SignService signService;

    @Before(value = "execution(* cn.icbc.seller.controller..*.*(..))&& args(authId,sign,text,..)")
    public void verify(String authId, String sign, SignText text) {
        String textStr = text.toText();
        LOG.info("=======text:{}",textStr);
        LOG.info("=======text.toText():{}",text.toText());
        String publicKey = signService.getPublicKeyByAuthId(authId);
       // LOG.info("=======publicKey:{}",publicKey);

        LOG.info("=====sign:{}",sign);
        String sign2 = RSAUtil.sign(textStr, privateKey);
        LOG.info("=====sign2:{}",sign2);


        System.out.println("&&&&&&&&&&"+RSAUtil.verify(textStr,sign,publicKey));
        Assert.isTrue(RSAUtil.verify(textStr,sign,publicKey),"验签失败");
    }
}
