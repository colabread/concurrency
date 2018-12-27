package com.aidilude.concurrency.example.condition;

import java.util.Map;

public class KillGame {

    private Integer round;   //轮次

    private Integer status;   //游戏状态：1 - 进行中，2 - 结束

    private Long currentRoundDayEndTime;   //本轮白天结束时间

    private Long currentRoundKillEndTime;  //本轮黑夜杀人结束时间

    private boolean currentRoundKilled;   //本轮黑夜是否杀人

    private Long currentRoundCheckEndTime;   //本轮黑夜验人结束时间

    private boolean currentRoundChecked;   //本轮黑夜是否验人

    private Long currentRoundLastWordsEndTime;   //本轮遗言结束时间

    /**
     * 开启本局游戏
     */
    public void run(){
        initParams();
        begin();
        end();
    }

    /**
     * 初始化本局游戏参数
     */
    public void initParams(){
        this.round = 1;
        this.status = 1;
    }

    /**
     * 游戏主循环
     */
    public void begin(){
        do{
            //1、白天自由发言以及投票
            while (System.currentTimeMillis() < this.currentRoundDayEndTime.longValue()){

            }
            //2、统计投票
            if(statisticalVoting()){  //得到票数最多的玩家只有一个
                if(votingCountBeyondHalf()){   //票数过半
                    outPlayer("you");   //当前玩家出局
                    publishVotingResults();   //公布投票结果1
                    if(isEnd()){   //out一民玩家之后，进行游戏是否结束判定
                        break;
                    }
                }
            }
            publishVotingResults();   //公布投票结果2
            //3、进入黑夜，杀手杀人
            while (System.currentTimeMillis() < this.currentRoundKillEndTime && !this.currentRoundKilled){

            }
            //警察验人
            while (System.currentTimeMillis() < this.currentRoundCheckEndTime && !this.currentRoundChecked){

            }
            //统计杀人结果
            if(!statisticalKill()){   //如果没有人被杀，直接进入下一轮白天
                publishKillingResults();   //公布杀人结果1
                continue;
            }
            publishKillingResults();   //公布杀人结果2
            if(isEnd()){   //杀人之后，进行游戏是否结束判定
                break;
            }
            //遗言时间
            while(System.currentTimeMillis() < this.currentRoundLastWordsEndTime){

            }
        }while (status.intValue() == 1);
    }

    /**
     * 结束游戏时的操作
     */
    public void end(){

    }

    /**
     * 统计投票
     * true - 得到票数最多的玩家只有一个
     * false - 多个
     * @return
     */
    public boolean statisticalVoting(){
        return true;
    }

    /**
     * 票数是否过半
     * @return
     */
    public boolean votingCountBeyondHalf(){
        return true;
    }

    /**
     * 玩家出局
     * @param player
     */
    public void outPlayer(String player){

    }

    /**
     * 公布投票结果
     */
    public void publishVotingResults(){

    }

    /**
     * 统计杀人结果
     * true - 有人被杀
     * false - 没人被杀
     * @return
     */
    public boolean statisticalKill(){
        return true;
    }

    /**
     * 公布杀人结果
     * @return
     */
    public void publishKillingResults(){

    }

    /**
     * 判断本局游戏是否结束
     * @return
     */
    public boolean isEnd(){
        return false;
    }

}