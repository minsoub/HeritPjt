package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Administrator authority model class
 * 
 * @author minso
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Alias("authchk")
public class AuthCheck extends CommonModel  {
	private int seq;
	private int new_seq;
	private String auth_name;
	private String auth_desc;
	private String a01;
	private String b01;
	private String b02;
	private String b03;
	private String b04;
	private String c01;
	private String c02;
	private String d01;
	private String d02;
	private String d03;
	private String d04;
	private String d05;
	private String d06;
	private String e01;
	private String e02;
	private String e03;
	private String f01;
	private String f02;
	private String f03;
	private String g01;
	private String g02;
	private String g03;
	private String g04;
	private String use_yn;
	private String reg_dt;
	private String mode;   // I/U/D
}
