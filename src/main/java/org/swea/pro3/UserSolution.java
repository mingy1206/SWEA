package org.swea.pro3;// 반례 조건 : 능력 값이 높을수록 능력이 좋은 선수이다. 능력 값이 같다면 ID가 작을수록 더 능력이 좋은 선수
// 정렬 nlogn
// 4000log4000 = 48,000 x 10 =48만 x 1000 -> 5억 이상  절대 안됨
// 결국 반복 되는 정렬 x
// 중요!!! - 하지만 우리가 리그에서 계속 사용하는 선수는 딱 3명
// top player, middle player, bottom player
// 1. 맨처음 선수들을 이동하기 전에 league배열 한번 정렬
// 2. 배열은 2개의 배열 사용, 주어진 mAbility, 선수들의 움직임을 구현한 league 배열
// -> mAbility는 건들지 않음 id를 통한 능력치 참조용
// -> league는 실제 선수들의 리그 이동을 나타냄
//->temp를 이용해서 league 위치에서 각 선수의 id를 교환해줌.
// 3-1. move 연산 ->  승급 선수는 낮은 index 쪽으로, 강등 선수는 index가 높은 쪽으로 단방향 정렬 -> O(2*N)연산
// 조심해야할 부분은, 기존의 정렬되지 않은 부분이 적용되지 않게 고려
// 1번 java 내부 sort algorithm을 통해서 시간 체크
// 2번 강등 선수와 승급 선수의 능력치 확인하고 큰 것을 앞으로, 작은 것을 뒤로 보내고
//      투포인터 느낌으로 정렬 진행 -> O(N)
//3-2. trade 연산
// move 연산과 마찬가지로 하나의 리그에서 상위 하위에서 트레이드 된 선수를 비교
// 능력치가 큰 것을 앞으로 작은 것을 뒤로 move와 똑같이 정렬 -> O(N)정도
//

import java.util.*;

class UserSolution {
    private int N; // 선수들의 수
    private int L; // 리그의 개수
    private int leaguePlayerNum; // 리그 별 선수의 수(항상 홀수)
    private int[] mAbility; //각 선수들의 능력 값
    private int[][] league; // 리그에서 선수들의 순위 배치
    private Map<Integer,Integer> map = new HashMap<>();

    void init(int N, int L, int mAbility[]) {
        // 초기값 설정
        this.N = N;
        this.L = L;
        this.leaguePlayerNum = N/L;
        this.mAbility = mAbility;
        this.league = new int[L][leaguePlayerNum];
        int idx = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < leaguePlayerNum; j++) {
                league[i][j] = idx++;
            }
        }
        // Map을 이용한 리그별 선수 능력치 정렬
        for(int i=0; i<N; i++)
            map.put(i,mAbility[i]);

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());

        // 처음 정렬에서 value가 같은 경우가 있을까? && map 정렬에서 value가 같으면 어떻게 동작할까?
        // 내림차순 정렬

        for(int i=0; i<L; i++)
            entries.subList(i*leaguePlayerNum, (i+1)*leaguePlayerNum).sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

