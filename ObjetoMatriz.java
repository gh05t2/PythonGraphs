package Grafos;
public class MatrizAdyacencia{
  public static main(String[] args){
    String ciudades[][]={["Ciudad Real","2","Toledo"],["7","Cuenca","3"],["Barcelona","1","Donosti"]};
    for(int i=0;i<ciudades.length;i++){
      for(int j=0;j<ciudades.length;j++)
        System.out.printf("%d ",ciudades[i][j]);
      System.out.println();
    }
  }
}
