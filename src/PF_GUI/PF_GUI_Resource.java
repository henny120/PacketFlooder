package PF_GUI;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.16
 *
 * PF_GUI의 RESOURCE를 관리 하는 클래스 
 * GUI에 사용되는 객체들이 정의되고, 생성되는 클래스
 * 
 */

public class PF_GUI_Resource
{
	/** content 패널 **/
	public JPanel m_contentPane = new JPanel(); 
	
	
	/** 프로토콜 타입 라벨 **/
	public JLabel m_lbl_protocolType = new JLabel("Protocol Type");
	
	/** 프로토콜 타입 라디오버튼 그룹 **/
	public ButtonGroup  m_rdbtnGroup_protocolType = new ButtonGroup();
	
	/** 프로토콜 타입 : TCP 라디오 버튼 **/
	public JRadioButton m_rdbtn_tcp = new JRadioButton("TCP");
	
	/** 프로토콜 타입 : UDP 라디오 버튼 **/
	public JRadioButton m_rdbtn_udp = new JRadioButton("UDP");

	/** 프로토콜 타입 : null1 라디오 버튼 **/
	public JRadioButton m_rdbtn_null1 = new JRadioButton("null1");
	
	/** 프로토콜 타입 : null2 라디오 버튼 **/
	public JRadioButton m_rdbtn_null2 = new JRadioButton("null2");

	/** 프로토콜 타입 : null3 라디오 버튼 **/
	public JRadioButton m_rdbtn_null3 = new JRadioButton("null3");


	/** 네트워크 디바이스 라벨 **/
	public JLabel m_lbl_NIC = new JLabel("");
	
	/** 네트워크 디바이스 정보를 받는 list **/
	public JList m_NIC_list = PF_GUI_NICList.get_PF_GUI_NICList_Instance();
	
	/** 네트워크 디바이스 list 스크롤 **/
	public JScrollPane m_NIC_scroll = new JScrollPane();
	
	
	/** 목적지 라벨 **/
	public JLabel m_lbl_destination = new JLabel("");

	/** 목적지 : IP Address 라벨 **/
	public JLabel m_lbl_ip = new JLabel("IP Address");
	
	/** 목적지 : IP Address 텍스트 필드 **/
	public JTextField m_tf_ip = new JTextField();
	
	/** 목적지 : Port Number 라벨 **/
	public JLabel m_lbl_port = new JLabel("Port Number");
	
	/** 목적지 : Port Number 텍스트 필드 **/
	public JTextField m_tf_port = new JTextField();
	
	
	/** 전송 제어 라벨 **/
	public JLabel m_lbl_transmission = new JLabel("");

	/** 전송 제어 : 스피드 라벨 **/
	public JLabel m_lbl_speed = new JLabel("Speed(pkts/sec)        min                                                                 max");

	/** 전송 제어 : 스피드  텍스트 필드 **/
	public JTextField m_tf_speed = new JTextField();
	
	/** 전송 제어 : 스피드 슬라이더바 **/ 
	public JSlider m_slider_speed = new JSlider();
	
		
	/** 데이타 라벨 **/
	public JLabel m_lbl_data = new JLabel("");

	/** 데이터 타입 라디오버튼 그룹 **/
	public ButtonGroup  m_rdbtnGroup_dataType = new ButtonGroup();
	
	/** 데이터 타입 : Random 라디오 버튼 **/
	public JRadioButton m_rdbtn_random = new JRadioButton("Random");

	/** 데이터 타입 : Text 라디오 버튼 **/
	public JRadioButton m_rdbtn_text = new JRadioButton("Text");

	/** 데이터 타입 : File 라디오 버튼 **/
	public JRadioButton m_rdbtn_file = new JRadioButton("File");
	
	/** Random : setByteSize 라벨 1 **/
	public JLabel m_lbl_random1 = new JLabel("to");

	/** Random : setByteSize 라벨 2 **/
	public JLabel m_lbl_random2 = new JLabel("bytes");
	
	/** Random : setByteSize 텍스트필드 1 **/
	public JTextField m_tf_random1 = new JTextField();

	/** Random : setByteSize 텍스트필드 2 **/
	public JTextField m_tf_random2 = new JTextField();
	
	/** Text : setText 텍스트필드 **/
	public JTextField m_tf_text = new JTextField();

	/** File : FileOpen 버튼 **/
	public JButton m_btn_file = new JButton("Open");
	
	/** File : FileName 텍스트필드 **/
	public JTextField m_tf_file = new JTextField();

