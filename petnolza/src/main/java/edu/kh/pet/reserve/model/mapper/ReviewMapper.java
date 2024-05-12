package edu.kh.pet.reserve.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.pet.reserve.model.dto.Review;

@Mapper
public interface ReviewMapper {

	/** 삭제되지 않은 게시글 수
	 * @param roomId
	 * @return
	 */
	int getListCount(int roomId);

	/** 후기리스트
	 * @param roomId
	 * @param rowBounds
	 * @return
	 */
	List<Review> selectReviewList(int roomId, RowBounds rowBounds);
	
	
}
