import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VendingMachineItemTest {
    @Test
    void constructorStoresNameAndPrice() {
        VendingMachineItem item = new VendingMachineItem("Chips", 2.75);
        assertEquals("Chips", item.getName());
        assertEquals(2.75, item.getPrice(), 0.00001);
    }

    @Test
    void negativePriceIsRejected() {
        assertThrows(VendingMachineException.class,
            () -> new VendingMachineItem("Invalid", -0.01));
    }
}
