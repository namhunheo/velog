이번 프로젝트에서 심부름 웹애플리케이션을 구현하기 위하여 서버가 필요한데, 이전 오목 게임 개발에서는 LOCALHOST로 톰캣서버를 돌려 서버를 사용했지만, 이번에는 AWS EC2를 사용하여 진행하고자 한다.

기존에 계정 생성은 해놓았던 것이 있어서 이후 진행부터 작성해보았다.

AWS EC2 생성 → 서버 접속 → MariaDB 설치 → 외부/내부에서 DB 연결

AWS EC2
EC2란?
EC2 (Elastic Compute Cloud): AWS에서 제공하는 가상 서버 서비스
용도: 웹 서버, 앱 서버, 배치 작업 등 컴퓨팅 자원 실행
특징
1. 필요할 때 빠르게 생성/종료
2. CPU·메모리·스토리지 사양 선택 가능
3. 사용한 만큼 과금
운영체제: Linux, Windows 등 선택

우선, 계정 생성까지의 과정을 진행할 것.
프리티어로 진행했기 때문에 인스턴스 서버 생성 시, 기본 옵션으로 진행하였음.

1. EC2 인스턴스 생성 (콘솔 기준)
기본 설정
AMI: Amazon Linux 2 (권장)
인스턴스 타입: t2.micro (프리티어)
키 페어: 새로 생성 (mykey.pem)
보안 그룹
SSH: 22 → 내 IP
MariaDB: 3306 → 내 IP (또는 테스트용 0.0.0.0/0 ❌권장X)
⚠️ 3306 포트는 반드시 IP 제한할 것!
2. 웹페이지 CLI 통하여 서버 연결


이후 위의 인스턴스 서버 항목 중 우측 상단의 연결을 누르고,
id, ip주소, 사용자 이름을 확인해주고 연결.

그럼 위와 같이 웹페이지에서 CLI 사용 가능
3. Powershell 통하여 EC2 서버 연결
나는 Powershell을 통한 연결도 해보겠슨!

1) C:\Users\fzaca\keys\<파일명.pem> 처럼 .pem 파일을 해당 경로에 다운로드 받아서 저장하기



2) PowerShell 실행



해당 경로 찾아가기
3) 키 파일 권한 설정
.pem 키 파일을 SSH가 신뢰하도록 권한을 잠그는 작업

Windows에서는 Linux처럼 chmod 대신 icacls를 사용
icacls C:\Users\fzaca\keys\mykey.pem /inheritance:r
icacls C:\Users\fzaca\keys\mykey.pem /grant:r "%USERNAME%:R"


4) EC2 접속 명령어

아래의 명령어를 입력하여 EC2 서버 접속
ssh -i C:\Users\fzaca\keys\mykey.pem ec2-user@<EC2_PUBLIC_IP>


5) 접속 시 경고 메시지 (정상)

처음 접속 시 아래 메시지가 나옵니다:
Are you sure you want to continue connecting (yes/no)?



YES 눌러주면 아래와 같이 EC2 서버가 켜짐
4. MariaDB 설치 및 연결
근데 헷갈려하면 안되는거!!
지금까지 한거 DB 아니고 서버 연결한거임!


이 현재 상태는 Linux 서버 쉘 (터미널)이고, DB 콘솔 아님!!
SQL로 CRUD 하려면 필요한 단계
1️⃣ MariaDB(MySQL) 서버가 실행 중이어야 함
2️⃣ MariaDB 클라이언트로 접속해야 함
3️⃣ 그 안에서 SQL 실행

우선,

1) MariaDB 설치
sudo yum install mariadb-server -y
2) MariaDB 실행 및 확인
실행
sudo systemctl start mariadb
확인
sudo systemctl status mariadb
3) DB 콘솔로 접속
mysql -u root -p


비밀번호 입력 후, 성공하면 프롬프트가 바뀜.
MariaDB [(none)]>
👉 이 상태가 SQL 입력 가능 상태
패키지 설치 여부 확인
rpm -qa | grep -i mariadb


실행 중인지 확인
sudo systemctl status mariadb
active (running) → 실행 중 ✅
inactive / not found → 미설치 또는 미실행 ❌

화면	의미	입력 가능
$	Linux 쉘	리눅스 명령
MariaDB>	DB 콘솔	SQL
+ EC2 vs RDS vs Lambda 설명
EC2
[사용자]
↓
[EC2 (Linux)]
├─ OS
├─ MariaDB (직접 설치)
├─ 설정(my.cnf)
├─ 백업 스크립트
└─ 모니터링

특징
서버 전체를 직접 관리
설정 자유도 100%
비용 저렴 (소규모)
운영 난이도 높음
내가 직접 해야 하는 것
DB 설치/업데이트
백업(cron, dump)
장애 복구
보안 패치
스케일링
RDS
[사용자 / EC2 / Lambda]
↓
[AWS RDS (MariaDB)]
├─ 자동 백업
├─ 자동 패치
├─ Multi-AZ
└─ 모니터링

특징
DB 전용 서비스
OS 접근 불가 (SSH ❌)
콘솔/엔드포인트로만 접근
안정성 높음
AWS가 대신 해주는 것
백업/복구
장애 대응
패치
스케일링
복제
항목	EC2 + MariaDB	RDS
설치	직접	❌
OS 접근	가능	불가
백업	직접	자동
장애 대응	직접	AWS
확장성	수동	자동
운영 난이도	높음	낮음
비용	저렴	상대적으로 높음
Lambda
정의
서버 없는 컴퓨팅(Serverless) 서비스
코드만 올려두면, AWS가 자동으로 실행해줌
서버 관리 불필요 (OS, 패치, 확장 걱정 없음)
항목	설명
서버 관리	❌ 필요 없음
실행 방식	이벤트 기반 (HTTP 요청, S3 업로드, DB 트리거 등)
비용	실행한 만큼만 과금
용도	API 서버, 데이터 처리, 배치, 알림 등
[사용자/서비스 요청]
↓
[Lambda 함수]
↓
결과 반환 / DB, S3 등 연동

👉 즉 Lambda = 코드만 올리면 알아서 실행되는 작은 서버
총정리
EC2 → “직접 서버 열고, OS랑 DB 다 관리”

RDS → “DB만 맡기고, AWS가 알아서 관리”

Lambda → “서버 없이, 코드만 올리면 자동 실행”