package com.spmystery.episode.account;

import com.spmystery.episode.exception.DramaException;
import lombok.Data;

import static com.spmystery.episode.exception.DramaErrorCode.DO010;

@Data
public class DramaTotalCount {

    private Long id;

    private Integer total;


    public boolean isComplete(Integer indexSum) {
        Integer totalSum = ((1 + total) * total) / 2;
        if (indexSum > totalSum) {
            throw new DramaException(DO010);
        }

        return totalSum.equals(indexSum);
    }
}
