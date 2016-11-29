package PF_EventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import PF_GUI.PF_GUI_Resource;

public class PF_FileOpenListener implements ActionListener {

	/**
	 * PF_GUI�� FileOpen ACTION EVENT�� �޾��ִ� CALL BACK Method
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/** ���� ���� ������Ʈ ������ ���ÿ� ���� ���� �����ϴ� ���� ���� */
		int status = PF_GUI_Resource.get_Instance().m_fc_file.showOpenDialog(null);
		
		// ����ڿ� ���� ������ ���õǾ��� ���
		if (status == JFileChooser.APPROVE_OPTION) {
			
			/** ���õ� ������ ������ ���ο� ���� ��ü ���� **/
			File selectedFile = PF_GUI_Resource.get_Instance().m_fc_file.getSelectedFile();
			
			// ������ ���� �ּҰ��� Data FileName TextField�� ����
			PF_GUI_Resource.get_Instance().m_tf_file.setText(selectedFile.getParent() + "\\" + selectedFile.getName());
			
		}

	}

}
