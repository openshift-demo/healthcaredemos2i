import org.apache.camel.component.hl7.HL7MLLPCodec;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class Test1 extends CamelTestSupport {




    @Test
    public void testHL7Codec() throws Exception {
        String inMessage = "MSH|^~\\&|hl7Integration|hl7Integration|||||ADT^A01|||2.5|\r" +
                "EVN|A01|20130617154644\r" +
                "PID|1|465 306 5961||407623|Wood^Patrick^^^MR||19700101|1|||High Street^^Oxford^^Ox1 4DP~George St^^Oxford^^Ox1 5AP|||||||";


        inMessage = "MSH|^~\\&|MYSENDER|MYRECEIVER|MYAPPLICATION||200612211200||QRY^A19|1234|P|2.4\r" +
                "QRD|200612211200|R|I|GetPatient|||1^RD|0101701234|DEM||";

        String out = (String) template.requestBody("mina2:tcp://localhost:8888?sync=true&codec=#hl7codec", inMessage);
        System.out.println(out);
    }

    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry jndi = super.createRegistry();
        jndi.bind("hl7codec", new HL7MLLPCodec());
        return jndi;
    }
}
