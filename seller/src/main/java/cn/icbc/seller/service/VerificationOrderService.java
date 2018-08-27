package cn.icbc.seller.service;

import cn.icbc.seller.repositories.VerificationOrderRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
