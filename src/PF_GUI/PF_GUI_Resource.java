package PF_GUI;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.16
 *
 * PF_GUI�� RESOURCE�� ���� �ϴ� Ŭ���� 
 * GUI�� ���Ǵ� ��ü���� ���ǵǰ�, �����Ǵ� Ŭ����
 * 
 */

public class PF_GUI_Resource
{
	/** content �г� **/
	public JPanel m_contentPane = new JPanel(); 
	
	
	/** �������� Ÿ�� �� **/
	public JLabel m_lbl_protocolType = new JLabel("Protocol Type");
	
	/** �������� Ÿ�� ������ư �׷� **/
	public ButtonGroup  m_rdbtnGroup_protocolType = new ButtonGroup();
	
	/** �������� Ÿ�� : TCP ���� ��ư **/
	public JRadioButton m_rdbtn_tcp = new JRadioButton("TCP");
	
	/** �������� Ÿ�� : UDP ���� ��ư **/
	public JRadioButton m_rdbtn_udp = new JRadioButton("UDP");

	/** �������� Ÿ�� : null1 ���� ��ư **/
	public JRadioButton m_rdbtn_null1 = new JRadioButton("null1");
	
	/** �������� Ÿ�� : null2 ���� ��ư **/
	public JRadioButton m_rdbtn_null2 = new JRadioButton("null2");

	/** �������� Ÿ�� : null3 ���� ��ư **/
	public JRadioButton m_rdbtn_null3 = new JRadioButton("null3");


	/** ��Ʈ��ũ ����̽� �� **/
	public JLabel m_lbl_NIC = new JLabel("");
	
	/** ��Ʈ��ũ ����̽� ������ �޴� list **/
	public JList m_NIC_list = PF_GUI_NICList.get_PF_GUI_NICList_Instance();
	
	/** ��Ʈ��ũ ����̽� list ��ũ�� **/
	public JScrollPane m_NIC_scroll = new JScrollPane();
	
	
	/** ������ �� **/
	public JLabel m_lbl_destination = new JLabel("");

	/** ������ : IP Address �� **/
	public JLabel m_lbl_ip = new JLabel("IP Address");
	
	/** ������ : IP Address �ؽ�Ʈ �ʵ� **/
	public JTextField m_tf_ip = new JTextField();
	
	/** ������ : Port Number �� **/
	public JLabel m_lbl_port = new JLabel("Port Number");
	
	/** ������ : Port Number �ؽ�Ʈ �ʵ� **/
	public JTextField m_tf_port = new JTextField();
	
	
	/** ���� ���� �� **/
	public JLabel m_lbl_transmission = new JLabel("");

	/** ���� ���� : ���ǵ� �� **/
	public JLabel m_lbl_speed = new JLabel("Speed(pkts/sec)        min                                                                 max");

	/** ���� ���� : ���ǵ�  �ؽ�Ʈ �ʵ� **/
	public JTextField m_tf_speed = new JTextField();
	
	/** ���� ���� : ���ǵ� �����̴��� **/ 
	public JSlider m_slider_speed = new JSlider();
	
		
	/** ����Ÿ �� **/
	public JLabel m_lbl_data = new JLabel("");

	/** ������ Ÿ�� ������ư �׷� **/
	public ButtonGroup  m_rdbtnGroup_dataType = new ButtonGroup();
	
	/** ������ Ÿ�� : Random ���� ��ư **/
	public JRadioButton m_rdbtn_random = new JRadioButton("Random");

	/** ������ Ÿ�� : Text ���� ��ư **/
	public JRadioButton m_rdbtn_text = new JRadioButton("Text");

	/** ������ Ÿ�� : File ���� ��ư **/
	public JRadioButton m_rdbtn_file = new JRadioButton("File");
	
	/** Random : setByteSize �� 1 **/
	public JLabel m_lbl_random1 = new JLabel("to");

	/** Random : setByteSize �� 2 **/
	public JLabel m_lbl_random2 = new JLabel("bytes");
	
	/** Random : setByteSize �ؽ�Ʈ�ʵ� 1 **/
	public JTextField m_tf_random1 = new JTextField();

	/** Random : setByteSize �ؽ�Ʈ�ʵ� 2 **/
	public JTextField m_tf_random2 = new JTextField();
	
	/** Text : setText �ؽ�Ʈ�ʵ� **/
	public JTextField m_tf_text = new JTextField();

	/** File : FileOpen ��ư **/
	public JButton m_btn_file = new JButton("Open");
	
	/** File : FileName �ؽ�Ʈ�ʵ� **/
	public JTextField m_tf_file = new JTextField();

