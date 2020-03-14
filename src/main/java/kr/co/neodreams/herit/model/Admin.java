package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Administrator data model class
 * 
 * @author minso
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Alias("admin")
public class Admin extends CommonModel {
	private String id;
	private String id_check;
	private String name;
	private String pass;
	private String pass1;
	private String email;
	private String phone;
	private String htel;
	private String auth_tp;
	private String use_yn;
	private String remark;
	private String reg_dt;
	private String last_login_dt;
	
	public Admin()
	{
		super();
	}
}
