import java.util.List;

/**
 * This program assume there exist a mysql database "inventory"
 * and a table "item(name varchar(20), price int, type varchar(20))"
 * And also change database credential in Producer.java class for successful connection
 * example of item table:
 * mysql> select * from item;
 * +--------+-------+--------------+
 * | name   | price | type         |
 * +--------+-------+--------------+
 * | item1  |   100 | raw          |
 * | item2  |   100 | manufactured |
 * | item3  |   500 | manufactured |
 * | item4  |   250 | raw          |
 * | item5  |   120 | imported     |
 * | item6  |   120 | raw          |
 * | item7  |   220 | imported     |
 * | item8  |   270 | imported     |
 * | item9  |   270 | manufactured |
 * | item10 |   270 | raw          |
 * | item11 |   475 | raw          |
 * | item12 |    40 | imported     |
 * | item13 |   140 | manufactured |
 * | item14 |   220 | imported     |
 * | item15 |    20 | imported     |
 * | item16 |   320 | manufactured |
 * | item17 |   820 | manufactured |
 * | item18 |    20 | raw          |
 * | item19 |  1120 | raw          |
 * | item20 |   550 | imported     |
 * +--------+-------+--------------+
 * 20 rows in set (0.00 sec)
 */

public class MainClass {

    public static void main(String[] args) throws InterruptedException
    {

        long start = System.currentTimeMillis();

        // Object of a class that has both produce()
        // and consume() methods
        ProducerConsumer pc = new ProducerConsumer();

        // Create producer thread
        Producer producer = new Producer(pc);

        // Create consumer thread
        Consumer consumer = new Consumer(pc);

        // Start both threads
        producer.start();
        consumer.start();

        // Wait for both thread to complete
        producer.join();
        System.out.println("Producer thread dead");
        consumer.join();
        System.out.println("Consumer thread dead\n");

        // Fetch item attributes and display
        List<Item> items = consumer.getItemWithTaxList();
        System.out.println("Item details: ");
        for (Item item: items){
            System.out.println(item);
        }

        long end = System.currentTimeMillis();
        System.out.println("\ntime taken to run code: " + (end - start));

    }

}
