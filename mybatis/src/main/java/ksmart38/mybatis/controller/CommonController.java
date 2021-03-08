package ksmart38.mybatis.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart38.mybatis.service.MemberService;

@Controller
public class CommonController {
	
	private final MemberService memberService;
	
	public CommonController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/loginHistory")
	public String loginHistory(Model model
							  ,@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage) {
		
		Map<String, Object> resultMap = memberService.getLoginHistory(currentPage);
		
		model.addAttribute("currentPage"		, currentPage);
		model.addAttribute("lastPage"			, resultMap.get("lastPage"));
		model.addAttribute("loginHistoryList"	, resultMap.get("loginHistory"));
		model.addAttribute("startPageNum"	, resultMap.get("startPageNum"));
		model.addAttribute("endPageNum"	, resultMap.get("endPageNum"));
		
		return "login/loginHistory";
	}
	

	
}
