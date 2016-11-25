package EventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

import PF_GUI.PF_GUI_Resource;
import PacketFlooder.PF_SendTCP;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.InvalidFilterException;

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

		// Speed 입력 값 설정
		speed = Integer.parseInt(PF_GUI_Resource.get_Instance().m_tf_speed.getText());

		// Packet Flooder 시작 전 유효성 체크
		if (!regIp.matcher(ip).matches()) {

			// 에러 메시지 다이어로그 생성
			JOptionPane.showMessageDialog(null, "입력하신 IP Address 값이 유효하지 않습니다. 확인 후 다시 시도하여 주세요.", "실행 오류",
					JOptionPane.ERROR_MESSAGE);

			// IP Address Text Field 포커스 설정
			PF_GUI_Resource.get_Instance().m_tf_ip.grabFocus();

		} else if (!regPort.matcher(port).matches()) {

			// 에러 메시지 다이어로그 생성
			JOptionPane.showMessageDialog(null, "입력하신 Port Number 값이 유효하지 않습니다. 확인 후 다시 시도하여 주세요", "실행 오류",
					JOptionPane.ERROR_MESSAGE);

			// Port Number Text Field 포커스 설정
			PF_GUI_Resource.get_Instance().m_tf_port.grabFocus();

		} else {

			// 패킷 전송 시작
			try {
				
				// 요청 프로토콜 타입별 PacketFlooder 실행
				switch (protocol) {
				case "TCP":
					if (new PF_SendTCP().set_PF_SendTCP(ip, port, speed)) // BOOLEAN 형태로 리턴 된다.
					{
						// 시작 버튼을 비활성화
						PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(false);

						// 정지 버튼을 활성화
						PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(true);
					}
					break;
					
				case "UDP":
					System.out.println("UDP");
					break;
					
				case "null1":
					System.out.println("ICMP");
					break;

				case "null2":
					System.out.println("ICMP");
					break;

				case "null3":
					System.out.println("ICMP");
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

		// 패킷 전송 중지
		// ...

		// 시작 버튼을 활성화
		PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(true);

		// 정지 버튼을 비활성화
		PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(false);

	}

}
