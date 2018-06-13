package com.programasterapps.smartthingsmount;

import com.programasterapps.smartthingsmount.common.DifferenceChecker;
import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.updates.SwitchUpdates;
import com.programasterapps.smartthingsmount.devices.Switch;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SwitchChangeTest {

    @Test
    public void result_should_have_one_addition() {

        HashMap<String, Switch> mockBeforeSwitches = new HashMap<>();
        Switch one = new Switch("Office Light", "OL1", "on");
        Switch two = new Switch("Master Bed Light", "MBL", "off");

        mockBeforeSwitches.put(one.getName(), one);
        mockBeforeSwitches.put(two.getName(), two);

        HashMap<String, Switch> mockAfterSwitches = new HashMap<>();
        Switch afterOne = new Switch("Office Light", "OL1", "on");
        Switch afterTwo = new Switch("Master Bed Light", "MBL", "off");
        Switch afterThree = new Switch("Hallway Light", "HL", "on");

        mockAfterSwitches.put(afterOne.getName(), afterOne);
        mockAfterSwitches.put(afterTwo.getName(), afterTwo);
        mockAfterSwitches.put(afterThree.getName(), afterThree);

        HashMap<String, SwitchUpdates> updates = DifferenceChecker.getSwitchDifferences(mockAfterSwitches, mockBeforeSwitches);

        assertEquals(1, updates.size());
        assertTrue(updates.containsKey("Hallway Light"));
        assertEquals("Hallway Light", updates.get("Hallway Light").name);
        assertEquals("HL", updates.get("Hallway Light").aSwitch.getId());
        assertEquals("Hallway Light", updates.get("Hallway Light").aSwitch.getName());
        assertEquals("on", updates.get("Hallway Light").aSwitch.getValue());
    }

    @Test
    public void result_should_have_two_deletions() {

        HashMap<String, Switch> mockBeforeSwitches = new HashMap<>();
        Switch one = new Switch("Office Light", "OL1", "on");
        Switch two = new Switch("Master Bed Light", "MBL", "off");
        Switch three = new Switch("Hallway Light", "HL", "off");

        mockBeforeSwitches.put(one.getName(), one);
        mockBeforeSwitches.put(two.getName(), two);
        mockBeforeSwitches.put(three.getName(), three);

        HashMap<String, Switch> mockAfterSwitches = new HashMap<>();
        Switch afterOne = new Switch("Office Light", "OL1", "on");

        mockAfterSwitches.put(afterOne.getName(), afterOne);

        HashMap<String, SwitchUpdates> updates = DifferenceChecker.getSwitchDifferences(mockAfterSwitches, mockBeforeSwitches);

        assertEquals(2, updates.size());
        assertTrue(updates.containsKey("Hallway Light"));
        assertTrue(updates.containsKey("Master Bed Light"));
        assertFalse(updates.containsKey("Office Light"));

        assertEquals("Hallway Light", updates.get("Hallway Light").name);
        assertEquals("HL", updates.get("Hallway Light").aSwitch.getId());
        assertEquals("Hallway Light", updates.get("Hallway Light").aSwitch.getName());
        assertEquals("off", updates.get("Hallway Light").aSwitch.getValue());
        assertEquals(Action.Delete, updates.get("Hallway Light").action);

        assertEquals("Master Bed Light", updates.get("Master Bed Light").name);
        assertEquals("MBL", updates.get("Master Bed Light").aSwitch.getId());
        assertEquals("Master Bed Light", updates.get("Master Bed Light").aSwitch.getName());
        assertEquals("off", updates.get("Master Bed Light").aSwitch.getValue());
        assertEquals(Action.Delete, updates.get("Master Bed Light").action);
    }

    @Test
    public void result_should_have_two_value_updates() {

        HashMap<String, Switch> mockBeforeSwitches = new HashMap<>();
        Switch one = new Switch("Office Light", "OL1", "on");
        Switch two = new Switch("Master Bed Light", "MBL", "off");
        Switch three = new Switch("Hallway Light", "HL", "off");

        mockBeforeSwitches.put(one.getName(), one);
        mockBeforeSwitches.put(two.getName(), two);
        mockBeforeSwitches.put(three.getName(), three);

        HashMap<String, Switch> mockAfterSwitches = new HashMap<>();
        Switch afterOne = new Switch("Office Light", "OL1", "on");
        Switch afterTwo = new Switch("Master Bed Light", "MBL", "on");
        Switch afterThree = new Switch("Hallway Light", "HL", "on");

        mockAfterSwitches.put(afterOne.getName(), afterOne);
        mockAfterSwitches.put(afterTwo.getName(), afterTwo);
        mockAfterSwitches.put(afterThree.getName(), afterThree);

        HashMap<String, SwitchUpdates> updates = DifferenceChecker.getSwitchDifferences(mockAfterSwitches, mockBeforeSwitches);

        assertEquals(2, updates.size());
        assertTrue(updates.containsKey("Hallway Light"));
        assertTrue(updates.containsKey("Master Bed Light"));
        assertFalse(updates.containsKey("Office Light"));

        assertEquals("Hallway Light", updates.get("Hallway Light").name);
        assertEquals("HL", updates.get("Hallway Light").aSwitch.getId());
        assertEquals("Hallway Light", updates.get("Hallway Light").aSwitch.getName());
        assertEquals("on", updates.get("Hallway Light").aSwitch.getValue());
        assertEquals(Action.SwitchChanged, updates.get("Hallway Light").action);

        assertEquals("Master Bed Light", updates.get("Master Bed Light").name);
        assertEquals("MBL", updates.get("Master Bed Light").aSwitch.getId());
        assertEquals("Master Bed Light", updates.get("Master Bed Light").aSwitch.getName());
        assertEquals("on", updates.get("Master Bed Light").aSwitch.getValue());
        assertEquals(Action.SwitchChanged, updates.get("Master Bed Light").action);
    }
}
