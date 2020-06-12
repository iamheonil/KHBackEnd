package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Member;

public interface MemberService {

	public Member getLoginMember(HttpServletRequest req);
	
	public boolean login(Member member);
	
	public Member getMemberByUserid(Member member);
	
	
}
