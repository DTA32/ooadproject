package controller;

import model.TransactionHistoryDetailModel;
import model.TransactionHistoryHeaderModel;

import java.util.ArrayList;

public class TransactionController {

    public static ArrayList<TransactionHistoryDetailModel> getUserTransactionDetail(int userID){
        return TransactionHistoryDetailModel.getUserTransactionDetail(userID);

    }

    public static ArrayList<TransactionHistoryHeaderModel> getAllTransactionHeaderData(){
        return TransactionHistoryHeaderModel.getAllTransactionHeaderData();
    }

    public static ArrayList<TransactionHistoryDetailModel> getAllTransactionDetail(int transactionID){
        return TransactionHistoryHeaderModel.getAllTransactionDetail(transactionID);
    }

}
