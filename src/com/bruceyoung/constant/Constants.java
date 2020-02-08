package com.bruceyoung.constant;
/**
 * 用于调整参数来展现不同的效果
 *
 */
public class Constants {
    public static int ORIGINAL_COUNT=50;//初始感染数量
    public static float BROAD_RATE = 0.8f;//传播率
    public static float SHADOW_TIME = 0;//潜伏时间
    public static int HOSPITAL_RECEIVE_TIME=1000000;//医院收治响应时间
    public static int BED_COUNT=1000;//医院床位
    public static float u=0.99f;//流动意向平均值
    /* 
     //假设初始感染人数为50，传播率为0.8,医院响应时间过长，人口流动未控制，则会迅速变红，床位不足
    public static int ORIGINAL_COUNT=50;//初始感染数量
    public static float BROAD_RATE = 0.8f;//传播率
    public static float SHADOW_TIME = 0;//潜伏时间
    public static int HOSPITAL_RECEIVE_TIME=1000000;//医院收治响应时间
    public static int BED_COUNT=1000;//医院床位
    public static float u=0.99f;//流动意向平均值
    */
    
    /* 
      //假设初始感染人数为50，传播率为0.8,医院响应时间过长，床位却不足，人口未控制，则会迅速变红
    public static int ORIGINAL_COUNT=50;//初始感染数量
    public static float BROAD_RATE = 0.8f;//传播率
    public static float SHADOW_TIME = 0;//潜伏时间
    public static int HOSPITAL_RECEIVE_TIME=1000000;//医院收治响应时间
    public static int BED_COUNT=0;//医院床位
    public static float u=0.99f;//流动意向平均值
    */
    /* 
    public static int ORIGINAL_COUNT=50;//初始感染数量
    public static float BROAD_RATE = 0.8f;//传播率
    public static float SHADOW_TIME = 0;//潜伏时间
    public static int HOSPITAL_RECEIVE_TIME=10;//医院收治响应时间
    public static int BED_COUNT=0;//医院床位
    public static float u=0.99f;//流动意向平均值
    */
    /* 
    public static int ORIGINAL_COUNT=50;//初始感染数量
    public static float BROAD_RATE = 0.8f;//传播率
    public static float SHADOW_TIME = 0;//潜伏时间
    public static int HOSPITAL_RECEIVE_TIME=10;//医院收治响应时间
    public static int BED_COUNT=0;//医院床位
    public static float u=0.99f;//流动意向平均值
    */
}
