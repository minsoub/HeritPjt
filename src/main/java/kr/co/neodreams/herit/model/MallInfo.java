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
@Alias("mallinfo")
public class MallInfo extends CommonModel  {
	private int     seq;
	private int		product_key;
	private String	mall_sts;
	private int		amt;
	private int		qty;
	private int		rest_qty;
	private String	use_yn;
	private String	reg_dt;	
	
	private String	product_name;
	private String	product_img;
	private String	product_sts;
	private String	product_amt;
}
