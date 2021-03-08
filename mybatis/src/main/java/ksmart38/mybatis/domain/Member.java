package ksmart38.mybatis.domain;

public class Member {
	private String memberId;				// 회원 아이디
	private String memberPw;				// 회원 비밀번호
	private String memberName;				// 회원 이름
	private int memberLevel;				// 회원 등급
	private String memberLevelName;			// 회원 등급 이름
	private String memberAddr;				// 회원 주소
	private String memberEmail;				// 회원 이메일
	private String memberRegDate;			// 회원 등록일자
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberLevel=" + memberLevel + ", memberLevelName=" + memberLevelName + ", memberAddr=" + memberAddr
				+ ", memberEmail=" + memberEmail + ", memberRegDate=" + memberRegDate + "]";
	}
	
}
