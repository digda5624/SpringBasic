# SOLID

* SRP : 단일 책임 원칙
* OCP : 개방 - 폐쇄 원칙
* LSP : 리스코프 치환 원칙
* ISP : 인터페이스 분리 원칙
* DIP : 의존관계 역전 원칙

## SRP
하나의 클래스는 하나의 책임만을 가져야 한다.

하나의 책임이라는 것은 모호하다
* 클 수 있고, 작을 수 있다.
* 문맥과 상황에 따라 다르다.

그렇다면 책임이라는 기준이 뭘까?

중요한 기준은 "변경" => 변경이 있을 때 파급 효과가 많다면 잘 따르지 못한 것이다.

## OCP 
소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있다.

=> 인터페이스는 변경을 하지 않고 구현 클래스를 만드는 것은 OCP 원칙을 잘 지킨 것이라고 볼 수 있다.

하지만 개발의 특성상 OCP 원칙을 지키는 것은 정말 어려운 일이다. 예를 들어 보자

만약 MemberService에서 MemberRepository를 참조하고 사용하고 있다고 해보자 MemberRepository를 작성할 때,
우리는 다형성을 잘 지키고자 MemberRepository 라는 인터페이스를 만들었고 그 구현체 들로 JdbcRepository, JpaRepository를 작성했다.

하지만 여기서 문제상황이 발생하게 된다. MemberService는 다음과 같이 구성된다.

🖥 MemberService
```java
public class MemberService{
    
    // private final MemberRepositroy memberRepositroy = new JdbcRepository();
    private final MemberRepositroy memberRepositroy = new JpaRepository();
    
    // 정책을 변경하게 되면 클라이언트 코드 즉 MemberService 에 대한 코드가 수정이 되어야 하고 이것은 OCP 원칙을 어기는 것이다.
}
```
따라서 이문제를 해결하기 위해서

객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다. => 이로 인해 DI, IOC 가 필요하게 되는 것이다.

## LSP
리스코프 치환 원칙

프로그램 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀수 있어야 한다.

다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙이다.
인터페이스를 구현한 구현체를 믿고 사용하려면 이 원칙이 필요하다.

자동차의 엑셀에 대한 interface 규약은 앞으로 가는 것인데 구현체에서 뒤로가는 구현을 해놓은 경우 LSP 원칙을 지키지 않은 것이다.

## ISP
인터페이스 분리 원칙

인터페이스 여러개가 범용 인터페이스 하나보다 낫다.

예를 들면 회원이 주문을 하는 상황이 있다고 했을 때 주문과 회원에 대한 Service 인터페이스를 하나로 작성하고 모든 코드를
작성 MemberOrderService 로 작성할 수 있다. 하지만 Order과 Member 로 분리를 하게 되면 유지 보수가 좋아진다.

인터페이스가 명확해지고 대체 가능성이 높아진다.

## DIP
의존관계 역전 원칙

추상화에 의존해야지, 구체화에 의존하면 안된다.

예를 들어, MemberService 에서는 그저 인터페이스에 의존하고 Interface 의 내용만 알면 된다.

🖥 MemberService
```java
public class MemberService{
    
    // private final MemberRepositroy memberRepositroy = new JdbcRepository();
    private final MemberRepositroy memberRepositroy = new JpaRepository();
    
    // 정책을 변경하게 되면 클라이언트 코드 즉 MemberService 에 대한 코드가 수정이 되어야 하고 이것은 OCP 원칙을 어기는 것이다.
    // 또한 MemberService는 MemberRepository 라는 인터페이스에도 의존하면서 특정 Repo 를 알아야 하므로 특정 구현체
    // 에도 의존하고 있다 따라서 DIP 원칙을 위배한다.
}
```

## 해당 SOLID 를 지키려면...
구성 코드와 실행 코드의 분리 즉 실행을 담당하는 코드와 역할을 배분시키는 코드를 분리 해야 한다.

예를 들자면 배우들과 감독을 분리해야한다. 배우들이 상대 배우들의 역할은 배분시키는 것은 옳지 않다.

위의 내용에서는 config 패키지를 통해서 분리작업이 일어났으며 실 사용 부분들은 나머지 클래스들에 속한다.

실사용 부분들에는 interface를 사용한 코드가 좋고 interface를 사용하지 않아도 실행 코드 쪽에서 new 하여 객체를 새로이 생성하는
행위는 옳지 않다. new를 하고 객체의 참조를 넣어주는 것은 config 에서 담당한다.

## spring은...
spring이 없었다면 개발자들은 config 정보들을 통해서 어플리케이션 실행을 config.메서드() 를 통해 실행 코드의 객체간 참조를
넣어주게 될 것이다. 하지만 이것은 상당히 번거로운 작업임은 틀림이 없다.

따라서 spring은 개발자가 실행의 흐름을 제어하지 않도록 자신들이 실행 코드의 객체간 참조를 넣어주게 된다.

이를 제어의 역전(IOC)라고 부른다. 따라서 spring은 IOC 컨테이너 이다.

또한, 이런 객체간의 참조를 주입시키는 것을 특히 DI 라고 하고 요즘에는 spring을 DI 컨테이너 라고 많이 부르게 된다.

## spring의 싱글톤
싱글톤은 자기 자신에 대한 인스턴스를 생성하고, getInstance()를 통해서 공유 객체를 반환하는 패턴이다.

따라서 이 공유객체를 관리하는 데에서 문제가 생길 수 있다. 공유 객체 내에 자원에 대해서 주의를 해야 한다.
공유 객체에 대해서 stateless 한 상태를 유지해야 하고, thread 간의 동시성을 고려하여 stack 영역의 데이터를 건드리도록
프로그래머가 신경을 써야 한다.

