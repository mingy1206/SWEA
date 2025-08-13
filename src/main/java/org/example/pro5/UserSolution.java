package org.example.pro5;
// 이게 문제에 데이터에 대한 구체적인 정보가 없기 때문에
// ex) 최악의 상황 10억개의 데이터가 모두 같은 점수 이런거 고려 안하고 만들어보자
// 각 상황에 맞는 모든 hash 구조의 메모리를 만든다고 생각하기

//결국 (학년 and 성별) -> 성적 -> ID
// Map<성적, Map<ID, List>>
// Map<mGrade, ID>
// Map<mGender, ID>
// female map, Male map < ID, Grade> / first map, second map, third map <ID, Grade>


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

enum Who{
    No_One,
    Female_Grade1,
    Female_Grade2,
    Female_Grade3,
    Male_Grade1,
    Male_Grade2,
    Male_Grade3;
}


class UserSolution {
    private LinkedHashMap<Integer, Integer> fg1Map; // female first grade map
    private LinkedHashMap<Integer, Integer> fg2Map; // female second grade map
    private LinkedHashMap<Integer, Integer> fg3Map; // female third grade map
    private LinkedHashMap<Integer, Integer> mg1Map; // male first grade map
    private LinkedHashMap<Integer, Integer> mg2Map; // male second grade map
    private LinkedHashMap<Integer, Integer> mg3Map; // male third grade map
    private LinkedHashMap<Integer, Who> whoMap;

    private int fg1MaxId;
    private int fg2MaxId;
    private int fg3MaxId;
    private int mg1MaxId;
    private int mg2MaxId;
    private int mg3MaxId;

    private int fg1MinId;
    private int fg2MinId;
    private int fg3MinId;
    private int mg1MinId;
    private int mg2MinId;
    private int mg3MinId;

