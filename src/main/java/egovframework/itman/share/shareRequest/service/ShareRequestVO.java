package egovframework.itman.share.shareRequest.service;

public class ShareRequestVO {
    private String reqIdx;         // 공유 요청 IDX
    private String invIdx;         // 초대 코드 참조
    private String reqMemIdx; // 요청자 MEM_IDX

    private String status;          // 승인 상태 (승인 대기/승인/거절)
    private String approvedBy;     // 승인/거절 처리자 MEM_IDX
    private String approvedDate;     // 승인/거절 시각
    private String note;            // 승인/거절 사유

    private String regDate;      // 생성일시
    private String modDate;      // 수정일시
    private String requesterName;
    private String groName;

    public String getGroName() {
        return groName;
    }

    public void setGroName(String groName) {
        this.groName = groName;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getReqIdx() {
        return reqIdx;
    }

    public void setReqIdx(String reqIdx) {
        this.reqIdx = reqIdx;
    }

    public String getInvIdx() {
        return invIdx;
    }

    public void setInvIdx(String invIdx) {
        this.invIdx = invIdx;
    }

    public String getReqMemIdx() {
        return reqMemIdx;
    }

    public void setReqMemIdx(String reqMemIdx) {
        this.reqMemIdx = reqMemIdx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
