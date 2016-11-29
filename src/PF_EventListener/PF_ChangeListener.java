package PF_EventListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import PF_GUI.PF_GUI_Resource;

public class PF_ChangeListener implements ChangeListener {

	/**
	 * PF_GUI�� CHANGE EVENT�� �޾��ִ� CALL BACK Method
	 */

	@Override
	public void stateChanged(ChangeEvent e) {

		JSlider source = (JSlider) e.getSource();

		if (source.getValue() == 100000) {
			// Speed TextField ���� ���Ѵ�(Infinity) ������ ���� 
			PF_GUI_Resource.get_Instance().m_tf_speed.setText("Infinity");
		} else {
			// Speed TextField ���� ���� �� Speed Silder ������ ���� 
			PF_GUI_Resource.get_Instance().m_tf_speed.setText(String.valueOf(source.getValue()));
		}

	}

}
