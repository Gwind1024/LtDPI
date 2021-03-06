package sort;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class SortEntity implements Serializable, Comparable {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public Long time;
    public String str;


    public static void main(String[] args) throws Exception {
//        fsdffsdfsfdfdsff2019-06-17 06:18:50.814029
//        15607243440294029
//        fsdffsdfsfdfdsff2019-06-17 06:18:51.411904
//        15607239429041904
        String strTime = "2019-06-17 06:18:50.814029";
        System.out.println(sdf.parse(strTime.substring(0, 23)).getTime() * 1000 + Integer.parseInt(strTime.substring(23, 26)));
        System.out.println(sdf.parse("2019-06-17 06:18:50.814").getTime() + "029");

    }

    public SortEntity() {
    }


    @Override
    public int compareTo(Object o) {
        if ((time - ((SortEntity) o).time) >= 0) {
            return -1;
        } else {
            return 1;
        }

    }

    /**
     * time格式为yyyy-MM-dd HH:mm:ss.SSSSSS
     *
     * @param sign 每行需要添加的字段
     * @param str
     * @return
     */
    public boolean createEntity(String sign, String str, int timeIndex, int size) {
//2019-06-17 06:18:50.814029
        String[] split = str.split("\\|", -1);
        if (split.length >= size) {
            try {
                String strTime = split[timeIndex];
                //2019-06-17 06:18:50.814029
                if (strTime.trim().length() == 26) {
                    this.time = sdf.parse(strTime.substring(0, 23)).getTime() * 1000 + Integer.parseInt(strTime.substring(23, 26));
                    this.str = "".equals(sign) ? str : sign + "|" + str;
                    return true;
                } else if (strTime.trim().length() == 27) {
                    this.time = sdf.parse(strTime.substring(0, 23)).getTime() * 10000 + Integer.parseInt(strTime.substring(23, 27));
                    this.str = "".equals(sign) ? str : sign + "|" + str;
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * time格式为yyyy-MM-dd HH:mm:ss.SSSSSS
     *
     * @param str
     * @return
     */
    public boolean createEntity(String str, int timeIndex, int size) {
        String[] split = str.split("\\|", -1);
        if (split.length >= size) {
            try {
                String strTime = split[timeIndex];
                //2019-06-17 06:18:50.814029
                if (strTime.trim().length() == 26) {
                    this.time = sdf.parse(strTime.substring(0, 23)).getTime() * 1000 + Integer.parseInt(strTime.substring(23, 26));
                    this.str = str;
                    return true;
                } else if (strTime.trim().length() == 27) {
                    this.time = sdf.parse(strTime.substring(0, 23)).getTime() * 10000 + Integer.parseInt(strTime.substring(23, 27));
                    this.str = str;
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * time格式为数字
     *
     * @param str
     * @return
     */
    public boolean createNumEntity(String str, int timeIndex, int size) {
        String[] split = str.split("\\|", -1);
        if (split.length >= size) {
            try {
                String strTime = split[timeIndex];
                this.time = Long.parseLong(strTime);
                this.str = str;
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean createMmeEntity(String str, int timeIndex, int size) {
        String[] split = str.split("\\|", -1);
        if (split.length >= size) {
            try {
                if (split[0].contains(".")) {
                    timeIndex = timeIndex + 1;
                }
                String strTime = split[timeIndex];
                //2019-06-17 06:18:50.814029
                if (strTime.trim().length() == 26) {
                    this.time = sdf.parse(strTime.substring(0, 23)).getTime() * 1000 + Integer.parseInt(strTime.substring(23, 26));
                    this.str = str;
                    return true;
                } else if (strTime.trim().length() == 27) {
                    this.time = sdf.parse(strTime.substring(0, 23)).getTime() * 10000 + Integer.parseInt(strTime.substring(23, 27));
                    this.str = str;
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return time.toString();
    }
}
