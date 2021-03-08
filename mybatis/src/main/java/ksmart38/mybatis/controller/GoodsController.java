package ksmart38.mybatis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart38.mybatis.dao.MemberMapper;
import ksmart38.mybatis.domain.Goods;
import ksmart38.mybatis.domain.Member;
import ksmart38.mybatis.service.GoodsService;

@Controller
public class GoodsController {

	private final GoodsService goodsService;
	private final MemberMapper memberMapper;
	
	public GoodsController(GoodsService goodsService, MemberMapper memberMapper) {
		this.goodsService = goodsService;
		this.memberMapper = memberMapper;
	}
	
	@GetMapping("/addGoods")
	public String addGoods(HttpSession session, Model model) {
		
		String memberLevel = (String) session.getAttribute("SLEVEL");
		if(memberLevel != null && "관리자".equals(memberLevel)) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("searchKey", "m_level");
			paramMap.put("searchValue", "판매자");
			List<Member> sellerList = memberMapper.getMemberList(paramMap);
			model.addAttribute("sellerList", sellerList);
		}
		
		return "goods/addGoods";
	}
	
	@PostMapping("/addGoods")
	public String addGoods(Goods goods) {
		
		if(goods.getGoodsSellerId() != null && !"".equals(goods.getGoodsSellerId())) {
			goodsService.addGoods(goods);	
		}		
		return "redirect:/goodsList";
	}
	
	@GetMapping("/goodsList")
	public String getGoodsList(@RequestParam(value="searchKey", required = false) String searchKey
							  ,@RequestParam(value="searchValue", required = false) String searchValue
							  ,Model model) {
		List<Goods> goodsList = goodsService.getGoodsList(searchKey, searchValue);
		System.out.println(goodsList);
		model.addAttribute("goodsList", goodsList);
		return "goods/goodsList";
	}
}
