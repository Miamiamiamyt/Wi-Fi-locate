import java.util.*;
import java.io.*;
class RSS {
  double[] val=new double[1000];
  double fin;
  public int len;
  public RSS() {
    int i;
    for(i=0;i<1000;i++) {
      val[i]=1.0;
    }//for
    fin=0.0;
    len=0;
  }
}
class R {
  public RSS[] rssi=new RSS[9];
  public R() {
    int i;
    for(i=0;i<9;i++) {
      rssi[i]=new RSS();
    }//for
  }//R 
  public void setRss(String f) {
    try {
      BufferedReader ri = new BufferedReader(new FileReader(f));
      String s;
      while((s=ri.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s," 	");
			while(st.hasMoreTokens()) {
			      int i;
				  st.nextToken();
				  //st.nextToken(); 
				  for(i=0;i<9;i++) {
				    for(rssi[i].len=0;rssi[i].len<1000;rssi[i].len++) {
				      if(rssi[i].val[rssi[i].len]==1.0)
				      break;
				    }//for
				    rssi[i].val[rssi[i].len]=Double.parseDouble(st.nextToken());
				    //System.out.println(i+" "+rssi[i].val[rssi[i].len]);
				  }//for
				  //System.out.println(i);
				  
				  st.nextToken();
				}
	  }//while
    }catch (IOException e) { 
           e.printStackTrace();  
    }//catch 
  }//setRSS
  public void count() {
    int i,j;
    double a=0.75;
    for(i=0;i<9;i++) {
      for(j=0;j<rssi[i].len+1;j++) {
        rssi[i].fin=a*rssi[i].val[j]+(1-a)*rssi[i].fin;
      }//for
    }//for
  }//count
}
class Ri {
  public R[] r=new R[18];
  public String[] f={"1.1.txt","1.2.txt","1.3.txt","1.4.txt","1.5.txt","1.6.txt","2.1.txt","2.2.txt","2.3.txt","2.4.txt","2.5.txt","2.6.txt","3.1.txt","3.2.txt","3.3.txt","3.4.txt","3.5.txt","3.6.txt"};
  public Ri() {
    int i;
    for(i=0;i<18;i++) {
      r[i]=new R();
      r[i].setRss(f[i]);
      r[i].count();
    }//for
  }//Ri
}//Ri
public class jw {
  public static void main(String[] args) {
    Ri ri=new Ri();
    FileWriter fw = null;
    try {
      //如果文件存在，则追加内容；如果文件不存在，则创建文件
      File f=new File("3.txt");
      fw = new FileWriter(f, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
      PrintWriter pw = new PrintWriter(fw);
      double[] ord=new double[9];
      int i,j,k;
      for(i=1;i<19;i++) {
        pw.print(i);
        pw.flush();
        for(j=0;j<9;j++) {
          ord[j]=ri.r[i-1].rssi[j].fin;
        }//for
        for(j=0;j<9;j++){
             for(k=0;k<9-j-1;k++){
                 if(ord[k]<ord[k+1]){
                     double temp=ord[k];
                     ord[k]=ord[k+1];
                     ord[k+1]=temp;
                 }//if
            }//for
        }//for
        for(j=0;j<9;j++) {
          if( ri.r[i-1].rssi[j].fin==ord[7]|| ri.r[i-1].rssi[j].fin==ord[8]) {
             ri.r[i-1].rssi[j].fin=1;
          }//if
        }//for
        for(j=0;j<9;j++) {
          pw.print(" "+ ri.r[i-1].rssi[j].fin);
          pw.flush();
        }//for
        pw.println();
        pw.flush();
      }//for
      try {
        fw.flush();
        pw.close();
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    //System.out.println(r.rssi[4].val[0]);
  }//public
}