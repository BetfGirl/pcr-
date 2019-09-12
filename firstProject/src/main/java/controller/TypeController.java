package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Type;
import service.Type_Service;
import utils.ReturnInfo;

@Controller
@RequestMapping("Type")
public class TypeController {
	@Autowired
	Type_Service service;
	
	@ExceptionHandler
	public void ex(Exception e) {
		e.printStackTrace();
	}
	
	@RequestMapping("index")
	public @ResponseBody ReturnInfo  index(String txt,ModelMap m,Integer page,Integer limit) {
		
		if(txt!=null) txt=" where book.name like '%"+txt+"%'";
		else txt="";
		return (ReturnInfo) service.select(txt, page, limit);
		
//		m.put("List", service.select());
//		System.out.println("into");
//		return "index";
	}
	
	@RequestMapping("add")
	public String  add(ModelMap m) {
		return "Type/edit";
	}
	
	@RequestMapping("edit")
	public String  edit(int id,ModelMap m) {
		m.put("info", service.selectById(id));
		return add(m);
	}
	
	@RequestMapping("insert")
	public ReturnInfo  insert(Type b,ModelMap m) {
		service.insert(b);
		return index(null, null, null, null);
	}
	@RequestMapping("update")
	public ReturnInfo  update(Type b,ModelMap m) {
		service.update(b);
		return index(null, null, null, null);
	}
	@RequestMapping("delete")
	public ReturnInfo  delete(int id,ModelMap m) {
		service.delete(id);
		return index(null, m, id, id);
	}
}
