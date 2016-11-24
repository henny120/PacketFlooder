package PF_GUI;

import javax.swing.JFrame;

import EventListener.PF_ActionListener;
import EventListener.PF_ChangeListener;
import EventListener.PF_KeyListener;


/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.16
 * 
 * PF_GUI_RESOURCE와 연계되어서 실제로 JFrame를 만드는 클래스
 * RESOURCE는 클래스는 모두 PF_GUI_RESOURCE에서 SINGLETON으로 받으며 리스너 등록과 화면을 생성한다.
 *
 */

public class PF_GUI extends JFrame
{
	/** PF_GUI_Resource클래스의 싱글톤 객체 **/
	private static final PF_GUI_Resource m_RESOURCE;

	
	/** static 영역 호출 **/
	static
	{
		m_RESOURCE = PF_GUI_Resource.get_Instance();
	}
	
	
	public PF_GUI()
	{
		// Jframe 구성과, OS확인 
		init();
		
		// Listener 등록 
		add_listener();
		
		
	} // 생성자 end
	
	
	/**
	 * GUI의 Resource를 호출 하여 셋팅하는 Method
	 */
	private void init()
	{
		// 타이틀 지정
		String os = System.getProperty("os.name");
		if (os.indexOf("Win") != -1) 
		{ // 윈도우의 경우
			this.setTitle("Packet Flooder. Window ver @myeonguni.com");
		} 
		else if (os.indexOf("Lin") != -1) 
		{ // 리눅스의 경우
			this.setTitle("Packet Flooder. Linux ver @myeonguni.com");
		}
		
		
		// Jframe 설정 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 425, 400);
		this.setContentPane(m_RESOURCE.m_contentPane);
		
		
		// Protocol Type label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_protocolType);
		
		// TCP Protocol Radio btn 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_tcp);
		
		// UDP Protocol Radio btn 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_udp);
		
		// ARP Protocol Radio btn 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_arp);
		
		// ICMP Protocol Radio btn 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_icmp);
		
		// IGMP Protocol Radio btn 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_igmp);
		
		
		// NIC list label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_NIC);

		// NIC list 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_NIC_scroll);
		
		
		// Destination label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_destination);
		
		// IP Address label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_ip);
		
		// IP Address Text Field 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_tf_ip);
		
		// Port Number label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_port);

		// Port Number Text Field 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_tf_port);
		

		// Transmission control label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_transmission);

		// speed label 등록		
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_speed);

		// speed Text Field 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_tf_speed);

		// speed slider 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_slider_speed);

		
		// Status label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_status);
		
		// Packets send label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_sendPacketsLbl);
		
		// Packets send value label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_sendPackets);
		
		// Seconds elapsed label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_elapsedSecondsLbl);

		// Seconds elapsed value label 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_elapsedSeconds);

		
		// Start btn 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_btn_flooder_Start);
		
		// Stop btn 등록
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_btn_flooder_Stop);
		
	}
	
	/**
	 * Event Listener를 추가 시키는 메소드
	 * EventListener 패키지에 Listener 종류들이 존재 한다.
	 */
	private void add_listener()
	{
		// Start 버튼에 이벤트 등록 
		m_RESOURCE.m_btn_flooder_Start.addActionListener(new PF_ActionListener());
		
		// Stop 버튼에 이벤트 등록 
		m_RESOURCE.m_btn_flooder_Stop.addActionListener(new PF_ActionListener());
		
		// Speed 텍스트필드에 입력(숫자) 유효성 체크 이벤트 등록
		m_RESOURCE.m_tf_speed.addKeyListener(new PF_KeyListener());
		
		// Speed 슬라이더에 이벤트 등록
		m_RESOURCE.m_slider_speed.addChangeListener(new PF_ChangeListener());
		
	}

}
