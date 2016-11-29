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

	/** Protocol Type �� **/
	private String protocol;

	/** IP Address �Է� �� **/
	private String ip;

	/** Port Number �Է� �� **/
	private String port;

	/** pkts Speed �Է� �� **/
	private int speed;

	/** IP Address ��ȿ�� �˻� ���� ���� **/
	private final Pattern regIp = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	/** Port Number ��ȿ�� �˻� ���� ���� **/
	private final Pattern regPort = Pattern.compile("(\\d{1,5})");
	
	
	/**
	 * PF_GUI�� ACTION EVENT�� �޾��ִ� CALL BACK Method
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
	 * PF_GUI�� ���� ��ư�� ó�� �ϴ� �޼ҵ�
	 * 
	 * @throws InvalidFilterException
	 * @throws CaptureDeviceOpenException
	 */
	private void pf_Start() {

		// Protocol Type �� ����
		for (Enumeration<AbstractButton> buttons = PF_GUI_Resource.get_Instance().m_rdbtnGroup_protocolType
				.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				protocol = button.getText();
				break;
			}
		}

		// IP Address �Է� �� ����
		ip = PF_GUI_Resource.get_Instance().m_tf_ip.getText();

		// Port Number �Է� �� ����
		port = PF_GUI_Resource.get_Instance().m_tf_port.getText();

		// Speed �Է� �� ����(Infinity�� ��� �� 999999 / �ƴ� ��� �Է� ��)
		speed = (PF_GUI_Resource.get_Instance().m_tf_speed.getText().equals("Infinity")) ? 999999 : Integer.parseInt(PF_GUI_Resource.get_Instance().m_tf_speed.getText());
		
		
		// Packet Flooder ���� �� ��ȿ�� üũ
		if (ip.equals("") || port.equals(""))
		{

			// IP Address �Է��� ���� ���
			if (ip.equals(""))
			{
				// ���� �޽��� ���̾�α� ����
				JOptionPane.showMessageDialog(null, "Destination - IP Address�� �Է����ּ���.", "���� ����",
						JOptionPane.ERROR_MESSAGE);
			}
			// Port Number �Է��� ���� ���
			else
			{
				// ���� �޽��� ���̾�α� ����
				JOptionPane.showMessageDialog(null, "Destination - Port Number�� �Է����ּ���.", "���� ����",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		// IP Address �Է� ���� ��ȿ���� ���� ���
		else if (!regIp.matcher(ip).matches())
		{

			// ���� �޽��� ���̾�α� ����
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� IP Address ���� ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �õ��Ͽ� �ּ���.", "���� ����",
					JOptionPane.ERROR_MESSAGE);

			// IP Address Text Field ��Ŀ�� ����
			PF_GUI_Resource.get_Instance().m_tf_ip.grabFocus();

		}
		// Port Number �Է� ���� ��ȿ���� ���� ���
		else if (!regPort.matcher(port).matches())
		{

			// ���� �޽��� ���̾�α� ����
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� Port Number ���� ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �õ��Ͽ� �ּ���", "���� ����",
					JOptionPane.ERROR_MESSAGE);

			// Port Number Text Field ��Ŀ�� ����
			PF_GUI_Resource.get_Instance().m_tf_port.grabFocus();

		}
		// ��ȿ�� üũ�� �ɸ��� ���� ���
		else
		{
			// ��Ŷ ���� ����
			try {
				
				// ��û �������� Ÿ�Ժ� Setting �� Flooder����
				switch (protocol) {
				
				case "TCP":
				
					// SYN Flooder ��ü ����
					PF_SendTCP tcpSet = new PF_SendTCP();
					
					// SYN Flooder ������ �����Ͽ��� ���
					if (tcpSet.set_PF_SendTCP(ip, port, speed)) // BOOLEAN ���·� ���� �ȴ�.
					{					
						// ���� ��ư�� ��Ȱ��ȭ
						PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(false);

						// ���� ��ư�� Ȱ��ȭ
						PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(true);
										
						/*
						 *  SYN Flooder�� ���� ������ ��ü ����
						 *  ���ʿ� GUI ������ EventQueue�� ����߱� ������ �ǽð� ���� ��Ŷ ���� �ð��� ���÷����ϱ� ���ؼ���
						 *  ���ο� �����带 �����Ͽ� ������־�� ��
						 */
						PF_SendThread_Singleton.getInstance().m_Thread = new Thread(tcpSet);
						
						// SYN Flooder ������ ����
						PF_SendThread_Singleton.getInstance().m_Thread.start();
						
					}
					break;
					
				case "UDP":
				
					// UDP Flooder ��ü ����
					PF_SendUDP udpSet = new PF_SendUDP();
					
					// UDP Flooder ������ �����Ͽ��� ���
					if (udpSet.set_PF_SendUDP(ip, port, speed)) // BOOLEAN ���·� ���� �ȴ�.
					{					
						// ���� ��ư�� ��Ȱ��ȭ
						PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(false);

						// ���� ��ư�� Ȱ��ȭ
						PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(true);
					
						/*
						 *  UDP Flooder�� ���� ������ ��ü ����
						 *  ���ʿ� GUI ������ EventQueue�� ����߱� ������ �ǽð� ���� ��Ŷ ���� �ð��� ���÷����ϱ� ���ؼ���
						 *  ���ο� �����带 �����Ͽ� ������־�� ��
						 */
						PF_SendThread_Singleton.getInstance().m_Thread = new Thread(udpSet);

						// UDP Flooder ������ ����
						PF_SendThread_Singleton.getInstance().m_Thread.start();
						
					}
					break;
					
				case "null1":
					// ��� �޽��� ���̾�α� ����
					JOptionPane.showMessageDialog(null, "�ش� ���������� �غ� ���Դϴ�.", "�غ� ��",
							JOptionPane.ERROR_MESSAGE);
					break;

				case "null2":
					// ��� �޽��� ���̾�α� ����
					JOptionPane.showMessageDialog(null, "�ش� ���������� �غ� ���Դϴ�.", "�غ� ��",
							JOptionPane.ERROR_MESSAGE);
					break;

				case "null3":
					// ��� �޽��� ���̾�α� ����
					JOptionPane.showMessageDialog(null, "�ش� ���������� �غ� ���Դϴ�.", "�غ� ��",
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
	 * PF_GUI�� ���� ��ư�� ó�� �ϴ� �޼ҵ�
	 */
	private void pf_Stop() {

		// Thread�� inturrupt �޼ҵ带 ȣ���Ͽ� ��Ŷ ������ ������
		PF_SendThread_Singleton.getInstance().m_Thread.interrupt();

		// ���� ��ư�� Ȱ��ȭ
		PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(true);

		// ���� ��ư�� ��Ȱ��ȭ
		PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(false);

	}

}
