package ba.unsa.etf.rpr;


public class App 
{
    public static void main( String[] args )
    {
        if(args.length != 1){
            System.out.println("Potreban je tacno jedan argument");
        }else{
            try{
                double broj = Double.parseDouble(args[0]);

                System.out.println("sinus(" + broj + ")= " + Math.sin(broj));
                System.out.println("faktorijel(" + (int)broj + ")= " + Math.faktorijel((int)broj));

            }catch (Exception e){
                System.err.println("Nevalidan argument, mora biti broj!");
            }
        }
    }
}
