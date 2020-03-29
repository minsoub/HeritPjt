package kr.co.neodreams.herit.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@Component("commonUtil")
public class CommonUtil {
	/**
	 * 현재 달을 리턴한다. 
	 * 형식 :: yyyy.MM
	 * 
	 * @return
	 */
    public static String getCurrentMonth() {
        java.util.Date dToday = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
        String today = dateFormat.format(dToday) ;
        return today;
    }
    
	/**
	 * 현재 년을 리턴한다. 
	 * 형식 :: yyyy
	 * 
	 * @return
	 */
    public static String getCurrentYear() {
        java.util.Date dToday = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String today = dateFormat.format(dToday) ;
        return today;
    }   
    
    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
     
        return ip;
    }

    
    /**
     * 월의 이전, 다음을 구해서 리턴한다.
     * 형식 : yyyy.MM
     * 
     * @param mon
     * @param mode
     * @return
     * @throws ParseException
     */
    public static String getSearchMonth(String mon, String mode) throws ParseException
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    	Date st = format.parse(mon+".01");
    	Calendar cal = new GregorianCalendar(Locale.KOREA);
    	cal.setTime(st);
    	if (mode.equals("prev"))
    	{
    		cal.add(Calendar.MONTH, -1);
    		
    	}else if(mode.equals("next"))
    	{
    		cal.add(Calendar.MONTH, 1);
    	}
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM");
    	return fmt.format(cal.getTime());
    }
    
    /**
     * Map을 json으로 변환한다.
     *
     * @param map Map<String, Object>.
     * @return JSONObject.
     * @throws JSONException 
     */
    public static JSONObject getJsonStringFromMap( Map<String, Object> map ) 
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }
        
        return jsonObject;
    }
    
    /**
     * List<Map>을 jsonArray로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     */
    public static JSONArray getJsonArrayFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.add( getJsonStringFromMap( map ) );
        }
        
        return jsonArray;
    }
    
    /**
     * List<Map>을 jsonString으로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return String.
     */
    public static String getJsonStringFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = getJsonArrayFromList( list );
        return jsonArray.toJSONString();
    }
 
    /**
     * JsonObject를 Map<String, String>으로 변환한다.
     *
     * @param jsonObj JSONObject.
     * @return Map<String, Object>.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj )
    {
        Map<String, Object> map = null;
        
        try {
            
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
            
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return map;
    }
 
    /**
     * JsonArray를 List<Map<String, String>>으로 변환한다.
     *
     * @param jsonArray JSONArray.
     * @return List<Map<String, Object>>.
     */
    public static List<Map<String, Object>> getListMapFromJsonArray( JSONArray jsonArray )
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        if( jsonArray != null )
        {
            int jsonSize = jsonArray.size();
            for( int i = 0; i < jsonSize; i++ )
            {
                Map<String, Object> map = CommonUtil.getMapFromJsonObject( ( JSONObject ) jsonArray.get(i) );
                list.add( map );
            }
        }
        
        return list;
    }    
    /**
     * 랜덤 패스워드 생성
     * 
     * @param len
     * @return
     */
    public static String getRamdomPassword(int len) { 
    	char[] charSet = new char[] { 
    			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
    			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' 
    			}; 
    	int idx = 0; 
    	StringBuffer sb = new StringBuffer(); 
    	System.out.println("charSet.length :::: "+charSet.length); 
    	for (int i = 0; i < len; i++) { 
    		idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거) 
    		System.out.println("idx :::: "+idx); 
    		sb.append(charSet[idx]); 
    	}
    	return sb.toString(); 
    }
 
}
