# 사용자의 선호와 현재 위치, 날씨에 따른 음식 추천 애플리케이션

2024.03-2024.06


## 프로젝트 개요

야외에서 식사를 할 때 항상 하는 고민인 '오늘 뭐 먹지?' 에 대한 고민을 덜기 위하여 사용자의 음식 취향과 날씨에 어울리는 음식을 선정하여 사용자의 위치 근처의 식당을 추천하는 애플리케이션을 제작하였습니다.

## 개발 환경

+ Kotlin
+ IDE : Android Studio

## 사용 APi

+ 네이버 지역 검색 API
+ 기상청 API
+ 구글 Geocoding API


## 주요 기능

### [회원가입]

  <img src="https://github.com/wqp99w/read-me_image/blob/main/ooad/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.jpg" width="600" height="600"/>

+ ID 중복 확인이 가능하며, 사용자의 음식 선호도를 입력합니다.
  

### [로그인]

  <img src="https://github.com/wqp99w/read-me_image/blob/main/ooad/%EB%A1%9C%EA%B7%B8%EC%9D%B8.jpg" width="600" height="600"/>

  + 로그인에 성공하면 Splash 화면으로 전환되며, 음식 추천 화면으로 이동됩니다.
  
### [사용자의 현재 위치 좌표 구하기]

  ![위치좌표](https://github.com/wqp99w/read-me_image/blob/main/ooad/%EC%9C%84%EC%B9%98%EC%A2%8C%ED%91%9C.jpg)

  + 현재 위치가 어딘지를 표시하며, 이를 통해 현재 좌표를 구할 수 있습니다.
  
### [사용자의 현재 위치 날씨 구하기]

  ![날씨](https://github.com/wqp99w/read-me_image/blob/main/ooad/%EB%82%A0%E3%85%86.jpg)

  + 현재 좌표를 이용하여 기상청 API를 사용하여 현재 위치의 날씨 정보를 받아올 수 있습니다.
  +   하늘 상태와 온도 정보만 필요하기 때문에 해당 정보들만 가져오는 모습입니다.
  
### [음식 추천]

  <img src="https://github.com/wqp99w/read-me_image/blob/main/ooad/%EC%9D%8C%EC%8B%9D%EC%B6%94%EC%B2%9C%ED%99%94%EB%A9%B4.jpg" width="300" height="600"/>

  + 음식 추천받기 버튼을 누르면 음식을 추천합니다.

  <img src="https://github.com/wqp99w/read-me_image/blob/main/ooad/%EC%B6%94%EC%B2%9C%EB%A1%9C%EA%B7%B81.jpg" width="400" height="150"/>

  <img src="https://github.com/wqp99w/read-me_image/blob/main/ooad/%EC%B6%94%EC%B2%9C%EB%A1%9C%EA%B7%B82.jpg" width="400" height="150"/>

  <img src="https://github.com/wqp99w/read-me_image/blob/main/ooad/%EC%B6%94%EC%B2%9C%EB%A1%9C%EA%B7%B83.jpg" width="400" height="150"/>
  +
  
### [식당 추천]

  <img src="https://github.com/wqp99w/read-me_image/blob/main/ooad/%EC%8B%9D%EB%8B%B9%EC%B6%94%EC%B2%9C.jpg" width="300" height="600"/>

* * * *

## 시연 영상

