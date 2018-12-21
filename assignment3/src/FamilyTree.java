import java.util.*;

/**
 * Represents the dependency graph of node
 */
class FamilyTree {

    //map all the id's to the address where corresponding node is stored
    private HashMap<String, Node> nodeIdToAddressMap= new HashMap<>();

    public FamilyTree() {
    }

    public HashMap<String, Node> getNodeIdToAddressMap() {
        return nodeIdToAddressMap;
    }

    /**
     * pass two id's to add dependency between them
     * id1 ---> id2, id1 will be parent of id2 and id2 will be child of id1
     * If node doesn't exist then it creates one and then add dependency
     * Also cyclic dependency is taken care of
     * @param id1
     * @param id2
     */
    public void addDependency(String id1, String id2){
        Node node1 = nodeIdToAddressMap.get(id1);
        if (node1 == null){
            node1 = new Node(id1);
            nodeIdToAddressMap.put(id1, node1);
        }
        Node node2 = nodeIdToAddressMap.get(id2);
        if (node2 == null){
            node2 = new Node(id2);
            nodeIdToAddressMap.put(id2, node2);
        }
        try {
            //Check for cyclic dependency
            List<Node> ancestorsOfNode1 = getAncestors(id1);
            if (ancestorsOfNode1.contains(node2)){
                throw new IllegalArgumentException("It lead to cyclic dependency. Action can't be completed");
            }
            node1.addGoingOutNode(node2);
            node2.addComingInNode(node1);
            System.out.println("Dependency added successfully");
        }catch (NullPointerException e){
            //Parent node is leaf node. It do not have any ancestors. Doesn't affect functionality
            //continue adding dependency
            node1.addGoingOutNode(node2);
            node2.addComingInNode(node1);
            System.out.println("Dependency added successfully");
        }
    }

    /**
     * To get immediate parents of a node return parentList
     * @param id
     * @return
     */
    public List<Node> getImmediateParents(String id){
        Node node = nodeIdToAddressMap.get(id);
        if (node == null) throw new NullPointerException("No such node exist. Please try Again!!");
        if (node.getParentList().size() == 0) throw new NullPointerException("Node is root. No parents found");
        return node.getParentList();
    }

    /**
     * To get immediate children of a node return childList
     * @param id
     * @return
     */
    public List<Node> getImmediateChildren(String id){
        Node node = nodeIdToAddressMap.get(id);
        if (node == null) throw new NullPointerException("No such node exist. Please try Again!!");
        if (node.getChildList().size() == 0) throw new NullPointerException("Node is leaf. No child found");
        return node.getChildList();
    }

    public List<Node> getAncestors(String id){
        Node node = nodeIdToAddressMap.get(id);
        if (node == null){
            throw new NullPointerException("No such node found. Please try again!!");
        }
        if (node.getParentList().size() == 0){
            throw new NullPointerException("No ancestors found. It is a root node");
        }
        List<Node> ancestors = new ArrayList<>();
        Queue<Node> tempQueue = new LinkedList<>(node.getParentList());
        while (!tempQueue.isEmpty()){
            Node curr = tempQueue.poll();
            tempQueue.addAll(curr.getParentList());
            if (!ancestors.contains(curr)){
                ancestors.add(curr);
            }
        }
        return ancestors;
    }

    public List<Node> getDescendants(String id){
        Node node = nodeIdToAddressMap.get(id);
        if (node == null){
            throw new NullPointerException("No such node found. Please try again!!");
        }
        if (node.getChildList().size() == 0){
            throw new NullPointerException("No descendants found. It is a leaf node");
        }
        List<Node> descendants = new ArrayList<>();
        Queue<Node> tempQueue = new LinkedList<>(node.getChildList());
        while (!tempQueue.isEmpty()){
            Node curr = tempQueue.poll();
            tempQueue.addAll(curr.getChildList());
            if (!descendants.contains(curr)){
                descendants.add(curr);
            }
        }
        return descendants;
    }

    /**
     * To delete a dependency remove child from parent's childList and
     * remove parent from child's parentList
     * @param parentId
     * @param childId
     */
    public void deleteDependency(String parentId, String childId){
        Node parentNode = nodeIdToAddressMap.get(parentId);
        Node childNode = nodeIdToAddressMap.get(childId);
        if (parentNode == null || childNode == null){
            throw new NullPointerException("No such node found. Please check and try again!!");
        }
        if (!parentNode.getChildList().contains(childNode)){
            throw new IllegalArgumentException("There is no dependency between given nodes");
        }
        parentNode.getChildList().remove(childNode);
        childNode.getParentList().remove(parentNode);
        System.out.println("Dependency deleted successfully");
    }

    /**
     * Delete given node from its parent's childList and from child's parentList
     * and also remove it from hashMap
     * @param id
     */
    public void deleteNode(String id){
        Node node = nodeIdToAddressMap.get(id);
        if (node == null){
            throw new NullPointerException("No such node exist. Please try again");
        }
        Queue<Node> tempNodes = new LinkedList<>(node.getChildList());
        while (!tempNodes.isEmpty()){
            Node temp = tempNodes.poll();
            temp.getParentList().remove(node);
        }
        tempNodes.addAll(node.getParentList());
        while (!tempNodes.isEmpty()){
            Node temp = tempNodes.poll();
            temp.getChildList().remove(node);
        }
        nodeIdToAddressMap.remove(id);
        System.out.println("Node deleted successfully");
    }

    /**
     * Add a new empty node with given id. It won't have any parents or child
     * @param id
     */
    public void addNewNode(String id){
        if (nodeIdToAddressMap.get(id) != null){
            throw new IllegalArgumentException("Node with given Id already exist. Please recheck and try again");
        }
        Node node = new Node(id);
        nodeIdToAddressMap.put(id, node);
        System.out.println("Node added successfully");
    }

}
