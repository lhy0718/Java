import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RoomReservationGUI extends JFrame{
	private ReservationFile file;
	
	private JComboBox<String> roomNameCB;
	private JComboBox<String> dayCB;
	private JComboBox<Integer> timeCB;
	private JTextField reservationNameInput;
	private JTextField memoInput;
	private JButton reservationButton;
	private DefaultTableModel model;
	private JTable table;
	private JButton cancelButton;
	private JComboBox<String> roomNameCB2;
	private JButton reservationCheckButton;
	private JButton reservationAvailableButton;

	void setComboBox(int day, int time){
		dayCB.setSelectedIndex(day);
		timeCB.setSelectedIndex(time);
	}
	void setComboBox(RoomName room, int day, int time){
		roomNameCB.setSelectedIndex(room.ordinal());
		dayCB.setSelectedIndex(day);
		timeCB.setSelectedIndex(time);
	}
	
	void setRowSelected(int index){
		table.setRowSelectionInterval(index-1, index-1);
	}
	
	public RoomReservationGUI(final String fileName){
		file = new ReservationFile(fileName);
		Head head = new Head();
		Body body = new Body();
		Tale tale = new Tale();
		
		add(head.getpanel(), BorderLayout.NORTH);
		add(body.getpanel(), BorderLayout.CENTER);
		add(tale.getpanel(), BorderLayout.SOUTH);
		setSize(500, 400);
		setTitle("RoomReservation");
		addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				file.save();
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {		
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	class Head{
		private JPanel head;
		Head(){
			JPanel head1 = new JPanel(new GridLayout(2, 5));
			head1.add(new JLabel("ȣ��"));
			head1.add(new JLabel("����"));
			head1.add(new JLabel("�ð�"));
			head1.add(new JLabel("������"));
			head1.add(new JLabel("�޸�"));
			roomNameCB = new JComboBox<String>();
			for(RoomName name: RoomName.values())
				roomNameCB.addItem(name.name());
			dayCB = new JComboBox<String>();
			for(Day day: Day.values())
				dayCB.addItem(day.name());
			timeCB = new JComboBox<Integer>();
			for(int i=1; i<9; i++)
				timeCB.addItem(i);
			head1.add(roomNameCB);
			head1.add(dayCB);
			head1.add(timeCB);
			reservationNameInput = new JTextField();
			memoInput = new JTextField();
			head1.add(reservationNameInput);
			head1.add(memoInput);
			
			head = new JPanel(new BorderLayout());
			head.add(head1, BorderLayout.CENTER);
			reservationButton = new JButton("����");
			
			reservationButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
						if(!reservationNameInput.getText().trim().equals("")){
							ReservationRecord r = new ReservationRecord((String) roomNameCB.getSelectedItem(), 
									Day.valueOf((String) dayCB.getSelectedItem()).getNum(), (int) timeCB.getSelectedItem(), 
									reservationNameInput.getText(), memoInput.getText());
							if(file.add(r)){
								Object[] data = new Object[5];
								data[0]=r.getRoomName();
								data[1]=Day.values()[r.getDay()];
								data[2]=r.getTime();
								data[3]=r.getPersonName();
								data[4]=r.getMemo();
								model.addRow(data);
								new Notice("���� ����");
							}
							else{
								new Notice("���� ����");
								try{
									roomNameCB.setSelectedIndex(roomNameCB.getSelectedIndex()+1);
								}
								catch(Exception e1){
									roomNameCB.setSelectedIndex(0);
								}
							}
						}
						else{
							new Notice("�����ڸ� �Է��Ͻÿ�");
						}
					}
				}
			);
			head.add(reservationButton, BorderLayout.EAST);
		}
		
		JPanel getpanel(){
			return head;
		}
	}
	class Body{
		private JPanel body;
		
		Body(){
			JPanel body1 = new JPanel(new BorderLayout());
			model = new DefaultTableModel();
			model.setColumnCount(5);
			table = new JTable(model);
			table.setAutoscrolls(true);
			table.setBorder(new LineBorder(new Color(100,100,100)));
			body1.add(table, BorderLayout.CENTER);
			JTextField tableTitle = new JTextField("���� ��Ȳ; 20151322���ѿ�");
			tableTitle.setEditable(false);
			body1.add(tableTitle, BorderLayout.NORTH);
			for(ReservationRecord r : file.getList()){
				Object[] data = new Object[5];
				data[0]=r.getRoomName();
				data[1]=Day.values()[r.getDay()];
				data[2]=r.getTime();
				data[3]=r.getPersonName();
				data[4]=r.getMemo();
				model.addRow(data);
			}
			body = new JPanel(new BorderLayout());
			body.add(body1, BorderLayout.CENTER);
			cancelButton = new JButton("���");
			cancelButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int index = table.getSelectedRow();
					try{
						int confirm = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "�˸�", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if(confirm == 2)
							return;
						file.remove(index);
						model.removeRow(index);
						model.fireTableDataChanged();
						new Notice("��ҵ�");
					}
					catch (Exception e1){
						new Notice("����� �����͸� �����ϼ���");
					}
						for(ReservationRecord r : file.getList()){
							System.out.println(r +""+ index);
						}
					}
				}
			);
			body.add(cancelButton, BorderLayout.EAST);
		}
		
		JPanel getpanel(){
			return body;
		}
	}

	class Tale{
		private JPanel tale;
		Tale(){
			JPanel tale1 = new JPanel();
			roomNameCB2 = new JComboBox<String>();
			for(RoomName name : RoomName.values())
				roomNameCB2.addItem(name.name());
			tale1.add(roomNameCB2);
			reservationCheckButton = new JButton("�溰�����Ȳ");
			reservationCheckButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(Window w : JFrame.getWindows()){
						if(w.getClass() == ReservationStatus.class){
							w.dispose();
						}
					}
					new ReservationStatus(new ReservationFile(file.getTempFileName()), RoomName.valueOf(roomNameCB2.getSelectedItem().toString()));
					System.out.println(RoomName.valueOf(roomNameCB2.getSelectedItem().toString()));
				}
			});
			tale1.add(reservationCheckButton);
			
			tale = new JPanel(new GridLayout(1, 2));
			reservationAvailableButton = new JButton("���డ�ɽð�");
			reservationAvailableButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(Window w : JFrame.getWindows()){
						if(w.getClass() == AvailableTime.class){
							w.dispose();
						}
					}
					new AvailableTime(new ReservationFile(file.getTempFileName()));
				}
			});
			tale.add(reservationAvailableButton);
			tale.add(tale1);
		}
		
		JPanel getpanel(){
			return tale;
		}
	}
}