	/** File : FileChooser **/
	public JFileChooser m_fc_file = new JFileChooser(".");
	
	
	/** ���� �� **/
	public JLabel m_lbl_status = new JLabel("");
	
	/** ���� : ���� ��Ŷ �� �ȳ� �� **/
	public JLabel m_lbl_sendPacketsLbl = new JLabel("Packets send");

	/** ���� : ���� ��Ŷ �� �� **/
	public JLabel m_lbl_sendPackets = new JLabel("0");
	
	/** ���� : ���� �ð� �ȳ� �� **/
	public JLabel m_lbl_elapsedSecondsLbl = new JLabel("Seconds elapsed");

	/** ���� : ���� �ð� �� **/
	public JLabel m_lbl_elapsedSeconds = new JLabel("0");
	
		
	/** Flooder ���� ��ư **/
	public JButton m_btn_flooder_Start = new JButton("Start");
	
	/** Flooder ���� ��ư **/
	public JButton m_btn_flooder_Stop = new JButton("Stop");
		
	
	/**singleton�� ���� ��ü **/
	private static final PF_GUI_Resource m_gui_resource;
	
	
	/** static ���� ȣ�� **/
	static
	{
		m_gui_resource = new PF_GUI_Resource();
	}
	
	
	/** heap ���� ȣ�� **/
	{
		// content pane ����
		m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		m_contentPane.setLayout(null);
		m_contentPane.setBackground(SystemColor.control);
		
		
		// �������� �� ����
		m_lbl_protocolType.setBounds(15,  14,  79,  15);
		
		// �� �������� ������ư �׷쿡 �߰�
		m_rdbtnGroup_protocolType.add(m_rdbtn_tcp);
		m_rdbtnGroup_protocolType.add(m_rdbtn_udp);
		m_rdbtnGroup_protocolType.add(m_rdbtn_null1);
		m_rdbtnGroup_protocolType.add(m_rdbtn_null2);
		m_rdbtnGroup_protocolType.add(m_rdbtn_null3);
		
		// �� �������� ���� ��ư ����
		m_rdbtn_tcp.setBounds(103, 10, 51, 23);
		m_rdbtn_udp.setBounds(160, 10, 51, 23);
		m_rdbtn_null1.setBounds(217, 10, 51, 23);
		m_rdbtn_null2.setBounds(274, 10, 57, 23);
		m_rdbtn_null3.setBounds(339, 10, 57, 23);
		
		// �ʱ� �������� ���� ����
		m_rdbtn_tcp.setSelected(true);
		
		
		// ��Ʈ��ũ ����̽� ����
		m_lbl_NIC.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Network Devices",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_NIC.setBounds(12, 43, 384, 70);
		
		// NIC list ����
		m_NIC_list.setBackground(SystemColor.control);
		m_NIC_list.setSelectedIndex(0);
		m_NIC_scroll.setViewportView(m_NIC_list);
		m_NIC_scroll.setBorder(null);
		m_NIC_scroll.setBounds(16, 60, 377, 46);
				
				
		// ������ ����
		m_lbl_destination.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Destination",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_destination.setBounds(12, 123, 384, 58);
		
		// ������ ip address �� ����
		m_lbl_ip.setBounds(26, 147, 79, 15);
		
		// ������ ip address �Է�â ����
		m_tf_ip.setBounds(103, 144, 131, 21);
		m_tf_ip.setColumns(10);
		
		// ������ port number �� ����
		m_lbl_port.setBounds(256, 147, 79, 15);
		
		// ������ port number �Է�â ����
		m_tf_port.setBounds(333, 144, 51, 21);
		m_tf_port.setColumns(10);
		
				
		// �������� ����
		m_lbl_transmission.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Transmission control",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_transmission.setBounds(12, 193, 384, 68);
		
		// �������� ���ǵ� �� ����
		m_lbl_speed.setBounds(26, 211, 370, 15);
				
		// �������� ���ǵ� �����̴��� ���� : �ּҰ�, �ִ밪, ���簪, �����ڴ���, ������ǥ��, ũ�� �� �����ġ
		m_slider_speed.setMinimum(0);
		m_slider_speed.setMaximum(100000);
		m_slider_speed.setValue(50000);
		m_slider_speed.setMinorTickSpacing(10000);
		m_slider_speed.setPaintTicks(true);
		m_slider_speed.setBounds(141, 230, 240, 23);

		// �������� ���ǵ� �Է�â ����
		m_tf_speed.setBounds(44, 227, 58, 25);
		m_tf_speed.setBackground(SystemColor.info);
		m_tf_speed.setColumns(6);
		m_tf_speed.setText(String.valueOf(m_slider_speed.getValue()));
		
		
		// ����Ÿ ����
		m_lbl_data.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Data(UDP Only)", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_data.setBackground(SystemColor.menu);
		m_lbl_data.setBounds(12, 271, 384, 108);

		// �� ����Ÿ ������ư �׷쿡 �߰�
		m_rdbtnGroup_dataType.add(m_rdbtn_random);
		m_rdbtnGroup_dataType.add(m_rdbtn_text);
		m_rdbtnGroup_dataType.add(m_rdbtn_file);
		
		// �� ����Ÿ ������ư ����
		m_rdbtn_random.setBounds(26, 291, 80, 23);
		m_rdbtn_text.setBounds(26, 316, 50, 23);
		m_rdbtn_file.setBounds(26, 341, 50, 23);
		
		// �ʱ� ����Ÿ ���� ����
		m_rdbtn_text.setSelected(true);

		// data : random Ÿ�� ���� ���� ����
		m_rdbtn_random.setEnabled(false);
		m_tf_random1.setEnabled(false);
		m_tf_random2.setEnabled(false);
		m_lbl_random1.setEnabled(false);
		m_lbl_random2.setEnabled(false);
		
		// Random : ����Ʈ ũ�� �Է�â ���� 1
		m_tf_random1.setColumns(10);
		m_tf_random1.setEnabled(false);
		m_tf_random1.setBounds(103, 292, 58, 21);
		
		// Random : ����Ʈ ũ�� �Է�â���� 2
		m_tf_random2.setColumns(10);
		m_tf_random2.setEnabled(false);
		m_tf_random2.setBounds(180, 292, 58, 21);
		
		// Random : ����Ʈ ũ�� �� ���� 1
		m_lbl_random1.setBounds(165, 295, 26, 15);
		m_lbl_random1.setBounds(165, 295, 26, 15);
		
		// Random : ����Ʈ ũ�� �� ���� 2
		m_lbl_random2.setBounds(242, 295, 44, 15);
		
		// Text : �ؽ�Ʈ �Է�â ����
		m_tf_text.setText("*** myeonguni.com ***");
		m_tf_file.setEnabled(true);
		m_tf_text.setBounds(103, 317, 282, 21);
		
		// File : Open ��ư ����
		m_btn_file.setEnabled(false);
		m_btn_file.setBounds(103, 341, 63, 23);
		
		// File : FileName �ؽ�Ʈ�ʵ� ����
		m_tf_file.setBackground(SystemColor.info);
		m_tf_file.setEnabled(false);
		m_tf_file.setBounds(172, 342, 212, 21);
		
		/*
		 *  FileChooser : ���� ���� ��ư ����
		 *  DoubleBuffered ���
		 *  MultiSelection �Ұ�
		 *  FileFilter txt
		 */
		m_fc_file.setDoubleBuffered(true);
		m_fc_file.setMultiSelectionEnabled(false);
		m_fc_file.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
		
		
		// ���� ����
		m_lbl_status.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180)), "Status", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		m_lbl_status.setBackground(SystemColor.menu);
		m_lbl_status.setBounds(12, 386, 274, 64);
				
		// ���� ���� ��Ŷ �� �ȳ� �� ����
		m_lbl_sendPacketsLbl.setBounds(61, 405, 162, 15);
		
		// ���� ���� ��Ŷ �� �� ����
		m_lbl_sendPackets.setBounds(177, 405, 150, 15);

		// ���� ���� �ð� �ȳ� �� ����
		m_lbl_elapsedSecondsLbl.setBounds(61, 425, 105, 15);
		
		// ���� ���� �ð� �� ����
		m_lbl_elapsedSeconds.setBounds(177, 425, 22, 15);
		

		// ��Ŷ �÷�� ���� ��ư ����
		m_btn_flooder_Start.setBounds(298, 394, 97, 23);

		// ��Ŷ �÷�� ���� ��ư Ȱ��ȭ
		m_btn_flooder_Start.setEnabled(true);
		
		// ��Ŷ �÷�� ���� ��ư ����
		m_btn_flooder_Stop.setBounds(299, 425, 97, 23);
		
		// ��Ŷ �÷�� ���� ��ư ��Ȱ��ȭ
		m_btn_flooder_Stop.setEnabled(false);
		
	}
	
	
	/**
	 * GUI�� Resource ��ü singleton�� ��ȯ�ϴ� �޼ҵ�
	 * @return gui_resource
	 */
    public static PF_GUI_Resource get_Instance()
    {
    	return m_gui_resource;
    }
	
}