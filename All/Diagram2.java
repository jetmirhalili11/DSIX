public class Diagram2{
   private double average=0,variance, dev;
   private double min=Double.POSITIVE_INFINITY;
   private double max=Double.NEGATIVE_INFINITY;
   private double[] a, b;
   
   public Diagram2(double []a){
      this.a=a;
      findAll();
      variance_dev();
      b= new double[a.length];
      b[0]=min;
      b[1]=max;
      b[2]=average;
      b[3]=variance;
      b[4]=dev;
   }
   
   private void findAll(){
      for(int i=0; i!=a.length; i++){
         average+=a[i];
         if(a[i]>max){
            max=a[i];
         }
         if(a[i]<min){
            min=a[i];
         }
      }
      average/=a.length;
   }
   
   public void variance_dev(){
      int n = a.length;
      double sum_squares = 0;
      for(double d : a)
      {
         sum_squares += Math.pow((d-average),2);
      }
      variance = sum_squares/n;
      dev= Math.sqrt(variance);
   }
   
   protected double[] getAll(){
      return b;
   }
}