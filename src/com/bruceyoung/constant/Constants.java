package com.bruceyoung.constant;
/**
 * ���ڵ���������չ�ֲ�ͬ��Ч��
 *
 */
public class Constants {
    public static int ORIGINAL_COUNT=50;//��ʼ��Ⱦ����
    public static float EXPOSED_BROAD_RATE = 0.3f;//Ǳ���ڴ�Ⱦ��
    public static float INFECTED_BROAD_RATE = 0.5f;//��¶�ڴ�Ⱦ��
    public static float CURED_HOSPITAL_RATE = 0.015f;//ҽԺ������
    public static float DEATH_RATE = 0.004f;//������
    public static float SUSCEPTIBLE_ISOLATE_RATE = 0.0f;//�׸��߾ӼҸ���ı���
    public static float EXPOSED_ISOLATE_RATE = 0.0f;//Ǳ���߸������
    public static float EXPOSED_TIME = 14f;//Ǳ����
    public static float EXPOSED_TO_INFECTED_RATE = 1/EXPOSED_TIME;//Ǳ���ڻ�����
    public static boolean ISOLATION = false;//�Ƿ���������
    public static float ISOLATE_RATE = 0.2f;//��������������ı���
    public static boolean WEAR_MASK = false;//�Ƿ������
    public static float WEAR_MASK_RATE = 1f;//�����ֱ���
    public static int HOSPITAL_RECEIVE_TIME=30;//ҽԺ������Ӧʱ��
    public static int BED_COUNT=100;//ҽԺ��λ
    public static float u=0.99f;//��������ƽ��ֵ
    public static float SAFE_DIST = 2f;//��ȫ����

}
