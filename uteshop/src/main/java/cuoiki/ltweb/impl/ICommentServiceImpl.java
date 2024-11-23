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

}
