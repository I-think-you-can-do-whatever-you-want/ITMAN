package egovframework.itman.share.sharePermission.service;

import egovframework.itman.common.Searching;

public class SharePermissionVO {
    private String shaIdx;         // 공유 IDX
    private String ownerMemIdx;   // 권한을 공유해준 멤버
    private String targetMemIdx;  // 공유를 받은 멤버
    private String targetMemName; // 공유받은 멤버 이름/별칭 (예: 인사팀 OOO 과장)
    private String groIdx;         // 공유 범위: 그룹 ID

    private String permMask;       // 권한 비트마스크 (R=1,W=2,D=4)
    private String isActive;       // Y=활성, N=중지
    private String validForm;      // 유효 시작(선택)
    private String validTo;        // 유효 종료(선택)
    private String revokedAt;      // 중지 시각
    private String revokedBy;      // 중지 처리자 MEM_IDX
    private String note;            // 비고/사유

    private String regDate;      // 생성일시
    private String modDate;
    private String rowNum;
    private String groName;
    private Searching searching;
    private String memName;

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public SharePermissionVO() {
        this.searching = new Searching();
    }

    public Searching getSearching() {
        return searching;
    }

    public void setSearching(Searching searching) {
        this.searching = searching;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getGroName() {
        return groName;
    }

    public void setGroName(String groName) {
        this.groName = groName;
    }

    public String getShaIdx() {
        return shaIdx;
    }

    public void setShaIdx(String shaIdx) {
        this.shaIdx = shaIdx;
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

    public String getTargetMemName() {
        return targetMemName;
    }

    public void setTargetMemName(String targetMemName) {
        this.targetMemName = targetMemName;
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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getValidForm() {
        return validForm;
    }

    public void setValidForm(String validForm) {
        this.validForm = validForm;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getRevokedAt() {
        return revokedAt;
    }

    public void setRevokedAt(String revokedAt) {
        this.revokedAt = revokedAt;
    }

    public String getRevokedBy() {
        return revokedBy;
    }

    public void setRevokedBy(String revokedBy) {
        this.revokedBy = revokedBy;
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