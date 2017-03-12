
package javaapplication2;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
public class JavaApplication2 {

    
    public static void main(String[] args) {
        String path = "C:\\Users\\Boss\\Downloads\\New folder\\Dev_Test_2016\\1.working_time.log";
    File file = new File(path);
    System.out.println("---รายได้รายวัน---");
    try {
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line[] = new String[100];
        int i=0;
        while((line[i] = br.readLine()) != null){	 
         i++;
        }        
        br.close(); 
        int index[] = new int[6];
        String data[][] = new String[i][6];
        index[0]=-1;
        int count=0,k=0,j=1,x=0;
        while(line[k] != null){  
            count=0;
            x=0;
            j=1;
        while(count<line[k].length()){            
            index[5] = line[k].length();
            if((line[k].charAt(count))=='|'){
                index[j] = count;
                data[k][x] = line[k].substring(index[j-1]+1,index[j] );
                j++;
                x++;
                if(x==4)
                    data[k][x] = line[k].substring(index[j-1]+1,index[j] );
            }                        
            count++;
        }
        k++;        
        }
        k=0;
        x=1;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f = new SimpleDateFormat("hh:mm");
        int timeF[][] = new int[i][2];
        int timeL[][] = new int[i][2];
        try{
            while(k<i){
            String name = data[k][0];
            Date date = new Date();
            date = df.parse(data[k][1]);
            Date date1 = new Date();
            if(data[k][2].length() == 0){
                data[k][2] = "00:00";
                date1 = f.parse(data[k][2]);
            }
            else if(data[k][2].length() > 0){
                date1 = f.parse(data[k][2]);
            }
            Date date2 = new Date();
            date2 = df.parse(data[k][3]);
            Date date3 = new Date();
            if(data[k][4].length() == 0){
                data[k][4] = "00:00";
                date3 = f.parse(data[k][4]);
            }
            else if(data[k][4].length() > 0){
                date3 = f.parse(data[k][4]);
            }
            timeF[k][0] = date1.getHours();
            timeF[k][1] = date1.getMinutes();
            timeL[k][0] = date3.getHours();
            timeL[k][1] = date3.getMinutes();
            double price;
            SimpleDateFormat sdf = new SimpleDateFormat("u dd/MM/yyyy");
            String dateDefult = sdf.format(date);
            String dateDefult2 = sdf.format(date2);
            String subDay = dateDefult.substring(0, 1);
            String subDay2 = dateDefult2.substring(0, 1);
            int dayBefore = Integer.parseInt(subDay);
            int dayAfter = Integer.parseInt(subDay2);
            System.out.print("ชื่อ "+data[k][0]+" ทำงานวันที่ "+data[k][1]+"-"+data[k][3]+" เวลา "+data[k][2]+"-"+data[k][4]);
            
            if(dayBefore<6){
                if((((timeF[k][0]==8)&&(timeF[k][1]<=5)) || (timeF[k][0]<8))  &&  ((timeL[k][0]==17)&&(timeL[k][1]<30))){
                    price = 290;
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }
                else if((((timeF[k][0]==8)&&(timeF[k][1]<=5)) || (timeF[k][0]<8)) &&(((timeL[k][0]==17)&&(timeL[k][1]>=30)) || (timeL[k][0]>17))){
                    price = 290*(1.5);
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }
                else if(((timeF[k][0]==8)&&(timeF[k][1]>5)) || (timeF[k][0]>8 && timeF[k][0]<17)){
                    price = 290-(0.604*(((timeF[k][0]-8)*60)+timeF[k][1]));
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }
                else{
                    price = 0.000;
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }
            }
            else{
                if((((timeF[k][0]==8)&&(timeF[k][1]<=5)) || (timeF[k][0]<8))  &&  ((timeL[k][0]==17)&&(timeL[k][1]<30))){
                    price = 290*1.5;
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }
                else if((((timeF[k][0]==8)&&(timeF[k][1]<=5)) || (timeF[k][0]<8)) &&(((timeL[k][0]==17)&&(timeL[k][1]>=30)) || (timeL[k][0]>17))){
                    price = 290*(2);
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }
                else if(((timeF[k][0]==8)&&(timeF[k][1]>5)) || (timeF[k][0]>8 && timeF[k][0]<17)){
                    price = 1.5*(290-(0.604*(((timeF[k][0]-8)*60)+timeF[k][1])));
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }       
                else{
                    price = 0.000;
                    data[k][5]=Double.toString(price);
                    System.out.println(" ได้รับค่าแรง "+price+" บาท");
                }
            }
            k++;
            }
            System.out.println("---รายได้รวม---");
            String total[][] = new String[16][2];
            k=0;
            double sum[] = new double[16];
            while(k<16){
                sum[k] = 0;
                k++;
            }
            k=0;    
            while(k<i){
                total[k%16][0] = data[k][0];
                sum[k%16] = Double.parseDouble(data[k][5])+sum[k%16];
                total[k%16][1] = Double.toString(sum[k%16]);
                k++;
            }
            k=0;
            while(k<16){
                System.out.println(total[k][0]+" "+total[k][1]);
                k++;
            }
        }catch (ParseException e) {
        e.printStackTrace();
        }
        


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
