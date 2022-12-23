package com.bingolotto.generators;

import com.bingolotto.model.Draft;
import com.bingolotto.model.DraftRound;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.bingolotto.util.BingoUtils.getColumnIndex;

public class DraftGenerator {

    private static final Set<Integer> columnIds = Set.of(1, 2, 3, 4, 5);
    private static final Random RANDOM = new Random();
    private final AtomicInteger nrOfDraftsGenerated = new AtomicInteger(0);
    private final Set<Draft> generatedDrafts = new HashSet<>();

    public Draft generateDraft() {
        List<DraftRound> draftRounds = new ArrayList<>();
        IntStream.range(1, 6).forEach(round -> draftRounds.add(new DraftRound(round, generateDraftRound())));
        Draft draft = new Draft(nrOfDraftsGenerated.getAndIncrement(), draftRounds);
        if (generatedDrafts.contains(draft)) {
            nrOfDraftsGenerated.getAndDecrement();
            return generateDraft();
        }

        generatedDrafts.add(draft);
        return draft;
    }

    private List<Integer> generateDraftRound() {
        Set<Integer> foundColumnIds = new HashSet<>();
        List<Integer> draftValues = new ArrayList<>();
        while (!foundColumnIds.equals(columnIds) || draftValues.size() < 20) {
            int draftValue = RANDOM.nextInt(1, 76);
            if (!draftValues.contains(draftValue)) {
                draftValues.add(draftValue);
                foundColumnIds.add(getColumnIndex(draftValue));
            }
        }

        return draftValues;
    }
}
