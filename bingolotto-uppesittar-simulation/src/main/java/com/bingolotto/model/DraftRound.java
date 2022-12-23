package com.bingolotto.model;

import java.util.List;
import java.util.Objects;

public record DraftRound(int roundId, List<Integer> draftNumbers) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DraftRound that)) return false;
        return roundId == that.roundId && Objects.equals(draftNumbers, that.draftNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, draftNumbers);
    }

    @Override
    public String toString() {
        return "DraftRound{" +
                "roundId=" + roundId +
                ", draftNumbers=" + draftNumbers +
                '}';
    }
}
