**注：**
此仿真程序是在现有的仿真程序的基础上做出了修改，添加了自己设定的一些条件，仅作学习交流。

**参考程序的链接：**
https://github.com/zmjjobs/VirusPropagationSimulationProgramOfBruceYoung

## 程序概述

本程序是在原程序的基础上，参考传染病的预测模型SEIR模型，将病毒传播的过程分为多个状态，设定不同状态之间的转化率以及根据防疫措施设定转化条件，然后采用元胞自动机模型模拟新冠病毒在不同防疫措施下在人群中传播的过程，最终得到模拟数据，绘制图表，评估各类防疫措施的效果。

## 代码思路

在800X800的二维网格中，按照正态分布随机生成5000个易感者，在5000个易感者中产生50个病毒感染者，这就是程序运行的初始状态。每个人随机生成自己的目的地，然后朝目的地移动，每走一步就进行状态的更新，到达目的地后再次规划新的目的地进行移动，以此往复。

在自由传播状态(不进行任何干预)下，人群的状态只有易感者、潜伏者、感染者和死亡者四个状态。易感者接触潜伏者或者感染者后有概率转化为潜伏者，潜伏者在潜伏期内有概率转化为感染者，超过潜伏期我们认为直接成为感染者，感染者有概率转化为死亡者。

在考虑医院救治的情况下，人群的状态增加了入院接受治疗的感染者和治愈者，感染者在医院床位有空余的情况下能够进入医院治疗，接受治疗的感染者有概率转化为治愈者，治愈者产生抗体，认为不再会被感染。

在考虑戴口罩的情况下，人群的状态增加了戴口罩的易感者、戴口罩的潜伏者和戴口罩的感染者，在易感者接触到潜伏者或者感染者时，若双方只有一人带口罩，易感者感染的概率降低，若双方都戴口罩，则认为易感者不会被感染。

在考虑隔离的情况下，人情的状态又有隔离的易感者、隔离的潜伏者和隔离的感染者，被隔离的人群不会传染病毒给他人，也不会被传染病毒。

## 程序运行时截图
左侧模拟人群中病毒传播的情况，白点代表易感者，黄点代表潜伏者，红点代表感染者，绿点代表治愈者。
右侧模拟医院接收病人的情况，此时可以看到医院已无法容纳更多病人。

![image-20200803174416146](https://github.com/LittleInoriai/VirusPropagationSimulationProgramOfBruceYoung/blob/master/image/image-20200803174416146.png)

## 用模拟的数据绘制折线图

这里只放一张自由传播时的折线图
**S**
易感者**E**
潜伏者**I**
感染者**R**
治愈者**D**
死亡者


![image-20200803174755736](https://github.com/LittleInoriai/VirusPropagationSimulationProgramOfBruceYoung/blob/master/image/image-20200803174755736.png)

