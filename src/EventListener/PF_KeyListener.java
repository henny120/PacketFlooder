package EventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSlider;
import javax.swing.JTextField;

import PF_GUI.PF_GUI_Resource;

public class PF_KeyListener implements KeyListener {

	/**
	 * PF_GUI�� KEY EVENT�� �޾��ִ� CALL BACK Method
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		/** �Էµ� Ű ���� ������ **/
		char c = e.getKeyChar();

		// �Է� �� ��ȿ�� üũ : ����Ű, �齺���̽�Ű, ����Ű�� �ƴ� ��� consume()
		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
		} else {
			/** speed ���� ���� ���� **/
			int speed;
			
			// speed �ؽ�Ʈ�ʵ� �� ��ȿ�� üũ 1 : ���� üũ
			if(PF_GUI_Resource.get_Instance().m_tf_speed.getText().length() != 0){
				speed = Integer.parseInt(PF_GUI_Resource.get_Instance().m_tf_speed.getText());
			} else{
				PF_GUI_Resource.get_Instance().m_tf_speed.setText("0");
				speed = 0;
			}
			
			// speed �ؽ�Ʈ�ʵ� �� ��ȿ�� üũ 2 : 10���� ���� ��� 10������ �ʱ�ȭ
			if (speed > 100000) {
				PF_GUI_Resource.get_Instance().m_tf_speed.setText("100000");
				speed = 100000;
			} else {
			}

			// Speed Silder ���� ���� �� Speed TextField ������ ����
			PF_GUI_Resource.get_Instance().m_slider_speed.setValue(speed);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// �Էµ� Ű ���� ������
		char c = e.getKeyChar();

		// �Է� �� ��ȿ�� üũ : ����Ű, �齺���̽�Ű, ����Ű�� �ƴ� ��� consume()
		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
			e.consume();
		} else {
		}
	}

}
