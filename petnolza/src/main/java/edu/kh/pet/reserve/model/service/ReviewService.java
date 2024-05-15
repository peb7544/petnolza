package edu.kh.pet.reserve.model.service;

import java.util.Map;

import edu.kh.pet.reserve.model.dto.Review;

public interface ReviewService {

	/**
	 * 후기목록
	 * 
	 * @param roomId
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectReviewList(int roomId, int cp);

	/**
	 * 리뷰상세
	 * 
	 * @param reviewNo
	 * @return review
	 */
	Review selectReviewDetail(int reviewNo);

	/**
	 * 리뷰수정
	 * 
	 * @param reviewNo
	 * @return result
	 */
	int reviewUpdate(Review inputReview);

	int reviewInsert(Review inputReview);

	/**
	 * 리뷰삭제
	 * 
	 * @param reviewNo
	 * @return result
	 */
	int selectReviewDelete(int reviewNo);

	Integer getMaxNoForInsert();

	/**
	 * 리뷰수정
	 * 
	 * @param reviewNo
	 * @return result
	 */
	/*
	 * int reviewUpdate(int reviewNo);
	 */

}