    public void init() {
        fg1Map = new LinkedHashMap<>();
        fg2Map = new LinkedHashMap<>();
        fg3Map = new LinkedHashMap<>();
        mg1Map = new LinkedHashMap<>();
        mg2Map = new LinkedHashMap<>();
        mg3Map = new LinkedHashMap<>();
        whoMap = new LinkedHashMap<>();

        fg1MaxId = 0;
        fg2MaxId = 0;
        fg3MaxId = 0;
        mg1MaxId = 0;
        mg2MaxId = 0;
        mg3MaxId = 0;

        fg1MinId = 0;
        fg2MinId = 0;
        fg3MinId = 0;
        mg1MinId = 0;
        mg2MinId = 0;
        mg3MinId = 0;
    }
    // add는 일단 만든 자료구조에 넣으면 됨
    // 추가 후, mGrade 학년, mGender 성별 중에서 가장 높은 성적의 ID(동일한 성적 -> 더 높은 ID), 없으면 0 return
    // 추가한 성적이 제일 높다면 추가한 성적울 반환하기
    public int add(int mId, int mGrade, char mGender[], int mScore) {
        Who who = Who.No_One;
        int maxValue;
        int minValue;
        if (mGender[4] != '\u0000')
            switch (mGrade){
                case 1:
                    fg1Map.put(mId, mScore);
                    who = Who.Female_Grade1;
                    whoMap.put(mId,who);
                    maxValue = fg1Map.getOrDefault(fg1MaxId, -1);

                    if(mScore > maxValue)
                        fg1MaxId = mId;
                    else if((mScore == maxValue)&&(mId > fg1MaxId))
                        fg1MaxId = mId;

                    minValue = fg1Map.getOrDefault(fg1MinId, Integer.MAX_VALUE);
                    if(mScore < minValue)
                        fg1MinId = mId;
                    else if((mScore == minValue)&&(mId < fg1MinId))
                        fg1MinId = mId;

                    return fg1MaxId;
                case 2:
                    fg2Map.put(mId, mScore);
                    who = Who.Female_Grade2;
                    whoMap.put(mId,who);

                    maxValue = fg2Map.getOrDefault(fg2MaxId, -1);
                    if(mScore > maxValue)
                        fg2MaxId = mId;
                    else if((mScore == maxValue)&&(mId > fg2MaxId))
                        fg2MaxId = mId;

                    minValue = fg2Map.getOrDefault(fg2MinId, Integer.MAX_VALUE);
                    if(mScore < minValue)
                        fg2MinId = mId;
                    else if((mScore == minValue)&&(mId < fg2MinId))
                        fg2MinId = mId;

                    return fg2MaxId;
                case 3:
                    fg3Map.put(mId, mScore);
                    who = Who.Female_Grade3;
                    whoMap.put(mId,who);

                    maxValue = fg3Map.getOrDefault(fg3MaxId, -1);
                    if(mScore > maxValue)
                        fg3MaxId = mId;
                    else if((mScore == maxValue)&&(mId > fg3MaxId))
                        fg3MaxId = mId;

                    minValue = fg3Map.getOrDefault(fg3MinId, Integer.MAX_VALUE);
                    if(mScore < minValue)
                        fg3MinId = mId;
                    else if((mScore == minValue)&&(mId < fg3MinId))
                        fg3MinId = mId;

                    return fg3MaxId;
            }
        else
            switch (mGrade){
                case 1:
                    mg1Map.put(mId, mScore);
                    who = Who.Male_Grade1;
                    whoMap.put(mId,who);

                    maxValue = mg1Map.getOrDefault(mg1MaxId, -1);
                    if(mScore > maxValue)
                        mg1MaxId = mId;
                    else if((mScore == maxValue)&&(mId > mg1MaxId))
                        mg1MaxId = mId;

                    minValue = mg1Map.getOrDefault(mg1MinId, Integer.MAX_VALUE);
                    if(mScore < minValue)
                        mg1MinId = mId;
                    else if((mScore == minValue)&&(mId < mg1MinId))
                        mg1MinId = mId;

                    return mg1MaxId;
                case 2:
                    mg2Map.put(mId, mScore);
                    who = Who.Male_Grade2;
                    whoMap.put(mId,who);

                    maxValue = mg2Map.getOrDefault(mg2MaxId, -1);
                    if(mScore > maxValue)
                        mg2MaxId = mId;
                    else if((mScore == maxValue)&&(mId > mg2MaxId))
                        mg2MaxId = mId;

                    minValue = mg2Map.getOrDefault(mg2MinId, Integer.MAX_VALUE);
                    if(mScore < minValue)
                        mg2MinId = mId;
                    else if((mScore == minValue)&&(mId < mg2MinId))
                        mg2MinId = mId;

                    return mg2MaxId;
                case 3:
                    mg3Map.put(mId, mScore);
                    who = Who.Male_Grade3;
                    whoMap.put(mId,who);

                    maxValue = mg3Map.getOrDefault(mg3MaxId, -1);
                    if(mScore > maxValue)
                        mg3MaxId = mId;
                    else if((mScore == maxValue)&&(mId > mg3MaxId))
                        mg3MaxId = mId;

                    minValue = mg3Map.getOrDefault(mg3MinId, Integer.MAX_VALUE);
                    if(mScore < minValue)
                        mg3MinId = mId;
                    else if((mScore == minValue)&&(mId < mg3MinId))
                        mg3MinId = mId;

                    return mg3MaxId;
            }
        return 0;

    }
    // remove는 학생 ID가 일딴 1~10억이기 때문에 hash를 써야할 것 같음
    // 또한 배열은 index를 위한 ID가 10억이고 어떤 숫자가 올지 모르기 때문에 hash 사용이 편해보임.
    // 삭제 후 -> 동일 학년, 성별 중 가장 낮은 성적의 ID(동일한 성적 -> 더 낮은 ID), 없으면 0 return
    public int remove(int mId) {
        Who who;
        if(whoMap.containsKey(mId))
            who = whoMap.get(mId);
        else
            who = Who.No_One;

        switch (who){
            case No_One:
                return 0;
            case Female_Grade1:
                fg1Map.remove(mId);

                if(fg1Map.size() == 0){
                    fg1MaxId = 0;
                    fg1MinId = 0;
                    return 0;
                }
                if(mId == fg1MaxId)
                    findMax(Who.Female_Grade1);
                if(mId == fg1MinId)
                    findMin(Who.Female_Grade1);

                return fg1MinId;
            case Female_Grade2:
                fg2Map.remove(mId);
                if(fg2Map.size() == 0){
                    fg2MaxId = 0;
                    fg2MinId = 0;
                    return 0;
                }
                if(mId == fg2MaxId)
                    findMax(Who.Female_Grade2);
                if(mId == fg2MinId)
                    findMin(Who.Female_Grade2);

                return fg2MinId;
            case Female_Grade3:
                fg3Map.remove(mId);
                if(fg3Map.size() == 0){
                    fg3MaxId = 0;
                    fg3MinId = 0;
                    return 0;
                }
                if(mId == fg3MaxId)
                    findMax(Who.Female_Grade3);
                if(mId == fg3MinId)
                    findMin(Who.Female_Grade3);

                return fg3MinId;
            case Male_Grade1:
                mg1Map.remove(mId);
                if(mg1Map.size() == 0){
                    mg1MaxId = 0;
                    mg1MinId = 0;
                    return 0;
                }
                if(mId == mg1MaxId)
                    findMax(Who.Male_Grade1);
                if(mId == mg1MinId)
                    findMin(Who.Male_Grade1);

                return mg1MinId;
            case Male_Grade2:
                mg2Map.remove(mId);
                if(mg2Map.size() == 0){
                    mg2MaxId = 0;
                    mg2MinId = 0;
                    return 0;
                }
                if(mId == mg2MaxId)
                    findMax(Who.Male_Grade2);
                if(mId == mg2MinId)
                    findMin(Who.Male_Grade2);

                return mg2MinId;
            case Male_Grade3:
                mg3Map.remove(mId);
                if(mg3Map.size() == 0){
                    mg3MaxId = 0;
                    mg3MinId = 0;
                    return 0;
                }
                if(mId == mg3MaxId)
                    findMax(Who.Male_Grade3);
                if(mId == mg3MinId)
                    findMin(Who.Male_Grade3);

                return mg3MinId;
        }
        return 0;
    }

