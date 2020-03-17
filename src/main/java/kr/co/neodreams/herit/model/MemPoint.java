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
@Alias("mempoint")
public class MemPoint extends CommonModel  {
	private int seq;
	private int mem_seq;
	private String content;
	private String point_ref;
	private String point_type;
	private int point;
	private int rest_point;
	private String use_yn;
	private String use_yn_name;
	private String reg_dt;
	
	// search
	private String searchFromDt;
	private String searchToDt;
}
