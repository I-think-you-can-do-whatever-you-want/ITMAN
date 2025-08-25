package egovframework.itman.shareRequest.service;

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

}
