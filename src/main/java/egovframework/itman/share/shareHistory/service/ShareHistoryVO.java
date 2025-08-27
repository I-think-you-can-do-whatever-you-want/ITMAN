package egovframework.itman.share.shareHistory.service;

public class ShareHistoryVO {
    private String hisIdx;       // 히스토리 PK
    private String eventType;    // 이벤트 종류

    private String ownerMemIdx;  // 소유자 MEM_IDX
    private String targetMemIdx; // 대상 MEM_IDX
    private String groIdx;       // 그룹 ID
    private String permMask;     // 권한 비트마스크

    private String actionBy;     // 행위자 MEM_IDX
    private String actionAt;     // 발생 시각
    private String note;         // 비고/사유

    public String getHisIdx() {
        return hisIdx;
    }

    public void setHisIdx(String hisIdx) {
        this.hisIdx = hisIdx;
    }

    public String getEventType() {
        if (eventType == null) return "";
        switch (eventType) {
            case "INVITE_CREATED":   return "초대 생성";
            case "INVITE_DELETED":   return "초대 삭제";
            case "REQUEST_CREATED":  return "요청 생성";
            case "REQUEST_APPROVED": return "요청 승인";
            case "REQUEST_REJECTED": return "요청 거절";
            case "PERMISSION_GRANTED": return "권한 부여";
            case "PERMISSION_REVOKED": return "권한 회수";
            case "PERMISSION_EXPIRED": return "권한 만료";
            default: return eventType; // 혹시 모르는 값은 그대로
        }
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOwnerMemIdx() {
        return ownerMemIdx;
    }

    public void setOwnerMemIdx(String ownerMemIdx) {
        this.ownerMemIdx = ownerMemIdx;
    }

    public String getTargetMemIdx() {
        return targetMemIdx;
    }

    public void setTargetMemIdx(String targetMemIdx) {
        this.targetMemIdx = targetMemIdx;
    }

    public String getGroIdx() {
        return groIdx;
    }

    public void setGroIdx(String groIdx) {
        this.groIdx = groIdx;
    }

    public String getPermMask() {
        return permMask;
    }

    public void setPermMask(String permMask) {
        this.permMask = permMask;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public String getActionAt() {
        return actionAt;
    }

    public void setActionAt(String actionAt) {
        this.actionAt = actionAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
