package PacketFlooder;

import java.util.HashMap;

import PF_GUI.PF_GUI_NICList;
import PF_GUI.PF_GUI_Resource;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
import net.sourceforge.jpcap.capture.PacketCapture;

/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.26
 * TCP Flooder를 위한 설정과 실제 패킷을 전송하는 클래스
 * 
 */

public class PF_SendTCP {

	/** NIC **/
	public String m_dev;
	
	/** Destination IP Address **/
	public String m_des_ip;
	
	/** Destination Port Number 설정 **/
	public String m_des_port;
	
	/** 초당 전송할 패킷의 양 **/
	public int m_pktsNum;
	
	/** 전손한 패킷의 양 **/
	public int m_pktsCount;
	
	/** 패킷 전송이 지속된 시간 **/
	private int m_seconds;
	
	
	/**
	 * TCP Flooder의 정보를 셋팅하는 메소드 지정된 디바이스가 있을 경우에만 true를 리턴
	 * @return boolean BOOLEAN타타입으로 리턴 
	 * @throws CaptureDeviceOpenException 예외처리
	 * @throws InvalidFilterException 예외처리
	 */
	public boolean set_PF_SendTCP(String ip, String port, int speed) throws CaptureDeviceOpenException, InvalidFilterException 
	{
		
		// Device의 이름을 가져오기
		HashMap<String, String> imsi_map = PF_GUI_NICList.get_PF_GUI_NICList_Instance().m_Map_Device;

		// 선택된 Device의 NPF 정보를 가져오기 Device의 이름으로 map에서 찾아온다.
		m_dev = (String) imsi_map.get(PF_GUI_NICList.get_PF_GUI_NICList_Instance().getSelectedValue());
		
		
		if(m_dev!=null) // 선택한 디바이스가 있을 경우만 실행 
		{
			// 목적지 아이피 주소 설정
			m_des_ip = ip;
			
			// 목적지 포트번호 설정
			m_des_port = port;
			
			// 초당 전송할 패킷의 양을 설정
			m_pktsNum = speed;
			
			// 패킷 전송
			send();
			
			return true;
		}
		else // 선택한 디바이스가 없다면 false를 리턴한다.
		{
			return false;
		}

	}// method end
	
	
	/**
	 * TCP Packet을 생성하여 전송하는 메소드
	 * @param nic 사용자에 의해 선택된 Device NPF 정보
	 */
	public void send() {
		// 패킷 전송
		// ...
	}


}//class end 