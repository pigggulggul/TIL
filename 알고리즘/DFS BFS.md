DFS와 BFS는 탐색으로 많은 양의 데이터 중에서 원하는 데이터를 찾는 과정을 말한다. 다양한 알고리즘에서 특정 조건에 맞는 데이터가 존재하는지, 존재한다면 어디 존재하는지 찾고자하는 목적으로 다양한 탐색 알고리즘을 사용한다. 대표적인 그래프 탐색 알고리즘으로 DFS/BFS가 있다.

## 스택자료구조

스택 자료구조는 먼저 들어온 데이터가 나중에 나가는 형식(선입후출)의 자료구조이다.

**입구와 출구가 동일한 형태로 스택**을 시각화 할 수 있다. (박스쌓기)

자바의 Stack<Integer> s = new Stack<>(); 등과 같은 Stack과 push, pop, peek 기능을 써서 구현한다. 괄호 문자열 짝짓기처럼 push와 pop을 다루는 문제가 많으며 이 때 Empty를 통하여 구분을 잘 해야한다. 짝짓기로 YES, NO 구분 문제시 함수를 이용하여 만든다.

## 큐 자료구조

먼저 들어온 데이터가 먼저 나가는 형식(선입선출)의 자료구조이다.

**큐는 입구와 출구가 모두 뚫려있는 터널**같은 형태로 시각화 할 수 있다.

Queue<Integer> q = new LinkedList<>(); 큐를 사용 할 때에 LinkedList내에 구현되어 있는 Q를 사용하는게 좋다. q.offer(숫자) 삽입, q.poll(숫자) 반환 등으로 확인 할 수 있다.

## 재귀 함수

재귀 함수란 **자기 자신을 다시 호출하는 함수**를 의미한다.

재귀함수는 별다른 설정을 하지 않고 함수를 재귀적으로 호출하면 오류메시지가 나올 수 있다.

재귀함수가 재귀적으로 호출되면 스택프레임에 함수가 반복적으로 쌓여서 마지막으로 호출된 함수가 처리된 이후에 그 함수를 불렀던 함수를 처리하는 방식이다.

이런 형태가 스택과 같은 형태로 동작한다고 할 수 있다.

스택 자료구조 안에 함수에 대한 정보가 차례대로 담겨서 컴퓨터 메모리에 올라간다고 이해할 수 있다.

무작정 함수가 종료되지않고 재귀적으로 호출만하면 메모리가 차서 문제가 발생할 수 있어 재귀 깊이 제한을 걸어둔다.

**재귀 함수를 문제 풀이에서 사용할 때는 재귀 함수의 종료 조건을 반드시 명시해야한다.**

종료 조건을 제대로 명시하지 않으면 함수가 무한히 호출될 수 있다.

재귀함수를 잘 활용하면 복잡한 알고리즘을 간결하게 작성할 수 있다.

재귀 함수는 반복문을 이용하여 동일한 기능을 구현할 수 잇다.

컴퓨터가 함수를 연속적으로 호출하면 메모리 내부의 스택 프레임에 쌓여서 구현상 **스택 대신에 재귀함수를 이용하여 작성** 할 수 있다.

## DFS

DFS는 깊이 우선 탐색이라고도 부르며 깊은 부분을 우선적으로 탐색하는 알고리즘이다

DFS는 스택 자료구조(혹은 재귀 함수)를 이용한다.

1. 탐색 시작 노드를 스택에 삽입하여 방문 처리를 한다.
2. 스택의 최상단 노드에 방문하지 않은 인접한 노드가 하나라도 있으면 그 노드를 스택에 넣고 방문처리를 한다. 방문하지 않은 인접노드가 없으면 스택에서 최상단 노드를 꺼낸다.
3. 더 이상 2번의 과정을 수행할 수 없을 때까지 반복한다.

DFS 깊이 우선 탐색의 코드

