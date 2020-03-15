package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@Alias("faqcategory")
public class FaqCategory {
	private int seq;
	private String category_name;
	private String reg_dt;
	private String mode;
}
