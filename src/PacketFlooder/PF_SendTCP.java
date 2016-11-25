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
 * TCP Flooder�� ���� ������ ���� ��Ŷ�� �����ϴ� Ŭ����
 * 
 */

public class PF_SendTCP {

	/** NIC **/
	public String m_dev;
	
	/** Destination IP Address **/
	public String m_des_ip;
	
	/** Destination Port Number ���� **/
	public String m_des_port;
	
	/** �ʴ� ������ ��Ŷ�� �� **/
	public int m_pktsNum;
	
	/** ������ ��Ŷ�� �� **/
	public int m_pktsCount;
	
	/** ��Ŷ ������ ���ӵ� �ð� **/
	private int m_seconds;
	
	
	/**
	 * TCP Flooder�� ������ �����ϴ� �޼ҵ� ������ ����̽��� ���� ��쿡�� true�� ����
	 * @return boolean BOOLEANŸŸ������ ���� 
	 * @throws CaptureDeviceOpenException ����ó��
	 * @throws InvalidFilterException ����ó��
	 */
	public boolean set_PF_SendTCP(String ip, String port, int speed) throws CaptureDeviceOpenException, InvalidFilterException 
	{
		
		// Device�� �̸��� ��������
		HashMap<String, String> imsi_map = PF_GUI_NICList.get_PF_GUI_NICList_Instance().m_Map_Device;

		// ���õ� Device�� NPF ������ �������� Device�� �̸����� map���� ã�ƿ´�.
		m_dev = (String) imsi_map.get(PF_GUI_NICList.get_PF_GUI_NICList_Instance().getSelectedValue());
		
		
		if(m_dev!=null) // ������ ����̽��� ���� ��츸 ���� 
		{
			// ������ ������ �ּ� ����
			m_des_ip = ip;
			
			// ������ ��Ʈ��ȣ ����
			m_des_port = port;
			
			// �ʴ� ������ ��Ŷ�� ���� ����
			m_pktsNum = speed;
			
			// ��Ŷ ����
			send();
			
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
	 */
	public void send() {
		// ��Ŷ ����
		// ...
	}


}//class end 