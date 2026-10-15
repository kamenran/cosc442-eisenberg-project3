import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VendingMachineTest {
    @Test
    void newMachineStartsWithZeroBalanceAndEmptySlots() {
        VendingMachine machine = new VendingMachine();
        assertEquals(0.0, machine.getBalance(), 0.00001);
        assertNull(machine.getItem("A"));
        assertNull(machine.getItem("D"));
    }

    @Test
    void insertedMoneyIncreasesBalance() {
        VendingMachine machine = new VendingMachine();
        machine.insertMoney(2.50);
        machine.insertMoney(1.25);
        assertEquals(3.75, machine.getBalance(), 0.00001);
    }

    @Test
    void negativeDepositIsRejected() {
        VendingMachine machine = new VendingMachine();
        assertThrows(VendingMachineException.class, () -> machine.insertMoney(-0.01));
        assertEquals(0.0, machine.getBalance(), 0.00001);
    }

    @Test
    void purchasingWithExactFundsRemovesItemAndDeductsCost() {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Water", 1.50);
        machine.addItem(item, "B");
        machine.insertMoney(1.50);
        assertTrue(machine.makePurchase("B"));
        assertNull(machine.getItem("B"));
        assertEquals(0.0, machine.getBalance(), 0.00001);
    }

    @Test
    void insufficientFundsDoNotPurchaseOrRemoveItem() {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Juice", 2.00);
        machine.addItem(item, "C");
        machine.insertMoney(1.00);
        assertFalse(machine.makePurchase("C"));
        assertSame(item, machine.getItem("C"));
        assertEquals(1.00, machine.getBalance(), 0.00001);
    }

    @Test
    void returningChangeClearsBalance() {
        VendingMachine machine = new VendingMachine();
        machine.insertMoney(4.25);
        assertEquals(4.25, machine.returnChange(), 0.00001);
        assertEquals(0.0, machine.getBalance(), 0.00001);
    }
@Test
void freeItemCanBePurchasedWithZeroBalance() {
    VendingMachine machine = new VendingMachine();
    VendingMachineItem freeItem =
        new VendingMachineItem("Free Sample", 0.0);

    machine.addItem(freeItem, "C");

    assertTrue(machine.makePurchase("C"));
    assertNull(machine.getItem("C"));
    assertEquals(0.0, machine.getBalance(), 0.001);
}
}
