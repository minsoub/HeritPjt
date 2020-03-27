package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Member Model Class
 * 
 * @author hist
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Alias("member")
public class Member extends CommonModel {
	private int seq;
	private String id;
	private String name;
	private String pass;
	private String phone;
	private String birthday;
	private String sex;
	private String sex_name;
	private String agree_dt;
	private String mark_chk;
	private String mark_dt;
	private String mem_sts;
	private String mem_sts_name;
	private String use_yn;
	private String reg_dt;
	private String last_login_dt;
	
	private String menu;		// bottom menu (1 결제내역, 2 포인트내역, 3 미션현황, 4 검진데이터)
	
	// 결제내역 검색 조건
	// search variable type
	private String searchFromDt;
	private String searchToDt;	
	private String searchSts;
	private String pay_sts;		// 세부사항에서 검색 조건
}
