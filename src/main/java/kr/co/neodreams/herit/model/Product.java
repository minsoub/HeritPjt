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
@Alias("product")
public class Product extends CommonModel  {
	private MultipartFile files;
	private int		seq;
	private String	product_name;
	private String	product_img;
	private String	product_sts;
	private String	product_amt;
	private String	use_yn;
	private String	reg_dt;
	private int     buy_total;
}
