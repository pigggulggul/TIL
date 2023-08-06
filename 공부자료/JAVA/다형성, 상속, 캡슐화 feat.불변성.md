## 다형성? 상속? 캡슐화?

자바를 배울 때 다형성과 상속은 강조되지만 캡슐화는 주목받지 못한다. 상태를 내면화해서 다른 컴포넌트로부터 숨기며 안전하게 디자인된 API로만 상태를 변경할 수 있게 하는것이 캡슐화의 기본 개념이자 복잡한 정보 시스템을 디자인하고 구현하는 핵심이다.

자바 세계에서는 제대로된 캡슐화된 시스템 구축에 대한 권장 사례가 제대로 전파되지 않고 있다.

getter, setter를 무분별하게 사용하고있다. 그러다보니 모순된 값이 일어나는 에러들이 생기고 이 경우 발견하기 매우 어렵게 된다. 우리는 불변성에서 해법을 찾을 수 있다. 불변객체는 재할당이 가능하지만, 한 번 할당하면 내부 데이터를 변경 할 수 없는 객체이다. String이 대표적인 예이다. 객체가 불변임을 보장하고 객체를 생성하는 시점에 상태의 무결성을 검사 할 수 있으면 시스템은 모순된 상태가 되지 않을 것이다. 대부분 자바 프레임워크는 불변성을 제대로 다루지 못한다는 점을 고려한다면 가변성을 최소화해야 한다. 올바르게 구현한 팩토리 메서드와 빌더 패턴을 이용하면 가변 상태를 최소화 할 수 있다. 그러므로 세터를 자동으로 생성하지 말고 생각해보자. 세터가 정말 필요한가. 필요하다면 변질 방지 계층을 사용하는 것을 고려하자.

- [ ] String은 가변객체?불변객체?

a에서 ab로 값을 바꾸는 경우 가변객체라 생각 할 수 있지만 String값이 참조하고있는 a값이 ab로 변경되는 것이 아니라 “ab”라는 새로운 객체를 만들고 그 객체를 String이 참조하는 구조이므로 불변객체이다.

- [ ] 가변객체인 클래스

```java
class MutablePerson {
   public int age;
   public int name;

   public MutablePerson(int age, int name) {
    	this.age = age;
        this.name = name;
    }
}
```

외부에서 age나 name을 변경 할 수 있기 때문에 가변객체이다.

- [ ] 불변객체인 클래스

```java
class ImmutablePerson {
    private final int age;
    private final int name;

    public ImmutablePerson(int age, int name) {
    	this.age = age;
        this.name = name;
    }
}
```

위와 같이 만들면 외부에서 값을 수정할 수 없기 때문에 불변객체가 된다.

(final 변수이므로 당연히 Setter 메서드를 작성 할 수 없다.)

## 불변객체의 장단점

장점

- 객체가 한 번 생성되어서 그게 변하지 않는다면 트랜잭션(DB의 상태를 변화시키는 작업의 단위)내에서 그 객체가 변하지 않기에 객체에 대한 신뢰도가 높아진다.
- 생성자, 접근메소드에 대한 방어 복사가 필요없다.
- 멀티스레드 환경에서 동기화 처리없이 객체를 공유 할 수 있다.

단점

- 객체가 가지는 값마다 새로운 객체가 필요하다.

## 불변객체 만들기

불변객체를 만드는 기본적인 아이디어는 필드에 final을 사용하고, Setter를 구현하지 않는 것이다.

모두 원시 타입일 경우에 가능하고 참조 타입이면 추가적인 작업이 필요하다.

- [ ] 원시타입에서의 불변객체 만들기 (변경전)

```java
public class BaseObject {

    private int value;

    public BaseObject(final int value) {
        this.value = value;
    }

    public void setValue(int newValue) {
    	this.value = newValue;
    }

    // getter는 생략 했음
}
```

- [ ] 원시타입에서의 불변객체 만들기 (변경후)

```java
public class BaseObject {

    private final int value;

    public BaseObject(final int value) {
        this.value = value;
    }

    // getter는 생략 했음
}
```

위 경우 value 값을 변경하려면 재할당하는 방법밖에 없다.

- [ ] 참조타입이 있는경우 (일반 객체인 경우)

```java
public class Animal {

    private final Age age;

    public Animal(final Age age) {
        this.age = age;
    }

    // getter
}

class Age {

    private final int value;

    public Age(final int value) {
        this.value = value;
    }

    // getter
}
```

위를 보면 참조 변수도 불변 객체이어야 한다.

- [ ] 참조타입이 있는경우 (Array인 경우)

```java
public class ArrayObject {

    private final int[] array;

    public ArrayObject(final int[] array) {
        this.array = Arrays.copyOf(array,array.length);
    }

    public int[] getArray() {
        return (array == null) ? null : array.clone();
    }
}
```

배열의 경우 생성자에서 배열을 받아 copy하여 저장하고 getter를 clone을 반환하도록 한다. 배열을 그대로 참조하거나, 그대로 반환할 경우 외부에서 배열 내부값을 변경시킬 수 있기 때문에, clone을 반환하여 외부에서 값을 변경하지 못하게 한다. 원시객체(int[] 등)가 아니라 Animal[]같은 형태면 해당 객체는 불변객체여야 한다.

- [ ] 참조타입이 있는경우 (List인 경우)

```java
public class ListObject {

    private final List<Animal> animals;

    public ListObject(final List<Animal> animals) {
        this.animals = new ArrayList<>(animals);
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
}
public static void main(String[] args) {
    List<Animal> animals = new ArrayList<>();
    animals.add(new Animal(new Age(1)));

    ListObject listObject = new ListObject(animals);

    for (Animal animal : listObject.getAnimals()) {
        System.out.print(animal.getAge().getValue());
    }
    System.out.println();
    // Output: 1

    animals.add(new Animal(new Age(2))); // List인 animals에는 추가되지만 listObject의 List에는 추가되지 않는다

    for (Animal animal : listObject.getAnimals()) {
        System.out.print(animal.getAge().getValue());
    }
    System.out.println();
    // Output: 1
}
```
