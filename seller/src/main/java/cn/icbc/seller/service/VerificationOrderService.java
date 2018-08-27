package cn.icbc.seller.service;

import cn.icbc.entity.VerificationOrder;
import cn.icbc.seller.enums.ChanEnum;
import cn.icbc.seller.repositories.VerificationOrderRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/27 22:03
 */
@Service
public class VerificationOrderService {

    @Autowired
    private VerificationOrderRepository verificationOrderRepository;

    @Value("${verification.rootdir:/opt/verification}")
    private String rootDir;


    private static String END_LINE = System.getProperty("line.separator","\n");

    private static DateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    private static DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public File createVerificationFile(String chanId, Date day){

        File path = getPath(rootDir, chanId, day);
        if (path.exists()){
            return path;
        }else {
            try {
                path.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String dayStr = DAY_FORMAT.format(day);
        Date start=null;
        try {
             start = DAY_FORMAT.parse(dayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date end = new Date(start.getTime() + 24 * 60 * 60 * 1000);

        List<String> info = verificationOrderRepository.queryVerificationOrders(chanId, start, end);
        String content = String.join(END_LINE, info);

        FileUtil.writeAsString(path,content);

        return path;

    }

    //获取对账文件路径
    public File getPath(String rootDir, String chanId, Date day){
        String fileName = DAY_FORMAT.format(day)+"-"+chanId+".txt";
        File filePath = Paths.get(rootDir, fileName).toFile();
        return filePath;


    }


  public static VerificationOrder parseOneLine(String line){
      VerificationOrder verificationOrder = new VerificationOrder();
      String[] props = line.split("\\|");
      verificationOrder.setOrderId(props[0]);
      verificationOrder.setOuterOrderId(props[1]);
      verificationOrder.setChanId(props[2]);
      verificationOrder.setChanUserId(props[3]);
      verificationOrder.setProductId(props[4]);
      verificationOrder.setOrderType(props[5]);
      verificationOrder.setAmount(new BigDecimal(props[6]));
      try {
          verificationOrder.setCreateAt(DATETIME_FORMAT.parse(props[7]));
      } catch (ParseException e) {
          e.printStackTrace();
      }
      return verificationOrder;
  }


  public void saveChanOrders(String chanId,Date day){

      ChanEnum conf = ChanEnum.getByChanId(chanId);
      //根据配置从ftp下载对账的对账数据
      File file = getPath(conf.getRootDir(),chanId,day);
      if(!file.exists()){
          return;
      }
      String content = null;
      try {
          content = FileUtil.readAsString(file);
      } catch (IOException e) {
          e.printStackTrace();
      }
      String[] lines = content.split(END_LINE);
      List<VerificationOrder> orders = new ArrayList<>();
      for (String line : lines) {
          orders.add(parseOneLine(line));
      }
      verificationOrderRepository.save(orders);
  }

}
