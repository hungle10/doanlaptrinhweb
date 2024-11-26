package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.CommentModel;

public interface ICommentDAO {

	public List<CommentModel> getAllComments();

	public void insert(CommentModel comment);

	public List<CommentModel> getAllCommentsOfProduct(long product_id);

	void deleteCommentOfUser(long idcomment, long iduser);

	void update(CommentModel comment);

}
