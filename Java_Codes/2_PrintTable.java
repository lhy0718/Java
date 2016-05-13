
class PrintTable {
	PrintTable(RoomReservationTable[] tables){
		for(int i = 0; i < tables.length && !tables[i].roomName.equals(""); i++){
			printDay(tables, i);
			for(int j = 0; j < 8; j++){
				System.out.print(j+1);
				for(int k = 0; k < 7; k++){
					if(tables[i].reservationer[k][j] != null)
						System.out.print("\t" + tables[i].reservationer[k][j]);
					else
						System.out.print("\t");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	private void printDay(RoomReservationTable[] tables, int index){
		System.out.println("ROOM NAME :: " + tables[index].roomName);
		System.out.println("\tSUN\tMON\tTUE\tWED\tTUR\tFRI\tSAT");
		System.out.println("--------------------------------------------------------------");
	}
}
