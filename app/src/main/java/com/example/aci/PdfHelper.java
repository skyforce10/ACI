package com.example.aci;

import android.content.Context;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import AppClasses.Orders;
import AppClasses.OrdersDetails;

public class PdfHelper {
    private Orders ordersObj;
    private Context context;

    public PdfHelper(Context context,Orders ordersObj) {
        this.context = context;
        this.ordersObj = ordersObj;
    }

    public String CreatePdfFile(){
          String result_path="";
          //==================================
          Document document = new Document();
        PdfWriter pdfWriter;

        File pdfFile = null;
        try {
            // Get the directory for the app's private documents
            File pdfpath = new File(context.getExternalFilesDir(null), "OrdersDocs/");
            if (!pdfpath.exists()) {
                if (!pdfpath.mkdirs()) {

                }
            }
            pdfFile=new File(pdfpath,ordersObj.getOrderNumber()+".pdf");
            if(pdfFile.exists()){
                pdfFile.delete();
            }
            pdfFile.createNewFile();
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            Rectangle rectangle = new Rectangle(0, 30, 700, 800);
            pdfWriter.setBoxSize("rectangle", rectangle);
            document.open();
            Font OrderTitle = FontFactory.getFont("res/font/tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            OrderTitle.setStyle(Font.BOLD);
            OrderTitle.setSize(10f);
            PdfPTable tablecompinfo = new PdfPTable(1); // 1 columns.
            tablecompinfo.setWidthPercentage(100);
            tablecompinfo.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);//====for arabic direction
            PdfPCell cell1 = new PdfPCell(new Paragraph("Order number : "+ordersObj.getOrderNumber(), OrderTitle));
            cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cell1space = new PdfPCell(new Paragraph(""));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Date : "+ordersObj.getOrder_date(), OrderTitle));
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tablecompinfo.addCell(cell1);
            tablecompinfo.addCell(cell1space);
            tablecompinfo.addCell(cell2);

            List<OrdersDetails> lst=ordersObj.getOrdersDetailsList();
            for(OrdersDetails lstobj:lst){
                tablecompinfo.addCell(new PdfPCell(new Paragraph("Supplier : "+lstobj.getSupplier(), OrderTitle))).
                          setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablecompinfo.addCell(new PdfPCell(new Paragraph("Item Name : "+lstobj.getItems().getItemName()
                                  + "Qty : "+lstobj.getItems().getQty()
                                  + "Customer : "+lstobj.getItems().getCustomer(), OrderTitle))).
                        setHorizontalAlignment(Element.ALIGN_RIGHT);
            }
            document.add(tablecompinfo);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Close the document after writing
            document.close();
        }
          result_path=pdfFile.getAbsolutePath();
          //==================================
          return result_path;
    }
}
