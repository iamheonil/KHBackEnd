package web.dao.face;

import web.dto.Member;

public interface MemberDao {

	/**
	 * userid 와 userpw가 일치하는 회원의 수를 조회
	 * 
	 * @param member - 조회할 회원의 정보
	 * @return int - 1(존재하는 회원), 0(존재하지 않는 회원), -1(에러)
	 */
	public int selectCntMemberByUseridUserpw(Member member);

	/**
	 * userid를 이용해 회원정보 조회
	 * 
	 * @param member - 조회할 회원
	 * @return Member - 조회된 결과 객체
	 */
	public Member selectMemberByUserid(Member member);

	/**
	 * 회원가입정보 삽입하기
	 * 
	 * @param member - 회원가입 정보 객체
	 */
	public void insert(Member member);

}
