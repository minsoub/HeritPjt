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
@Alias("payinfo")
public class PayInfo extends CommonModel  {
	private int seq;
	private int mem_seq;
	private String product_key;
	private int pay_amt;
	private int pay_cnt;
	private int pay_total;
	private String pay_sts;
	private String pay_sts_name;
	private String pay_dt;

	// search variable type
	private String searchFromDt;
	private String searchToDt;
}
