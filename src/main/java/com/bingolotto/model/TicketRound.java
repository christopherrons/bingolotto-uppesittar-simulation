package com.bingolotto.model;

import java.util.Arrays;
import java.util.Objects;

public record TicketRound(int roundId, int[][] numbers) {

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId +
                ", numbers=" + Arrays.deepToString(numbers) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketRound that)) return false;
        return roundId == that.roundId && Arrays.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(roundId);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }
}
