package cuoiki.ltweb.impl;

import cuoiki.ltweb.services.ICommentService;


import java.util.List;

import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.CommentModel;

public class ICommentServiceImpl implements ICommentService{
	ICommentDAO comment_dao = new CommentDAOImpl();
	
	
	@Override
	public List<CommentModel> getAllComments(){
		return comment_dao.getAllComments();
	}
	
	@Override
	public void insert(CommentModel comment) {
		comment_dao.insert(comment);
	}
	@Override
	public List<CommentModel> getAllCommentsOfProduct(long product_id){
		return comment_dao.getAllCommentsOfProduct(product_id);
	}
	@Override
	public void deleteCommentOfUser(long idcomment,long iduser) {
		comment_dao.deleteCommentOfUser(idcomment, iduser);
	}
	@Override
	public void update(CommentModel comment) {
		comment_dao.update(comment);
	}

}
