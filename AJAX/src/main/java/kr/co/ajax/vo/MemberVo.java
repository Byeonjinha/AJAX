package kr.co.ajax.vo;

import kr.co.ajax.vo.MemberVo;
import lombok.Data;

@Data
public class MemberVo {
	
	private String uid;
	private String name;
	private int gender;
	private String hp;
	private String email;
	private String bDay;
	private String ip;
	private String leaveDate;
	private String rdate;
	private String pass;

}
