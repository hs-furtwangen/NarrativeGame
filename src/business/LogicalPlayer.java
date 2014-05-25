package business;

public class LogicalPlayer {
	
	private static boolean[] artefacts= new boolean[3];
	
	public static void init(){
		for(int i = 0; i < artefacts.length; i++){
			artefacts[i] = false;
		}
	}
	
	public static void archievedArtefact(int i){
		artefacts[i-1] = true;
	}
	
	public static boolean[] getArtefacts(){
		return artefacts;
	}
	
	public static boolean allDone(){
		boolean b = true;
		for (boolean artefact : artefacts) {
			if(!artefact)
				b= false;
		}
		
		return b;
		
	}
	
	

}
