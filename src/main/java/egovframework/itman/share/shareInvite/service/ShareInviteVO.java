package egovframework.itman.share.shareInvite.service;

public class ShareInviteVO {
    private String invIdx;     // 초대 IDX
    private String groIdx;     // 대상 그룹
    private String inviteCode; // 그룹 초대 코드
    private String regIdx; // 코드 발급자
    private String permMask;   // 기본 부여할 권한 (R=1,W=2,D=4)

    private String expDate;  // 만료 시각
    private String isActive;   // Y=유효, N=비활성
    private String delYn;      // 삭제 여부
    private String regDate;  // 생성일시
    private String shareCnt; //공유 받아 사용하는 유저 수
    private String rowNum;
    private String groName;

    public String getGroName() {
        return groName;
    }

    public void setGroName(String groName) {
        this.groName = groName;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getShareCnt() {
        return shareCnt;
    }

    public void setShareCnt(String shareCnt) {
        this.shareCnt = shareCnt;
    }

    public String getInvIdx() {
        return invIdx;
    }

    public void setInvIdx(String invIdx) {
        this.invIdx = invIdx;
    }

    public String getGroIdx() {
        return groIdx;
    }

    public void setGroIdx(String groIdx) {
        this.groIdx = groIdx;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRegIdx() {
        return regIdx;
    }

    public void setRegIdx(String regIdx) {
        this.regIdx = regIdx;
    }

    public String getPermMask() {
        return permMask;
    }

    public void setPermMask(String permMask) {
        this.permMask = permMask;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
