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
 * devices List를 나타내는 클래스 JList를 상속 받아서 생성과 동시에 devices 셋팅
 * 
 */

public class PF_GUI_NICList extends JList {

	/** Pcap_LIST Singleton 객체 **/
	private static final PF_GUI_NICList m_List = new PF_GUI_NICList();

	
	/** KEY : NIC NAME + DESCRIPTION : VALUE : DEVICE NPF  Capture를 위해서는 NPF값이 필요하다.**/
	public HashMap<String,NetworkInterface> m_Map_Device = new HashMap<String,NetworkInterface>();
	
	
	/** List 에 Device 이름을 추가하기 위한 배열 **/
	public ArrayList<String> m_List_DeviceName= new ArrayList<String>();
	
	
	/** List 에 Device 이름을 추가하기 위한 배열 **/
	public ArrayList<String> m_List_Device = new ArrayList<String>();

	/**
	 * 생성자 Method 생성과 동시에 get_NIC_Device()을 통하여 NIC Device 받아오기
	 */
	public PF_GUI_NICList() {
		try {
			get_NIC_Device();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * NIC Device를 받아와 리스트에 추가
	 * @throws CaptureDeviceLookupException 예외처리
	 */
	private void get_NIC_Device() {
		// NIC device 정보 받아오기
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		//String[] devices = PacketCapture.lookupDevices();

		for (NetworkInterface nic : devices) {
			get_NIC_Device_Name(nic);
		}

		// 모든 Devices의 이름을 받은 후에 List Data 지정
		this.setListData(m_List_DeviceName.toArray());
	}
	

	/**
	 * @param nic nic NPF를 매개변수로 받아서 처리
	 */
	private void get_NIC_Device_Name(NetworkInterface nic) {
		// KEY 값으로 NIC의 name+description을 설정 하고 List에 추가 
		String key;
		NetworkInterface value;
		
		// key , value 받아오기 
		key = nic.description + " " + nic.name.substring(nic.name.indexOf("{"), nic.name.length());
		value = nic;

		// key value 지정
		m_Map_Device.put(key, value);
		
		// List 에 추가
		m_List_DeviceName.add(key);
					
		System.out.println(key);
	} // end
		
	
	/**
	 * Pcap_LIST의 Singleton을 반환하는 메소드
	 * @return m_List
	 */
	public static PF_GUI_NICList get_PF_GUI_NICList_Instance() {
		return m_List;
	}

}
