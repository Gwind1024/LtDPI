package com.tuoming.utils;

import com.tuoming.common.CdrKind;
import com.tuoming.writeFile.WriteCountResult;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class RealTimeMsgCountUtil {
    private Logger logger = Logger.getLogger(RealTimeMsgCountUtil.class);
    private SimpleDateFormat sdf1 = new SimpleDateFormat("mm");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHH");
    private boolean flag00 = true;
    private boolean flag15 = true;
    private boolean flag30 = true;
    private boolean flag45 = true;
    private File fileResultCount;
//    private File fileOnOff;
//    private File fileCalling;
//    private File fileLocation;
//    private File fileUser5G;

    private WriteCountResult wc = new WriteCountResult();

    private String countDir;
    private volatile Map<Integer, UDMsgSendCount> udpMsgCountMap;
    private File msgCountDir;
    public RealTimeMsgCountUtil(String countDir,Map<Integer, UDMsgSendCount> udpMsgCountMap){
        this.countDir = countDir.endsWith(File.separator)?countDir:countDir+File.separator;
        this.udpMsgCountMap = udpMsgCountMap;
        msgCountDir = new File(countDir);
        if(!msgCountDir.exists()){
            msgCountDir.mkdirs();
        }
    }
    public void timeGrainDealFile(long timeStamp) {
        try {
            Date date = new Date(timeStamp);
            String minStr = sdf1.format(date);
            String hourStr = sdf2.format(date);
            Integer min = CommonUtils.strToInteger(minStr);
            if(min>=0 && min<15){
                if(flag00){
                    long parse = sdf2.parse(hourStr).getTime()/1000;
                    resultFileAlg(parse,hourStr+"0000");
                    flag45 = true;
                    flag00 = false;
                }
            }else if(min<30){
                if(flag15){
                    long parse = sdf2.parse(hourStr).getTime()/1000+15*60;
                    resultFileAlg(parse,hourStr+"1500");
                    flag00 = true;
                    flag15 = false;
                }

            }else if(min<45){
                if(flag30){
                    long parse = sdf2.parse(hourStr).getTime()/1000+30*60;
                    resultFileAlg(parse,hourStr+"3000");
                    flag15 = true;
                    flag30 = false;
                }
            }else{
                if(flag45){
                    long parse = sdf2.parse(hourStr).getTime()/1000+45*60;
                    resultFileAlg(parse,hourStr+"4500");
                    flag30 = true;
                    flag45 = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resultFileAlg(long time,String name) throws IOException {
        Iterator<Integer> types = udpMsgCountMap.keySet().iterator();
        while(types.hasNext()){
            Integer type = types.next();
            UDMsgSendCount udMsgSendCount = udpMsgCountMap.get(type);
            switch (type){
                case CdrKind.USER :
                    if(fileResultCount != null){
                        int sum = udMsgSendCount.userMsgSum.get();
                        udMsgSendCount.userMsgSum.set(0);
                        long sumBytes = sum *1l* CdrKind.USER_LEN;
                        wc.write(countResultStr(time,sum,sumBytes,type));
//                        fileUser = null;
                    }
                    break;
                case CdrKind.ON_OFF :
                    if(fileResultCount != null){
                        int sum = udMsgSendCount.onOffMsgSum.get();
                        udMsgSendCount.onOffMsgSum.set(0);
                        long sumBytes = sum *1l* CdrKind.ON_OFF_LEN;
                        wc.write(countResultStr(time,sum,sumBytes,type));
//                        fileOnOff = null;
                    }
                    break;
                case CdrKind.CALLING :
                    if(fileResultCount != null){
                        int sum = udMsgSendCount.callingMsgSum.get();
                        udMsgSendCount.callingMsgSum.set(0);
                        long sumBytes = sum *1l* CdrKind.CALLING_LEN;
                        wc.write(countResultStr(time,sum,sumBytes,type));
//                        fileCalling = null;
                    }
                    break;
                case CdrKind.LOCATION :
                    if(fileResultCount != null){
                        int sum = udMsgSendCount.locationMsgSum.get();
                        udMsgSendCount.locationMsgSum.set(0);
                        long sumBytes = sum *1l* CdrKind.LOCATION_LEN;
                        wc.write(countResultStr(time,sum,sumBytes,type));
//                        fileLocation = null;
                    }
                    break;
                case CdrKind.USER_5G :
                    if(fileResultCount != null){
                        int sum = udMsgSendCount.user5gMsgSum.get();
                        udMsgSendCount.user5gMsgSum.set(0);
                        long sumBytes = sum *1l* CdrKind.USER_5G_LEN;
                        wc.write(countResultStr(time,sum,sumBytes,type));
//                        fileUser5G = null;
                    }
                    break;
                default:
            }
        }
        if(fileResultCount != null){
            wc.close();
            wc.renameFile(fileResultCount);
            fileResultCount = null;
        }
        logger.info("-------------------------------------------");
        String userName = wc.getFileName(name);
        fileResultCount = new File(countDir+userName);
        fileResultCount.createNewFile();
        wc.Init(fileResultCount);
        logger.info("创建用户面实时消息统计结果文件:"+userName);
        logger.info("-------------------------------------------");
    }

    private String countResultStr(long time,int result,long resultBytes,int flag){
        StringBuffer sb = new StringBuffer();
        sb.append(time).append("|")
                .append(130).append("|")
                .append(result).append("|")
                .append(resultBytes).append("|")
                .append(flag);
        return sb.toString();
    }
}
