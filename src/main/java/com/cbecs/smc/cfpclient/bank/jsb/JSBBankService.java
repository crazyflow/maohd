package com.cbecs.smc.cfpclient.bank.jsb;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cbecs.smc.cfpclient.RequestMessage;
import com.cbecs.smc.cfpclient.ResponseMessage;
import com.cbecs.smc.cfpclient.bank.BankService;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098RequestBody;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098RequestMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098ResponseBody.JSB200098RespItem;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098ResponseMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200142RequestMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200142ResponseBody;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200142ResponseBody.JSB200142RespItem;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200142ResponseMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200143RequestMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200143ResponseMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200144ResponseMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200145ResponseMessage;

@Service
public class JSBBankService implements BankService
{
    private static final Logger logger = LoggerFactory.getLogger(JSBBankService.class);

    @Value("${cfpclient.jsb.host}")
    private String host = "192.168.47.152";

    @Value("${cfpclient.jsb.port}")
    private int port = 10010;

    @Override
    public ResponseMessage getCollection(RequestMessage request) throws JAXBException, IOException, ParseException
    {
        byte[] pkg = pack(request, JSB200142RequestMessage.class);
        JSB200142ResponseMessage response = null;

        try (Socket socket = new Socket(host, port);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream())
        {
            // send
            out.write(pkg, 0, pkg.length);

            // receive
            response = (JSB200142ResponseMessage) unpack(in, JSB200142ResponseMessage.class);
        }

        if (!"0".equals(response.getHead().getSuccFlag()) || !"0000".equals(response.getHead().getRetCode())
                || !"1".equals(response.getHead().getFileFlag()))
        {
            return response;
        }

        String text = "";
        List<JSB200142RespItem> respItems = new ArrayList<JSB200142RespItem>();
        // String filename = FilenameUtils.concat(filePath,
        // response.getBody().getFileName());
//        String filename = FilenameUtils.concat(filePath, "a.bat");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(""), "gb2312")))
        {
            // read file
            boolean canRead = false;

            do
            {
                text = reader.readLine();
                canRead = StringUtils.isNotBlank(text);
                JSB200142RespItem item = null;

                if (canRead)
                {
                    String[] arr = StringUtils.split(text, (char) 0x02);
                    item = new JSB200142ResponseBody.JSB200142RespItem();
                    item.setSystemId(arr[0]);
                    item.setCmsCorpNo(arr[1]);
                    item.setCorpCnName(arr[2]);
                    item.setTrAcdt(DateUtils.parseDate(arr[3], "yyyy-MM-dd"));
                    item.setSerialNo(arr[4]);
                    item.setCurCode(arr[5]);
                    item.setAmt(Double.parseDouble(arr[6]));
                    item.setRemtCurCode(arr[7]);
                    item.setRemtAmt(Double.parseDouble(arr[8]));
                    item.setPayAcname(arr[9]);
                    item.setRemtBk(arr[10]);
                    item.setDetails(arr[11]);
                    item.setAcno(arr[12]);
                    item.setAsAcno(arr[13]);
                    item.setAsAcname(arr[14]);
                    item.setValueDt(DateUtils.parseDate(arr[15], "yyyy-MM-dd"));
                    item.setAddies(arr[16]);

                    respItems.add(item);
                }

            } while (canRead);

        }

        response.getBody().setRespItems(respItems);
        return response;
    }

    @Override
    public ResponseMessage settleExchange(RequestMessage request) throws JAXBException, IOException
    {
        byte[] pkg = pack(request, JSB200143RequestMessage.class);
        JSB200143ResponseMessage response = null;

        try (Socket socket = new Socket(host, port);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream())
        {
            // send
            out.write(pkg, 0, pkg.length);
            // receive
            response = (JSB200143ResponseMessage) unpack(in, JSB200143ResponseMessage.class);
        }
        return response;
    }

    @Override
    public ResponseMessage getSettleExchangeResult(RequestMessage request) throws JAXBException, IOException
    {
        byte[] pkg = pack(request, JSB200144ResponseMessage.class);
        JSB200144ResponseMessage response = null;
        try (Socket socket = new Socket(host, port);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream())
        {
            // send
            out.write(pkg, 0, pkg.length);
            // receive
            response = (JSB200144ResponseMessage) unpack(in, JSB200144ResponseMessage.class);
        }
        return response;
    }

    @Override
    public ResponseMessage getExchangeRate(RequestMessage request) throws JAXBException, IOException
    {
        byte[] pkg = pack(request, JSB200145ResponseMessage.class);
        JSB200145ResponseMessage response = null;
        try (Socket socket = new Socket(host, port);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream())
        {
            // send
            out.write(pkg, 0, pkg.length);
            // receive
            response = (JSB200145ResponseMessage) unpack(in, JSB200145ResponseMessage.class);
        }
        return response;
    }

