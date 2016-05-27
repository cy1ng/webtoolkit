package com.cying.webtoolkit.service.authentication.manager;

import java.util.ArrayList;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
  
import org.springframework.security.access.ConfigAttribute;  
import org.springframework.security.access.SecurityConfig;  
import org.springframework.security.web.FilterInvocation;  
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;  
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;;  
/** 
*  
* �����ڳ�ʼ��ʱ��Ӧ��ȡ��������Դ�����Ӧ��ɫ�Ķ��� 
*  
* @author Robin 
*  
*/  
public class MyInvocationSecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {  
   
	private PathMatcher urlMatcher = new AntPathMatcher();
	
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;  
  
    public MyInvocationSecurityMetadataSource() {  
         loadResourceDefine();  
     }  
  
    private void loadResourceDefine() {  
         resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
         Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();  
         ConfigAttribute ca = new SecurityConfig("ROLE_ADMIN");  
         atts.add(ca);  
         resourceMap.put("/index.jsp", atts);  
         resourceMap.put("/i.jsp", atts);  
     }  
  
    // According to a URL, Find out permission configuration of this URL.  
    public Collection<ConfigAttribute> getAttributes(Object object)  
            throws IllegalArgumentException {  
        // guess object is a URL.  
         String url = ((FilterInvocation)object).getRequestUrl();  
         Iterator<String> ite = resourceMap.keySet().iterator();  
        while (ite.hasNext()) {  
             String resURL = ite.next();  
            if (urlMatcher.match(resURL, url)) {  
                return resourceMap.get(resURL);  
             }  
         }  
        return null;  
     }  
  
    public boolean supports(Class<?> clazz) {  
        return true;  
     }  
      
    public Collection<ConfigAttribute> getAllConfigAttributes() {  
        return null;  
     }  
  
}  