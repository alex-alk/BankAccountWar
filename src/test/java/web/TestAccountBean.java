package web;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import bank.BillPay;
import bank.Checking;

public class TestAccountBean {
	AccountBean ab = new AccountBean();
	Checking ch = mock(Checking.class);
	BillPay bp = mock(BillPay.class);
	
	@Test
	public void testCheckPayment() {
		ab.setChecking(ch);
		Assert.assertEquals(ab.getAmount(), 15, 0.01);
		Mockito.when(ch.getBalance()).thenReturn(500.00);
		Assert.assertEquals(ch.getBalance(),500.00, 0.01);
		Assert.assertEquals(ab.checkPayment(), "confirma");
		ab.setAmount(600);
		Assert.assertEquals(ab.checkPayment(), "plateste");
	}
	
	@Test
	public void testGetCurrentPayee() {
		ab.setBillPay(bp);
		List<String> list = new ArrayList<>();
		list.add("");
		Mockito.when(bp.getPayees()).thenReturn(list);
		ab.setCurrentPayee("");
		Assert.assertNotEquals(ab.getCurrentPayee(), null);
		ab.setCurrentPayee(null);
		Assert.assertNotEquals(ab.getCurrentPayee(), null);
	}
	
	@Test
	public void testMakePayment() {
		ab.setChecking(ch);
		ab.setBillPay(bp);
		ab.makePayment();
	}
	
	@Test
	public void testGetWarn() {
		ab.setWarn("");
		Assert.assertTrue(ab.getWarn().equals(""));
	}
	@Test
	public void testSetWarn() {
		ab.setWarn("");
		Assert.assertTrue(ab.getWarn().equals(""));
	}
	
	@Test 
	public void testGetCheckingAccountNumber() {
		ab.setChecking(ch);
		Mockito.when(ch.getAccountNumber()).thenReturn("123");
		Assert.assertEquals(ab.getCheckingAccountNumber(),"123");
	}
	
	@Test
	public void testGetCheckingBalance() {
		ab.setChecking(ch);
		Mockito.when(ch.getBalance()).thenReturn(500.00);
		Assert.assertEquals(ab.getCheckingBalance(),500.00,0.01);
	}
	
	@Test
	public void testIsEmpty() {
		ab.setChecking(ch);
		Mockito.when(ch.isEmpty()).thenReturn(true);
		Assert.assertTrue(ab.isEmpty());
	}
	
	@Test
	public void testGetPayees() {
		ab.setBillPay(bp);
		List<String> list = new ArrayList<>();
		list.add("");
		Mockito.when(ab.getPayees()).thenReturn(list);
		Assert.assertNotEquals(ab.getPayees(),null);
	}
	@Test
	public void testReset() {
		ab.reset();
	}
	@Test
	public void testGetPaymentConfirmation() {
		ab.getPaymentConfirmation();
	}
	@Test
	public void testGetPaymentOk() {
		Assert.assertFalse(ab.getPaymentOK());
	}
}
