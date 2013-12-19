package taskservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SuppressWarnings("serial")
public class GoogletaskserviceServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String access_token,token_type,refresh_token;long experies_in;
		String code=req.getParameter("code");
		System.out.println(code);
		if(code!=null){
			
			URL url =null;
			HttpURLConnection con=null;
			String clientId="663001625774-390nod030v11k38dtg12ahe7qc69verc.apps.googleusercontent.com";
			String clientSecretKey="0td_v0CWkb7priMcvlfL5sNU";
			String redirectUri="http://localhost:8888/oauth2callback";
			String urlvalue="https://accounts.google.com//o/oauth2/token";
			
			String paramvalue="code="+code+"&redirect_uri="+redirectUri+"&client_id="+clientId+"&scope=&client_secret="+clientSecretKey+"&grant_type=authorization_code";
		    try{
			url=new URL(urlvalue);
			con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
            con.setDoInput(true);
	        con.setDoOutput(true);
	        DataOutputStream out=new DataOutputStream(con.getOutputStream());
	        out.writeBytes(paramvalue);
	        out.flush();
	        out.close();
	        BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String line=null;
	        StringBuffer outputValue = new StringBuffer();
	        while((line=br.readLine())!=null){
	        	outputValue.append(line);
	        	System.out.println(line);
	        		        }
	        System.out.println(outputValue.toString());
	        JSONObject jsonObject  = new JSONObject(); 
	        JSONParser parser = new JSONParser();
	        jsonObject = (JSONObject) parser.parse(outputValue.toString());
	         access_token = (String) jsonObject.get("access_token");
	        token_type = (String) jsonObject.get("token_type");
	        experies_in = (long) jsonObject.get("expires_in");
	        refresh_token = (String) jsonObject.get("refresh_token");
	        System.out.println(access_token);
	        System.out.println(token_type);
	        System.out.println(experies_in);
	        System.out.println(refresh_token);
	        
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		    
			
		}
		
		
	}
}
