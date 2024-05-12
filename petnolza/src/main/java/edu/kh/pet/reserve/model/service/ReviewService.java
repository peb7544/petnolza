package edu.kh.pet.reserve.model.service;

import java.util.Map;

public interface ReviewService {

	/** 후기목록
	 * @param roomId
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectReviewList(int roomId, int cp); 
}
