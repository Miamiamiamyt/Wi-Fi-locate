import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.Math;
class S {
  double[] si=new double[9];
  public S(double s1,double s2,double s3,double s4,double s5,double s6,double s7,double s8,double s9) {
    si[0]=s1;
    si[1]=s2;
    si[2]=s3;
    si[3]=s4;
    si[4]=s5;
    si[5]=s6;
    si[6]=s7;
    si[7]=s8;
    si[8]=s9;
  }//s 
}

class CL {
  double[] val=new double[9];
  public CL(double s1,double s2,double s3,double s4,double s5,double s6,double s7,double s8,double s9) {
    val[0]=s1;
    val[1]=s2;
    val[2]=s3;
    val[3]=s4;
    val[4]=s5;
    val[5]=s6;
    val[6]=s7;
    val[7]=s8;
    val[8]=s9;
  }//CL
}
class R {
  public CL[] cl=new CL[400];
  double[] d=new double[18];
  double[] w=new double[18];
  public int setCL(String f1) {
    try {
      //String filename="text.txt";
      BufferedReader ri = new BufferedReader(new FileReader(f1));
      String s;
      int i=0;
      while((s=ri.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s," 	");
			while(st.hasMoreTokens()) {
				  st.nextToken();
				  //st.nextToken(); 
				  cl[i]=new CL(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				  st.nextToken();
				}
				i++;
	  }//while
	  return i-1;
    }catch (IOException e) { 
           e.printStackTrace();  
    }//catch 
    return 0;
  }//setCL
}

class dw extends JPanel {
  Image i=new ImageIcon("1.png").getImage();
  int[] point=new int[400];
  int[] x={100,500,225,450,100,500,300,100,500,100,500,300,225,450,450,225,225,450};
  int[] y={10,10,200,350,550,550,10,150,150,400,400,550,100,100,200,350,450,450};
  int len=0;
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(i,0,0,i.getWidth(null),i.getHeight(null),0,0,i.getWidth(null),i.getHeight(null),null);
    g.setColor(Color.red);
    int i;
    for(i=0;i<len;i++) {
      g.fillOval(x[point[i]-1],y[point[i]-1],30,30);
    }//for
  }//paint
  public void set(int p) {
    this.point[len]=p;
    len++;
    repaint();
  }//set
}

