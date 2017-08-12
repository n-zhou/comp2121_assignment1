import static org.junit.Assert.*;
import org.junit.Test;

public class BlockchainTest_1 {
	
	@Test
	public void test_invalid_transactions() {
		Blockchain block = new Blockchain();
		assertEquals(0, block.addTransaction("aaa"));
		assertEquals(0, block.addTransaction("tx"));
		assertEquals(0, block.addTransaction("fam"));
	}
	
	@Test
	public void test_more_invalid() {
		Blockchain block = new Blockchain();
		assertEquals(0, block.addTransaction("tx|test000a|2"));
		assertEquals(0, block.addTransaction("tx|0est0000|7"));
		assertEquals(0, block.addTransaction("ff|test0000|7"));
		assertEquals(0, block.addTransaction("tx|test0000|7|"));
	}
	
	@Test
	public void test_blockchain_addTransaction() {
		Blockchain block = new Blockchain();
		assertEquals(1, block.addTransaction("tx|test0000|7"));
		assertEquals(1, block.addTransaction("tx|test0000|7"));
		assertEquals(2, block.addTransaction("tx|test0000|7"));
	}
	
	@Test 
	public void test_blockchain_length_one() {
		Blockchain block = new Blockchain();
		assertEquals(0, block.getLength());
		assertEquals(1, block.addTransaction("tx|test0000|7"));
		assertEquals(1, block.addTransaction("tx|test0000|7"));
		assertEquals(2, block.addTransaction("tx|test0000|7"));
		assertEquals(1, block.getLength());
	}
	
	@Test 
	public void test_blockchain_length_two() {
		Blockchain block = new Blockchain();
		assertEquals(0, block.getLength());
		assertEquals(1, block.addTransaction("tx|test0000|7"));
		assertEquals(1, block.addTransaction("tx|test0000|7"));
		assertEquals(2, block.addTransaction("tx|test0000|7"));
		assertEquals(1, block.getLength());
		assertEquals(1, block.addTransaction("tx|test0011|7"));
		assertEquals(1, block.addTransaction("tx|test0022|7"));
		assertEquals(2, block.addTransaction("tx|test0033|7"));
		assertEquals(2, block.getLength());
	}
	
	public void test_blockhain_length_mixed() {
		Blockchain block = new Blockchain();
		
	}

}
