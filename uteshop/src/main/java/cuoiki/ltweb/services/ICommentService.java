package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.CommentModel;

public interface ICommentService {

	public List<CommentModel> getAllComments();

	public void insert(CommentModel comment);

}
