package service;

import model.Book;
import utils.ReturnInfo;

public interface Book_Service {
	
	
	public ReturnInfo select(String txt,Integer page,Integer max);
	
	public Book selectById(int id);
	
	public void insert(Book t);
	
	public void update(Book t);
	
	public void delete(int id);
}
