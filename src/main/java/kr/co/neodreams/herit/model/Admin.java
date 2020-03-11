package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("admin")
public class Admin {
	private String id;
	private String name;
	private String pass;
	private String email;
	private String phone;
	private String htel;
	private String auth_tp;
	private String use_yn;
	private String remark;
	private String reg_dt;
	private String last_login_dt;
}
