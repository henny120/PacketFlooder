package EventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import PF_GUI.PF_GUI_Resource;

public class PF_ActionListener implements ActionListener {

	/** Protocol Type �� **/
	private String protocol;

	/** IP Address �Է� �� **/
	private String ip;

	/** Port Number �Է� �� **/
	private String port;

	/** pkts Speed �Է� �� **/
	private String speed;

	/** IP Address ��ȿ�� �˻� ���� ���� **/
	private final Pattern regIp = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

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
	 */
	private void pf_Start() {

		// Protocol Type �� ����
		for (Enumeration<AbstractButton> buttons = PF_GUI_Resource.get_Instance().m_rdbtnGroup_protocolType.getElements(); buttons.hasMoreElements();) {
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
		
		// Speed �Է� �� ����
		speed = PF_GUI_Resource.get_Instance().m_tf_speed.getText();
		
		
		// Packet Flooder ���� �� ��ȿ�� üũ
		if (!regIp.matcher(ip).matches()) {
			
			// ���� �޽��� ���̾�α� ����
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� IP Address ���� �������� �ʽ��ϴ�. Ȯ�� �� �ٽ� �õ��Ͽ� �ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
			
			// IP Address Text Field ��Ŀ�� ����
			PF_GUI_Resource.get_Instance().m_tf_ip.grabFocus();
		
		} else if(!regPort.matcher(port).matches()) {

			// ���� �޽��� ���̾�α� ����
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� Port Number ���� �������� �ʽ��ϴ�. Ȯ�� �� �ٽ� �õ��Ͽ� �ּ���", "���� ����", JOptionPane.ERROR_MESSAGE);
			
			// Port Number Text Field ��Ŀ�� ����
			PF_GUI_Resource.get_Instance().m_tf_port.grabFocus();
			
		} else {
			
			// ��Ŷ ���� ����
			// ...
			
			// ���� ��ư�� ��Ȱ��ȭ
			PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(false);
			
			// ���� ��ư�� Ȱ��ȭ
			PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(true);
			
		}
	}

	/**
	 * PF_GUI�� ���� ��ư�� ó�� �ϴ� �޼ҵ�
	 */
	private void pf_Stop() {
		
		// ��Ŷ ���� ����
		// ...
		
		// ���� ��ư�� Ȱ��ȭ
		PF_GUI_Resource.get_Instance().m_btn_flooder_Start.setEnabled(true);
		
		// ���� ��ư�� ��Ȱ��ȭ
		PF_GUI_Resource.get_Instance().m_btn_flooder_Stop.setEnabled(false);
		
	}

}
