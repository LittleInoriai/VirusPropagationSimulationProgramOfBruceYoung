package com.bruceyoung.constant;
/**
 * 用于调整参数来展现不同的效果
 *
 */
public class Constants {
    public static int ORIGINAL_COUNT=50;//初始感染数量
    public static float EXPOSED_BROAD_RATE = 0.3f;//潜伏期传染率
    public static float INFECTED_BROAD_RATE = 0.5f;//暴露期传染率
    public static float CURED_HOSPITAL_RATE = 0.015f;//医院治愈率
    public static float DEATH_RATE = 0.004f;//死亡率
    public static float SUSCEPTIBLE_ISOLATE_RATE = 0.0f;//易感者居家隔离的比例
    public static float EXPOSED_ISOLATE_RATE = 0.0f;//潜伏者隔离比例
    public static float EXPOSED_TIME = 14f;//潜伏期
    public static float EXPOSED_TO_INFECTED_RATE = 1/EXPOSED_TIME;//潜伏期患病率
    public static boolean ISOLATION = false;//是否自主隔离
    public static float ISOLATE_RATE = 0.2f;//患病者自主隔离的比例
    public static boolean WEAR_MASK = false;//是否戴口罩
    public static float WEAR_MASK_RATE = 1f;//戴口罩比例
    public static int HOSPITAL_RECEIVE_TIME=30;//医院收治响应时间
    public static int BED_COUNT=100;//医院床位
    public static float u=0.99f;//流动意向平均值
    public static float SAFE_DIST = 2f;//安全距离

}
