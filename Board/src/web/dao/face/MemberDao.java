package web.dao.face;

import web.dto.Member;

public interface MemberDao {

	public Member selectMemberByUserid(Member member);
	public int selectCntMemberByUseridUserpw(Member member);
	
}
