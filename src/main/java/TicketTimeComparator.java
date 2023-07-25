import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        int time1 = t1.getTimeFrom() - t1.getTimeTo();
        int time2 = t2.getTimeFrom() - t2.getTimeTo();
        if (time1 < time2) {
            return -1;
        } else  if (time1 > time2) {
            return 1;
        } else {
        return 0;
    }
//        return time1 - time2;

  }
}