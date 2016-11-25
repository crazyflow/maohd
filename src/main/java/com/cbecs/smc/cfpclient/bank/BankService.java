package com.cbecs.smc.cfpclient.bank;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import com.cbecs.smc.cfpclient.RequestMessage;
import com.cbecs.smc.cfpclient.ResponseMessage;

public interface BankService
{
    ResponseMessage getCollection(RequestMessage request) throws JAXBException, IOException, ParseException;

    ResponseMessage settleExchange(RequestMessage request) throws JAXBException, IOException;

    ResponseMessage getSettleExchangeResult(RequestMessage request) throws JAXBException, IOException;

    ResponseMessage getExchangeRate(RequestMessage request) throws JAXBException, IOException;

    ResponseMessage updateAccountBook(RequestMessage request) throws JAXBException, IOException;

    ResponseMessage getAccountBooks(RequestMessage request);

    ResponseMessage pay(RequestMessage request);

    ResponseMessage checkBalance(RequestMessage request);

    ResponseMessage getAccountBookBalance(RequestMessage request);

    ResponseMessage getTradeDetail(RequestMessage request);
}