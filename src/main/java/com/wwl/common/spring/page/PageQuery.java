package com.wwl.common.spring.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * controller 查询入参
 * 
 * @author wuwl
 *
 */
@Setter
@Getter
public class PageQuery {

	// 当前页数
	@ApiModelProperty(value = "当前页数")
	private Integer page;
	// 每页条数
	@ApiModelProperty(value = "每页条数")
	private Integer size;
	// 排序字段
	@ApiModelProperty(value = "排序字段，比如 ‘id’")
	private String sort;
	// 排序类型
	@ApiModelProperty(value = "升序或降序，asc or desc")
	private String order;
	
	public PageQuery() {
		
	}
	
	public PageQuery(Integer page , Integer size){
		this.page = page;
		this.size = size;
	}
	
	public PageQuery(Integer page , Integer size , String sort , String order ){
		this.page = page;
		this.size = size;
		this.sort = sort;
		this.order = order;
	}
	
	public Pageable buildPageable() {
		return PageRequest.of(page, size);
	}



}
