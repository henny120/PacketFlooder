package PF_EventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import PF_GUI.PF_GUI_Resource;

public class PF_FileOpenListener implements ActionListener {

	/**
	 * PF_GUI의 FileOpen ACTION EVENT를 받아주는 CALL BACK Method
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/** 파일 선택 컴포넌트 생성과 동시에 상태 값을 저장하는 변수 선언 */
		int status = PF_GUI_Resource.get_Instance().m_fc_file.showOpenDialog(null);
		
		// 사용자에 의해 파일이 선택되었을 경우
		if (status == JFileChooser.APPROVE_OPTION) {
			
			/** 선택된 파일을 값으로 새로운 파일 객체 생성 **/
			File selectedFile = PF_GUI_Resource.get_Instance().m_fc_file.getSelectedFile();
			
			// 파일의 절대 주소값을 Data FileName TextField에 저장
			PF_GUI_Resource.get_Instance().m_tf_file.setText(selectedFile.getParent() + "\\" + selectedFile.getName());
			
		}

	}

}
