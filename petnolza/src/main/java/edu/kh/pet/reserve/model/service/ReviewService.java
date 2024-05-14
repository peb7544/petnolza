package edu.kh.pet.reserve.model.service;

import java.util.Map;

import edu.kh.pet.reserve.model.dto.Review;

public interface ReviewService {

	/** 후기목록
	 * @param roomId
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectReviewList(int roomId, int cp);

	/** 리뷰상세
	 * 
	 * @param reviewNo
	 * @return review
	 */
	Review selectReviewDetail(int reviewNo); 
}
