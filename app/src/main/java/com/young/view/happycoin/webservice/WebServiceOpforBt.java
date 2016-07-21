package com.young.view.happycoin.webservice;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.young.view.happycoin.HappyCoinApplication;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
public class WebServiceOpforBt {
    private static LinkedHashMap<String, Object> paramMap;
    public WebServiceOpforBt() {}
    /**
     * @param NameSpace
     * @param Url
     * @param methodname
     * @param paramMap
     * @return
     * @throws IOException
     */
    public SoapObject LoadResult(String NameSpace, String Url, String methodname, Map<String, Object> paramMap) throws IOException {

        SoapObject soapObject = new SoapObject(NameSpace, methodname);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        HttpTransportSE trans = new HttpTransportSE(Url, 600000);
        if (paramMap != null) {
            Iterator<Entry<String, Object>> iter = paramMap.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, Object> me = iter.next();
                soapObject.addProperty(me.getKey(), "".equals(me.getValue()) ? null : me.getValue());
            }

        }
        envelope.bodyOut = soapObject;
        envelope.setOutputSoapObject(soapObject);
        envelope.dotNet = true;
        trans.debug = true;
        try {
            trans.call(NameSpace + "." + methodname, envelope);
            System.out.println(trans.requestDump);
            SoapObject result = (SoapObject) envelope.bodyIn;
            return result;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param nameSpace webservice命名空间
     * @param url url地址
     * @param methodName webservice 方法
     * @param contentXml xml格式的字符串
     * @param serviceCode
     * @param source 01 ： 终端
     * @param target 1501：蒙东
     * @return
     * @throws IOException
     */
    public SoapObject webServiceQuery(String nameSpace , String url  , String methodName ,String contentXml , String serviceCode , String source , String target) throws IOException {
        paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("serviceCode", serviceCode);
        paramMap.put("source", source);
        paramMap.put("target", target);
        paramMap.put("contentXml", contentXml);
        HappyCoinApplication.loger.info("contentXml:" + contentXml);
        return LoadResult(nameSpace,
                url,
                methodName,
                paramMap);
    }

}
