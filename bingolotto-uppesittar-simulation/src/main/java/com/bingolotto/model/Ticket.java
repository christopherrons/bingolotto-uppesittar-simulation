package com.bingolotto.model;

import java.util.List;
import java.util.Objects;

public record Ticket(int ticketId, List<TicketRound> ticketRounds) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ticket Id: " + ticketId).append("\n");
        for (TicketRound ticketRound : ticketRounds) {
            sb.append("Round: ").append(ticketRound.roundId()).append("\n");
            int[][] numbers = ticketRound.numbers();
            for (int[] number : numbers) {
                for (int v : number) {
                    sb.append(v).append("\t");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;
        return ticketRounds.equals(ticket.ticketRounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketRounds);
    }
}
