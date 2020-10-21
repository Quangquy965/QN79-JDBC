package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel> {
	NewModel findOne(Long id); // lấy một nếu gặp findOne();
	List<NewModel> findByCategoryId(Long categoryId); // lấy kiểu list ( danh sách )
	// hàm trả về id của bài viết 
	Long save(NewModel newModel); 
	// hàm update
	void update(NewModel updateNew);
	void delete(long id);
	
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
}
