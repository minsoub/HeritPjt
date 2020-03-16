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
	private String agree_dt;
	private String mark_chk;
	private String mark_dt;
	private String mem_sts;
	private String use_yn;
	private String reg_dt;
	private String last_login_dt;
}
