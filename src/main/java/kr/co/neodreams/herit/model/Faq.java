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
@Alias("faq")
public class Faq extends CommonModel  {
	private int seq;
	private int category;
	private String category_name;
	private String id;
	private String name;
	private String content;
	private String use_yn;
	private String reg_dt;
	private String re_content;
	private String re_reg_id;
	private String re_reg_dt;
}