public class zxdw {
  dw ddd=new dw();
  S[] Si=new S[18];//参照
  R r=new R();//测试
  int num=0;//record
  String[] filename={"predict1<07>.txt","predict2<07>.txt"};
  int[] x={0,9,2,7,0,9,4,0,9,0,9,5,2,7,7,2,2,7};
  int[] y={12,12,7,5,0,0,12,9,9,3,3,0,10,10,7,5,2,2};
  public zxdw(String f1) {
    num=r.setCL(f1);
  }
  void setS() {
    try {
      String f="3.txt";
      BufferedReader ri = new BufferedReader(new FileReader(f));
      String s;
      while((s=ri.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s," 	");
			while(st.hasMoreTokens()) {
			      int i=Integer.parseInt(st.nextToken())-1;
				  Si[i]=new S(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				}
	  }//while
    }catch (IOException e) { 
           e.printStackTrace();  
    }//catch 
  }//setS
  public void distance(int m,int k) {
    //r.setCL();
    double[] temp=new double[9];
    double t=0.0;
    int i;
    for(i=0;i<9;i++) {
      if(Si[m].si[i]!=1.0) {
        temp[i]=Si[m].si[i]-r.cl[k].val[i];
        temp[i]*=temp[i];
      }//if
      else {
        temp[i]=0.0;
      }//else
    }//for
    for(i=0;i<9;i++) {
      t+=temp[i];
    }//for 
    r.d[m]=Math.sqrt(t);
    //System.out.println(d[m]);
  }//distance
  void confirm(int k,int filenum) {
    int i,j;
    for(i=0;i<18;i++) {
      distance(i,k);
    }//for
    double[] ttt=new double[18];
    for(i=0;i<18;i++) {
      ttt[i]=r.d[i];
    }//for
    for(i=0;i<18;i++){
      for(j=0;j<18-i-1;j++){
        if(ttt[j]>ttt[j+1]){//从小到大
          double temp=ttt[j];
            ttt[j]=ttt[j+1];
            ttt[j+1]=temp;
        }//if
      }//for
    }//for
    for(i=1;i<18;i++) {
      for(j=0;j<18;j++) {
        if(r.d[j]==ttt[i])
          r.d[j]=0.0;
      }
    }//for
    double temp1=0.0;
    for(j=0;j<18;j++) {
      if(r.d[j]!=0.0) {
        r.d[j]*=r.d[j];
        temp1+=1/r.d[j];
      }//if
    }//for
    for(i=0;i<18;i++) {
     if(r.d[i]!=0.0) {
       r.w[i]=1/(temp1*r.d[i]);
       //System.out.println(r.w[i]);
     }//if
     else
       r.w[i]=0.0;
    }//for
    double x1=0.0,y1=0.0;
   for(i=0;i<18;i++) {
      x1+=r.w[i]*x[i];
      y1+=r.w[i]*y[i];
    }
    x1=Math.round(x1);
    y1=Math.round(y1);
    System.out.print(x1+",");
    System.out.println(y1);
    locate((int)x1,(int)y1,filenum);
    //ddd.set(x1,y1);
  }//confirm
  public void locate(int x1,int y1,int i) {
    FileWriter fw = null;
    try {
      //如果文件存在，则追加内容；如果文件不存在，则创建文件
      File f=new File(filename[i]);
      fw = new FileWriter(f, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
      PrintWriter pw = new PrintWriter(fw);
    if(x1==0) {
      if(y1==12) {
        ddd.set(1);
        pw.println(1);
      }//12
      else if(y1==9) {
        ddd.set(8);
        pw.println(8);
      }//9
      else if(y1==3) {
        ddd.set(10);
        pw.println(10);
      }//3
      else if(y1==0) {
        ddd.set(5);
        pw.println(5);
      }//0
      else {
        pw.println(x1+","+y1);
      }
    }//0
    else if(x1==2) {
      if(y1==10) {
        ddd.set(13);
        pw.println(13);
      }//10
      else if(y1==7) {
        ddd.set(3);
        pw.println(3);
      }//7
      else if(y1==5) {
        ddd.set(16);
        pw.println(16);
      }//5
      else if(y1==2) {
        ddd.set(17);
        pw.println(17);
      }//2
      else {
        pw.println(x1+","+y1);
      }
    }//2
    else if(x1==4) {
      if(y1==12) {
        ddd.set(7);
        pw.println(7);
      }//12
      else {
        pw.println(x1+","+y1);
      }
    }//4
    else if(x1==5) {
      if(y1==0) {
        ddd.set(12);
        pw.println(12);
      }//0
      else {
        pw.println(x1+","+y1);
      }
    }//5
    else if(x1==7) {
      if(y1==10) {
        ddd.set(14);
        pw.println(14);
      }//10
      else if(y1==7) {
        ddd.set(15);
        pw.println(15);
      }//7
      else if(y1==5) {
        ddd.set(4);
        pw.println(4);
      }//5
      else if(y1==2) {
        ddd.set(18);
        pw.println(18);
      }//2
      else {
        pw.println(x1+","+y1);
      }
    }//7
    else if(x1==9) {
      if(y1==12) {
        ddd.set(2);
        pw.println(2);
      }//12
      else if(y1==9) {
        ddd.set(9);
        pw.println(9);
      }//9
      else if(y1==3) {
        ddd.set(11);
        pw.println(11);
      }//3
      else if(y1==0) {
        ddd.set(6);
        pw.println(6);
      }//0
      else {
        pw.println(x1+","+y1);
      }
    }//9
    pw.flush();
    try {
        fw.flush();
        pw.close();
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }//locate
  public void draw() {
      JFrame ff;
      JPanel p=new JPanel();
      ff=new JFrame();
      ff.setBounds(250,300,700,800);
      ff.add(p);
      ff.setVisible(true);
      p.setLayout(new GridLayout());
      p.add(ddd);
  }//draw
  public static void main(String[] args) {
    System.out.println("请输入要测量的数据文件text1.txt:");
    Scanner sc = new Scanner(System.in);   
    String s=sc.nextLine();
    zxdw Z=new zxdw(s);
    Z.setS();
    int i=0;
    for(i=0;i<Z.num;i++) {
      Z.confirm(i,0);
    } 
    Z.draw();
    System.out.println("请输入要测量的数据文件text2.txt:");  
    s=sc.nextLine();
    zxdw Z1=new zxdw(s);
    Z1.setS();
    for(i=0;i<Z1.num;i++) {
      Z1.confirm(i,1);
    } 
    Z1.draw();
    //System.out.println(Z.r.cl[0].val[0]);
  }//main
}