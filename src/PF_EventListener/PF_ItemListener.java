package PF_EventListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;

import PF_GUI.PF_GUI_Resource;

public class PF_ItemListener implements ItemListener {

	/**
	 * PF_GUI�� ITEM EVENT�� �޾��ִ� CALL BACK Method RadioButton �� ���� / �������� �Ǿ��� ���
	 * �̺�Ʈ�� �߻���
	 */

	@Override
	public void itemStateChanged(ItemEvent e) {
		String item = ((AbstractButton) e.getItem()).getText();

		// ���õǾ��� ���
		if (e.getStateChange() == ItemEvent.SELECTED) {

			// Random ���� ��ư�� �����Ͽ��� ���
			if (item.equals("Random")) {
				PF_GUI_Resource.get_Instance().m_tf_random1.enable();
				PF_GUI_Resource.get_Instance().m_tf_random2.enable();
				PF_GUI_Resource.get_Instance().m_tf_text.disable();
				PF_GUI_Resource.get_Instance().m_tf_file.disable();
				PF_GUI_Resource.get_Instance().m_contentPane.revalidate();
				PF_GUI_Resource.get_Instance().m_contentPane.repaint(); 
			}
			// Text ���� ��ư�� �����Ͽ��� ���
			else if (item.equals("Text")) {
				PF_GUI_Resource.get_Instance().m_tf_text.enable();
				PF_GUI_Resource.get_Instance().m_tf_random1.disable();
				PF_GUI_Resource.get_Instance().m_tf_random2.disable();
				PF_GUI_Resource.get_Instance().m_tf_file.disable();
				PF_GUI_Resource.get_Instance().m_contentPane.revalidate();
				PF_GUI_Resource.get_Instance().m_contentPane.repaint(); 
			}
			// File ���� ��ư�� �����Ͽ��� ���
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
