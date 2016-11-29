package PF_EventListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;

import PF_GUI.PF_GUI_Resource;

public class PF_ItemListener implements ItemListener {

	/**
	 * PF_GUI의 ITEM EVENT를 받아주는 CALL BACK Method RadioButton 이 선택 / 선택해제 되었을 경우
	 * 이벤트가 발생함
	 */

	@Override
	public void itemStateChanged(ItemEvent e) {
		String item = ((AbstractButton) e.getItem()).getText();

		// 선택되었을 경우
		if (e.getStateChange() == ItemEvent.SELECTED) {

			// Random 라디오 버튼을 선택하였을 경우
			if (item.equals("Random")) {
				PF_GUI_Resource.get_Instance().m_tf_random1.enable();
				PF_GUI_Resource.get_Instance().m_tf_random2.enable();
				PF_GUI_Resource.get_Instance().m_tf_text.disable();
				PF_GUI_Resource.get_Instance().m_tf_file.disable();
				PF_GUI_Resource.get_Instance().m_contentPane.revalidate();
				PF_GUI_Resource.get_Instance().m_contentPane.repaint(); 
			}
			// Text 라디오 버튼을 선택하였을 경우
			else if (item.equals("Text")) {
				PF_GUI_Resource.get_Instance().m_tf_text.enable();
				PF_GUI_Resource.get_Instance().m_tf_random1.disable();
				PF_GUI_Resource.get_Instance().m_tf_random2.disable();
				PF_GUI_Resource.get_Instance().m_tf_file.disable();
				PF_GUI_Resource.get_Instance().m_contentPane.revalidate();
				PF_GUI_Resource.get_Instance().m_contentPane.repaint(); 
			}
			// File 라디오 버튼을 선택하였을 경우
			else if (item.equals("File")) {
				PF_GUI_Resource.get_Instance().m_tf_file.enable();
				PF_GUI_Resource.get_Instance().m_tf_text.disable();
				PF_GUI_Resource.get_Instance().m_tf_random1.disable();
				PF_GUI_Resource.get_Instance().m_tf_random2.disable();
				PF_GUI_Resource.get_Instance().m_contentPane.revalidate();
				PF_GUI_Resource.get_Instance().m_contentPane.repaint(); 
			} else {

			}
			
		}
	}

}
