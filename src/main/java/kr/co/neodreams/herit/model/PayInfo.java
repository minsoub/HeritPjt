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
	private String name;		// 회원명
	private int p_key;			// Point Mall key
	private String product_name; 		// 상품명
	private int pay_amt;
	private int pay_cnt;
	private int pay_total;		
	private String pay_sts;
	private String pay_sts_name;
	private String send_sts;
	private String send_sts_name;
	private String pay_dt;
	private String pay_type_name;		// 구분 : 포인트 몰
	private int pay_count;				// 구매자수
	// search variable type
	private String searchFromDt;
	private String searchToDt;
}
