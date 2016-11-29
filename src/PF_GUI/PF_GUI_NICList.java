package PF_GUI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JList;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

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

	
	/** KEY : NIC NAME + DESCRIPTION : VALUE : DEVICE NPF  Capture�� ���ؼ��� NPF���� �ʿ��ϴ�.**/
	public HashMap<String,NetworkInterface> m_Map_Device = new HashMap<String,NetworkInterface>();
	
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * NIC Device�� �޾ƿ� ����Ʈ�� �߰�
	 * @throws CaptureDeviceLookupException ����ó��
	 */
	private void get_NIC_Device() {
		// NIC device ���� �޾ƿ���
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		//String[] devices = PacketCapture.lookupDevices();

		for (NetworkInterface nic : devices) {
			get_NIC_Device_Name(nic);
		}

		// ��� Devices�� �̸��� ���� �Ŀ� List Data ����
		this.setListData(m_List_DeviceName.toArray());
	}
	

	/**
	 * @param nic nic NPF�� �Ű������� �޾Ƽ� ó��
	 */
	private void get_NIC_Device_Name(NetworkInterface nic) {
		// KEY ������ NIC�� name+description�� ���� �ϰ� List�� �߰� 
		String key;
		NetworkInterface value;
		
		// key , value �޾ƿ��� 
		key = nic.description + " " + nic.name.substring(nic.name.indexOf("{"), nic.name.length());
		value = nic;

		// key value ����
		m_Map_Device.put(key, value);
		
		// List �� �߰�
		m_List_DeviceName.add(key);
					
		System.out.println(key);
	} // end
		
	
	/**
	 * Pcap_LIST�� Singleton�� ��ȯ�ϴ� �޼ҵ�
	 * @return m_List
	 */
	public static PF_GUI_NICList get_PF_GUI_NICList_Instance() {
		return m_List;
	}

}
