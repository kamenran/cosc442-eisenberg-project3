
package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Color;

public class InventoryTest {

    private Item item(String name) {
        return new Item('!', Color.WHITE, name, name);
    }

    @Test
    void newInventoryStartsEmpty() {
        Inventory inv = new Inventory(3);
        assertEquals(3, inv.getItems().length);
        assertNull(inv.get(0));
        assertFalse(inv.isFull());
    }

    @Test
    void addStoresItem() {
        Inventory inv = new Inventory(2);
        Item sword = item("Sword");
        inv.add(sword);
        assertSame(sword, inv.get(0));
        assertTrue(inv.contains(sword));
    }

    @Test
    void addUsesNextAvailableSlot() {
        Inventory inv = new Inventory(3);
        Item a = item("A");
        Item b = item("B");
        inv.add(a);
        inv.add(b);
        assertSame(a, inv.get(0));
        assertSame(b, inv.get(1));
    }

    @Test
    void fullInventoryRejectsAdditionalItem() {
        Inventory inv = new Inventory(1);
        Item a = item("A");
        Item b = item("B");
        inv.add(a);
        inv.add(b);
        assertTrue(inv.isFull());
        assertSame(a, inv.get(0));
        assertFalse(inv.contains(b));
    }

    @Test
    void removingItemClearsSlot() {
        Inventory inv = new Inventory(2);
        Item a = item("A");
        inv.add(a);
        inv.remove(a);
        assertNull(inv.get(0));
        assertFalse(inv.contains(a));
        assertFalse(inv.isFull());
    }

    @Test
    void removingMissingItemChangesNothing() {
        Inventory inv = new Inventory(2);
        Item a = item("A");
        Item b = item("B");
        inv.add(a);
        inv.remove(b);
        assertSame(a, inv.get(0));
    }

    @Test
    void removedSlotCanBeReused() {
        Inventory inv = new Inventory(2);
        Item a = item("A");
        Item b = item("B");
        Item c = item("C");
        inv.add(a);
        inv.add(b);
        inv.remove(a);
        inv.add(c);
        assertSame(c, inv.get(0));
        assertSame(b, inv.get(1));
    }

    @Test
    void containsReturnsFalseForMissingItem() {
        Inventory inv = new Inventory(2);
        assertFalse(inv.contains(item("Missing")));
    }

    @Test
    void zeroCapacityInventoryIsFull() {
        Inventory inv = new Inventory(0);
        assertTrue(inv.isFull());
        inv.add(item("A"));
        assertEquals(0, inv.getItems().length);
    }

    @Test
    void removingNullClearsFirstEmptySlotOnly() {
        Inventory inv = new Inventory(2);
        Item a = item("A");
        inv.add(a);
        inv.remove(null);
        assertSame(a, inv.get(0));
        assertNull(inv.get(1));
    }
}
