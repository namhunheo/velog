#Github Commit 날짜 변경 반영 / 잔디 심기

## **로컬파일 github에 올려 잔디 심는 방법** 
### (커밋 날짜 변경 반영)

**1. 로컬에서 본인이 원하는 경로에 git clone 받기**
![](https://velog.velcdn.com/images/huni6711/post/b0ab7349-4ec5-446f-941f-2b37dbd34016/image.png)

---
**2. 폴더가 생기면 그 안에 원하는 로컬파일 넣기**
>
- 나의 경우, 그동안 velog에 올린 내용을 github에도 파일형식으로 올리고자 하여 작업 진행
![](https://velog.velcdn.com/images/huni6711/post/f6d4d418-f1bb-46a8-921f-9dce7b3255b4/image.png)

---
**3. 우선, powershell로 이동하여 
github 계정 연결을 위해 아래의 정보 입력**
>
PS C:\Users\0525h\velog> git config --global user.email "github email"
PS C:\Users\0525h\velog> git config --global user.name "github name"
![](https://velog.velcdn.com/images/huni6711/post/734395be-046f-4e67-a1a6-90fafebe500d/image.png)
- 처음에 클론받은 폴더의 경로로 가서 입력하지만, 작업자 권한으로 인하여 리젝트 당함.

그래서 아래의 이미지와 같이 GITHUB에서 사용하는 Email과 Name 입력
![](https://velog.velcdn.com/images/huni6711/post/0bc8e5a1-d271-4a68-88f9-6281d7d89453/image.png)

---
**4. 원하는 날짜 입력**
>
PS C:\Users\0525h\velog> $env:GIT_AUTHOR_DATE="2025-12-07 21:00:00 +0900"
PS C:\Users\0525h\velog> $env:GIT_COMMITTER_DATE="2025-12-07 21:00:00 +0900"
![](https://velog.velcdn.com/images/huni6711/post/4035979b-a54d-4738-ac3b-4c51730b9e54/image.png)

---
**5. git 커밋 내용 입력**
>PS C:\Users\0525h\velog> git commit -m "docs: BE_post_3"
![](https://velog.velcdn.com/images/huni6711/post/0f1928b4-eaed-46b6-adaa-c55bdc2f4ae5/image.png)

---
**6. 이제 커밋까지 완료되었으니, 푸시하여 작업 완료**
![](https://velog.velcdn.com/images/huni6711/post/df11688f-9d74-4621-a383-4326b12d5b07/image.png)

---
----
---
그럼 설정한 커밋 날짜에 잔디까지 반영되어 있는 것을 확인할 수 있음.
![](https://velog.velcdn.com/images/huni6711/post/79cac25d-6b6e-455a-aa34-e85e2bb82dbe/image.png)

