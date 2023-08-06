# Git Basic - Interface

## 기본 인터페이스

> **git init** : Git의 사용(시작)을 알려주는 명령어. 현재 디렉토리에 Git의 저장소를 초기화 시킨다. .git이라는 디렉토리가 생기는데 버전정보를 담고있다.

git 명령어 뒤에 —help 를 주면 그 명령어의 상세 옵션을 알려준다.

> **git status** : 현재 디렉토리의 상태를 보여준다. 추가, 수정, 삭제 된 파일은 추적되지않고 무시하므로 변경을 원할 시 add 를 통하여 파일을 추가해줘야한다.

> **git add 파일이름** : 파일을 추가해준다. 프로젝트를 진행 시 임시로 필요한 파일들은 버전 관리를 하면 안 되기 때문에 add를 통하여 파일을 추가해 git이 변경된 파일을 찾아가게 한다. add를 하면 저장소(repository)에 commit을 하기위한 대기상태(stage)가 된다.

> **git config —global user.name 이름, git config —global user.email 이메일** : 버전들이 누가 만들었는지 알려주기 위하여 기본 정보를 세팅하는 명령어. 한 번 만 해주면 된다.

> **git commit** : Git의 버전을 생성한다.

- -a : 수정한 삭제한 파일은 자동으로 추가를 하여 add 과정을 스킵을 해도 된다. 한 번도 add 안 했을 시에 작동 안 하므로 적어도 1번의 버전관리(add)가 필요하다.

- -m : 메시지를 명령어에서 작성한다.

(ex : git commit -am “Message”) add 과정과 commit 메시지 입력을 동시에 해준다.

> **git log** : Git의 로그를 확인 할 수 있다. (commit 날짜, 이름, 이메일 등)

- -p : commit한 소스 사이에 차이점을 알 수 있게 해준다.

- commit의 ID : commit ID 이전의 메시지만 볼 수 있다.

- —branches —decorate —graph : branch 로그들의 최신 commit들을 표시하여 보여준다. graph 효과를 주어 작업을 가지형식으로 파악 할 수 있게해준다.

- (branchID 1)..(branchID 2) : branchID 1에는 없고 branchID 2에는 있는 로그를 알려준다.

> **git diff (commitID 1)..(commitID 2)** : 두 commit의 차이점을 알 수 있다. add를 사용하기 전에 git diff를 사용하면 commit하기 전 문제가 있는지 없는지 확인 할 수 있는 기회를 제공한다. add를 한 후에 diff 사용하면 코드가 보이지 않는다. commitID 대신 branchID도 넣을 수 있다.

> **git reset (commitID 1) —hard** : commitID 1 부분을 최신 버전의 상태로 되돌리고 그 이후에 버전은 삭제된다. 하지만 실제로는 버린게 아니라 남아있다. (눈에 보이지는 않는다.) **협업 시 저장소의 버전들을 올릴 때 공유 후 reset은 절대 하면 안 된다.** reset하는 commit은 공유 전에 내 컴퓨터에만 있는 버전을 대상으로 해야한다.

> **git revert (commitID 1)** : reset과 비슷하지만 최신 버전의 상태를 새로 만든다.

> **git branch 브랜치이름** : Branch를 만든다.

-d : branch를 삭제한다.

> **git checkout 브랜치이름**: Branch를 바꾼다.

-b : 브랜치를 만들고 checkout를 하는걸 한번에 할 수 있다.

> **git merge 브랜치(끌고올)**: 현재 Branch에 끌고올 Branch를 넣는다

> **git stash**: Working Directory의 변경사항을 감춘다.

- -list : stash된 리스트를 확인한다.

- -apply : 최신 statsh가 적용된다.

- -drop : 최신 stash를 삭제한다.

- -pop : 최신 stash가 apply되고 drop이 된다.(두 기능을 합친 것)

# 💭 Git의 원리

> Git은 파일을 저장 할 때 파일이름이 달라도 내용이 같으면 같은 오브젝트를 가르킨다.

- Git의 commit ID의 원리

Git은 우리가 저장한 파일의 내용을 SHA1이라는 해쉬 알고리즘을 통과시켜서 파일의 이름을 도출하고 나온 해쉬 값의 앞의 2글자를 떼서 디렉토리를 만들고 나머지 글자의 이름으로 파일을 만들어 정보를 저장한다. 그러기 때문에 같은 내용이면 똑같은 곳에 내용이(blob) 저장이 된다.

> Commit시 동작 원리.

Commit안에는 parent라는 값과 tree라는 값이 있다.

- parent : 이전의 commit의 정보
- tree : commit이 일어난 시점의 작업 디렉토리에 있는 파일의 이름과 파일이 담은 내용 사이의 정보들(blob들의 정보).

각각의 버전마다 다른 트리를 가리키고 있고 트리마다 파일의 이름과 그 파일의 내용이 담겨있다. 그렇기 때문에 우리는 버전에 적혀있는 tree를 통하여 commit시의 프로젝트 폴더의 상태를 알아낼 수 있다. (snapstop처럼 사진을 찍는 느낌이다.)

