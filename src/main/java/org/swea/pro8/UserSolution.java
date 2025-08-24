package org.swea.pro8;
import java.util.*;
class UserSolution {
    private static int N;
    private static int K;

    private static Map<Integer, List<Integer>> ChannelSubscribe;
    private static Map<Integer, List<Integer>> Channels;

    private static TreeMap<Integer, List<Integer>> reservation;
    private static Map<Integer, List<Integer>> Users;
    private static Map<Integer, List<Integer>> News;
    class newsMetaData{
        int mTime;
        int mDelay;
        int reservationTime;
        int mChannelID;
        newsMetaData(int mTime, int mDelay, int mChannelID){
            this.mTime = mTime;
            this.mDelay = mDelay;
            this.reservationTime = mTime + mDelay;
            this.mChannelID = mChannelID;
        }
    }
    void init(int N, int K)
    {
        this.N = N;
        this.K = K;
        ChannelSubscribe = new HashMap<>();
        Channels = new HashMap<>();
        reservation = new TreeMap<>();
        Users = new HashMap<>();
        News = new HashMap<>();
    }

    void registerUser(int mTime, int mUID, int mNum, int mChannelIDs[])
    {
        // reservation mTime 먼저 실행

        // 예약 시간별 보내야하는 채널들 추출
        for (Map.Entry<Integer, List<Integer>> entry : reservation.headMap(mTime, true).entrySet()){

            List<Integer> channelList = entry.getValue(); // 채널과 채널이 가지고 있는 구독자들

            // 채널들을 구독하는 유저들 추출
            for(int channel : channelList){

                List<Integer> subcribers = ChannelSubscribe.get(channel); //구독자들

                // 유저별로 채널이 가지고 있는 뉴스들 넣어주기
                for(int subscriber : subcribers){

                    List<Integer> newList = new ArrayList<>(Channels.get(channel)); // 뉴스 목록들
                    if(!Users.containsKey(subscriber)){
                        Users.put(subscriber, newList);
                    }
                    else{
                        List<Integer> originalList = new ArrayList<>(Channels.get(channel));
                        originalList.addAll(newList);
                        Users.put(subscriber, originalList);
                    }
                    News.put()
                }
            }
        }

        reservation = new TreeMap<>(reservation.tailMap(mTime, false));

        // 새로 채널 구독하는 유저들 저장
        for(int channelID : mChannelIDs){
            if(!ChannelSubscribe.containsKey(channelID)){
                List<Integer> list = new ArrayList<>();
                list.add(mUID);
                ChannelSubscribe.put(channelID, list);
            }
            else{
                List list = ChannelSubscribe.get(channelID);
                list.add(mUID);
                ChannelSubscribe.put(channelID, list);
            }
        }
        // 해당 시간 알림 처리 구현 나중에
    }

    int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID)
    {
        // 채널들이 가지고 있는 뉴스 저장
        if(!Channels.containsKey(mNewsID)){
            List<Integer> list = new ArrayList<>();
            list.add(mNewsID);
            Channels.put(mChannelID, list);
        }
        else{
            List list = Channels.get(mChannelID);
            list.add(mNewsID);
            ChannelSubscribe.put(mChannelID, list);
        }

        //시간별 전송해야할 채널들 저장
        int time = mTime+mDelay;
        if(!reservation.containsKey(time)){
            List<Integer> list = new ArrayList<>();
            list.add(mChannelID);
            Channels.put(mChannelID, list);
        }
        else{
            List list = Channels.get(time);
            list.add(mChannelID);
            ChannelSubscribe.put(time, list);
        }



        return ChannelSubscribe.get(mChannelID).size();
    }

    void cancelNews(int mTime, int mNewsID)
    {
        // 예약을 아예 취소
        reservation.remove(mTime);
        // mNewsID도 삭제하기
        Channels.remove(mNewsID);

    }

    int checkUser(int mTime, int mUID, int mRetIDs[])
    {
        // reservation mTime 먼저 실행

        // 예약 시간별 보내야하는 채널들 추출
        for (Map.Entry<Integer, List<Integer>> entry : reservation.headMap(mTime, true).entrySet()){

            List<Integer> channelList = entry.getValue(); // 채널과 채널이 가지고 있는 구독자들

            // 채널들을 구독하는 유저들 추출
            for(int channel : channelList){

                List<Integer> subcribers = ChannelSubscribe.get(channel); //구독자들

                // 유저별로 채널이 가지고 있는 뉴스들 넣어주기
                for(int subscriber : subcribers){

                    List<Integer> newList = new ArrayList<>(Channels.get(channel));

                    if(!Users.containsKey(subscriber)){
                        Users.put(subscriber, newList);
                    }
                    else{
                        List<Integer> originalList = new ArrayList<>(Channels.get(channel));
                        originalList.addAll(newList);
                        Users.put(subscriber, originalList);
                    }
                }
            }
        }


        return -1;
    }
}
// 이게 결국 시간마다 돌아가는 로직이 없으니까
// 특정 로직에서 해당 시간까지의 알림을 전송했다는 로직을 구현하는 것?
// 그러면 reservation에서 mTime까지의 로직을 모두 알림함으로 전송하고 처리
// 따라서 reservation의 mTime 이하의 값들을 쉽게 찾을 수 있고 없앨 수 있어야 함.
// 그러면 mTime을 해야할 때마다 특정 값 이하의 트리셋을 뽑아서 알림함에 넣고
// 특정 값 이상을 새로운 reservation의 셋으로