package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@Alias("comcode")
public class Comcode {
	private int seq;
	private String code;
	private String codename;
	private String codegrp;
	private String use_yn;
	private String reg_dt;
}
