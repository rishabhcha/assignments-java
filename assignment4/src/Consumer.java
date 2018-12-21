import java.util.ArrayList;
import java.util.List;

public class Consumer extends Thread {

    private ProducerConsumer producerConsumer;

    // Save processed item in this list
    private List<Item> itemWithTaxList = new ArrayList<>();

    public Consumer(ProducerConsumer producerConsumer){
        this.producerConsumer = producerConsumer;
    }

    public List<Item> getItemWithTaxList() {
        return itemWithTaxList;
    }

    @Override
    public void run() {
        while (true){
            Item item = producerConsumer.consume();
            if (item == null) {
                break;
            }
            double tax;
            switch (item.getType()){
                case "manufactured":
                    tax = Tax.taxOnManufactured(item.getPrice());
                    break;
                case "imported":
                    tax = Tax.taxOnImport(item.getPrice());
                    break;
                default:
                    tax = Tax.taxOnRaw(item.getPrice());
                    break;
            }
            item.setTax(tax);
            item.setFinalPrice(item.getPrice()+tax);
            itemWithTaxList.add(item);
        }
    }

}
