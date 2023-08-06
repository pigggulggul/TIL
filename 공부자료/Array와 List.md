# Array 와 List

## Array(배열)

> Array는 연속적인 메모리에서 같은 타입의 데이터들을 저장 할 수 있는 자료구조(Data Structure)이다. 인덱스가 활용 가능하다. 사이즈에 상관없이 접근 할 때 항상 같은 시간이 걸린다. 연속된 메모리 공간에 데이터들을 저장하기 때문에 cpu cache를 통해 같은 배열에 있는 다른 데이터에 접근하는 시간을 단축 할 수 있다.

> 일반적으로 배열은 크기를 정해놓고 거기에 값을 넣어 사용하지만 동적 배열의 경우는 크기가 변할 수 있어서 데이터를 더하거나 빼는 것이 가능하다. array list가 여기에 포함된다. 연관배열은 key-value pair들을 저장하는 ADT이다. map과 dictionary가 여기에 포함되고 같은 key를 가지는 pair는 최대 한 개 만 존재한다.

## ADT

ADT(Abstract Data Type)는 자료구조의 특징, 속성, 어떤 oprations이 있는지 설명을 하는것인데 무엇이고 무엇을 할 수 있는지는 다루지만 어떻게 작동하고 어떻게 동작하는지에 대해서는 작동하지 않는다.

Stack을 예로들면 Stack은 FILO 구조라 먼저 들어온 애는 나중에 나가고 나중에 들어온 애가 먼저 나간다. 그리고 push, pop라는 opration이 있다.

이런식으로 설명하는데 이것이 ADT를 설명한거라고 볼 수 있다. 왜냐면 내부적으로 어떻게 구현되어있는지 다루지 않았기 때문이다. Stack에 구현이 들어가는 순간 DS(데이터 구조)가 된다. 예를 들면 Stack에 구현이 들어갈려면 Array를 통하여 구체적으로 구현을 했을 때에 DS라고 한다.

추상적으로 표현 했을 때, 스택이 무엇이고 어떤기능이 있는지 설명하지만 어떻게 구현하는지에 대해선 설명하지 않는것을 ADT라한다

ADT 의 ex 2.) 월드컵에서 골을 넣은 선수를 관리하는 자료구조는?

- ADT로 설명

ADT : Alphabetical Ordered

- add(사람이름):사람 추가, getAllPlayer():알파벳 순서대로, remove(사람이름)

위에 쓴 자료구조를 어떻게 구현할지 설명하지 않았으니 ADT라고 할 수있다.

여기서 sorting을 어떻게 쓸지, remove()를 어떻게 구현할지, get, set을 어떻게 쓸지 고민하고 설계하면 그것을 DS라고 한다. Java로 따지면 Interface는 ADT이고 Class는 DS이다.

## List

> List는 값들을 저장하는 추상 자료형이고 순서를 가지며 중복을 허용한다. 예를들면 100가지 객관식 문제가 있고 정답이 있다. List에는 순서가있고 정답이 있고 정답이 중복이 될 수 있다. 이런 경우 List를 쓰는게 좋다. Set이나 Map을 사용하는게 더 적절한 상황이 아니라면 일반적으로 List를 사용한다.

### ArrayList

ArrayList는 처음에 특정 공간이 만들어지고 추가, 삭제, 탐색 등을 하는데 특정 공간보다 크기가 커지면 더 큰 공간을 만들고 원래 배열의 값을 Copy하고 원래 공간을 사용하지 않게된다. 인덱스 기능이 있어 getAt() 등을 쓸 때 인덱스로 바로 접근이 가능하다.

### Linked list

Linked list는 노드를 연결시키는 형태로 구현이 된다. 노드에는 값과 다음 노드를 가리키는 point나 ref가 있다. 처음 부분과 마지막 부분의 노드를 가르키는 head와 tail이 있다. ArrayList는 배열을 사용하기 때문에 연속적인 메모리 공간에 데이터를 저장하지만 Linked list는 노드 각각이 포인트를 통하여 다음 노드를 가르키는 형태라 메모리가 따로따로 저장이 된다. getAt() 등을 쓸 때 인덱스로 바로 접근 할 수 없기 때문에 head에서부터 찾아야하기 때문에 시간적 딜레이가 발생한다. Linked list는 여러 확장팩이 있다.

Circular linked list : tail과 head가 연결되어있다

Doubly linked list : 양방향으로 통로가 생겨서 next뿐만 아니라 prev링크도 있다.

> 함수들로 알아보는 List

- append(값) : 앞쪽부터 값을 넣는다.

- insert(param1, param2) : param1 위치에 param2 값을 넣는다. param1 위치에 값이 있으면 shift가 일어나서 시간이 딜레이 된다.

- getAt(param1) : param 위치의 값을 가져온다.

- contains(param1) : param1의 값이 있는지 true false로 확인한다.

- remove(param1) : ArrayList에서 param1의 값이 있으면 삭제한다. 지우면 shift가 발생하여 값이 당겨와진다.

- removeAt(param1) : param1의 위치에 있는 값을 삭제한다.

## ArrayList와 Linked List의 차이

- Array list는 배열을 사용하고 Linked list는 노드를 연결한다.
- Array list는 배열을 사용하여 데이터한테 접근 할 때 상수 시간에 접근 가능하지만 Linked list는 이동 시간이 발생하여 위치에 따라 다르다
- 삽입과 삭제시간은 둘 다 상수 시간이지만 ArrayList는 데이터 시프트시에 추가 시간이 발생한다. Linked List는 삽입, 삭제 위치까지 이동하는 시간이 발생한다.
- ArrayList는 배열 확장이 필요한 경우 배열에 복사하는 추가 시간이 발생한다.
- 검색 시 최악의 경우 리스트에 있는 아이템의 수만큼 확인해야한다.
- CPU cache는 Array list가 연속적인 메모리 할당이 되므로 사용이 가능하다.

근데 결국에는 Linked List는 거의 안 사용한다. FIFO같은 Stack 같은 기능도 ArrayDeque가 훨씬 좋다.
