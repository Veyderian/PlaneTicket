import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


public class AviaSoulsTest {

    @Test //1

    public void shouldLowPriceFromSipToLed() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24);
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12);
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17);
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22);
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 9);
        Ticket ticket7 = new Ticket("SIP", "LED", 60_000, 1, 3);
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 8);
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 17);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);
        Ticket[] expected = {ticket4, ticket2, ticket1, ticket5, ticket9, ticket3, ticket7};
        Ticket[] actual = manager.search("SIP", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test //2

    public void shouldLowPriceFromLEDToMSQ() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24);
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12);
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17);
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22);
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 9);
        Ticket ticket7 = new Ticket("SIP", "LED", 60_000, 1, 3);
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 8);
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 17);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);
        Ticket[] expected = {ticket8, ticket10, ticket6};
        Ticket[] actual = manager.search("LED", "MSQ");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //3

    public void shouldFastTimeFromSipToLedComparator() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24); //3
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);  //3
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12); //9
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17); //3
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22); //4
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 9);
        Ticket ticket7 = new Ticket("SIP", "LED", 60_000, 1, 3); //2
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 8); // 3
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 17);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket7, ticket1, ticket2, ticket4, ticket9, ticket5, ticket3};
        Ticket[] actual = manager.searchAndSortBy("SIP", "LED", comparator);


        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //4

    public void shouldFastTimeFromLedToMSQComparator() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24);
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12);
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17);
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22);
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 11);  // 4
        Ticket ticket7 = new Ticket("SIP", "LED", 60_000, 1, 3);
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);  // 2
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 9);
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 18); // 3

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket8, ticket10, ticket6};
        Ticket[] actual = manager.searchAndSortBy("LED", "MSQ", comparator);


        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //5 // пустой массив

    public void shouldLowPriceFromSipToLedEmpty() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24);
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12);
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17);
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22);
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 9);
        Ticket ticket7 = new Ticket("SIP", "LED", 60_000, 1, 3);
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 8);
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 17);


        Ticket[] expected = {};
        Ticket[] actual = manager.search("SIP", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //6 //поиск 1 -граничное значение

    public void shouldLowPriceFromMMKToOSL() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24);
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12);
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17);
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22);
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 9);
        Ticket ticket7 = new Ticket("SIP", "LED", 60_000, 1, 3);
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 8);
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 17);
        Ticket ticket11 = new Ticket("MMK", "OSL", 20_300, 15, 16);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);
        manager.add(ticket11);


        Ticket[] expected = {ticket11};
        Ticket[] actual = manager.search("MMK", "OSL");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //7

    public void shouldFastTimeFromSipToLedEmptyComparator() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24); //3
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);  //3
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12); //9
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17); //3
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22); //4
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 9);
        Ticket ticket7 = new Ticket("SIP", "LED", 60_000, 1, 3); //2
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 8); // 3
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 17);


        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("SIP", "LED", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //8

    public void shouldFastTimeFromMOWToROMComparator() {

        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SIP", "LED", 7_500, 21, 24);
        Ticket ticket2 = new Ticket("SIP", "LED", 6_000, 8, 11);
        Ticket ticket3 = new Ticket("SIP", "LED", 17_600, 3, 12);
        Ticket ticket4 = new Ticket("SIP", "LED", 4_600, 14, 17);
        Ticket ticket5 = new Ticket("SIP", "LED", 7_500, 18, 22);
        Ticket ticket6 = new Ticket("LED", "MSQ", 11_500, 7, 9);
        Ticket ticket7 = new Ticket("MOW", "ROM", 23_000, 1, 5); //4
        Ticket ticket8 = new Ticket("LED", "MSQ", 7_900, 13, 15);
        Ticket ticket9 = new Ticket("SIP", "LED", 15_000, 5, 8);
        Ticket ticket10 = new Ticket("LED", "MSQ", 9_600, 15, 17);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);


        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket7};
        Ticket[] actual = manager.searchAndSortBy("MOW", "ROM", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}


