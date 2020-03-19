package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Alias("hospital")
public class Hospital extends CommonModel  {
	private int seq;
	private String h_name;
	private String name;
	private String phone;
	private String addr;
	private String use_yn;
	private String reg_dt;
	
	public Hospital()
	{
		super();
	}
}
