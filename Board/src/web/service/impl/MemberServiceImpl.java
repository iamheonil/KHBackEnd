package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.MemberDao;
import web.dao.impl.MemberDaoImpl;
import web.dto.Member;
import web.service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		Member member = new Member();
		
		String pid = req.getParameter("userid");
		String ppw = req.getParameter("userpw");
		
		if (pid != null && !"".equals(pid)) {
			
			member.setId(pid);
			member.setPw(ppw);
			
		}
		
		return member;
	}

	@Override
	public boolean login(Member member) {
		return false;
	}

	@Override
	public Member getMemberByUserid(Member member) {
		return null;
	}

}
