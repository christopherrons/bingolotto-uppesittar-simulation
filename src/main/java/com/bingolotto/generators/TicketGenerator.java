package com.bingolotto.generators;

import com.bingolotto.model.Ticket;
import com.bingolotto.model.TicketRound;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TicketGenerator {
    private static final int[] roundIds = {1, 2, 3, 4, 5};
    private final PermutationIterator<Integer> columnOneGenerator = new PermutationIterator<>(IntStream.range(1, 16).boxed().toList());
    private final PermutationIterator<Integer> columnTwoGenerator = new PermutationIterator<>(IntStream.range(16, 31).boxed().toList());
    private final PermutationIterator<Integer> columnThreeGenerator = new PermutationIterator<>(IntStream.range(31, 46).boxed().toList());
    private final PermutationIterator<Integer> columnFourGenerator = new PermutationIterator<>(IntStream.range(46, 61).boxed().toList());
    private final PermutationIterator<Integer> columnFiveGenerator = new PermutationIterator<>(IntStream.range(61, 76).boxed().toList());
    private final AtomicInteger nrOfTicketsGenerated = new AtomicInteger(0);
    private final Set<Ticket> generatedTickets = new HashSet<>();

    public Ticket generateTicket() {
        List<TicketRound> ticketRounds = new ArrayList<>();
        for (int roundId : roundIds) {
            int[][] numbers = new int[15][5];
            addEntry(1, columnOneGenerator.next(), numbers);
            addEntry(2, columnTwoGenerator.next(), numbers);
            addEntry(3, columnThreeGenerator.next(), numbers);
            addEntry(4, columnFourGenerator.next(), numbers);
            addEntry(5, columnFiveGenerator.next(), numbers);
            ticketRounds.add(new TicketRound(roundId, numbers));
        }

        Ticket ticket = new Ticket(nrOfTicketsGenerated.getAndIncrement(), ticketRounds);
        if (generatedTickets.contains(ticket)) {
            nrOfTicketsGenerated.getAndDecrement();
            return generateTicket();
        }

        generatedTickets.add(ticket);
        return ticket;
    }

    private void addEntry(int columnId, List<Integer> columnValues, int[][] numbers) {
        for (int row = 0; row < columnValues.size(); row++) {
            numbers[row][columnId - 1] = columnValues.get(row);
        }
    }
}
