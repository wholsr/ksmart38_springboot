package ksmart38.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart38.mybatis.dao.GoodsMapper;
import ksmart38.mybatis.domain.Goods;

@Service
@Transactional
public class GoodsService {
	private final GoodsMapper goodsMapper;
	
	public GoodsService(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}
	
	public int addGoods(Goods goods) {
		return goodsMapper.addGoods(goods);
	}
	
	public List<Goods> getGoodsList(String searchKey, String searchValue){
		if(searchKey != null) {
			if("goodsName".equals(searchKey)){
				searchKey = "g.g_name";
			}else if("memberId".equals(searchKey)) {
				searchKey = "m.m_id";
			}else if("memberName".equals(searchKey)) {
				searchKey = "m.m_name";
			}else {
				searchKey = "m.m_email";				
			}
		}
		
		return goodsMapper.getGoodsList(searchKey, searchValue);
	}
}
