package org.zeprs.unittest.persistence;

import junit.framework.TestCase;
import org.cidrz.webapp.dynasite.valueobject.MenuItem;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;

import java.util.List;

/**
 * Tests the Menu object graph to ensure proper mapping.
 * This test currently relies on a known DB state.
 * TODO: remove this dependency
 */
public class MenuGraphTest extends TestCase {
    /**
     * tests the menu object graph, simulating menu building
     */
    public void testGraph() {
        List menu = null;
        try {
            menu = PersistenceManagerFactory.getInstance(MenuItem.class).getChildren(new Long(0));
            assertFalse("top level menu should have items.", menu.isEmpty());
            validateMenu(menu);
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }

    private void validateMenu(List menu) {
        assertNotNull("menuItem list was null", menu);

        MenuItem menuItem;
        for (int i = 0; i < menu.size(); i++) {
            menuItem = (MenuItem) menu.get(i);
            String type = menuItem.getType();
            assertNotNull("menu items all need type", type);
            if (type.equals(MenuItem.TYPE_MENU)) {
                try {
                    List children = PersistenceManagerFactory.getInstance(MenuItem.class).getChildren(menuItem.getId());
                    validateMenu(children);
                } catch (PersistenceException e) {
                    fail("problem with mapping?" + e.getMessage());
                }
            } else {
                if (menuItem.getTargetId() == null || menuItem.getTargetId().equals("")) {
                    assertNotNull("menu items must have a targetId or a textTarget. id=" + menuItem.getId(), menuItem.getTextTarget());
                }
            }
        }

    }

}