```java
import java.util.*;

public class Main {
	public static boolean[] visited = new boolean[9];
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	//ArrayList는 특정 index원소에 상수시간이 소요되므로 일반적인 배열보다 ArrayList가 좋다.
	public static void main(String[] args) {
		// 그래프 초기화
		for (int i = 0; i < 9; i++) {
			graph.add(new ArrayList<Integer>());
			}
		// 노드 1에 연결된 노드 정보 저장
    graph.get(1).add(2);
    graph.get(1).add(3);
    graph.get(1).add(8);

    // 노드 2에 연결된 노드 정보 저장
    graph.get(2).add(1);
    graph.get(2).add(7);

    // 노드 3에 연결된 노드 정보 저장
    graph.get(3).add(1);
    graph.get(3).add(4);
    graph.get(3).add(5);

    // 노드 4에 연결된 노드 정보 저장
    graph.get(4).add(3);
    graph.get(4).add(5);

    // 노드 5에 연결된 노드 정보 저장
    graph.get(5).add(3);
    graph.get(5).add(4);

    // 노드 6에 연결된 노드 정보 저장
    graph.get(6).add(7);

    // 노드 7에 연결된 노드 정보 저장
    graph.get(7).add(2);
    graph.get(7).add(6);
    graph.get(7).add(8);

    // 노드 8에 연결된 노드 정보 저장
    graph.get(8).add(1);
    graph.get(8).add(7);

    dfs(1);
}

public static void dfs(int x) {
	visited[x] = true;
	System.out.print(x + " ");
	for(int i = 0 ; i < graph.get(x).size();i++) {
		int y = graph.get(x).get(i);
		if(!visited[y]) dfs(y);
	}
}

```

}

## BFS

BFS는 너비 우선 탐색이라고도 부르며 그래프에서 가까운 노드부터 우선적으로 탐색하는 알고리즘이다

BFS는 큐 자료구조(혹은 재귀 함수)를 이용한다.

1. 탐색 시작 노드를 큐에 삽입하여 방문 처리를 한다.
2. 큐에서 노드를 꺼내고 꺼낸 노드의 인접 노드 중 방문하지 않은 노드를 모두 큐에 넣고 방문 처리를 수행한다.
3. 더 이상 2번의 과정을 수행할 수 없을 때까지 반복한다.

BFS 알고리즘은 특정 조건(간선의 비용이 동일하다 등)에서 최단경로 문제를 해결하기 위한 목적으로 효과적으로 사용될 수 있다.

```java
import java.util.*;
public class Main {
	public static boolean[] visited = new boolean[9];
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	//ArrayList는 특정 index원소에 상수시간이 소요되므로 일반적인 배열보다 ArrayList가 좋다.
	public static void main(String[] args) {
		// 그래프 초기화
        for (int i = 0; i < 9; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 노드 1에 연결된 노드 정보 저장
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(1).add(8);

        // 노드 2에 연결된 노드 정보 저장
        graph.get(2).add(1);
        graph.get(2).add(7);

        // 노드 3에 연결된 노드 정보 저장
        graph.get(3).add(1);
        graph.get(3).add(4);
        graph.get(3).add(5);

        // 노드 4에 연결된 노드 정보 저장
        graph.get(4).add(3);
        graph.get(4).add(5);

        // 노드 5에 연결된 노드 정보 저장
        graph.get(5).add(3);
        graph.get(5).add(4);

        // 노드 6에 연결된 노드 정보 저장
        graph.get(6).add(7);

        // 노드 7에 연결된 노드 정보 저장
        graph.get(7).add(2);
        graph.get(7).add(6);
        graph.get(7).add(8);

        // 노드 8에 연결된 노드 정보 저장
        graph.get(8).add(1);
        graph.get(8).add(7);

        bfs(1);
	}

	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start]=true;
		while(!q.isEmpty()) {
			int x = q.poll();
			System.out.print(x + " ");
			for(int i = 0 ; i < graph.get(x).size();i++) {
				int y = graph.get(x).get(i);
				if(!visited[y]) {
					q.offer(y);
					visited[y]=true;
				}
			}
		}
	}
}
```
