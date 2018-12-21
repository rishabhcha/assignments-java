import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class FamilyTreeTest {

    private FamilyTree familyTree = new FamilyTree();

    @Before
    public void setUp() throws Exception {
        familyTree.addDependency("A", "B");
        familyTree.addDependency("A", "C");
        familyTree.addDependency("A", "D");
        familyTree.addDependency("B", "E");
        familyTree.addDependency("C", "F");
        familyTree.addDependency("C", "G");
        familyTree.addDependency("C", "E");
    }

    @Test
    public void addDependencyBetweenValidNode() {
        familyTree.addDependency("G", "H");
        HashMap<String, Node> map = familyTree.getNodeIdToAddressMap();
        assertTrue(map.containsKey("G") && map.containsKey("H") &&
                map.get("G").getChildList().contains(map.get("H")) &&
                map.get("H").getParentList().contains(map.get("G")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDependencyBetweenAlreadyDependentNodeShouldFail() {
        familyTree.addDependency("A", "C");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDependencyWhichCreateCycleShouldFial() {
        familyTree.addDependency("E", "A");
    }

    @Test
    public void getImmediateParents() {
        List<Node> parents = familyTree.getImmediateParents("C");
        assertEquals(1, parents.size());
    }

    @Test(expected = NullPointerException.class)
    public void getImmediateParentsOfRootNode() {
        List<Node> parents = familyTree.getImmediateParents("A");
    }

    @Test(expected = NullPointerException.class)
    public void getImmediateParentsOfNonExistingNode() {
        List<Node> parents = familyTree.getImmediateParents("Z");
    }

    @Test
    public void getImmediateChildren() {
        List<Node> children = familyTree.getImmediateChildren("B");
        assertEquals(1, children.size());
    }

    @Test(expected = NullPointerException.class)
    public void getImmediateChildrenOfLeafNode() {
        List<Node> children = familyTree.getImmediateChildren("G");
    }

    @Test(expected = NullPointerException.class)
    public void getImmediateChildrenOfNonExistingNode() {
        List<Node> children = familyTree.getImmediateChildren("Z");
    }

    @Test
    public void getAncestors() {
        List<Node> ancestors = familyTree.getAncestors("E");
        assertEquals(3, ancestors.size());
    }

    @Test(expected = NullPointerException.class)
    public void getAncestorsOfRootNodeShouldThrowError() {
        List<Node> ancestors = familyTree.getAncestors("A");
//        assertEquals(0, ancestors.size());
    }

    @Test(expected = NullPointerException.class)
    public void getAncestorOfNonExistingNodeShouldThrowError() {
        List<Node> ancestors = familyTree.getAncestors("Z");
        System.out.println(ancestors.size());
    }

    @Test
    public void getDescendants() {
        List<Node> descendants = familyTree.getDescendants("A");
        assertEquals(6, descendants.size());
    }

    @Test(expected = NullPointerException.class)
    public void getDescendantsOfLeafNodeShouldThrowError() {
        List<Node> descendants = familyTree.getDescendants("E");
//        assertEquals(0, descendants.size());
    }

    @Test(expected = NullPointerException.class)
    public void getDescendantsOfNonExistingNodeShouldThrowError() {
        List<Node> descendants = familyTree.getDescendants("Z");
        System.out.println(descendants.size());
    }

    @Test
    public void deleteDependency() {
        familyTree.deleteDependency("C", "E");
        assertFalse("C's child list shouldn't have E",
                familyTree.getImmediateChildren("C").contains(familyTree.getNodeIdToAddressMap().get("E")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deletingDependencyWhichDoesNotExistShouldThrowError() {
        familyTree.deleteDependency("A", "E");
    }

    @Test(expected = NullPointerException.class)
    public void deleteDependencyBetweenNonExistingNodeShouldThrowError() {
        familyTree.deleteDependency("Z", "E");
    }

    @Test
    public void deleteNode() {
        familyTree.deleteNode("B");
        assertNull("B should not be present in A's child or C's parent and neither in hashmap",
                familyTree.getNodeIdToAddressMap().get("B"));
    }

    @Test(expected = NullPointerException.class)
    public void deleteNodeWhichDoesNotExist() {
        familyTree.deleteNode("Z");
    }

    @Test
    public void addNewNode() {
        familyTree.addNewNode("H");
        assertEquals("H", familyTree.getNodeIdToAddressMap().get("H").getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNewNodeWhichAlreadyExisitShouldFail() {
        familyTree.addNewNode("A");
    }
}
