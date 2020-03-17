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
@Alias("mission")
public class Mission extends CommonModel  {
	private int seq;
	private int mem_seq;
	private String mission_key;
	private int result;
	private String succ_yn;
	private String succ_yn_name;
	private String reg_dt;
	private String upt_dt;
	
	// search
	private String searchFromDt;
	private String searchToDt;
}
