import java.util.ArrayList;
import java.util.Arrays;

public class Blockchain {

	private Block head;
	private ArrayList<Transaction> pool;
	private int length;

	private final int poolLimit = 3;

	public Blockchain() {
		pool = new ArrayList<>();
		length = 0;
	}

	// getters and setters
	public Block getHead() {
		return head;
	}

	public ArrayList<Transaction> getPool() {
		return pool;
	}

	public int getLength() {
		return length;
	}

	public void setHead(Block head) {
		this.head = head;
	}

	public void setPool(ArrayList<Transaction> pool) {
		this.pool = pool;
	}

	public void setLength(int length) {
		this.length = length;
	}

	// add a transaction
	public int addTransaction(String txString) {
		// implement you code here.
		if(txString == null)
			return 0;
		if(txString.split("[|]", 3).length != 3) {
			//System.out.println("txt split length: " + txString.split("[|]").length);
			return 0;
		}
		String header = txString.split("[|]")[0];
		if(!header.equals("tx"))
			return 0;
		String sender = txString.split("[|]")[1];
		if(!sender.matches("[a-z]{4}[0-9]{4}"))
			return 0;
		String content = txString.split("[|]")[2];
		if((content.contains("[^a-zA-Z0-9]") && content.length() != 0) || content.length() > 70)
			return 0;
		pool.add(new Transaction(sender, content));
		if(pool.size() == poolLimit) {
			Block block = new Block();
			block.setTransactions(pool);
			pool = new ArrayList<>();
			if(head != null) {
				block.setPreviousBlock(this.head);
				block.setPreviousHash(this.head.calculateHash());
			}
			else {
				block.setPreviousHash(new byte[32]);
			}
			this.head = block;
			++length;
			return 2;
		}
		return 1;
	}

	public String toString() {
		String cutOffRule = new String(new char[81]).replace("\0", "-") + "\n";
		String poolString = "";
		for (Transaction tx : pool) {
			poolString += tx.toString();
		}

		String blockString = "";
		Block bl = head;
		while (bl != null) {
			blockString += bl.toString();
			bl = bl.getPreviousBlock();
		}

		return "Pool:\n" + cutOffRule + poolString + cutOffRule + blockString;
	}

	// implement helper functions here if you need any.
}
