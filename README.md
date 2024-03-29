# java-chess
## 1단계

### 도메인 로직
-[x] 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 입력
-[x] 게임 진행 명령어 입력
  -[x] start는 최초 한번만 입력 가능
  -[x] 게임시작: start 종료: end

### 출력
-[x] 체스판을 출력한다

## 2단계

### 도메인 로직
-[x] 기물 종류에 맞는 이동 규칙을 갖는다.
  -[x] 해당 위치로 이동할 수 있는지 검증한다.
  -[x] 나이트를 제외한 기물들은 이동 경로에 다른 기물이 있는지 확인한다.
  -[x] 출발위치와 도착위치를 통해서 어느 방향으로 이동하는 지 알 수 있다.
  
### 입력
-[x] move 명령어로 출발위치와 도착위치를 받는다.
-[x] end 명령어를 입력할 때까지 명령어를 입력 받는다.

### 출력
-[x] 최신화된 체스판을 출력한다

## 3단계

### 도메인 로직
- [x] 백팀과 흑팀이 번갈아 가며 턴을 진행한다.
  - [x] 첫 번째 턴은 백팀이 진행한다.
- [x] 게임이 종료되면 각 팀의 점수를 계산한다.
  - [x] king이 잡히면 게임이 종료된다.
  - [x] queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
  - [x] pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
  - [x] king은 잡히면 게임이 종료되기 때문에 점수가 없다.

### 출력
- [x] 각 팀의 점수를 출력한다.
