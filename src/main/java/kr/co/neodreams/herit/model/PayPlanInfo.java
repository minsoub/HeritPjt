package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Alias("payplaninfo")
public class PayPlanInfo extends CommonModel  {
	private MultipartFile files;
	private int seq;
	private String subject;
	private String sts;
	private String category;
	private int period;
	private int price;
	private String file_name;
	private String use_yn;
	private String reg_dt;
}
