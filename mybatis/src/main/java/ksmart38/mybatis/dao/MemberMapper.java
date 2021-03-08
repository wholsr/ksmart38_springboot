package ksmart38.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmart38.mybatis.domain.Member;

@Mapper
public interface MemberMapper {
	// 로그인 이력 전체 행의 갯수
	public int getLoginHistoryCount();
	
	// 로그인이력 조회
	public List<Map<String, Object>> getLoginHistory(Map<String, Object> paramMap);

	// 회원삭제
	public int removeMember(String memberId);
	
	// 판매자 상품 삭제
	public int removeGoods(String memberId);
	
	// 판매자 주문 상품 삭제
	public int removeOrderBySellerId(String memberId);
	
	// 주문자 주문 삭제
	public int removeOrder(String memberId);
	
	// 회원 로그인 삭제
	public int removeLogin(String memberId);
	
	// 회원정보수정
	public int modifyMember(Member member);
	
	// 회원정보 조회
	public Member getMemberInfoById(String memberId);

	// 회원등록
	public int addMember(Member member);
	
	// 회원목록 조회
	public List<Member> getMemberList(Map<String, Object> paramMap);


}
