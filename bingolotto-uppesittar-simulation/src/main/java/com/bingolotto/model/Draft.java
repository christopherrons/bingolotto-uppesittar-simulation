package com.bingolotto.model;

import java.util.List;
import java.util.Objects;

public record Draft(int draftId, List<DraftRound> draftRounds) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Draft Id: " + draftId).append("\n");
        for (DraftRound draftRound : draftRounds) {
            sb.append("Round: ").append(draftRound.roundId()).append("\n");
            List<Integer> numbers = draftRound.draftNumbers();
            for (int number : numbers) {
                sb.append(number).append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Draft draft)) return false;
        return draftRounds.equals(draft.draftRounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(draftRounds);
    }
}
