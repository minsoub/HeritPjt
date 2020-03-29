package kr.co.neodreams.herit.model;

import org.apache.ibatis.type.Alias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 * 검진 결과 요청/결과 등록 Model Class
 * 
 * @author hist
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Alias("chkinfo")
public class ChkInfo  extends CommonModel  {
	private int		seq;
	private int		mem_seq;
	private String	id;
	private String	name;
	private String	phone;
	private String	birthday;
	private String	req_dt;
	private String	chk_dt;
	private int		hospital_no;
	private String	h_name;		//건강검진 병원명
	private String	subject;
	private String	item01;
	private String	item02;
	private String	item03;
	private String	item04;
	private String	item05;
	private String	item06;
	private String	item07;
	private String	item08;
	private String	item09;
	private String	item10;
	private String	item11;
	private String	item12;
	private String	item13;
	private String	item14;
	private String	item15;
	private String	item16;
	private String	item17;
	private String	item18;
	private String	item19;
	private String	item20;
	private String	result;
	private String	result_name;
	private String	result_subject;
	private String	result_content;
	private String	reg_id;
	private String	sts;
	private String	sts_name;
	private String	reg_dt;
	
	// 검색조건
	private String searchFromDt;
	private String searchToDt;
	private String searchLastFrom;
	private String searchLastTo;
	private String searchReqFrom;		// 검진데이터 - 건강검진 요청 : 요청일
	private String searchReqTo;
	private String searchRegFrom;		// 검진데이터 - 건강검진 요청 : 등록일
	private String searchRegTo;	
}
