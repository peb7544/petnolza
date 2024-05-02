package edu.kh.pet.community.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.pet.mypage.model.dto.Mtm;

@Mapper
public interface MtmMapper {

	/** 1:1문의 게시글 Count
	 * @return
	 */
	int getListCount();

	/** 1:1문의 조회
	 * @param rowBounds
	 * @return
	 */
	List<Mtm> selectMtmList(RowBounds rowBounds);

}
