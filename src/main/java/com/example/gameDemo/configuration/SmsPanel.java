package com.example.gameDemo.configuration;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class SmsPanel {
    public static String sendSms(String mob, String sms) throws IOException, KeyManagementException, NoSuchAlgorithmException {

        URL url2 = new URL("http://zpluscybertech.in/app/smsapi/index.php");
        HttpURLConnection con = (HttpURLConnection) url2.openConnection();


//        "http://zpluscybertech.in/app/smsapi/index.php?key=260B1EBD7AC2DC&campaign=11778&routeid=7&type=text&contacts="+mob+"&senderid=PCKING&msg="+sms+"&template_id=1207161684068756755"

//        http://zpluscybertech.in/app/smsapi/index.php?key=260B1EBD7AC2DC&campaign=11778&routeid=7&type=text&contacts=8208047263&senderid=PCKING&msg=verification OTP code 1234&template_id=1207161684068756755

        con.setRequestMethod("GET");

        con.setUseCaches(false);
        //con.setDoInput(true);

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", "260B1EBD7AC2DC");
        parameters.put("routeid", "7");
        parameters.put("type", "text");
        parameters.put("contacts", mob);
        parameters.put("senderid", "PCKING");
        parameters.put("sms", sms);
        parameters.put("template_id", "1207161684068756755");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        System.out.println("Server Response: " + content);
        return String.valueOf(content);

    }
}
