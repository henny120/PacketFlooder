package PF_GUI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JList;

import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.PacketCapture;

/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.16
 * devices List�� ��Ÿ���� Ŭ���� JList�� ��� �޾Ƽ� ������ ���ÿ� devices ����
 * 
 */

public class PF_GUI_NICList extends JList {

	/** Pcap_LIST Singleton ��ü **/
	private static final PF_GUI_NICList m_List = new PF_GUI_NICList();

	
	/** KEY : NIC NAME : VALUE : DEVICE NPF  Capture�� ���ؼ��� NPF���� �ʿ��ϴ�.**/
	public HashMap<String,String> m_Map_Device = new HashMap<String,String>();
	
	
	/** List �� Device �̸��� �߰��ϱ� ���� �迭 **/
	public ArrayList<String> m_List_DeviceName= new ArrayList<String>();
	
	
	/** List �� Device �̸��� �߰��ϱ� ���� �迭 **/
	public ArrayList<String> m_List_Device = new ArrayList<String>();

	/**
	 * ������ Method ������ ���ÿ� get_NIC_Device()�� ���Ͽ� NIC Device �޾ƿ���
	 */
	public PF_GUI_NICList() {
		try {
			get_NIC_Device();
		} catch (CaptureDeviceLookupException e) {
			e.printStackTrace();
		}
	}

	/**
	 * NIC Device�� �޾ƿ� ����Ʈ�� �߰�
	 * @throws CaptureDeviceLookupException ����ó��
	 */
	private void get_NIC_Device() throws CaptureDeviceLookupException {
		// NIC device ���� �޾ƿ���
		String[] devices = PacketCapture.lookupDevices();

		for (String nic : devices) {
			get_NIC_Device_Name(nic);
			get_NIC_Device_Name(nic);
			get_NIC_Device_Name(nic);
		}

		// ��� Devices�� �̸��� ���� �Ŀ� List Data ����
		this.setListData(m_List_DeviceName.toArray());
	}
	

	/**
	 * library�� ���׷� \n������ �������� ��� ���־� �Ѵ�.
	 * @param nic nic �̸��� �Ű������� �޾Ƽ� ó��
	 */
	private void get_NIC_Device_Name(String nic) {
		// KEY ������ NIC�� �̸��� ���� �ϰ� List�� �߰� 
		String key;
		String value;
		
		// key , value �޾ƿ��� 
		key = nic.substring(nic.indexOf("\n"), nic.length());
		value = nic.substring(0, nic.indexOf("\n"));
	
		// ��������
		key = key.trim();
		value = value.trim();
		
		// key value ����
		m_Map_Device.put(key, value);
		
		// List �� �߰�
		m_List_DeviceName.add(key);
					
	} // end
		
	
	/**
	 * Pcap_LIST�� Singleton�� ��ȯ�ϴ� �޼ҵ�
	 * @return m_List
	 */
	public static PF_GUI_NICList get_PF_GUI_NICList_Instance() {
		return m_List;
	}

}
