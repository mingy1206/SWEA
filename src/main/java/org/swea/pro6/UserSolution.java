package org.swea.pro6;

import java.util.*;

// 트리맵과 해시맵을 사용한 자료 다루기
// 인원 수를 위한 탐색은 트리맵
// 트리맵은 자동으로 정렬하기 때문에 자식 노드를 알 수 없으므로 해시맵에서 찾기
// distribute 는 갯수를 중앙에서 이진 탐색으로 찾기 1000log80000 * 1000

class UserSolution {
    private static List<Map> treeMapList;
    private static List<Map> hashMapList;
    private static int root;
    public void init(int N, int mId[], int mNum[]) {
        root = N;
        treeMapList = new ArrayList<>();
        hashMapList = new ArrayList<>();

        for(int i = 0; i < N; i++){
            Map<Integer, Integer> treeMap = new TreeMap<>();
            treeMap.put(mId[i], mNum[i]);
            treeMapList.add(i, treeMap);
        }
        for(int i = 0; i < N; i++){
            Map<Integer, Integer> hashMap = new HashMap<>();
            hashMap.put(mId[i], 0);
            hashMapList.add(i, hashMap);
        }

        return;
    }

    public int add(int mId, int mNum, int mParent) {
        Map<Integer, Integer> treeMap = new TreeMap<>();
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < root; i++){
            Map HM = hashMapList.get(i);
            Map TM;
            if(HM.containsKey(mParent)){
                //if(HM.get(mParent) >= )
                TM = treeMapList.get(i);
                TM.put(mId,mNum);
            }
        }
        return 0;
    }

    public int remove(int mId) {
        return 0;
    }

    public int distribute(int K) {
        return 0;
    }
}