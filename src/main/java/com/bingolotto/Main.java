package com.bingolotto;

import com.bingolotto.generators.DraftGenerator;
import com.bingolotto.generators.TicketGenerator;
import com.bingolotto.model.Draft;
import com.bingolotto.model.DraftRound;
import com.bingolotto.model.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static final TicketGenerator TICKET_GENERATOR = new TicketGenerator();
    private static final DraftGenerator DRAFT_GENERATOR = new DraftGenerator();

    public static void main(String[] args) {

        List<Ticket> tickets = new ArrayList<>();
        System.out.println("Generating Tickets...");
        IntStream.range(0, 100000).forEach(k -> tickets.add(TICKET_GENERATOR.generateTicket()));
        int nrOfSimulations = 1000;
        int nrOfWinningTickets = 0;
        System.out.println("Running Simulation...");
        for (int simulation = 0; simulation < nrOfSimulations; ++simulation) {
            Draft draft = DRAFT_GENERATOR.generateDraft();
            for (Ticket ticket : tickets) {
                if (isWinningTicket(draft, ticket)) {
                    nrOfWinningTickets++;
                }
            }
        }
        System.out.println(String.format("Number of generated tickets: %s. Winning percentage: %s",
                tickets.size(), (1.0 / nrOfSimulations) * 100 * (nrOfWinningTickets / (double) tickets.size())));
    }

    private static boolean isWinningTicket(Draft draft, Ticket ticket) {
        for (DraftRound draftRound : draft.draftRounds()) {
            var draftRoundNumbers = new HashSet<>(draftRound.draftNumbers());
            int[][] ticketRoundNumbers = ticket.ticketRounds().get(draftRound.roundId() - 1).numbers();
            for (int row = 0; row < ticketRoundNumbers[0].length; row++) {
                int[] values = ticketRoundNumbers[row];
                if (draftRoundNumbers.containsAll(Arrays.stream(values).boxed().toList())) {
                    return true;
                }
            }
        }
        return false;
    }
}
