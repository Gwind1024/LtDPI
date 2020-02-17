package com.tuoming.writefile;

public class WriteFactory {

    /**
     * 1:sgsWrite
     *
     * @param sign
     * @param time
     * @return
     */
    public static Write getWrite(int sign, String time) {
        if (sign == WriteIndex.sgsWrite) {
            return new SgsWrite(time);
        } else if (sign == WriteIndex.csfbWrite) {
            return new CsfbWrite(time);
        } else if (sign == WriteIndex.sms4gWrite) {
            return new Sms4gWrite(time);
        } else if (sign == WriteIndex.s6aWrite) {
            return new S6aWrite(time);
        }else if(sign==WriteIndex.s1mmeWrite){
            return new S1mmeWrite(time);
        }else if(sign==WriteIndex.s1udnsWrite){
            return new S1udnsWrite(time);
        }else if(sign==WriteIndex.s10S11Write){
            return new S10S11Write(time);
        }else if(sign==WriteIndex.s11uWrite){
            return new S11uWrite(time);
        }else if(sign==WriteIndex.dnsWrite){
            return new S1udnsWrite(time);
        }else if(sign==WriteIndex.httpWrite){
            return new S1uhttpWrite(time);
        }else if(sign==WriteIndex.otherWrite){
            return new OtherWrite(time);
        }else if(sign==WriteIndex.streamingWrite){
            return new StreamingWrite(time);
        }else if(sign==WriteIndex.imWrite){
            return new S1uimWrite(time);
        }else if(sign==WriteIndex.gameWrite){
            return new S1ugameWrite(time);
        }else if(sign == WriteIndex.iucsWrite){
            return new IucsWrite(time);
        }

        return null;
    }
}
