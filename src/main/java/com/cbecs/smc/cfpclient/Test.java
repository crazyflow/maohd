package com.cbecs.smc.cfpclient;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cbecs.smc.cfpclient.bank.BankService;
import com.cbecs.smc.cfpclient.bank.jsb.JSBBankService;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098RequestBody;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098RequestBody.JSB200098ReqItem;
import com.cbecs.smc.commision.service.impl.SettlementApplicationServiceImpl;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098RequestMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098ResponseMessage;

public class Test
{

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws JAXBException, IOException, ParseException
    {
        //Test200098();
        
        String str = new String("beijing");  
        test(str);  
        System.out.print(str); 
    }
    
    public static void  test(String str) {  str = "hello"; } 
    public static void Test200098() throws JAXBException, IOException
    {
        JSB200098RequestMessage request = new JSB200098RequestMessage();
        request.getBody().setAcno("90260188000090840");
        request.getBody().setCurCode("01");

        // 新增
        List<JSB200098ReqItem> list = new ArrayList<JSB200098ReqItem>();
        JSB200098ReqItem item1 = new JSB200098ReqItem();
        // 操作类型 0-新增，1-删除，2-修改
        item1.setOperFlag("0");
        // 银行账号
        item1.setAcno("90260188000090840");
        // 人民币账户
        item1.setCurCode("01");
        item1.setAsAcno("1000037");
        item1.setAsAcname("20009453Test");
        // 上级账簿
        item1.setSupAsAcno("000000");
        list.add(item1);

        request.getBody().setRespItems(list);
        BankService bankService = new JSBBankService();
        JSB200098ResponseMessage response = (JSB200098ResponseMessage) bankService.updateAccountBook(request);
        // 只考虑添加一个虚拟子账号的情况,失败记录条数为0或1
        if ("0".equals(response.getBody().getStat()))
        {
            if ("0".equals(response.getHead().getSuccFlag()))
            {
                logger.info("success,param{}：" + response.getBody().getSerialRecord());
                // TODO 业务
            }
            else
            {
                logger.info("unknown,param{}：response.getBody().getStat()=" + response.getBody().getStat()
                        + ",response.getHead().getSuccFlag()=" + response.getHead().getSuccFlag());
            }

        }
        else if ("1".equals(response.getBody().getStat()))
        {
            // response.getHead().getSuccFlag() = 3 代表账簿已经存在或者上级账簿不存在
            // response.getHead().getSuccFlag() = 2 账户没有开通相应产品
         // response.getHead().getSuccFlag() = 1 
            // response.getHead().getSuccFlag() = 0 成功
            logger.info("error,param{}：" + response.getBody().getSerialRecord());
            logger.info("error,errorCode：" + response.getHead().getSuccFlag() + ",errorInfo："
                    + response.getHead().getRetInfo() + ",subErrorCode：" + response.getHead().getRetCode()
                    + ",subErrorInfo：" + response.getHead().getExtInfo());
            // TODO 业务
        }
        else
        {
            logger.info("non-existent param{}：response.getBody().getStat()=" + response.getBody().getStat());
        }
    }
}
