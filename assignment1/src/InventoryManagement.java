import java.util.*;

public class InventoryManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.print("\nEnter item name: ");
                String name = sc.next();
                System.out.print("Enter item type: ");
                String type = sc.next();
                System.out.print("Enter item price: ");
                int price = sc.nextInt();
                double tax;
                switch (type.toLowerCase()){
                    case "raw":
                        tax = Tax.taxOnRaw(price);
                        System.out.printf("\nItem details-> name: %s, price: %d, tax: %f," +
                                " final price: %f", name, price, tax, price+tax);
                        break;
                    case "manufactured":
                        tax = Tax.taxOnManufactured(price);
                        System.out.printf("\nItem details-> name: %s, price: %d, tax: %f," +
                                " final price: %f", name, price, tax, price+tax);
                        break;
                    case "imported":
                        tax = Tax.taxOnImport(price);
                        System.out.printf("\nItem details-> name: %s, price: %d, tax: %f," +
                                " final price: %f", name, price, tax, price+tax);
                        break;
                    default:
                        System.out.print("\nPlease enter type of item correctly");
                        break;
                }
                System.out.print("\n\nDo you want to enter details of any other item (y/n):");
                String toContinue = sc.next();
                if (toContinue.equals("n")){
                    break;
                }
            } catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("\nPlease enter input in correct format. Lets try again!!");
            } catch (IllegalArgumentException e){
                System.out.println("\n"+e.getMessage()+". Lets try again!!");
            }
        }

    }

}
