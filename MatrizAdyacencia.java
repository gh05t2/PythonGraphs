package Grafos;
import java.util.Scanner;
public class MatrizAdyacencia {
    public static void main(String[] args){
        String ciudades[][]={{"Ciudad Real","Toledo"},
            {"Barcelona","Donosti"}};
        int viaje[]=new int[4];
        
        System.out.println("Elige un lugar de partida:");
        elegirCiudad(ciudades,viaje);
        System.out.println("Elige un lugar de destino");
        elsegirCiudad(ciudades,viaje);
        System.out.println(ciudades[viaje[0]][viaje[1]]);
    }
    
  /**
    Método que imprime por pantalla el contenido del String ciudades y permite elegir el origen y el destino
    @param ciudades
    @param viaje
  */
    public static void elegirCiudad(String[][] ciudades, int[] viaje){
        Scanner sc=new Scanner(System.in);
        int k=1,option;
        for (String[] ciudad : ciudades) {
            for (int j = 0; j<ciudades[0].length; j++){
                if(ciudad[j]!=ciudades[viaje[0]][viaje[1]]){
                    System.out.printf("%d)%s\n", k, ciudad[j]);
                }
                k++;
            }
        }
        option=sc.nextInt();sc.close();
        almacenarCiudad(ciudades,viaje,option);
    }
    
    /**
      Método que almacena la posición en la matriz de la ciudad elegida
      @param ciudades
      @param viaje
      @param option
    */
    public static void almacenarCiudad(String[][] ciudades,int[] viaje,
            int option){
        int k=0;
        for (int i=0;i<ciudades.length;i++){
            for(int j = 0; j<ciudades[0].length; j++,k++){
                if(k==option-1){
                    viaje[0]=i;viaje[1]=j;
                }else if(k==option-1 && viaje[0]!='\0'){
                    viaje[1]=i;viaje[2]=j;
                }
            }
        }
   }
}
