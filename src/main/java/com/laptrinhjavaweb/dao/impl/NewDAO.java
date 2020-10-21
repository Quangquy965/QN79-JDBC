package com.laptrinhjavaweb.dao.impl;



import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;



public class NewDAO extends AbstractDAO<NewModel> implements INewDAO { // câu lệnh sql luôn để ở tầng DAO

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) { // get dữ liệu 
		String sql = "select * from news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);

	}
	
	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content, ");
		sql.append(" shortdescription, thumbnail, categoryid, createddate, createdby)");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), 
				newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCategoryId() ,newModel.getCreatedDate(),
				newModel.getCreatedBy());
		
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "select * from news WHERE id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) { // hàm update shortdescription content categoryid
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?  WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(), 
				updateNew.getContent(),
				updateNew.getCategoryId(), updateNew.getCreatedDate(), updateNew.getCreatedBy(),
				updateNew.getModifiedDate(), updateNew.getModifiedBy() ,updateNew.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
		
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
			
		} 
		return query(sql.toString(), new NewMapper());
		
	}

	@Override
	public int getTotalItem() {
		String sql = "select count(*) FROM news";
		return count(sql);
	}
	
	
	
	
//	// thêm 
//	// PreparedStatement thì có thể truyền tham số trực tiếp vào
//	// Statement ko thể truyền tham số vào
//	@Override
//	public Long save(NewModel newModel) {
//		ResultSet resultSet = null; 
//		Long id = null;
//		Connection connection = null;
//		PreparedStatement statement = null;
//		try {
//			String sql = "insert into news (title, content, categoryid) VALUES (?, ?, ?)"; // thêm dữ liệu vào table news
//			connection = getConnection(); // mở kết nối
//			connection.setAutoCommit(false);// không cho tự động commit
//			statement = connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS); // truyền câu sql vào đối tượng 
//			// set vào 3 tham số trong câu sql
//			statement.setString(1, newModel.getTitle());
//			statement.setString(2, newModel.getContent());
//			statement.setLong(3 , newModel.getCategoryId());
//			statement.executeUpdate(); // thực thi các câu sql và lưu dữ liệu xuống database
//			// Ví dụ về việc sử dụng Statement.getGeneratedKeys ()
//		    // để lấy giá trị tăng tự động (id)
//			resultSet = statement.getGeneratedKeys();
//			if (resultSet.next()) {
//				 id = resultSet.getLong(1);
//			}
//			
//			//commit() lưu xuống database 
//			connection.commit(); // tất cả tác vụ đã thành công thì commit và chỉ có commit dữ liệu mới đc thay đổi 
//			return id;
//		} catch (SQLException e) {
//			// kiểm tra connection
//			if (connection != null) {
//				try {
//					//rollback() Bạn có thể khôi phục (hoàn tác) mọi thay đổi được thực hiện trong quá trình giao dịch với ROLLBACK
//					connection.rollback();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//			return null;
//		}finally {
//			try {
//				if(connection != null) {
//					connection.close();
//				}
//				if(statement != null) {
//					statement.close();
//				}
//				if(resultSet != null) {
//					resultSet.close();
//				}
//			} catch (SQLException e) {
//				return null;
//			}
//		}
//
//	}
}
