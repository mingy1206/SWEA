package org.swea.pro8;

import java.util.*;

class UserSolution {
    // [수정] 채널 구독자를 중복이 불가능한 Set으로 변경
    private Map<Integer, Set<Integer>> channelSubscribers;
    private TreeMap<Integer, TreeSet<newsMetaData>> reservationNews;
    private Map<Integer, List<Integer>> userNotifications;
    private Map<Integer, Integer> newsReservationTime;
    private Map<Integer, List<Integer>> newsReceivedBy;

    // [수정] newsMetaData 클래스 간소화
    class newsMetaData implements Comparable<newsMetaData> {
        int mNewsID;
        int mChannelID;

        newsMetaData(int mNewsID, int mChannelID) {
            this.mNewsID = mNewsID;
            this.mChannelID = mChannelID;
        }

        @Override
        public int hashCode() { return Objects.hash(mNewsID); }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            newsMetaData nmd = (newsMetaData) o;
            return mNewsID == nmd.mNewsID;
        }

        @Override
        public int compareTo(newsMetaData o) {
            return o.mNewsID - this.mNewsID; // 뉴스 ID 내림차순 정렬
        }
    }

    private void processPendingNews(int mTime) {
        // 안전한 순회를 위해 처리할 키(시간) 목록을 미리 복사
        List<Integer> timesToProcess = new ArrayList<>(reservationNews.headMap(mTime, true).keySet());

        for (Integer time : timesToProcess) {
            TreeSet<newsMetaData> newsSet = reservationNews.get(time);
            if (newsSet == null) continue;

            for (newsMetaData nmd : newsSet) {
                Set<Integer> subscribers = channelSubscribers.getOrDefault(nmd.mChannelID, Collections.emptySet());

                newsReceivedBy.put(nmd.mNewsID, new ArrayList<>(subscribers));


                for (int subscriberID : subscribers) {
                    userNotifications.computeIfAbsent(subscriberID, k -> new ArrayList<>()).add(nmd.mNewsID);
                }
            }
            reservationNews.remove(time);
        }
    }

    public void init(int N, int K) {
        channelSubscribers = new HashMap<>();
        reservationNews = new TreeMap<>();
        userNotifications = new HashMap<>();
        newsReservationTime = new HashMap<>();
        newsReceivedBy = new HashMap<>();
    }

    public void registerUser(int mTime, int mUID, int mNum, int[] mChannelIDs) {
        processPendingNews(mTime);
        for (int i = 0; i < mNum; i++) {
            // [수정] Set에 구독자를 추가 (중복 자동 처리)
            channelSubscribers.computeIfAbsent(mChannelIDs[i], k -> new HashSet<>()).add(mUID);
        }
    }

    public int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID) {
        processPendingNews(mTime);
        int reservationTime = mTime + mDelay;
        newsReservationTime.put(mNewsID, reservationTime);
        reservationNews.computeIfAbsent(reservationTime, k -> new TreeSet<>()).add(new newsMetaData(mNewsID, mChannelID));

        return channelSubscribers.getOrDefault(mChannelID, Collections.emptySet()).size();
    }

    public void cancelNews(int mTime, int mNewsID) {
        processPendingNews(mTime);
        if (!newsReservationTime.containsKey(mNewsID)) return;

        int reservationTime = newsReservationTime.get(mNewsID);

        if (reservationTime > mTime) { // 아직 발송되지 않은 뉴스
            TreeSet<newsMetaData> newsSet = reservationNews.get(reservationTime);
            if (newsSet != null) {
                newsSet.remove(new newsMetaData(mNewsID, 0));
                if (newsSet.isEmpty()) {
                    reservationNews.remove(reservationTime);
                }
            }
            newsReservationTime.remove(mNewsID);
            newsReceivedBy.remove(mNewsID);
        } else { // 이미 발송된 뉴스
            List<Integer> receivedUsers = newsReceivedBy.get(mNewsID);
            if (receivedUsers != null) {
                for (int userID : receivedUsers) {
                    List<Integer> notifications = userNotifications.get(userID);
                    if (notifications != null) {
                        notifications.remove(Integer.valueOf(mNewsID));
                    }
                }
            }
            newsReceivedBy.remove(mNewsID);
        }
    }

    public int checkUser(int mTime, int mUID, int[] mRetIDs) {
        processPendingNews(mTime);

        List<Integer> newsList = userNotifications.get(mUID);

        if (newsList == null || newsList.isEmpty()) {
            return 0;
        }

        // [수정] 문제의 '최신순' 조건에 맞게 정렬 로직 변경
        newsList.sort((id1, id2) -> {
            int time1 = newsReservationTime.get(id1);
            int time2 = newsReservationTime.get(id2);
            if (time1 != time2) {
                return Integer.compare(time2, time1); // 1. 시간 내림차순
            }
            return Integer.compare(id2, id1); // 2. 시간이 같으면 ID 내림차순
        });

        int returnCount = Math.min(newsList.size(), 3);

        for (int i = 0; i < returnCount; i++) {
            mRetIDs[i] = newsList.get(i);
        }

        userNotifications.remove(mUID);

        return newsList.size(); // [수정] 반환값은 '전체' 받은 알림 개수여야 함
    }
}