//        for(int i=0; i<L; i++)
//            entries.subList(i * leaguePlayerNum, (i + 1) * leaguePlayerNum)
//                .sort(
//                    Comparator.comparing(Map.Entry<Integer, Integer>::getValue, Comparator.reverseOrder())
//                        .thenComparing(Map.Entry::getKey)
//                );

        // 초기 능력치 순으로 정렬한 선수를 리그에 배치
        for(int i=0; i<N; i++){
            Map.Entry<Integer,Integer> entry = entries.get(i);
            league[Math.abs(i/leaguePlayerNum)][i%leaguePlayerNum] = entry.getKey();
        }
    }

    int move() {
        int first = 0;
        int last = leaguePlayerNum-1;
        int result = 0;
        // 강등 및 승급 진행
        for(int i=0; i<L-1; i++){
            result += (league[i][last]+league[i+1][first]);
            int tempId;
            tempId=league[i+1][first];
            league[i+1][first] = league[i][last]; // 강등
            league[i][last] = tempId; // 승급
        }

        // 신규 선수 유입에 인한 리그 정렬 진행(투포인터 응용)
        for(int i=0; i<L; i++){
            int upgradedId = league[i][last];
            int downgradedId = league[i][first];


            // 투포인터를 위한 끝 값 배치 큰 것을 앞으로, 작은 것을 뒤로
            if((mAbility[upgradedId] > mAbility[downgradedId])||((mAbility[upgradedId] == mAbility[downgradedId])&&(upgradedId<downgradedId))){
                int tempId;
                tempId = downgradedId;
                league[i][first] = upgradedId;
                league[i][last] = tempId;
            }
            int frontIndex = first;
            int backIndex = last;
            int tempId, compareIndex, compareId;


            for(int j=0; j<leaguePlayerNum-1; j++){
                int frontId = league[i][frontIndex];
                int backId = league[i][backIndex];

                // 앞의 선수 뒤로
                compareIndex = frontIndex+1;
                compareId = league[i][compareIndex];
                if((mAbility[frontId] < mAbility[compareId])||((mAbility[frontId] == mAbility[compareId])&&(frontId>compareId))){
                    tempId = frontId;
                    league[i][frontIndex] = compareId;
                    league[i][compareIndex] = tempId;
                    frontIndex++;
                }
                // 뒤의 선수 앞으로
                compareIndex = backIndex-1;
                compareId = league[i][compareIndex];
                if((mAbility[backId] > mAbility[compareId])||((mAbility[backId] == mAbility[compareId])&&(backId<compareId))){
                    tempId = backId;
                    league[i][backIndex] = compareId;
                    league[i][compareIndex] = tempId;
                    backIndex--;
                }

            }

        }
        return result;
    }

    int trade() {
        int first = 0;
        int middle = leaguePlayerNum/2;
        int last = leaguePlayerNum-1;
        int result = 0;
        // 트레이드 진행
        for(int i=0; i<L-1; i++){
            result += (league[i][middle]+league[i+1][first]);
            int tempId;
            tempId=league[i+1][first];
            league[i+1][first] = league[i][middle]; // 하위 리그로
            league[i][middle] = tempId; // 상위 리그로

            // 내가 틀렸던 부분
            // middle을 last로 옮겨서 투포인터 진행을 위한 전처리
            int change = league[i][middle];
            for (int j = middle; j < last; j++) league[i][j] = league[i][j+1];
            league[i][last] = change;
        }

        // 신규 선수 유입에 인한 리그 정렬 진행(투포인터 응용)
        for(int i=0; i<L; i++){
            int upgradedId = league[i][last];
            int downgradedId = league[i][first];


            // 투포인터를 위한 끝 값 배치 큰 것을 앞으로, 작은 것을 뒤로
            if((mAbility[upgradedId] > mAbility[downgradedId])||((mAbility[upgradedId] == mAbility[downgradedId])&&(upgradedId<downgradedId))){
                int tempId;
                tempId = downgradedId;
                league[i][first] = upgradedId;
                league[i][last] = tempId;
            }
            int frontIndex = first;
            int backIndex = last;
            int tempId, compareIndex, compareId;


            for(int j=0; j<leaguePlayerNum-1; j++){
                int frontId = league[i][frontIndex];
                int backId = league[i][backIndex];

                // 앞의 선수 뒤로
                compareIndex = frontIndex+1;
                compareId = league[i][compareIndex];
                if((mAbility[frontId] < mAbility[compareId])||((mAbility[frontId] == mAbility[compareId])&&(frontId>compareId))){
                    tempId = frontId;
                    league[i][frontIndex] = compareId;
                    league[i][compareIndex] = tempId;
                    frontIndex++;
                }
                // 뒤의 선수 앞으로
                compareIndex = backIndex-1;
                compareId = league[i][compareIndex];
                if((mAbility[backId] > mAbility[compareId])||((mAbility[backId] == mAbility[compareId])&&(backId<compareId))){
                    tempId = backId;
                    league[i][backIndex] = compareId;
                    league[i][compareIndex] = tempId;
                    backIndex--;
                }

            }

        }
        return result;
    }

}