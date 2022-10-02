package com.studySpringBasic.springBasic.singleton;

public class StatefulService {

    // 여기서 price 는 공유 자원이 되겠다. 즉 동시성 문제가 터질 수 있고, 현재 stateless 하지 않다
    // 또한 heap 메모리 영역이여서 thread 끼리 공유가 된다.
    private int price;

    public void order(String name, int price){

        System.out.println("name = " + name + "price = " + price);
        this.price = price;
        // 따라서 공유 필드를 만들지 않고 price 를 스택 영역에서 관리를 해야한다는 점을 잊지말자
    }

    public int getPrice(){
        return price;
    }
}
