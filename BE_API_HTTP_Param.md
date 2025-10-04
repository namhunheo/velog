## [API 명세] Http 요청에서 Param (query / path / form / session)

1. param(파라미터)란?
👉 한 줄 정의
브라우저 → 서버로 전달되는 모든 값

서버 입장에서는

“어디서 왔든, 어떻게 왔든”

들어오기만 하면 전부 param

2. query param (URL 뒤 조건)
언제 쓰는지?
어떤 조건으로 보고 싶을 때

📌 예시
GET /errands?dong_code=GASAN&sort=latest
이때 서버로 들어오는 값:

dong_code = GASAN
sort = latest
말로 풀면
“가산동 심부름 중에서 최신순으로 보여줘”

➡️ 찾는 조건이라서 query param

3. path param (URL 경로 안에 있는 값)
언제 쓰는지?
특정 데이터 하나만 정확히 보여줄 때

📌 예시
GET /errands/123
이때 서버로 들어오는 값:

123
말로 풀면
“ID가 123번인 심부름 글 하나 보여줘”

➡️ 대상을 특정하니까 path param

4. form param (사용자가 입력해서 보내는 값)
언제 쓰는지?
특정 데이터를 내용으로 저장해 줄 때

📌 예시 (JSP 폼)
<formmethod="post"action="/errands">
<inputname="title"value="편의점 다녀와주세요">
<textareaname="description">콜라 하나 부탁해요</textarea>
</form>
서버로 들어오는 값:

title = 편의점 다녀와주세요
description = 콜라 하나 부탁해요
말로 풀면
“이 제목이랑 내용으로 심부름 글 작성해줘”

➡️ 사용자가 입력한 값이라서 form param

5. session param (서버가 이미 알고 있는 값)
언제 쓰는지?
“누가 요청했는지” 확인할 때

📌 예시
LonguserId= (Long) session.getAttribute("userId");
이 값은:

URL에도 없음
form에도 없음
서버가 로그인할 때 저장해둔 값
말로 풀면
“아, 이 요청은 userId=7인 사람이 보냈구나”

➡️ 사용자 신원 정보라서 session param

서버로 들어오는 값은 전부 param이고,
그중 URL 뒤에 붙은 조건이 query,
URL 안에 박힌 게 path,
폼으로 온 게 form,
서버가 기억하는 게 session이다.
내가 헷갈렸던 부분 : “param을 JSON이나 form 중 하나로 골라서 보내는 거 아냐?” 라고 생각함.
query / path / session → 요청의 “위치”
JSON / form → body의 “형식
API 명세 쓸때 기준
URL 조건인가? (query/path)
사용자가 입력한 본문인가? (body: JSON/form)
서버가 이미 알고 있는 정보인가? (session)
구분	언제 쓰는지	예시
param	서버로 들어오는 모든 값	query, path, form, session
query param	조건/필터	dong_code=GASAN
path param	대상 식별	/errands/123
form param	입력 데이터	title=...
session param	로그인 정보	userId=7
param은 데이터 성격별로 자리 정해서 함께 전달됨

JSON/form은 body의 표현 방식 차이

query/path/session은 요청 구조상의 위치

이 개념은 언어/프레임워크 무관한 HTTP 기본 규칙