package controller;

import model.PCBook;
import model.TransactionHistoryDetailModel;
import model.TransactionHistoryHeaderModel;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static void addTransaction(int TransactionID, List<PCBook> PCBooks, User StaffID){
        String dateNow = String.format("%tF", new Date());
        int transactionId = TransactionID;
        transactionId = TransactionHistoryHeaderModel.addNewTransactionHeader(StaffID, dateNow);
        if(transactionId <= 0){
            System.out.println("error!");
        }
        TransactionHistoryDetailModel.addTransactionDetail(transactionId, PCBooks);
    }

}
