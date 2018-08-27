import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Auther: asus
 * @Date: 2018/8/27 14:18
 */
public class BigDecimalTest {


    BigDecimal big5 = new BigDecimal("3000");
    BigDecimal big6 = new BigDecimal("1200");

    BigDecimal stepAmount =new BigDecimal("00");
    BigDecimal thresholdAmount = new BigDecimal("1000");

    public boolean isRight(BigDecimal amount,BigDecimal stepAmount,BigDecimal thresholdAmount){

        double amountDou = amount.doubleValue();
        double stepAmountDou = stepAmount.doubleValue();
        double thresholdAmountDou = thresholdAmount.doubleValue();
        double result =(amountDou-thresholdAmountDou)/stepAmountDou;

        String resultStr = String.valueOf(result);
        char[] chars = resultStr.toCharArray();

        return 0==(Integer.parseInt(chars[chars.length-1]+""));
    }

    @Test
    public void fun(){

        double big5Dou = big5.doubleValue();
        double big6Dou = big6.doubleValue();

        double stepAmountDou = stepAmount.doubleValue();

        double thresholdAmountDou = thresholdAmount.doubleValue();

        double result  = (big5Dou-thresholdAmountDou)/stepAmountDou;

        System.out.println(result);

        double result2  = (big6Dou-thresholdAmountDou)/stepAmountDou;

        System.out.println(result2);


        String str = String.valueOf(result);

        System.out.println(str);
        char[] chars = str.toCharArray();
        System.out.println(chars.length);
        System.out.println(chars[chars.length-1]);
        System.out.println(0==(Integer.parseInt(chars[chars.length-1]+"")));


    }


}