![CYO5D](https://user-images.githubusercontent.com/76617155/234971637-20987b1e-ca60-4a18-bad4-df45350bd2fe.jpg)

> Working Directory(작업 폴더) 동작 원리.

1. add하게 되면 index파일에(staging area) 등록된다.
2. commit하면 index파일에 등록된 내용들이 repository에 commit object로 저장이 되면서 파일이 저장된다.. 이 때 commit object 안에는 tree(blob 파일들), parent, commit 시간과 정보 등이 포함된다.

# 💭 Branch

> Branch의 뜻

작업을 하다가 필요에 의하여 작업이 분기되는 현상을 Branch를 만든다고 한다. 그래서 분기가 된 문서들은 새로운 Branch가 되고 원래 있었던 문서들은 원래의 Branch가 되어 여러 개의 Branch가 된다.

- 특별한 커스텀 기능을 추가하는데 원래 소스코드를 바꾸지 않아야 할 때
- 개발을 하다가 서버에 반영을 하기 위해 메인이 되는 작업과 테스트를 위한 작업을 분기 할 때

여러 경우에 Branch를 사용한다. 기본 Branch는 master이다.

예를 들어, master 브랜치의 a.txt파일의 내용이 aa이다. exam 브랜치를 만들고 exam 브랜치로 이동하여 a.txt파일의 내용을 aaa로 바꾸고 b.txt 파일을 만들면 exam 브랜치는 aaa내용의 a.txt파일과 b.txt 파일이 있지만 master 브랜치로 이동하면 aa의 내용을 담고있는 a.txt파일만 있다.

Git은 Head라는 파일을 가지고 있고 Head파일은 refs/heads/master같이 참조 파일이 있고 최근에 commit의 id값을 가지고 있어서 git은 log를 하면 Head 파일을 보고 ref로 된 master파일을 보고 master에 적혀있는 commit id값을 통해 최신 commit을 알아낼 수 있다. 그 이전 commit은 commit에 있는 parent를 통하여 탐색해 나갈 수 있다. 이를 통하여 Git에서 Branch는 단지 refs 디렉토리 안에 있는 파일을 의미한다. Branch를 checkout 하면 HEAD의 ref:refs/heads/master가 ref:refs/heads/exp로 바뀐다.

> merge의 두 가지 작동

![화면 캡처 2023-04-23 173246](https://user-images.githubusercontent.com/76617155/234971655-9f02e972-4e87-43ab-9313-80db21e33847.png)

(1. hotfix branch에서 작업 후 merge hotfix)

![화면 캡처 2023-04-23 173320](https://user-images.githubusercontent.com/76617155/234971665-194c6b84-0a2c-4f81-a236-7f53a5b39bec.png)

(2. master branch에서 hotfix를 합병 후 상태 - fast forward)

![화면 캡처 2023-04-23 173340](https://user-images.githubusercontent.com/76617155/234971673-42362672-1c2b-41fc-b792-bf802001d494.png)

![화면 캡처 2023-04-23 173407](https://user-images.githubusercontent.com/76617155/234971685-87310d4c-597d-4900-b789-1f83d8c40761.png)
(4. master branch에서 iss53를 합병 후 상태)

(3. 합병 된 이후 master branch에서 iss53 branch를 merge)

전제 : master branch에서 hotfix와 iss53 branch를 만든 뒤 commit을 하여 수정을 한 상태.

1. hotfix가 끝난 후 master branch로 checkout하여 hotfix를 merge한다.
2. master branch는 hotfix가 독립한 후에 아무런 commit을 하지 않았다. 이 경우 병합 할 때 master branch가 가르키는 commit을 hotfix가 가르키는 commit으로 빨리감기를 하는데 이것을 fast-forward 라고 한다. master와 hotfix는 같은 commit을 가르키기 때문에 별도의 commit을 생성하지 않는다.
3. hotfix branch를 지우고 iss53 branch로 이동하여 iss53을 편집한다. 작업 후 master branch로 돌아가 iss53 branch를 merge 한다. iss53이 master로부터 독립한 이후에 master에는 변화가 생겨서 별도의 commit을 가르키게 되었다. 이 경우 fast-forward를 할 수 있다.

4. iss53은 master와 공통의 조상을 찾아서 commitA(C4)와 commitB(C5)를 합치고 새로운 commit을 만든다. master와 hotfix의 단계가 없었으면 fast-forword가 되었을 것이다.

> Stash - 작업이 끝나지 않은 상태로 branch 이동

상황 : 작업을 하다가 다 끝나지 않은 상태로 다른 branch로 checkout해서 일을 해야하는 경우가 있다. 이 경우 기존의 작업을 commit하기는 애매하다. 하지만 commit을 하지 않고 checkout하여 다른 작업을 하면 다른 branch에서 수정하고 있을 때 중간에 멈춘 파일의 영향을 받는다. 이런 경우 stash를 이용하여 Working Directory의 변경사항을 감추고 HEAD 버전으로 이동하여 branch관리를 깔끔하게 할 수 있다.

- 주의 : stash는 추적이 가능한 파일에 작동이 되기 때문에 버전관리가 한 번은 되어있어야 한다.
