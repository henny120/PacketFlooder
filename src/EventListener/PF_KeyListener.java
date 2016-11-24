package EventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSlider;
import javax.swing.JTextField;

import PF_GUI.PF_GUI_Resource;

public class PF_KeyListener implements KeyListener {

	/**
	 * PF_GUI의 KEY EVENT를 받아주는 CALL BACK Method
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		/** 입력된 키 값을 가져옴 **/
		char c = e.getKeyChar();

		// 입력 값 유효성 체크 : 숫자키, 백스페이스키, 삭제키가 아닐 경우 consume()
		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
		} else {
			/** speed 값을 담을 변수 **/
			int speed;
			
			// speed 텍스트필드 값 유효성 체크 1 : 공백 체크
			if(PF_GUI_Resource.get_Instance().m_tf_speed.getText().length() != 0){
				speed = Integer.parseInt(PF_GUI_Resource.get_Instance().m_tf_speed.getText());
			} else{
				PF_GUI_Resource.get_Instance().m_tf_speed.setText("0");
				speed = 0;
			}
			
			// speed 텍스트필드 값 유효형 체크 2 : 10만을 넘을 경우 10만으로 초기화
			if (speed > 100000) {
				PF_GUI_Resource.get_Instance().m_tf_speed.setText("100000");
				speed = 100000;
			} else {
			}

			// Speed Silder 값을 변경 된 Speed TextField 값으로 설정
			PF_GUI_Resource.get_Instance().m_slider_speed.setValue(speed);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 입력된 키 값을 가져옴
		char c = e.getKeyChar();

		// 입력 값 유효성 체크 : 숫자키, 백스페이스키, 삭제키가 아닐 경우 consume()
		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
			e.consume();
		} else {
		}
	}

}
