package edu.kh.pet.reserve.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import edu.kh.pet.common.model.dto.Pagination;
import edu.kh.pet.reserve.model.dto.Review;
import edu.kh.pet.reserve.model.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewMapper mapper;
	
	@Override
	public Map<String, Object> selectReviewList(int roomId, int cp) {
		
		int listCount= mapper.getListCount(roomId);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Review> reviewList = mapper.selectReviewList(roomId, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("reviewList", reviewList);
		
		
		return map;
	}

	@Override
	public Review selectReviewDetail(int reviewNo) {
		
		Review review = mapper.selectReviewDetail(reviewNo); 
		
		return review;
	}   

	@Override
	public int reviewUpdate(Review inputReview) {
		
		return mapper.reviewUpdate(inputReview);
	}

	@Override
	public int selectReviewDelete(int reviewNo) {
		
		return mapper.ReviewDelete(reviewNo);
	}

	/*
	@Override
	public int reviewUpdate(int reviewNo) {
		
		
		return mapper.reviewUpdate(reviewNo);
	}
	*/

}
