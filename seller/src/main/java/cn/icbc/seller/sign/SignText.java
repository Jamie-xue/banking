package cn.icbc.seller.sign;


import cn.icbc.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @Auther: asus
 * @Date: 2018/8/27 17:41
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {

    default String toText(){
        return JsonUtil.toJson(this);
    }
}
