


# IoT_Fire_detector


![구조도](https://user-images.githubusercontent.com/59923602/144969892-5d88be77-a382-4f35-b3e1-09ac379e3bb9.png)







## REST API 기반 안드로이드 앱 기능

------------------------------------------------------------------------------------------------------------
****** 화재 위험 감지 이벤트를 보여드리기 위해 온도 값들을 제각각 낮게 설정하였습니다. ********

SMS 버전을 사용하기 위하여 도쿄버전으로 전환하여 제작하였습니다.

아두이노 
- count 값 추가 
- 30도 이상일시 count 값 증가
- 부저 장착(비상벨 역할)
- LED -> buzzer

Lambda
-  count 값에 따른 이벤트와 특정 온도 이상 일 때 각각의 SMS 이벤트를 설정하였습니다.
-  특정 온도 이상 일 때 DynamoDB에 입력되도록 하였습니다.
-  count 값도 DynamoDB에 저장되도록 하였습니다.

API GateWay
-  디바이스 상태 조회/변경
-  디바이스 로그 조회/시간대별 차트


android
- 온도 값에 따른 3단계 앱 화면 구성 (위험,경고,양호)
-  URL을 입력하지 않아도 되도록 구성
-  LED -> buzzer

------------------------------------------------------------------------------------------------------------



![image](https://user-images.githubusercontent.com/59923602/144698108-a667c31b-439f-42c5-a2e4-8ee21cfe705e.png)


- 아두이노 스케치에 count 값 생성

- 특정 온도 시 count 값 증가

------------------------------------------------------------------------------------------------------------
![image](https://user-images.githubusercontent.com/59923602/144698283-73d32d72-80fc-4279-8b48-81aa49db336a.png)

 [URL 주의!]
- 앱 실행 전 API URI를 입력해야 합니다.

	
	- 사물상태 조회/변경 API URI

		```
		https://xxxxxxxx.execute-api.ap-northeast-2.amazonaws.com/prod/devices/{devices_name}
		```	
	
	- 사물로그 조회 API URI

		```
		https://xxxxxxxx.execute-api.ap-northeast-2.amazonaws.com/prod/devices/{devices_name}/log
		```	
		
		
------------------------------------------------------------------------------------------------------------

![image](https://user-images.githubusercontent.com/59923602/144740464-c0175f7c-2a51-4156-bdf1-20fc18045079.png)


- AWS_IoT_DHT11-main  아두이노 파일이므로 구별해서 사용하도록 주의

------------------------------------------------------------------------------------------------------------





