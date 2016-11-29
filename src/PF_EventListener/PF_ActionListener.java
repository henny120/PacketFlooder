package PF_EventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

import PF_GUI.PF_GUI_Resource;
import PacketFlooder.PF_SendTCP;
import PacketFlooder.PF_SendThread_Singleton;
import PacketFlooder.PF_SendUDP;

public class PF_ActionListener implements ActionListener {

	/** Protocol Type 값 **/
	private String protocol;

	/** IP Address 입력 값 **/
	private String ip;

	/** Port Number 입력 값 **/
	private String port;

	/** pkts Speed 입력 값 **/
	private int speed;

	/** IP Address 유효성 검사 변수 선언 **/
	private final Pattern regIp = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	/** Port Number 유효성 검사 변수 선언 **/
	private final Pattern regPort = Pattern.compile("(\\d{1,5})");
	
	
	/**
	 * PF_GUI의 ACTION EVENT를 받아주는 CALL BACK Method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Start")) // START BUTTON CLICK
		{
			pf_Start();
		} else if (e.getActionCommand().equals("Stop")) // STOP BUTTON CLICK
		{
			pf_Stop();
		}

	}// actionPerformed end

	/**
	 * PF_GUI의 시작 버튼을 처리 하는 메소드
	 * 
	 * @throws InvalidFilterException
	 * @throws CaptureDeviceOpenException
	 */
	private void pf_Start() {

		// Protocol Type 값 설정
		for (Enumeration<AbstractButton> buttons = PF_GUI_Resource.get_Instance().m_rdbtnGroup_protocolType
				.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				protocol = button.getText();
				break;
			}
		}

		// IP Address 입력 값 설정
		ip = PF_GUI_Resource.get_Instance().m_tf_ip.getText();

		// Port Number 입력 값 설정
		port = PF_GUI_Resource.get_Instance().m_tf_port.getText();

		// Speed 입력 값 설정(Infinity일 경우 → 999999 / 아닐 경우 입력 값)
		speed = (PF_GUI_Resource.get_Instance().m_tf_speed.getText().equals("Infinity")) ? 999999 : Integer.parseInt(PF_GUI_Resource.get_Instance().m_tf_speed.getText());
		
		
		// Packet Flooder 시작 전 유효성 체크
		if (ip.equals("") || port.equals(""))
		{

			// IP Address 입력이 없을 경우
			if (ip.equals(""))
			{
				// 에러 메시지 다이어로그 생성
				JOptionPane.showMessageDialog(null, "Destination - IP Address를 입력해주세요.", "실행 오류",
						JOptionPane.ERROR_MESSAGE);
			}
			// Port Number 입력이 없을 경우
			else
			{
				// 에러 메시지 다이어로그 생성
				JOptionPane.showMessageDialog(null, "Destination - Port Number를 입력해주세요.", "실행 오류",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		// IP Address 입력 값이 유효하지 않을 경우
		else if (!regIp.matcher(ip).matches())
		{

			// 에러 메시지 다이어로그 생성
			JOptionPane.showMessageDialog(null, "입력하신 IP Address 값이 유효하지 않습니다. 확인 후 다시 시도하여 주세요.", "실행 오류",
					JOptionPane.ERROR_MESSAGE);

			// IP Address Text Field 포커스 설정
			PF_GUI_Resource.get_Instance().m_tf_ip.grabFocus();

		}
		// Port Number 입력 값이 유효하지 않을 경우
		else if (!regPort.matcher(port).matches())
		{

			// 에러 메시지 다이어로그 생성
			JOptionPane.showMessageDialog(null, "입력하신 Port Number 값이 유효하지 않습니다. 확인 후 다시 시도하여 주세요", "실행 오류",
					JOptionPane.ERROR_MESSAGE);

			// Port Number Text Field 포커스 설정
			PF_GUI_Resource.get_Instance().m_tf_port.grabFocus();

		}
		// 유효성 체크에 걸리지 않은 경우
		else
		{
			// 패킷 전송 시작
			try {
				
				// 요청 프로토콜 타입별 Setting 및 Flooder실행
				switch (protocol) {
				
				case "TCP":
				
					// SYN Flooder 객체 생성
					PF_SendTCP tcpSet = new PF_SendTCP();
					
					// SYN Flooder 설정에 성공하였을 경우
					if (tcpSet.set_PF_SendTCP(ip, port, speed)) // BOOLEAN 형태로 리턴 된다.
					{					
						// 시작 버튼을 비활성화
						PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(false);

						// 정지 버튼을 활성화
						PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(true);
										
						/*
						 *  SYN Flooder를 위한 스레드 객체 생성
						 *  최초에 GUI 생성시 EventQueue를 사용했기 때문에 실시간 전송 패킷 수와 시간을 디스플레이하기 위해서는
						 *  새로운 스레드를 생성하여 사용해주어야 함
						 */
						PF_SendThread_Singleton.getInstance().m_Thread = new Thread(tcpSet);
						
						// SYN Flooder 스레드 실행
						PF_SendThread_Singleton.getInstance().m_Thread.start();
						
					}
					break;
					
				case "UDP":
				
					// UDP Flooder 객체 생성
					PF_SendUDP udpSet = new PF_SendUDP();
					
					// UDP Flooder 설정에 성공하였을 경우
					if (udpSet.set_PF_SendUDP(ip, port, speed)) // BOOLEAN 형태로 리턴 된다.
					{					
						// 시작 버튼을 비활성화
						PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(false);

						// 정지 버튼을 활성화
						PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(true);
					
						/*
						 *  UDP Flooder를 위한 스레드 객체 생성
						 *  최초에 GUI 생성시 EventQueue를 사용했기 때문에 실시간 전송 패킷 수와 시간을 디스플레이하기 위해서는
						 *  새로운 스레드를 생성하여 사용해주어야 함
						 */
						PF_SendThread_Singleton.getInstance().m_Thread = new Thread(udpSet);

						// UDP Flooder 스레드 실행
						PF_SendThread_Singleton.getInstance().m_Thread.start();
						
					}
					break;
					
				case "null1":
					// 경고 메시지 다이어로그 생성
					JOptionPane.showMessageDialog(null, "해당 프로토콜은 준비 중입니다.", "준비 중",
							JOptionPane.ERROR_MESSAGE);
					break;

				case "null2":
					// 경고 메시지 다이어로그 생성
					JOptionPane.showMessageDialog(null, "해당 프로토콜은 준비 중입니다.", "준비 중",
							JOptionPane.ERROR_MESSAGE);
					break;

				case "null3":
					// 경고 메시지 다이어로그 생성
					JOptionPane.showMessageDialog(null, "해당 프로토콜은 준비 중입니다.", "준비 중",
							JOptionPane.ERROR_MESSAGE);
					break;
					
				default:
					System.out.println("default Protocol");
					break;
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}

	/**
	 * PF_GUI의 정지 버튼을 처리 하는 메소드
	 */
	private void pf_Stop() {

		// Thread의 inturrupt 메소드를 호출하여 패킷 전송을 중지함
		PF_SendThread_Singleton.getInstance().m_Thread.interrupt();

		// 시작 버튼을 활성화
		PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(true);

		// 정지 버튼을 비활성화
		PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(false);

	}

}
