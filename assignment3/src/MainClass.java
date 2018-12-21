import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class MainClass {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        FamilyTree familyTree = new FamilyTree();

        while (true){
            System.out.println("\nEnter your choice:\n1.Add a new dependency to a tree\n" +
                    "2.Get the immediate parents of a node\n3.Get the immediate children of a node\n" +
                    "4.Get the ancestors of a node\n5.Get the descendants of a node\n" +
                    "6.Delete dependency from a tree\n7.Delete a node from a tree\n" +
                    "8.Add a new node to tree\n9.Exit");

            try {
                int choice = sc.nextInt();
                sc.nextLine();
                String id;
                switch (choice){
                    case 1:
                        System.out.print("\nEnter space separated parent and child id: ");
                        String[] ids = sc.nextLine().trim().split(" ");
                        familyTree.addDependency(ids[0], ids[1]);
                        break;
                    case 2:
                        System.out.print("\nEnter id of node: ");
                        id = sc.nextLine();
                        List<Node> parents = familyTree.getImmediateParents(id);
                        System.out.println("Parents:\n ");
                        printListOfNode(parents);
                        break;
                    case 3:
                        System.out.print("\nEnter id of node: ");
                        id = sc.nextLine();
                        List<Node> children = familyTree.getImmediateChildren(id);
                        System.out.println("Children:\n ");
                        printListOfNode(children);
                        break;
                    case 4:
                        System.out.print("\nEnter id of node: ");
                        id = sc.nextLine();
                        List<Node> ancestors = familyTree.getAncestors(id);
                        System.out.println("Ancestors:\n ");
                        printListOfNode(ancestors);
                        break;
                    case 5:
                        System.out.print("\nEnter id of node: ");
                        id = sc.nextLine();
                        List<Node> descendants = familyTree.getDescendants(id);
                        System.out.println("Descendants:\n ");
                        printListOfNode(descendants);
                        break;
                    case 6:
                        System.out.print("\nEnter space separated parent and child id: ");
                        String[] idss = sc.nextLine().trim().split(" ");
                        familyTree.deleteDependency(idss[0], idss[1]);
                        break;
                    case 7:
                        System.out.print("\nEnter id of node: ");
                        id = sc.nextLine();
                        familyTree.deleteNode(id);
                        break;
                    case 8:
                        System.out.print("\nEnter id of node: ");
                        id = sc.nextLine();
                        familyTree.addNewNode(id);
                        break;
                    case 9:
                        return;
                    default:
                        System.out.println("You have entered wrong choice please. Please check again and retry");
                        break;

                }
            }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter input in correct format");
            }catch (NullPointerException | IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }

    }

    private static void printListOfNode(List<Node> nodes) {
        for (Node node: nodes){
            System.out.println(node.getId());
        }
    }

}
