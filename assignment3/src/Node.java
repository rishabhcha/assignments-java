import java.util.ArrayList;
import java.util.List;

/**
 * It represents the node of the family tree.
 * id uniquely identifies the node
 * and parentList holds all the nodes of incoming edges(which are immediate parent to node)
 * whereas childList holds all the nodes of outgoing edges(which are immediate child to node)
 */
class Node {

    private String id;
    private List<Node> parentList;
    private List<Node> childList;

    public Node(String id) {
        this.id = id;
        parentList = new ArrayList<>();
        childList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getParentList() {
        return parentList;
    }

    public void setParentList(List<Node> parentList) {
        this.parentList = parentList;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public void setChildList(List<Node> childList) {
        this.childList = childList;
    }

    /**
     * Adds an incoming node to the current node
     */
    public void addComingInNode(Node node) {
        if (parentList.contains(node)) throw new IllegalArgumentException("Dependency already existing");
        parentList.add(node);
    }

    /**
     * Adds an outgoing node from the current node
     */
    public void addGoingOutNode(Node node) {
        if (childList.contains(node)) throw new IllegalArgumentException("Dependency already existing");
        childList.add(node);
    }
}
