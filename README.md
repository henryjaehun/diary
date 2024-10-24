Self study. making diary service.
#Diary Web Application
(Java, Spring Boot)

---
구현하고 싶은 사항 
1. 일기 생성후 데이터베이스에 CRUD (프로젝트의 layers도 공부)
2. 일기 내용으로 사진 파일 다루기 
3. 웹 어플리케이션 만들기
4. 프런트 엔드 맛보기


-----
##2024-10-23
####오늘의 목표 : create 시 사진 파일 추가 기능 생성
####새로 배울 내용 : MultipartFile
####절차
1. 데이터베이스 테이블에 imagePath 항목 추가 : 이미지를 서버의 특정 디렉터리에 저장하고, 데이터베이스에는 이미지 파일 경로를 저장한다
2. 엔티티도 수정하여 이미지 경로를 저장할 필드를 추가한다
2. 이미지 업로드를 위한 필드를 DTO에 추가한다. 이미지 파일은 MultipartFile로 처리한다.
3. 컨트롤러에서는 클라이언트로부터 MultipartFile을 받아 파일을 저장한 후, 그 파일의 경로를 데이터베이스에 저장한다.
4. 서비스에도 이미지 파일 경로와 함께 일기 데이터를 저장하는 메소드를 추가한다.

----
##2024-10-24
####오늘의 목표 : 템플릿 엔진 - 타임리프를 사용하여 데이터베이스의 일기 내용들을 웹페이지로 보여주기 시도
####새로 배울 내용 : Thymeleaf
####절차 
1. making file named "dairy.html" in src/main/resources/templates/
2. Add Thymeleaf DI in build.gradle 
3. 컨트롤러 패키지에 메소드 만들고 파라미터로 Model model 설정.
####문제 : 컨트롤러에서 템플릿으로 데이터가 전달이 안되거나,  Thymeleaf 템플릿에서 데이터 바인딩이 안되는 것을 보임
---