package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;


@WebServlet(urlPatterns = {"/api-admin-new"}) // nhận request vào  //(/api-admin-new) giao tiếp bằng api , để api để bt 
public class NewAPI extends HttpServlet{


	private static final long serialVersionUID = -915988021506484384L;
	
	// http method
	//	POST – Create: Tạo dữ liệu mới
	//	GET – Read: Lấy dữ liệu về
	//	PUT – Update: Cập nhật dữ liệu
	//	DELETE – Delete: Xóa dữ liệu
	
	@Inject
	private INewService newService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8"); // set tiếng việt 
		response.setContentType("application/json");
		NewModel newModel =  HttpUtil.of(request.getReader()).toModel(NewModel.class); // máp vào model
		newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		newModel = newService.save(newModel); // lưu và trả về newModel
		mapper.writeValue(response.getOutputStream(), newModel);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8"); // set tiếng việt 
		response.setContentType("application/json");
		NewModel updateNew =  HttpUtil.of(request.getReader()).toModel(NewModel.class); // máp vào model
		updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateNew = newService.update(updateNew);
		mapper.writeValue(response.getOutputStream(), updateNew);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8"); // set tiếng việt 
		response.setContentType("application/json");
		NewModel newModel =  HttpUtil.of(request.getReader()).toModel(NewModel.class); // máp vào model
		newService.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	

}
