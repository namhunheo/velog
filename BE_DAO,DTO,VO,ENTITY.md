최근 서블릿에 대해 공부중인데, DAO / DTO / VO / Entity에 대해 정리가 필요할 것 같아 작성하게 되었다!

우선, DAO / DTO / VO / Entity가 뭐냐?

아키텍처 패턴에서 쓰이는 '설계 개념(설계 역할)'
객체의 역할 분류
++ 들어가기에 앞서 왜 DAO, DTO같이 약자인건데..?

전부 디자인 패턴 계열 네이밍이기 때문!
전체플로우:
클라이언트가 요청을 보내면
Controller가 DTO로 요청을 받고
Service에서 비즈니스 로직을 수행한 뒤
Entity를 생성하여 DAO에 전달하고
DAO가 DB에 저장

개념	한 줄 정의	핵심 역할
Entity	DB 테이블과 1:1로 매핑되는 객체	DB 구조 표현
DAO	DB에 직접 접근하는 객체	CRUD 수행
DTO	계층 간 데이터 전달용 객체	데이터 운반
VO	값 자체가 의미인 불변 객체	값 표현
단계	정하는 것
DB 설계	테이블, 컬럼
Entity	DB 설계 직후
DAO	Entity 이후
DTO	API 설계 시
Service	전체 흐름 설계 시
DTO는 API 기준
Entity는 DB 기준
Service는 DTO를 받아도 되지만, 내부에서는 Entity/VO로 처리
Service 리턴 타입은 보통 DTO

Client
↓
Controller
↓ (요청 DTO)
Service
↓ (Entity / VO)
DAO
↓
DB

🤖 Entity
실제 DB 테이블과 매핑이 되는 클래스
DB 구조를 코드로 표현하기 위해!

테이블 컬럼 = 필드
ORM에서 사용
비즈니스 로직 최소화 (비즈니스 로직, setter()메소드 포함 가능)
🚨주의

데이터를 전달하는 클래스로 사용하면 안됨
Entity 기준으로 테이블이 형성되고, 칼럼이 변경됨
💻예시코드
// UserEntity.java
public class UserEntity {
    private Long id;
    private String email;
    private String password;
}
🤖 DAO (Data Access Object)
Database에 접근하는 역할을 하는 객체
DB랑 말하는 애라고 보면 됨!

프로젝트의 서비스 모델에 해당하는 부분과 데이터베이스를 연결하는 역할
CRUD 작업 담당
데이터 추가 시에는, Controller(POST /users 추가) -> Service -> DAO -> DB
🚨주의

DAO에 비즈니스 로직은 넣으면 안됌
DAO는 '저장소 관리자'일뿐
💻예시코드
// UserDao.java
public UserEntity findById(Long id) {
    String sql = "SELECT * FROM user WHERE id = ?";
    // JDBC / JPA / MyBatis가 실행
}
🤖 DTO (Data Transfer Object)
데이터를 전달하기 위한 객체
필요한 데이터만 안전하게 전달하기 위해

로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스)
Controller ↔ Service ↔ Client 사이 이동
getter / setter 메소드를 포함하지만, 다른 비즈니스 로직은 포함 X
가변 객체 / 불변 객체 모두 활용 가능
💻예시코드_1
// UserResponseDto.java
public class UserResponseDto {
    private String email;
}   
----->> 클라이언트에세 EMAIL만 보여주겠다는 뜻
💻예시코드_2
// Controller에서 사용
UserResponseDto dto = new UserResponseDto();
dto.setEmail(userEntity.getEmail());
--->> DB에서 가져온 UserEntity 객체
🤖 VO (Value Object)
값 자체를 표현하는 객체
의미 있는 값을 안전하게 묶기 위해

DTO와 유사하지만 DTO는 setter를 가지고 있어 값이 변할 수 있음
etter() 메소드를 포함해서, 이외의 비즈니스 로직도 포함 가능
🚨주의

두 객체의 모든 필드 값들이 동일하면 두 객체는 같음
오로지, 읽기 기능만 가능
불변(immutable) 이 핵심