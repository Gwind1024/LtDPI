package com.tuoming.writefile;

import com.tuoming.common.FileDealUntil;
import com.tuoming.entity.iups.IUPSDecode;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IuPSWriteThread implements Runnable {
    private Logger logger = Logger.getLogger(IuPSWriteThread.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private volatile Queue<IUPSDecode> resultBuffer;
    private IuPSRelationWrite write = new IuPSRelationWrite();
    private String outputPath;
    private int fileSize;
    public IuPSWriteThread(Queue<IUPSDecode> resultBuffer,String outputPath,int fileSize){
        this.resultBuffer = resultBuffer;
        this.outputPath = outputPath;
        this.fileSize = fileSize;
    }
    @Override
    public void run() {
        while (true) {
            while (resultBuffer.size() > 0) {
                IUPSDecode result = resultBuffer.poll();

                if (write.getWf() != null) {
                    if (write.getWf().write(result.toString())) {
                        write.getWf().close();
                        logger.info("生成结果文件:"+outputPath + File.separator + write.getFinlName());
                        FileDealUntil.renameFile(outputPath + File.separator + write.getTmpName(), outputPath + File.separator + write.getFinlName());
                        write.setWf(null);
                        write.setFileSignCount(write.getFileSignCount() + 1);
                    }
                } else {
                    WriteFile writeFile = new WriteFile(fileSize);
                    write.setWf(writeFile);
                    long fileCreatTime = System.currentTimeMillis();
                    write.setTime(sdf.format(new Date(fileCreatTime)));
                    write.getWf().initialize(outputPath + File.separator + write.getFile());
                    if (write.getWf().write(result.toString())) {
                        write.getWf().close();
                        FileDealUntil.renameFile(outputPath + File.separator + write.getTmpName(), outputPath + File.separator + write.getFinlName());
                        write.setWf(null);
                        write.setFileSignCount(write.getFileSignCount() + 1);
                    }
                }
            }
        }
    }
}
