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

또한 @Bean 을 통해서 주입을 하는 경우 생성자 level 에서 여러개의 싱글톤 객체가 생성된다고 생각 할 수 있는데,
spring은 @Configuration 이 붙어 있는 클래스를 CGLIB 객체로 새로 만들어(프록시 활용) 다른 동작을 하게 한다.

수도 코드로 보면 다음과 같아진다.

🖥 재정의된 Configuration class 의 내부 로직
```java
@Bean
public MemberRepository memberRepository() {
        if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) { 
            return 스프링 컨테이너에서 찾아서 반환;
        } else { 
            //스프링 컨테이너에 없으면
        기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록 return 반환
        } 
}
```

따라서 @Configuration 을 적용하지 않고 작성을 하게 되면 싱글톤을 보장하지 않게 된다는 점을 유의하자.

실제로도 각 @Bean에 넣었던 내용들이 전부 출력되는 것을 알 수 있다.

## @ComponentScan, @Component
간단하게 말해 기존 @Configuration + @Bean 조합을 => @Component + @ComponentScan 조합으로 변경한다.

클래스 단에 @Component 어노테이션만 달아주게 되면 된다. @ComponentScan 대상을 정할 수 있다.
지정을 하지 않게 되면 현재 패키지가 시작위치가 되고 하위 패키지를 뒤지게 된다.

@Component, @Controller, @Service, @Repository, @Configuration 은 내부적으로 @Component 가 붙어있다.<br>
참고로 이 어노테이션이 특정 어노테이션을 들고 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, Spring 이 지원하는 기능이다.

또한 @Repository 는 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환시켜준다.

필드의 의존관계에 대해서 주입을 하게 되며 주입관련 내용으로는 생성자, 필드, setter 주입이 있다.

## 같은 빈이름으로 등록 -> 충돌
Conflict 가 난다.

자동 빈등록(ComponentScan) + 수동 빈등록(@Bean) == overriding이 되어 버린다.
> Overriding bean definition for bean 'memoryMemberRepository' with a different definition

spring Boot 기준으로는 override 하지 않고 팅겨버린다.

## spring 의 의존관계 주입 life-cycle
spring은 용빼는 재주가 있는 것이 아니다. 결국 spring-Container에 등록을 할 때 객체를 올리고 의존 관계를 주입하게 된다.

1. 객체 생성 후 (스프링 컨테이너에 등록 후)
2. 의존 관계 주입

생성자 주입의 경우 1, 2번이 동시에 일어나게 된다. 필드 주입,  수정자 주입의 경우 1번후 2번이 일어나게 된다

생성자 주입을 선택해라!
1. 의존관계를 변경할 일이 없다. 불변해야한다. (대부분)
2. Test 코드와의 관련성 Test 코드를 작성할 때 순수한 java 코드로 Test를 작성하고 싶다면... 후에 Mock 객체를 사용할 때도 생성자를 통한 test
target 설정이 편리하다. 물론 injectMock을 쓸때도 마찬가지
3. final 키워드를 통한 컴파일 오류 잡아내기가 가능해진다.

## @Qualifier tips
기존에는 Qualifier(value = "") 형식으로 사용했지만 컴파일 시점에 매칭을 잡아내기 힘들다는 점이 있다.
만약 "mainDataBase" string 에 오타가 있다면? 따라서 spring에서 제공하는 어노테이션 커스텀을 사용하여 쉽게 해보자

🖥 @Qualifier custom
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
```

## Collection 을 이용한 spring의 전략패턴 제공
동일 타입의 bean 객체를 가져오려고 하면 오류가 난다. NoUniqueBeanDefinitionException 기본적으로 어플리케이션에서
전략을 동적으로 교체하거나, 여러개를 가질 일이 거의 없기 때문에 대부분의 로직은 @Primary 혹은 @Qualifier 로 해결이 가능하다.
하지만 만약 여러개의 전략이 필요한 경우라면??

🖥 여러가지 전략 패턴을 지원하는 spring
```java
public static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, Strategy strategy){
            DiscountPolicy discountPolicy = policyMap.get(strategy.getPolicy());
            return discountPolicy.discount(member, price);
        }
}
```
필드로 다양한 DiscountPolicy 전략을 가질 수 있는 것을 확인해 볼 수 있다.

추가적으로 Strategy라는 Enum 타입 선언으로 깔끔하게 코드를 뽑아 낼 수 있다.

🖥 Strategy Enum
```java
@Getter
@AllArgsConstructor
public enum Strategy {
    FIX_POLICY("fixDiscountPolicy"),
    RATE_POLICY("rateDiscountPolicy");

    String policy;
}
```

## spring 앱을 개발할 때 나오는 개괄적인 bean 종류
1. 업무 로직 빈 - 웹을 지원하는 컨트롤러, 핵심 비지지스 로직이 있는 서비스, 리포지토리 등이 모두 업무 로직
2. 기술 지원 빈 - 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용
3. 비즈니스 로직이 다양한 전략을 가져야 할 경우

1번은 대부분 자동 빈등록, 2번은 기술을 명확하게 들어내야할 수록 편하기 때문에 수동빈 등록 권장
3번의 경우도 마찬가지로 전략에 대한 Config를 따로 빼내어서 전략들을 개괄적으로 보여주는 것이 편리하다.

🖥 전략에 대한 Config 명확하게
```java
@Configuration
public class DiscountPolicyConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
        return new RateDiscountPolicy();
    }
    @Bean
    public DiscountPolicy fixDiscountPolicy() {
        return new FixDiscountPolicy();
    }
}
```

## 빈 생명주기 콜백
1. 데이터베이스 커넥션은 어떻게 생성이 되는 걸까? => 데이터베이스 커넥션 풀의 경우에 미리 커넥션을 만들고 할당해 놓는다
2. 네트워크 소캣을 미리 열어둘 수 있다면?
3. 어플리케이션 종료 시점에 사용하던 resources 들을 어떻게 release 시킬까? 

먼저 spring bean은 객체를 생성 -> 의존관계 주입의 순서를 따른다.

🖥 빈 생성과 의존관계 주입
```java
@Configuration
static class BeanLifeCycle {

    @Bean
    public NetworkClient networkClient(){
        // spring 은 다음과 같은 의존관계 주입 순서를 가진다.

        // 1. 객체 생성
        NetworkClient networkClient = new NetworkClient();

        // 2. 의존관계 주입 완료 여기서는 setter 로 퉁치자 물론 예시가 부정확하지만 대략 이런 느낌이라고 생각하자.
        networkClient.setUrl("http://digda5624/test");
        return networkClient;
        }
}
```
스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료
따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.

=> 이것을 어떻게 알 것인가?

스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능 제공(후에 빈 후처리기 라고 함)
또한 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다. 

## 용어 정리 해보기 
여기서 참 재밌는 메서드 명이 나온다. 예전에 봤을 때는 몰랐던 afterPropertiesSet 이 그것이다.

AfterPropertiesSet은 말 그대로 의존관계 주입이후를 의미하게 된다.

코드 level 에서 살펴보자

🖥 AfterPropertiesSet, Destroy
```java
public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
        disconnect();
    }

    // spring bean 이 생성된 이후
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
}
```

사실 @PostConstruct, @PreDestroy 쓰면 된다.

추가 학습할 것 AutoClosable

## 빈 스코프
1. 싱글톤
2. 프로토타입 => 초기화 메서드는 호출 하지만 종료 콜백은 호출되지 않음(@Destroy 호출 X)
3. request (Spring Web 관련)

2, 3 의 경우 주입 시점에 