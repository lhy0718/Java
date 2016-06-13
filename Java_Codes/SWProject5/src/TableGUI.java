import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
abstract class TableGUI extends JFrame {
	private JPanel buttonTable;
	private ReservationFile file;
	protected JButton[][] buttons = new JButton[7][8];
	
	TableGUI(String title, ReservationFile file){
		this.file = file;
		setTitle(title);
		setSize(500, 500);
		setVisible(true);
		
		add(getButtonTable(), BorderLayout.CENTER);
	}
	
	protected abstract String setButtonsName(int day, int time);

	private JPanel getButtonTable(){
		buttonTable = new JPanel(new GridLayout(9, 8));
		buttonTable.add(new Label());
		for(Day day : Day.values())
			buttonTable.add(new Label(day.name()));
		for(int i=0; i<8; i++){
			buttonTable.add(new Label((i+1)+"교시"));
			for(int j=0; j<7; j++){
				buttons[j][i] = new JButton("");
				buttonTable.add(buttons[j][i]);
			}
		}
		return buttonTable;
	}
	protected class DeleteTempFileOnExit implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			File tempTemp = new File(file.getTempFileName());
			tempTemp.delete();
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			File tempTemp = new File(file.getTempFileName());
			tempTemp.delete();
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
		
	}
}
/////////////////////////////////////////////////////////////////////////////////

class AvailableTime extends TableGUI{
	private ReservationFile file;
	private RoomReservationTable[] tables;
	
	AvailableTime(final ReservationFile file) {
		super("Available Time", file);
		this.file = file;
		tables = getAvailableStates();
		for(int day=0; day<7; day++){
			for(int time=0; time<8; time++){
				buttons[day][time].setText(setButtonsName(day, time));
				buttons[day][time].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RoomReservationGUI main = (RoomReservationGUI) getFrames()[0];
						for(int i = 0; i < 7; i++)
							for(int j =0; j<8; j++){
								int day_,  time_;
								if(e.getSource() == buttons[i][j]){
									day_ = i;
									time_ = j;
									if(buttons[i][j].getText().equals("X")){
										new Notice("해당 시간에 비어있는 강의실이 없습니다.");
										return;
									}
									main.setComboBox(day_, time_);
									dispose();
								}
							}
					}
				});
			}
		}
		addWindowListener(new DeleteTempFileOnExit());
	}
	
	private RoomReservationTable[] getAvailableStates(){
		RoomReservationTable table_[] = new RoomReservationTable[RoomName.values().length];
		for(int i=0; i<RoomName.values().length; i++)
			table_[i] = new RoomReservationTable(RoomName.values()[i]);
		for(ReservationRecord r : file.getList()){
			for(RoomReservationTable table : table_){
				table.insert(r);
			}
		}
		return table_;
	}
	
	@Override
	protected String setButtonsName(int day, int time) {
		if(tables[0].getTable()[day][time].isReserved && tables[1].getTable()[day][time].isReserved 
				&& tables[2].getTable()[day][time].isReserved)
			return "X";
		else
			return "O";
	}
	
}

///////////////////////////////////////////////////////////////////////////////////

class ReservationStatus extends TableGUI{
	private RoomReservationTable table;
	private RoomName room;
	ReservationStatus(final ReservationFile file, final RoomName room) {
		super(room.name()+" ReservationStatus", file);
		this.room = room;
		table = new RoomReservationTable(room);
		int listIndex = 0;
		for(ReservationRecord r : file.getList())
			table.insert(r, ++listIndex);
		for(int day=0; day<7; day++){
			for(int time=0; time<8; time++){
				buttons[day][time].setText(setButtonsName(day, time));
				buttons[day][time].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RoomReservationGUI main = (RoomReservationGUI) getFrames()[0];
						for(int i = 0; i < 7; i++)
							for(int j =0; j<8; j++){
								int day_, time_;
								if(e.getSource() == buttons[i][j]){
									day_ = i;
									time_ = j;
									if(buttons[i][j].getText().equals("")){
										main.setComboBox(room, day_, time_);
										dispose();
										return;
									}
									main.setRowSelected(table.getTable()[day_][time_].listIndex);
									dispose();
								}
							}
					}
				});
			}
		}
		addWindowListener(new DeleteTempFileOnExit());
	}
	
	RoomName getRoomName(){
		return room;
	}
	@Override
	protected String setButtonsName(int day, int time) {
		return table.getTable()[day][time].personName;
	}
	
}