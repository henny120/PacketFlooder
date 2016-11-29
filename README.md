# PacketFlooder
jpcap(+winpcap) 을 이용한 PacketFlooder GUI Tool 입니다. 


# 프로그램 설명
최근 인터넷에서 발생하는 DDoS 형태 중 큰 부분을 차지하고 있는 공격 유형인, SYN(TCP) Flooding Attack과 UDP Flooding Attack에 대한 테스트 툴입니다.


# 지원하는 공격유형
1. SYN Flooding Attack
2. UDP Flooding Attack


# 사용 라이브러리
1. Winpcap(ver 4.1.3)
 -(참고) https://www.winpcap.org/
2. Jpcap(ver 0.7)
 -(참고) http://jpcap.gitspot.com/

 
# 추후 업데이트할 사항
1. 공통  
1) 전송 패킷 수 : 무한대(Infinity) 지원 추가할 것 - 16.11.29 완료  
2) 생성되는 패킷의 세부 사용자정의 기능(Ethenet header, Ip Header, Protocol Header) 지원 추가할 것  
2. SYN Flooding Attack  
1) ..  
3. UDP Flooding Attack  
1) Payload(data) 사용자정의 기능 추가할 것 - 16.11.29 완료 
	
# 그 외..
연구, 스터디 목적 외 악의적으로 사용하지 마시기 바랍니다 - 명우니닷컴(http://myeonguni.com)