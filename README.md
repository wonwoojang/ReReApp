# ReReApp
![01_ReReApp](https://github.com/wonwoojang/ReReApp/assets/43309869/9d95c9c3-cc3e-4f81-a398-05447f630718)![02_ReReApp](https://github.com/wonwoojang/ReReApp/assets/43309869/ba0fe6ca-6ca8-4c9a-a608-12f706f25b16)![03_ReReApp](https://github.com/wonwoojang/ReReApp/assets/43309869/40e68182-938c-42b0-aca5-38c48d5b9580)![04_ReReApp](https://github.com/wonwoojang/ReReApp/assets/43309869/f4f30e9c-b8a0-459f-96f8-97548aff41c9)![05_ReReApp](https://github.com/wonwoojang/ReReApp/assets/43309869/bca03f8c-f555-431f-af2a-42f7d7e02de7)![06_ReReApp](https://github.com/wonwoojang/ReReApp/assets/43309869/d16d8a93-a732-4a47-bfda-416f01fe2371)![07_ReReApp](https://github.com/wonwoojang/ReReApp/assets/43309869/2dc1a1b5-e5f8-4f48-ab6d-90ac51b49ffe)

----------------------------------------------------------------------------------------------------------

* 개발 스팩 정리

  *  언어 : 코틀린
  
  * 안드로이드 최소 버전 8.1 (27)
  
  * 안드로이드 최대 버전 13 (33)
  
  * 깃 커밋 룰
  
    - Feat = 새로운 할일
    - Fix = 버그 수정
    - Refactor = 개선
    - Modify = 버그는 아니고 기획 변경으로 인한 수정 사항
    
  * 깃 머지 관리
  
    - dev, release 커밋은 전체 내용 push
    - master 커밋은 Squash commit 으로 버전 표시 push 예) version - 1.0.0
    
  * DI - Koin
  
  * CI/CD
  
    - Github Action 사용
    - 배포 Firebase - App Distribution 사용
    
  * 영화 검색 api
  
    - kmdb(한국 영화 데이터 베이스 사용) - https://www.kmdb.or.kr/info/api/apiList?menuIndex=115
  
  * 책 검색 api
  
    - 네이버 검색 (책 사용) - https://developers.naver.com/products/service-api/search/search.md

----------------------------------------------------------------------------------------------------------

* ReReApp 개발 내용
  
  - 앱 설명: 과거 콘텐츠를 지금 시점에서 재평가하는 어플리케이션
  - 목적: 지금까지 회사에서 일하면서 습득한 기술을 제 스킬로 만들고 싶어 만들고 있는 프로젝트 입니다.
  - 사용 패턴: MVVM
  - 디자인 프로그램: Figma
  - 형상 관리: Github, Android Version Controller
  - 라이브러리:  Glide / Retrofit2 / Okhttp3 / Coroutine / Paging / FlexBox / MPAndroidChart / DataBinding / DI - Koin / Gson / EnentFlow / DiffUtil / ViewModel
  - Firebase: Crashlytics , App Distribution
  - 경험:
