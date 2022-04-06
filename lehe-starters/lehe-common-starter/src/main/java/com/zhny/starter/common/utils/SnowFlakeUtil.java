package com.zhny.starter.common.utils;

/**
 * 原作者 zzxadi https://github.com/zzxadi/Snowflake-IdWorker
 *
 * @author houqj
 */
public class SnowFlakeUtil {

    private static final SnowFlakeUtil flowIdWorker = new SnowFlakeUtil(1);
    private final long id;
    /**
     * 机器标识位数
     */
    private final long workerIdBits = 10L;
    /**
     * 毫秒内自增位
     */
    private final long sequenceBits = 12L;
    /**
     * 0，并发控制
     */
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private SnowFlakeUtil(long id) {
        /**
         * 机器ID最大值: 1023
         */
        long maxWorkerId = -1L ^ -1L << this.workerIdBits;
        if (id > maxWorkerId || id < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.id = id;
    }

    public static SnowFlakeUtil getFlowIdInstance() {
        return flowIdWorker;
    }

    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static Long getId() {
        return getFlowIdInstance().nextId();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            SnowFlakeUtil snowFlakeUtil = SnowFlakeUtil.getFlowIdInstance();
            System.out.println(snowFlakeUtil.nextId());
        }
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (this.lastTimestamp == timestamp) {
            //如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            /**
             * 4095,111111111111,12位
             */
            long sequenceMask = -1L ^ -1L << this.sequenceBits;
            this.sequence = this.sequence + 1 & sequenceMask;
            if (this.sequence == 0) {
                // 重新生成timestamp
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            return -1;
        }

        this.lastTimestamp = timestamp;
        /**
         * 22
         */
        long timestampLeftShift = this.sequenceBits + this.workerIdBits; /**
         * 12
         */ /**
         * 时间起始标记点，作为基准，一般取系统的最近时间
         */long epoch = 1524291141010L;
        return timestamp - epoch << timestampLeftShift | this.id << this.sequenceBits | this.sequence;
    }

    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
}
