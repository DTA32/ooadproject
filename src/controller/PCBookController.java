package controller;


import java.time.LocalDate;

import model.PC;
import model.PCBook;
import helper.Helper;
import javafx.scene.control.Alert.AlertType;

public class PCBookController {

    public static void addNewBook(Integer pcid, LocalDate booked_date){

        Integer user_id = Integer.parseInt(Helper.getCurrentUser().getUserID());
        if(user_id == null){
            Helper.showAlert(AlertType.ERROR, "Authentication error!");
            return;
        }

        if(booked_date == null){
            Helper.showAlert(AlertType.ERROR, "Please select a date to book a PC!");
            return;
        } else if (booked_date.isBefore(LocalDate.now())) {
            Helper.showAlert(AlertType.ERROR, "You can't book a PC for the past!");
            return;
        }

        String pcStatus = PC.getPCStatus(pcid);
        if(pcStatus.equalsIgnoreCase("Broken") || pcStatus.equalsIgnoreCase("Maintenance")){
            Helper.showAlert(AlertType.ERROR, "PC is not available for booking currently due to "+ pcStatus +"!");
            return;
        }

        if(!PCBook.isAvailablePC(pcid, booked_date)){
            Helper.showAlert(AlertType.ERROR, "PC is not available on this date!");
            return;
        }

        //nanti tambahin 1 parameter baru user_id
        boolean isSaved = PCBook.addNewBook(pcid, booked_date, user_id);
        if (isSaved) {
            Helper.showAlert(AlertType.INFORMATION, "PC Booked on date : "+ booked_date + " Successfully!");
        }
        else {
            Helper.showAlert(AlertType.ERROR, "Failed to book the PC!");
        }

    }

    public static void cancelBook(int book_id){
        LocalDate booked_date = PCBook.getBookingbyID(book_id);

        if(booked_date == null){
            Helper.showAlert(AlertType.ERROR, "No PC was found with that booking date");
            return;
        }

        LocalDate today = LocalDate.now();
        if(!today.isAfter(booked_date)){
            Helper.showAlert(AlertType.ERROR, "The scheduled booking has not passed today's date");
            return;
        }

        if(PCBook.DeleteBookData(book_id)){
            Helper.showAlert(AlertType.INFORMATION, "PC Booked on date : "+ booked_date + " Successfully Canceled!");
            return;
        }else{
            Helper.showAlert(AlertType.ERROR, "Failed to cancel the PC booking!");
            return;
        }

    }

}
