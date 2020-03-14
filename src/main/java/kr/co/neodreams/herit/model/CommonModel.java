package kr.co.neodreams.herit.model;

import lombok.Data;

@Data
public class CommonModel {
	private String searchString;		// search string 
	private String searchId;			// search id	
	private int pageNo;					// page number
	private int perPageCnt;				// Paging count per 1page
	private int pageRange;				// Paging block count
	private int totalCnt;				// total list count
	
	private int pageStartNo;			// page start number 
	private int pageEndNo;				// page end number
	
	public CommonModel()
	{
		this.pageNo = 1;
		this.perPageCnt = 10;
		this.pageRange = 10;
	}
}