    // mGrade학년, mGender 성별 중에서 mScore 점 이상 가장 낮은 점수의 ID(동일한 성적 -> 더 낮은 ID)
    // 학년과 성별은 여러개가 올 수 있음.

    public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        int minValue = Integer.MAX_VALUE;
        int minId = 0;
        for(int grade : mGrade){
            if(mGenderCnt == 2) { // female, male
                if(grade == 1){
                    for(Map.Entry<Integer, Integer> entries : fg1Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                    for(Map.Entry<Integer, Integer> entries : mg1Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                }
                if (grade == 2) {
                    for (Map.Entry<Integer, Integer> entries : fg2Map.entrySet()) {
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                    for (Map.Entry<Integer, Integer> entries : mg2Map.entrySet()) {
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                }
                if (grade == 3) {
                    for (Map.Entry<Integer, Integer> entries : fg3Map.entrySet()) {
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                    for (Map.Entry<Integer, Integer> entries : mg3Map.entrySet()) {
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                }
            }

            else if (mGender[0][4] != '\u0000') {// female
                if(grade == 1)
                    for(Map.Entry<Integer, Integer> entries : fg1Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                if(grade == 2)
                    for(Map.Entry<Integer, Integer> entries : fg2Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                if(grade == 3)
                    for(Map.Entry<Integer, Integer> entries : fg3Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
            }
            else { // male
                if(grade == 1)
                    for(Map.Entry<Integer, Integer> entries : mg1Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                if(grade == 2)
                    for(Map.Entry<Integer, Integer> entries : mg2Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
                if(grade == 3)
                    for(Map.Entry<Integer, Integer> entries : mg3Map.entrySet()){
                        int compareValue = entries.getValue();
                        if(compareValue >= mScore){
                            if(compareValue < minValue){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                            else if((compareValue == minValue)&&(entries.getKey() < minId)){
                                minId = entries.getKey();
                                minValue = compareValue;
                            }
                        }
                    }
            }
        }
            return minId;
    }


    public void findMin(Who who){
        int minKey = Integer.MAX_VALUE;
        int minValue = Integer.MAX_VALUE;
        switch (who){
            case No_One:
                break;
            case Female_Grade1:
                for(int key : fg1Map.keySet()){
                    int compareValue = fg1Map.get(key);
                    if(compareValue < minValue){
                        minValue = compareValue;
                        minKey = key;
                    }
                    else if((compareValue == minValue) &&(key < minKey)){
                        minKey = key;
                    }
                }
                fg1MinId = minKey;
                break;
            case Female_Grade2:
                for(int key : fg2Map.keySet()){
                    int compareValue = fg2Map.get(key);
                    if(compareValue < minValue){
                        minValue = compareValue;
                        minKey = key;
                    }
                    else if((compareValue == minValue) &&(key < minKey)){
                        minKey = key;
                    }
                }
                fg2MinId = minKey;
                break;
            case Female_Grade3:
                for(int key : fg3Map.keySet()){
                    int compareValue = fg3Map.get(key);
                    if(compareValue < minValue){
                        minValue = compareValue;
                        minKey = key;
                    }
                    else if((compareValue == minValue) &&(key < minKey)){
                        minKey = key;
                    }
                }
                fg3MinId = minKey;
                break;
            case Male_Grade1:
                for(int key : mg1Map.keySet()){
                    int compareValue = mg1Map.get(key);
                    if(compareValue < minValue){
                        minValue = compareValue;
                        minKey = key;
                    }
                    else if((compareValue == minValue) &&(key < minKey)){
                        minKey = key;
                    }
                }
                mg1MinId = minKey;
                break;
            case Male_Grade2:
                for(int key : mg2Map.keySet()){
                    int compareValue = mg2Map.get(key);
                    if(compareValue < minValue){
                        minValue = compareValue;
                        minKey = key;
                    }
                    else if((compareValue == minValue) &&(key < minKey)){
                        minKey = key;
                    }
                }
                mg2MinId = minKey;
                break;
            case Male_Grade3:
                for(int key : mg3Map.keySet()){
                    int compareValue = mg3Map.get(key);
                    if(compareValue < minValue){
                        minValue = compareValue;
                        minKey = key;
                    }
                    else if((compareValue == minValue) &&(key < minKey)){
                        minKey = key;
                    }
                }
                mg3MinId = minKey;
                break;
        }
    }

    public void findMax(Who who){
        int maxKey = 0;
        int maxValue = -1;
        switch (who){
            case No_One:
                break;
            case Female_Grade1:
                for(int key : fg1Map.keySet()){
                    int compareValue = fg1Map.get(key);
                    if(compareValue > maxValue){
                        maxValue = compareValue;
                        maxKey = key;
                    }
                    else if((compareValue == maxValue) &&(key > maxKey)){
                        maxKey = key;
                    }
                }
                fg1MaxId = maxKey;
                break;
            case Female_Grade2:
                for(int key : fg2Map.keySet()){
                    int compareValue = fg2Map.get(key);
                    if(compareValue > maxValue){
                        maxValue = compareValue;
                        maxKey = key;
                    }
                    else if((compareValue == maxValue) &&(key > maxKey)){
                        maxKey = key;
                    }
                }
                fg2MaxId = maxKey;
                break;
            case Female_Grade3:
                for(int key : fg3Map.keySet()){
                    int compareValue = fg3Map.get(key);
                    if(compareValue > maxValue){
                        maxValue = compareValue;
                        maxKey = key;
                    }
                    else if((compareValue == maxValue) &&(key > maxKey)){
                        maxKey = key;
                    }
                }
                fg3MaxId = maxKey;
                break;
            case Male_Grade1:
                for(int key : mg1Map.keySet()){
                    int compareValue = mg1Map.get(key);
                    if(compareValue > maxValue){
                        maxValue = compareValue;
                        maxKey = key;
                    }
                    else if((compareValue == maxValue) &&(key > maxKey)){
                        maxKey = key;
                    }
                }
                mg1MaxId = maxKey;
                break;
            case Male_Grade2:
                for(int key : mg2Map.keySet()){
                    int compareValue = mg2Map.get(key);
                    if(compareValue > maxValue){
                        maxValue = compareValue;
                        maxKey = key;
                    }
                    else if((compareValue == maxValue) &&(key > maxKey)){
                        maxKey = key;
                    }
                }
                mg2MaxId = maxKey;
                break;
            case Male_Grade3:
                for(int key : mg3Map.keySet()){
                    int compareValue = mg3Map.get(key);
                    if(compareValue > maxValue){
                        maxValue = compareValue;
                        maxKey = key;
                    }
                    else if((compareValue == maxValue) &&(key > maxKey)){
                        maxKey = key;
                    }
                }
                mg3MaxId = maxKey;
                break;
        }
    }
}