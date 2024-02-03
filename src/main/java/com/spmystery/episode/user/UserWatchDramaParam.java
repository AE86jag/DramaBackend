package com.spmystery.episode.user;

import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.drama.entity.UserWatchDramaRecord;
import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.util.StringUtil;
import lombok.Data;

import java.math.BigDecimal;

import static com.spmystery.episode.account.entity.UserAccountRecord.ChangeType.D;
import static com.spmystery.episode.exception.DramaErrorCode.*;

@Data
public class UserWatchDramaParam {

    private Long dramaId;

    private Integer dramaIndex;

    private Integer dramaTotal;

    private String dramaTitle;

    public void check() {
        if (dramaId == null) {
            throw new DramaException(DD001);
        }

        if (dramaIndex == null) {
            throw new DramaException(DD002);
        }

        if (dramaTotal == null) {
            throw new DramaException(DD003);
        }
    }


    public UserWatchDramaRecord toUserWatchDramaRecord(String userId) {
        UserWatchDramaRecord record = new UserWatchDramaRecord();
        record.setDramaId(dramaId);
        record.setDramaIndex(dramaIndex);
        record.setDramaTotal(dramaTotal);
        record.setDramaTitle(dramaTitle);
        record.setUserId(userId);
        return record;
    }

    public UserAccountRecord toUserAccountRecord(BigDecimal amount, String userId) {
        UserAccountRecord record = new UserAccountRecord();
        record.setUserId(userId);
        record.setChangeAmount(amount);
        record.setChangeType(D);
        record.setChangeMessage("观看短剧:《" + dramaTitle + "(第" + dramaIndex + "集)》");
        return record;
    }
}
