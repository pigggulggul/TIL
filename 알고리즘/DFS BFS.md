# DFS BFS

- DFS와 BFS는 탐색으로 많은 양의 데이터 중 원하는 데이터를 찾는 과정.

## DFS

- 깊이 우선 탐색이라고도 부르며 깊은 부분을 우선적으로 탐색하는 알고리즘
- 스택 자료구조(or 재귀 함수)를 이용한다.

- [ ] DFS 원리

1. 탐색 시작 노드를 스택에 삽입하여 방문 처리.
2. 스택의 최상단 노드에 방문하지 않은 인접한 노드가 하나라도 있으면 그 노드를 스택에 넣고 방문처리. 방문하지 않은 인접노드가 없으면 스택에서 최상단 노드를 꺼낸다.
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

### DFS의 특징

- 모든 노드를 방문하고자 하는 경우에 이 방법을 선택함
- 깊이 우선 탐색(DFS)이 너비 우선 탐색(BFS)보다 좀 더 간단함
- 검색 속도 자체는 너비 우선 탐색(BFS)에 비해서 좀 느림

## BFS

- 너비 우선 탐색이라고도 부르며 그래프에서 가까운 노드부터 우선적으로 탐색
- 큐 자료구조(혹은 재귀 함수)를 이용한다.
- ### BFS의 원리

1. **초기화** : 시작 노드를 큐에 넣기
2. **탐색 시작 :** 큐에서 노드 하나 꺼내기
3. **인접 노드 확인** : 꺼낸 노드에 인접한 모든 노드를 확인.
   - 방문하지 않은 노드라면, 해당 노드 방문 표시 후 큐에 넣기.
4. **반복** : 큐가 빌 때까지 2-3의 과정 반복

- ### BFS의 특징
- BFS는 시작 노드에서 가장 가까운 노드부터 차례대로 탐색하므로, 두 노드 사이의 최단 경로나 최소 간선의 수를 찾는 문제에 적합.
- BFS는 모든 노드를 방문하게 되므로, 그래프의 모든 연결 요소를 찾는 데에도 사용될 수 있음.
- BFS는 큐를 사용하기 때문에, 메모리 사용량이 DFS (Depth-First Search)에 비해 상대적으로 크다는 단점이 있음.

```java
//bfs 미로의 기본 형태
public static int bfs(int x, int y) {
		deqX.add(0);
		deqY.add(0);

		while(!deqX.isEmpty()&&!deqY.isEmpty()) {
			int first = deqX.peek();
			int second = deqY.peek();
			deqX.pop();
			deqY.pop();

			for(int i = 0 ; i < 4 ; i++) {
				int nx = first+dx[i];
				int ny = second+dy[i];

				if(nx<0 || nx>=N || ny<0|| ny>=M) {
					continue;
				}
				else if( Arr[x][y]==0) {
					continue;
				}
				if(Arr[nx][ny]==1) {
					Arr[nx][ny]=Arr[first][second]+1;
					deqX.add(nx);
					deqY.add(ny);
				}
			}
		}
		return Arr[N-1][M-1];
	}
```

### DFS와 BFS가 등장하는 유형

- 그래프의 모든 정점을 방문하는 경우 - DFS BFS
- 경로의 특징을 저장해둬야 하는 경우 - DFS (ex : 경로에 같은 숫자가 있으면 안된다, 경로마다 특징을 저장해둬야 한다 등)
- 최단거리 - BFS (ex : 미로찾기 )
- 검색 대상 그래프가 큰 경우 - DFS
- 검색 시작 지점으로부터 대상이 별로 멀지 않다면 - BFS
