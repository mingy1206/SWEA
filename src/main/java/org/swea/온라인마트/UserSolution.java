package org.swea.온라인마트;
import java.util.*;

class Item implements Comparable<Item>{
    int id, category, company;
    long price;

    public Item(int id, int category, int company, long price){
        this.id = id;
        this.category = category;
        this.company = company;
        this.price = price;
    }

    @Override
    public int compareTo(Item o){
        //오름차순
        if(this.price == o.price) return this.id - o.id; // 가격 같으면 id 비교 로직
        return (int) (this.price - o.price);
    }
}

class UserSolution {
    HashMap<Integer, Item> allItems; // 모든 item 정보
    PriorityQueue<Item>[][] sellingItems = new PriorityQueue[6][6];
    HashSet<Integer>[][] closeSelling = new HashSet[6][6];
    long[][] discounts = new long[6][6]; // 품목별 할인값

    public void init(){
        allItems = new HashMap<>();
        for(int i = 0; i <= 5; i++){
            for (int j = 0; j <= 5; j++){
                sellingItems[i][j] = new PriorityQueue<>();
                closeSelling[i][j] = new HashSet<>();
                discounts[i][j] = 0;
            }
        }
        return;
    }
    public int sell(int mID, int mCategory, int mCompany, int mPrice)
    {
        Item item = new Item(mID, mCategory, mCompany,mPrice);
        sellingItems[mCategory][mCompany].add(item);
        allItems.put(mID, item);
        return sellingItems[mCategory][mCompany].size() - closeSelling[mCategory][mCompany].size();
    }

    public int closeSale(int mID)
    {
        if(allItems.get(mID) == null) return -1; //이미 끝난 item이면
        Item item = allItems.get(mID);
        allItems.remove(mID);

        closeSelling[item.category][item.company].add(mID); // set에 넣어 lazy하게 관리
        return (int) (item.price - discounts[item.category][item.company]);
    }

    // 이거 진짜 핵심 코드 (할인을 lazy하게)
    // 어차피 값을 물어보는 것은 없음
    // 할인률을 계속 저장해서 그때 그때 모든 값의 변경이 아닌 n번의 할인으로 인해 price가 0보다 작아진 것들만
    // 제거해주기
    public void updateList(int category, int company){ // 할인 갱신으로 인한 pq정리
        PriorityQueue<Item> list = sellingItems[category][company];
        // pq를 사용하므로 작은 필수적으로 바꿔야하는 값들 먼저 판단
        // pq 사용하는 이유 1번
        while (!list.isEmpty() && list.peek().price <= discounts[category][company]){ // 현재 할인가보다 낮으면
            Item item = list.poll();
            allItems.remove(item.id);
            closeSelling[category][company].remove(item.id); //반영되었으므로 set에서 삭제
        }
    }
    public int discount(int mCategory, int mCompany, int mAmount)
    {
        discounts[mCategory][mCompany] += mAmount;
        updateList(mCategory, mAmount); // pq정리
        return sellingItems[mCategory][mCompany].size() - closeSelling[mCategory][mCompany].size();
    }
    public List<Item> search(int category, int company){
        List<Item> temp = new ArrayList<>();
        List<Item> realPrice = new ArrayList<>();

        PriorityQueue<Item> list = sellingItems[category][company];
        int cnt = 0;
        while (cnt < 5 && !list.isEmpty()){ //5개까지 가격순 가져오기, pq 사용하는 이유 2번
            Item item = list .poll();
            if(closeSelling[category][company].contains(item.id)){
                closeSelling[category][company].remove(item.id);
                continue;
            }
            temp.add(item);
            cnt++; // 삭제하지 못한 경우에 대해서 cnt++을 if 이후에 작성
        }
        // 회사&카테고리에서 가장 작은 값 반환
        for(Item item : temp){
            list.add(item);
            // 실제 할인율을 빼야 실제 가격이 나옴
            realPrice.add(new Item(item.id, item.category, item.company, item.price - discounts[category][company]));
        }
        return realPrice;
    }

    Solution.RESULT show(int mHow, int mCode)
    {
        Solution.RESULT res = new Solution.RESULT();
        List<Item> cheapItems = new ArrayList<>();
        if(mHow == 0){
            for(int i = 1; i <= 5; i++){
                for (int j = 1; j <= 5; j++){
                    //각 회사 카테고리끼리 피튀기는 살육전 시작
                    cheapItems.addAll(search(i,j));
                }
            }
        } else if (mHow == 1) {// 카테고리 기준
            for (int i = 1; i<=5; i++){
                cheapItems.addAll(search(mCode, i));
            }

        }else { // 회사 기준
            for (int i = 1; i<=5; i++){
                cheapItems.addAll(search(i, mCode));
            }
        }
        cheapItems.sort(null);
        res.cnt = Math.min(cheapItems.size(), 5);
        for(int i = 0; i < res.cnt; i++){
            res.IDs[i] = cheapItems.get(i).id;
        }
        return res;
    }

}