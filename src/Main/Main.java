package Main;

import java.io.File;

import javax.swing.UnsupportedLookAndFeelException;

import PF_GUI.PF_GUI;


/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.16
 *
 */

public class Main {

	/**
	 * Native Library Load (jpcap.dll)
	 */
	static {
		try {
			System.load(new File("jpcap.dll").getAbsolutePath());
			System.out.println(new File("jpcap.dll").getAbsolutePath());
		} catch (UnsatisfiedLinkError e) {
			System.out.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}// static end

	/**
	 * ���α׷��� ���� �޼ҵ�
	 * ������� ���
	 * UI Thread�� ���� ���� �������� Thread�� ����ؼ� GUIȣ��
	 * 
	 * @param args �Ű�����
	 * @throws ClassNotFoundException ����ó��
	 * @throws InstantiationException ����ó��
	 * @throws IllegalAccessException ����ó��
	 * @throws UnsupportedLookAndFeelException ����ó��
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		// ����� ���� 1 (JDK�� ����� Nimbus)
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			} // if end
		} // for end
		
		// ����� ���� 2 (Quaqua.jar �� ��Ÿ��)
		// javax.swing.UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		// javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);

		
		// Thread�� ����Ͽ��� GUI�� ȣ��
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PF_GUI().setVisible(true);
			} // run end
		}); // runnable end

	}

}
