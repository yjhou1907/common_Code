package common.core.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期类型
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String returnDateStr =  sdf.format(date);
        return  returnDateStr;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        String datestr =   sdf.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期+ 时间
     * @return
     */
    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        datestr = sdf.format(new Date());
        return datestr;
    }

    /**
     *  获取知道格式的当前日期+时间
     * @param pattern 指定的格式化字符串
     * @return
     */
    public static String getCurrentDateTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String datestr = sdf.format(new Date());
        return datestr;
    }

    /**
     * 将字符串日期转换为日期格式
     * @param dateString
     * @return
     *
     */
    public static Date stringToDate(String dateString) {
        if(dateString ==null ||dateString.equals("")){
            return null;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT);
       try {
           date = sdf.parse(dateString);
       }catch (ParseException e){
           DateUtil.stringToDate(dateString,"yyyy/MM/dd");
       }
        return date;
    }
    /**
     * 将字符串日期转换为日期格式
     * 自定义格式
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString, String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据指定的日期计算间隔后的日期 （间隔前或间隔后）  计算方法1
     * @param initDate
     * @param n
     * @param pattern
     * @return
     */
    public static String getCalculateDay(Object initDate,int n,String pattern){
        String calculateDay = "";//返回计算后的日期
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date initDateTime = null;
            if (initDate == null) {
                initDateTime = new Date();
            } else if (initDate instanceof Date) {
                initDateTime = (Date) initDate;
            } else {
                initDateTime = sdf.parse((String) initDate);
            }
            calendar.setTime(initDateTime);
            calendar.add(calendar.DATE,n);
            Date calculateDate = calendar.getTime();
            calculateDay = sdf.format(calculateDate);
        }catch (ParseException e){
            return calculateDay;
        }
        return calculateDay;
    }
    /**
     * 根据指定的日期计算间隔后的日期 （间隔前或间隔后）  计算方法2
     * @param initTime
     * @param n
     * @param pattern
     * @return  计算后的日期
     */
    public static String getCalculateDayTwo(Object initTime,int n,String pattern) throws ParseException{
        String calculateDay ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 初始日期
        Date initDate = null;
        if (initTime instanceof Date) {
            initDate = (Date) initTime;
        }else {
            initDate = sdf.parse((String) initTime);
        }
        Long initMilliSeconds = initDate.getTime(); // 转换成距离1970年0时0分的毫秒数
        int oneDayTime = 24 * 60 * 60 * 1000;  // 一天代表的毫秒数
        initMilliSeconds += oneDayTime * n;
        Date calculateDate = new Date(initMilliSeconds);
        calculateDay = sdf.format(calculateDate);
        return calculateDay;
    }

    /**
     * 计算日期间隔_后
     * @param nowDate       当前日期
     * @param format        格式化方式
     * @param intervalType  间隔类型(年 、月)
     * @param Company       间隔单位
     * @return
     */
    public static String getAfterDateInterval(Date nowDate , String format, int intervalType, int Company){
        Date dBefore = new Date();//间隔后的日期
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(nowDate);//把当前时间赋给日历
        if(intervalType==calendar.YEAR){
            calendar.add(calendar.YEAR, Company);
        }else if(intervalType==calendar.MONTH){
            calendar.add(calendar.MONTH, Company);
        }
        dBefore = calendar.getTime(); //得到前n月的时间
        SimpleDateFormat sdf=new SimpleDateFormat(format); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前n月的时间
        return defaultStartDate;
    }



    /**
     * 根据身份证号获取出生日期
     * @param  CertId
     * @return
     */
    public static String getBirthday(String CertId){
        String year = "",month= "", day = "";
        if(CertId.length()>16){//18位身份证号
            int i = 6;//截取字符的起点
            year = CertId.substring(i, i+4);//截取年
            month = CertId.substring(i+4, i+6);//截取月
            day = CertId.substring(i+6, i+8);//截取日
        }
        return year+"/"+month+"/"+day;
    }
}
