package ksmart38.mybatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart38.mybatis.dao.MemberMapper;
import ksmart38.mybatis.domain.Member;

@Service
@Transactional
public class MemberService {
	
	
	private static final Logger log = LoggerFactory.getLogger(MemberService.class);

	/*
	// 1.DI 필드 주입방식
	@Autowired
	private MemberMapper memberMapper;
	*/
	/*
	// 2.DI SETTER 메소드 주입방식
	private MemberMapper memberMapper;
	
	@Autowired
	public void setMemberMapper(MemberMapper memberMapper) {
		this.memberMapper2 = memberMapper;
	}
	*/
	
	// 3.DI 생성자 메소드 주입방식
	private final MemberMapper memberMapper;
	
	// 3-1. spring framework 4.3 이후 부터 @Autowired 쓰지 않아도 주입 가능
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	/*
	@PostConstruct
	public void initialize() {
		log.debug("MemberService :::::: {}","initialize()");
		System.out.println("===============================");
		System.out.println("MemberService bean 등록");
		System.out.println("===============================");
	}
	*/
	
	public Map<String, Object> getLoginHistory(int currentPage) {
		
		// 페이지에 보여줄 행의 갯수
		int rowPerPage = 5;
		// login table 행의 시작점
		int startNum = 0;
		// 마지막 페이지
		int lastPage = 0;
		// 페이지 시작 
		int startPageNum = 1;
		// 페이지 끝
		int endPageNum = 10;
		
		// 페이지 알고리즘 (현재페이지-1)*페이지에 보여줄 행의 갯수 => LIMIT ?,5
		startNum = (currentPage - 1) * rowPerPage;
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startNum", startNum);
		paramMap.put("rowPerPage", rowPerPage);
		
		List<Map<String, Object>> loginHistory = memberMapper.getLoginHistory(paramMap);
		
		// 로그인 이력 행의 갯수
		double loginHistoryCount = memberMapper.getLoginHistoryCount();
		
		// 마지막 페이지
		lastPage = (int) Math.ceil(loginHistoryCount/rowPerPage);
		
		//7페이지 인 경우 동적 페이지 번호 구성
		if(currentPage > 6) {
			startPageNum 	= currentPage - 5;
			endPageNum		= currentPage + 4;
			
			if(endPageNum >= lastPage) {
				startPageNum = lastPage - 9;
				endPageNum = lastPage;
			}
		}
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("loginHistory", loginHistory);
		resultMap.put("lastPage", lastPage);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		
		return resultMap;
	}
	
	public String removeMember(String memberId, String memberPw) {
		String result = "회원삭제 실패";
		
		Member member = memberMapper.getMemberInfoById(memberId);
		
		if(memberPw.equals(member.getMemberPw())) {
			if(member.getMemberLevel() == 2) {
				memberMapper.removeOrderBySellerId(memberId);
				memberMapper.removeGoods(memberId);
			}
			
			if(member.getMemberLevel() == 3) {
				memberMapper.removeOrder(memberId);
			}
			
			memberMapper.removeLogin(memberId);
			memberMapper.removeMember(memberId);
			
			result = "회원삭제완료";
		}
		
		
		return result;
	}
	
	public int modifyMember(Member member) {
		return memberMapper.modifyMember(member);
	}
	
	public Member getMemberInfoById(String memberId) {
		Member member = memberMapper.getMemberInfoById(memberId);
		int memberLevel = 0;
		
		if(member != null && member.getMemberLevel() != 0) {
			memberLevel += member.getMemberLevel();
			
			switch (memberLevel) {
			case 1:
				member.setMemberLevelName("관리자");
				break;
			case 2:
				member.setMemberLevelName("판매자");
				break;
			case 3:
				member.setMemberLevelName("구매자");
				break;
			case 4:
				member.setMemberLevelName("회원");
				break;
			default:
				member.setMemberLevelName("비회원");
				break;
			}
		}
		
		return member;
	}
	
	public int addMember(Member member) {
		int result = memberMapper.addMember(member);
		return result;
	}
	
	
	public List<Member> getMemberList(Map<String, Object> paramMap){
		String searchKey = (String) paramMap.get("searchKey");
		if(searchKey != null) {
			if("memberId".equals(searchKey)){
				searchKey = "m_id";
			}else if("memberLevel".equals(searchKey)) {
				searchKey = "m_level";
			}else if("memberName".equals(searchKey)) {
				searchKey = "m_name";
			}else {
				searchKey = "m_email";				
			}
			paramMap.put("searchKey", searchKey);
		}
		
		/* MemberMapper member = new MemberMapper(); */
		List<Member> memberList = memberMapper.getMemberList(paramMap);
		// memberList 안에 객체  회원레벨 1: 관리자 ,2:판매자,3:구매자 etc 
		// memberLevelName : 관리자 or 판매자 or 구매자 etc
		return memberList;
	}
}
