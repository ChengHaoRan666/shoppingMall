package com.chr.shoppingmall.mapper;


import com.chr.shoppingmall.entity.product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 程浩然
 * @Create: 2024/9/15 - 19:42
 * @Description: 商品表
 */
@Component
public interface productMapper {
    /**
     * 增
     */
    @Insert("insert into Products values (null, #{product.name}, #{product.productStoreID}, #{product.description}, #{product.price}, #{product.stockQuantity}, #{product.categoryId}, #{product.imageUrl}, #{product.listedDate}, #{product.status})")
    int addProduct(product product);

    /**
     * 删
     */
    @Delete("delete from products where ProductID = #{id}")
    int deleteProduct(Integer id);

    /**
     * 改
     */
    @Update("update products set " +
            "ProductName = #{product.name}, " +
            "ProductStoreID = #{product.productStoreID}, " +
            "Description = #{product.description}, " +
            "Price = #{product.price}, " +
            "StockQuantity = #{product.stockQuantity}, " +
            "CategoryID = #{product.categoryId}, " +
            "ImageURL = #{product.imageUrl}, " +
            "ListedDate = #{product.listedDate}, " +
            "Status = #{product.status} " +
            "where ProductID = #{id};")
    int updateProduct(Integer id,  product product);

    /**
     * 查一个
     */
    product selectProductById(Integer id);

    /**
     * 查全部
     */
    List<product> selectProductAll();

    /**
     * 根据商家id查询商品
     */
    List<product> selectProductByProductStoreID(Integer ProductStoreID);

    /**
     * 查各分类数量
     */
    @Select("select COUNT(*) from products where CategoryID = #{CategoryID}")
    Integer getCount(Integer CategoryID);

    /**
     * 关键词，商品类别，价格区间，三个搜索条件可有可无进行搜索
     */
    List<product> search(String keyword, Integer CategoryId, Double min, Double max);
}
