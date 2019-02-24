package com.cying.webtoolkit.study.unqId;

/**
 * @author chengying13378 chengying13378@hundsun.com
 * @ClassName SnowFlake
 * @Description TODO
 * @date 2018/10/9 9:44 PM
 */

import org.apache.commons.lang3.StringUtils;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author chengying
 * TODO 新版本改造设想,长度有原来的固定比特位变为固定字符长度
 * 精确到s的时间戳(14位) + 机器id(3位) + 流水类型(2位) +本机序列(6位)(要考虑时钟回拨)
 */
public class NewSnowFlake {

    // ==============================Fields===========================================

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    /**
     * 机器id,3位长度
     */
    private  String workId;

    /**
     * 流水类型标志,2位长度
     */
    private  String serialType;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    private String  lastDateTime = LocalDateTime.now().format(FORMATTER);

    private long lastTimestamp = -1L;

    public  NewSnowFlake(String workId, String serialType){
        this.workId = workId;
        this.serialType = serialType;
    }

    public synchronized String nextId() {

        long timestamp = timeNow();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1);
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
            //上次生成ID的时间截
            lastTimestamp = timestamp;
        }
        //拼接成最后的字符串
        Instant instant = Instant.ofEpochMilli(timestamp);
        String timeStr = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(FORMATTER);
        return timeStr+workId+serialType+sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeNow();
        while (timestamp <= lastTimestamp) {
            timestamp = timeNow();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeNow() {
//        return Instant.now().toEpochMilli();
        return System.currentTimeMillis();
    }

//
//    /**
//     * 阻塞到下一秒，直到获得新的时间戳
//     * @param lastTimestamp 上次生成ID的时间截
//     * @return 当前时间戳
//     */
//    protected String tilNextSec(long lastTimestamp) {
//        String nowDateTime = timeNow();
//        while (nowDateTime.compareTo(lastDateTime) < 0) {
//            nowDateTime = timeNow();
//        }
//        return nowDateTime;
//    }
//
//    /**
//     * 返回以秒为单位的当前时间
//     * @return 当前时间(秒)
//     */
//    protected String timeNow() {
//        return LocalDateTime.now().format(FORMATTER);
//    }

    public static void main(String[] args) {
        NewSnowFlake idWorker = new NewSnowFlake("0", "0");
        for (int i = 0; i < 1000; i++) {
            new Thread(){
                @Override
                public void run() {
                    String id = idWorker.nextId();
                    System.out.println(id);
                }
            }.start();
        }
    }
}