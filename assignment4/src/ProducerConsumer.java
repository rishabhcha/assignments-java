import java.util.LinkedList;

/*
This class has a list, producer adds items to list and consumer removes items.
In every run new order of producer producing and consumer consuming will be seen
*/
class ProducerConsumer {

    // Create a list shared by producer and consumer
    private LinkedList<Item> items = new LinkedList<>();

    // Function called by producer thread
    public void produce(Item item) {

        synchronized (this){

            /*
              There is no need to put producer on wait() since there is no boundary
              condition for overflow.
              If the requirement is to produce one->wait producer->consume one->wait consumer and so on then
              TODO: uncomment below lines and also uncomment notifyAll() in  consume() function
             */

//            while (items.size() > 0 ){
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            if (item != null) System.out.println("Producing item: " + item.getName());
            items.add(item);
            notifyAll();
        }

    }

    // Function called by consumer thread
    public Item consume() {

        synchronized (this){
            //Wait until there is a item to consume
            while (items.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Item item = items.removeFirst();
            if (item != null) System.out.println("Consuming item: " + item.getName() );

            /*
            If the requirement is to produce one->wait producer->consume one->wait consumer and so on then
            TODO: uncomment below line and also mentioned lines on produce() function
             */

//            notifyAll();

            return item;
        }

    }
}
