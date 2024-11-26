package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.CommentModel;

public interface ICommentService {

	public List<CommentModel> getAllComments();

	public void insert(CommentModel comment);

	List<CommentModel> getAllCommentsOfProduct(long product_id);

	void deleteCommentOfUser(long idcomment, long iduser);

	void update(CommentModel comment);

}
