package Network;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ScoreThread extends Thread{
	private DataInputStream input;
	private List<ClientHandler> clients;
	private String scores = "";
	private List<DataOutputStream> ctOutputs;
	
	
	public ScoreThread(DataInputStream input, List<ClientHandler> clients, List<DataOutputStream> ctOutputs) {
		this.input = input;
		this.clients = clients;
		this.ctOutputs = ctOutputs;
    }
	
	public void run() {
		try {
			System.out.println(input.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	while(true) {
    		try {
    			input.readUTF();
    			sendToRivals(printScores());
    			
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
	
	public String printScores(){
		scores = "";
		for (int i = 0; i < clients.size(); i++) {
    		scores += clients.get(i).getScore()+" /";
		}
    	return scores;
    }
	
	private void sendToRivals(String scores) {
		for (int i = 0; i < ctOutputs.size(); i++) {
			try {
				ctOutputs.get(i).writeUTF(scores);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