	/** File : FileChooser **/
	public JFileChooser m_fc_file = new JFileChooser(".");
	
	
	/** 상태 라벨 **/
	public JLabel m_lbl_status = new JLabel("");
	
	/** 상태 : 전송 패킷 수 안내 라벨 **/
	public JLabel m_lbl_sendPacketsLbl = new JLabel("Packets send");

	/** 상태 : 전송 패킷 수 라벨 **/
	public JLabel m_lbl_sendPackets = new JLabel("0");
	
	/** 상태 : 전송 시간 안내 라벨 **/
	public JLabel m_lbl_elapsedSecondsLbl = new JLabel("Seconds elapsed");

	/** 상태 : 전송 시간 라벨 **/
	public JLabel m_lbl_elapsedSeconds = new JLabel("0");
	
		
	/** Flooder 시작 버튼 **/
	public JButton m_btn_flooder_Start = new JButton("Start");
	
	/** Flooder 정지 버튼 **/
	public JButton m_btn_flooder_Stop = new JButton("Stop");
		
	
	/**singleton을 위한 객체 **/
	private static final PF_GUI_Resource m_gui_resource;
	
	
	/** static 구역 호출 **/
	static
	{
		m_gui_resource = new PF_GUI_Resource();
	}
	
	
	/** heap 구역 호출 **/
	{
		// content pane 설정
		m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		m_contentPane.setLayout(null);
		m_contentPane.setBackground(SystemColor.control);
		
		
		// 프로토콜 라벨 설정
		m_lbl_protocolType.setBounds(15,  14,  79,  15);
		
		// 각 프로토콜 라디오버튼 그룹에 추가
		m_rdbtnGroup_protocolType.add(m_rdbtn_tcp);
		m_rdbtnGroup_protocolType.add(m_rdbtn_udp);
		m_rdbtnGroup_protocolType.add(m_rdbtn_null1);
		m_rdbtnGroup_protocolType.add(m_rdbtn_null2);
		m_rdbtnGroup_protocolType.add(m_rdbtn_null3);
		
		// 각 프로토콜 라디오 버튼 설정
		m_rdbtn_tcp.setBounds(103, 10, 51, 23);
		m_rdbtn_udp.setBounds(160, 10, 51, 23);
		m_rdbtn_null1.setBounds(217, 10, 51, 23);
		m_rdbtn_null2.setBounds(274, 10, 57, 23);
		m_rdbtn_null3.setBounds(339, 10, 57, 23);
		
		// 초기 프로토콜 선택 설정
		m_rdbtn_tcp.setSelected(true);
		
		
		// 네트워크 디바이스 설정
		m_lbl_NIC.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Network Devices",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_NIC.setBounds(12, 43, 384, 70);
		
		// NIC list 설정
		m_NIC_list.setBackground(SystemColor.control);
		m_NIC_list.setSelectedIndex(0);
		m_NIC_scroll.setViewportView(m_NIC_list);
		m_NIC_scroll.setBorder(null);
		m_NIC_scroll.setBounds(16, 60, 377, 46);
				
				
		// 목적지 설정
		m_lbl_destination.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Destination",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_destination.setBounds(12, 123, 384, 58);
		
		// 목적지 ip address 라벨 설정
		m_lbl_ip.setBounds(26, 147, 79, 15);
		
		// 목적지 ip address 입력창 설정
		m_tf_ip.setBounds(103, 144, 131, 21);
		m_tf_ip.setColumns(10);
		
		// 목적지 port number 라벨 설정
		m_lbl_port.setBounds(256, 147, 79, 15);
		
		// 목적지 port number 입력창 설정
		m_tf_port.setBounds(333, 144, 51, 21);
		m_tf_port.setColumns(10);
		
				
		// 전송제어 설정
		m_lbl_transmission.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Transmission control",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_transmission.setBounds(12, 193, 384, 68);
		
		// 전송제어 스피드 라벨 설정
		m_lbl_speed.setBounds(26, 211, 370, 15);
				
		// 전송제어 스피드 슬라이더바 설정 : 최소값, 최대값, 현재값, 눈금자단위, 눈금자표시, 크기 및 출력위치
		m_slider_speed.setMinimum(0);
		m_slider_speed.setMaximum(100000);
		m_slider_speed.setValue(50000);
		m_slider_speed.setMinorTickSpacing(10000);
		m_slider_speed.setPaintTicks(true);
		m_slider_speed.setBounds(141, 230, 240, 23);

		// 전송제어 스피드 입력창 설정
		m_tf_speed.setBounds(44, 227, 58, 25);
		m_tf_speed.setBackground(SystemColor.info);
		m_tf_speed.setColumns(6);
		m_tf_speed.setText(String.valueOf(m_slider_speed.getValue()));
		
		
		// 데이타 설정
		m_lbl_data.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Data(UDP Only)", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_data.setBackground(SystemColor.menu);
		m_lbl_data.setBounds(12, 271, 384, 108);

		// 각 데이타 라디오버튼 그룹에 추가
		m_rdbtnGroup_dataType.add(m_rdbtn_random);
		m_rdbtnGroup_dataType.add(m_rdbtn_text);
		m_rdbtnGroup_dataType.add(m_rdbtn_file);
		
		// 각 데이타 라디오버튼 설정
		m_rdbtn_random.setBounds(26, 291, 80, 23);
		m_rdbtn_text.setBounds(26, 316, 50, 23);
		m_rdbtn_file.setBounds(26, 341, 50, 23);
		
		// 초기 데이타 선택 설정
		m_rdbtn_text.setSelected(true);

		// data : random 타입 추후 지원 예정
		m_rdbtn_random.setEnabled(false);
		m_tf_random1.setEnabled(false);
		m_tf_random2.setEnabled(false);
		m_lbl_random1.setEnabled(false);
		m_lbl_random2.setEnabled(false);
		
		// Random : 바이트 크기 입력창 설정 1
		m_tf_random1.setColumns(10);
		m_tf_random1.setEnabled(false);
		m_tf_random1.setBounds(103, 292, 58, 21);
		
		// Random : 바이트 크기 입력창설정 2
		m_tf_random2.setColumns(10);
		m_tf_random2.setEnabled(false);
		m_tf_random2.setBounds(180, 292, 58, 21);
		
		// Random : 바이트 크기 라벨 설정 1
		m_lbl_random1.setBounds(165, 295, 26, 15);
		m_lbl_random1.setBounds(165, 295, 26, 15);
		
		// Random : 바이트 크기 라벨 설정 2
		m_lbl_random2.setBounds(242, 295, 44, 15);
		
		// Text : 텍스트 입력창 설정
		m_tf_text.setText("*** myeonguni.com ***");
		m_tf_file.setEnabled(true);
		m_tf_text.setBounds(103, 317, 282, 21);
		
		// File : Open 버튼 설정
		m_btn_file.setEnabled(false);
		m_btn_file.setBounds(103, 341, 63, 23);
		
		// File : FileName 텍스트필드 설정
		m_tf_file.setBackground(SystemColor.info);
		m_tf_file.setEnabled(false);
		m_tf_file.setBounds(172, 342, 212, 21);
		
		/*
		 *  FileChooser : 파일 선택 버튼 설정
		 *  DoubleBuffered 사용
		 *  MultiSelection 불가
		 *  FileFilter txt
		 */
		m_fc_file.setDoubleBuffered(true);
		m_fc_file.setMultiSelectionEnabled(false);
		m_fc_file.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
		
		
		// 상태 설정
		m_lbl_status.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Status", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_status.setBackground(SystemColor.menu);
		m_lbl_status.setBounds(12, 386, 274, 64);
				
		// 상태 전송 패킷 수 안내 라벨 설정
		m_lbl_sendPacketsLbl.setBounds(61, 405, 162, 15);
		
		// 상태 전송 패킷 수 라벨 설정
		m_lbl_sendPackets.setBounds(177, 405, 150, 15);

		// 상태 전송 시간 안내 라벨 설정
		m_lbl_elapsedSecondsLbl.setBounds(61, 425, 105, 15);
		
		// 상태 전송 시간 라벨 설정
		m_lbl_elapsedSeconds.setBounds(177, 425, 22, 15);
		

		// 패킷 플루더 시작 버튼 설정
		m_btn_flooder_Start.setBounds(298, 394, 97, 23);

		// 패킷 플루더 시작 버튼 활성화
		m_btn_flooder_Start.setEnabled(true);
		
		// 패킷 플루더 정지 버튼 설정
		m_btn_flooder_Stop.setBounds(299, 425, 97, 23);
		
		// 패킷 플루더 정지 버튼 비활성화
		m_btn_flooder_Stop.setEnabled(false);
		
	}
	
	
	/**
	 * GUI의 Resource 객체 singleton을 반환하는 메소드
	 * @return gui_resource
	 */
    public static PF_GUI_Resource get_Instance()
    {
    	return m_gui_resource;
    }
	
}