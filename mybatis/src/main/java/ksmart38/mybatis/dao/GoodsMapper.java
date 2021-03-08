package ksmart38.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart38.mybatis.domain.Goods;

@Mapper
public interface GoodsMapper {
	public List<Goods> getGoodsList(String searchKey, String searchValue);

	public int addGoods(Goods goods);
}