    @Override
    public ResponseMessage updateAccountBook(RequestMessage request) throws JAXBException, IOException
    {
        JSB200098RequestMessage reqestMessage = (JSB200098RequestMessage) request;
        reqestMessage.getHead().setFileFlag("0");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char separator = '|';

        sb.append(
                "oper_flag|acno|cur_code|as_acno|as_acname|sup_as_acno|accrual_mode|dr_rate|cr_rate|pay_mode|exceed_limit|accrual_flag|assign_flag|notice_flag|pres_flag|inherit|");

        for (JSB200098RequestBody.JSB200098ReqItem item : reqestMessage.getBody().getRespItems())
        {
            sb.append(item.getOperFlag()).append(separator).append(item.getAcno()).append(separator)
                    .append(item.getCurCode()).append(separator).append(item.getAsAcno()).append(separator);
            sb.append(item.getAsAcname()).append(separator).append(item.getSupAsAcno()).append(separator)
                    .append(item.getAccrualMode()).append(separator).append(item.getDrRate()).append(separator);
            sb.append(item.getCrRate()).append(separator).append(item.getPayMode()).append(separator)
                    .append(item.getExceedLimit()).append(separator).append(item.getAccrualFlag()).append(separator);
            sb.append(item.getAssignFlag()).append(separator).append(item.getNoticeFlag()).append(separator)
                    .append(item.getPresFlag()).append(separator).append(item.getInherit()).append(separator);
            count++;
        }

        reqestMessage.getBody().setSerialRecord(sb.toString());
        // string filename = Guid.NewGuid().ToString();

        // reqestMessage.Body.FileName = filename;
        reqestMessage.getBody().setFieldNum("16");
        reqestMessage.getBody().setRecordNum(count + "");
        // File.WriteAllText(Path.Combine(FilePath, filename), sb.ToString(),
        // gb2312);

        byte[] pkg = pack(request, JSB200098RequestMessage.class);
        JSB200098ResponseMessage response = null;
        logger.info("JSBBankService 200098 socket param{}:" + host + "," + port);
        try (Socket socket = new Socket(host, port);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream())
        {
            // send
            out.write(pkg, 0, pkg.length);
            // receive
            response = (JSB200098ResponseMessage) unpack(in, JSB200098ResponseMessage.class);
        }

        count = Integer.parseInt(response.getBody().getRecordNum());

        if (0 == count)
        {
            return response;
        }

        String[] arr = response.getBody().getSerialRecord().split("|");
        List<JSB200098RespItem> respItems = new ArrayList<JSB200098RespItem>();
        JSB200098RespItem respItem = null;
        int i = 0;
        for (String s : arr)
        {
            if (0 == i)
            {
                i += 6;
                continue;
            }

            respItem = new JSB200098RespItem();
            respItem.setOperFlag(arr[i++]);
            respItem.setAcno(arr[i++]);
            respItem.setCurCode(arr[i++]);
            respItem.setAsAcno(arr[i++]);
            respItem.setAsAcname(arr[i++]);
            respItem.setErrInfo(arr[i++]);

            respItems.add(respItem);

            if (i == ((count + 1) * 6))
            {
                break;
            }
        }

        response.getBody().setRespItems(respItems);
        return response;
    }

    @Override
    public ResponseMessage getAccountBooks(RequestMessage request)
    {
        return null;
    }

    @Override
    public ResponseMessage pay(RequestMessage request)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseMessage checkBalance(RequestMessage request)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseMessage getAccountBookBalance(RequestMessage request)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseMessage getTradeDetail(RequestMessage request)
    {
        // TODO Auto-generated method stub
        return null;
    }

    private byte[] pack(RequestMessage request, Class<?> requestType) throws JAXBException, IOException
    {
        JAXBContext context = JAXBContext.newInstance(requestType);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "gb2312");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        byte[] data = null;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream())
        {
            marshaller.marshal(request, out);
            data = out.toByteArray();
        }

        int dataLength = data.length;
        int msgBodyLength = 2 + dataLength;
        int packageLength = 10 + msgBodyLength;

        logger.info("send msg body length = {}", msgBodyLength);

        byte[] pkg = new byte[packageLength];
        byte[] head = StringUtils.rightPad((msgBodyLength + ""), 10, ' ').getBytes();
        System.arraycopy(head, 0, pkg, 0, head.length);
        byte[] b2 = "00".getBytes();
        System.arraycopy(b2, 0, pkg, head.length, b2.length);
        System.arraycopy(data, 0, pkg, 12, data.length);

        String p = new String(pkg, "gb2312");
        logger.info("send msg = {}", p);

        return pkg;
    }

    private Object unpack(InputStream stream, Class<?> requestType) throws IOException, JAXBException
    {
        byte[] head = new byte[10];
        // read head
        stream.read(head, 0, head.length);
        int msgBodyLength = Integer.parseInt(new String(head));
        int dataLength = msgBodyLength - 2;
        logger.info("receive msg body length = {}", msgBodyLength);

        byte[] b2 = new byte[2];
        // read b2
        stream.read(b2, 0, b2.length);

        // read data of body
        byte[] data = new byte[dataLength];
        stream.read(data, 0, data.length);
        String responseXml = new String(data, "gb2312");
        logger.info("receive msg = {}", responseXml);

        JAXBContext context = JAXBContext.newInstance(requestType);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(responseXml))
        {
            return unmarshaller.unmarshal(reader);
        }
    }
}
