package com.programasterapps.smartthingsmount;

import com.programasterapps.smartthingsmount.common.DifferenceChecker;
import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.updates.RoutineUpdates;
import com.programasterapps.smartthingsmount.devices.Routine;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoutineChangeTest {

    @Test
    public void result_should_have_one_addition() {
        HashMap<String, Routine> mockBeforeRoutines = new HashMap<>();
        Routine one = new Routine("Turn off Lights");
        Routine two = new Routine("Lock the Doors");

        mockBeforeRoutines.put(one.getName(), one);
        mockBeforeRoutines.put(two.getName(), two);

        HashMap<String, Routine> mockAfterRoutines = new HashMap<>();
        Routine afterOne = new Routine("Turn off Lights");
        Routine afterTwo = new Routine("Lock the Doors");
        Routine afterThree = new Routine("Wash the windows");

        mockAfterRoutines.put(afterOne.getName(), afterOne);
        mockAfterRoutines.put(afterTwo.getName(), afterTwo);
        mockAfterRoutines.put(afterThree.getName(), afterThree);

        HashMap<String, RoutineUpdates> updates = DifferenceChecker.getRoutineDifferences(mockAfterRoutines, mockBeforeRoutines);

        assertEquals(1, updates.size());
        assertTrue(updates.containsKey("Wash the windows"));
        assertEquals("Wash the windows", updates.get("Wash the windows").routine.getName());
        assertEquals(Action.Add, updates.get("Wash the windows").action);
    }

    @Test
    public void result_should_have_two_additions() {
        HashMap<String, Routine> mockBeforeRoutines = new HashMap<>();
        Routine one = new Routine("Turn off Lights");
        Routine two = new Routine("Lock the Doors");

        mockBeforeRoutines.put(one.getName(), one);
        mockBeforeRoutines.put(two.getName(), two);

        HashMap<String, Routine> mockAfterRoutines = new HashMap<>();
        Routine afterOne = new Routine("Turn off Lights");
        Routine afterTwo = new Routine("Lock the Doors");
        Routine afterThree = new Routine("Wash the windows");
        Routine afterFour = new Routine("Eat Pie");

        mockAfterRoutines.put(afterOne.getName(), afterOne);
        mockAfterRoutines.put(afterTwo.getName(), afterTwo);
        mockAfterRoutines.put(afterThree.getName(), afterThree);
        mockAfterRoutines.put(afterFour.getName(), afterFour);

        HashMap<String, RoutineUpdates> updates = DifferenceChecker.getRoutineDifferences(mockAfterRoutines, mockBeforeRoutines);

        assertEquals(2, updates.size());
        assertTrue(updates.containsKey("Wash the windows"));
        assertEquals("Wash the windows", updates.get("Wash the windows").routine.getName());
        assertEquals(Action.Add, updates.get("Wash the windows").action);
        assertTrue(updates.containsKey("Eat Pie"));
        assertEquals("Eat Pie", updates.get("Eat Pie").routine.getName());
        assertEquals(Action.Add, updates.get("Eat Pie").action);
    }

    @Test
    public void result_should_have_one_delete() {
        HashMap<String, Routine> mockBeforeRoutines = new HashMap<>();
        Routine one = new Routine("Turn off Lights");
        Routine two = new Routine("Lock the Doors");

        mockBeforeRoutines.put(one.getName(), one);
        mockBeforeRoutines.put(two.getName(), two);

        HashMap<String, Routine> mockAfterRoutines = new HashMap<>();
        Routine afterOne = new Routine("Turn off Lights");


        mockAfterRoutines.put(afterOne.getName(), afterOne);

        HashMap<String, RoutineUpdates> updates = DifferenceChecker.getRoutineDifferences(mockAfterRoutines, mockBeforeRoutines);

        assertEquals(1, updates.size());
        assertTrue(updates.containsKey("Lock the Doors"));
        assertEquals("Lock the Doors", updates.get("Lock the Doors").routine.getName());
        assertEquals(Action.Delete, updates.get("Lock the Doors").action);
    }

    @Test
    public void result_should_have_two_deletes() {
        HashMap<String, Routine> mockBeforeRoutines = new HashMap<>();
        Routine one = new Routine("Turn off Lights");
        Routine two = new Routine("Lock the Doors");
        Routine three = new Routine("Wash the Windows");

        mockBeforeRoutines.put(one.getName(), one);
        mockBeforeRoutines.put(two.getName(), two);
        mockBeforeRoutines.put(three.getName(), three);

        HashMap<String, Routine> mockAfterRoutines = new HashMap<>();
        Routine afterOne = new Routine("Turn off Lights");


        mockAfterRoutines.put(afterOne.getName(), afterOne);

        HashMap<String, RoutineUpdates> updates = DifferenceChecker.getRoutineDifferences(mockAfterRoutines, mockBeforeRoutines);

        assertEquals(2, updates.size());
        assertTrue(updates.containsKey("Lock the Doors"));
        assertEquals("Lock the Doors", updates.get("Lock the Doors").routine.getName());
        assertEquals(Action.Delete, updates.get("Lock the Doors").action);
    }

}
