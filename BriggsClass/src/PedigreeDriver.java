/*
   This is the driver program for the pedigree printing project.
*/
import java.util.*;
import java.io.*;
public class PedigreeDriver{

   private static final String count = "count";
   private static final String eval = "evaluate";

   public static void main(String args[]){

      String
         fileName,
         command,
         commandAux;

      Scanner
         inptFile,
         stdIn = new Scanner(System.in);

      Database herdBook;

      if (args.length < 1){
         System.out.println("Must have an input file name on the command line.");
         return;
      }
      else{
         fileName = args[0];

         try{
            inptFile = new Scanner(new File(fileName));
            herdBook = new Database(inptFile);

            while (stdIn.hasNext()){
               command = stdIn.next();
               /*
                  you will need to adjust this comment
                  if you do extra credit

               if (count.equals(command)){
                  commandAux = stdIn.next();
                  herdBook.countOffspring(commandAux);
               }
               else if (eval.equals(command)){
                  commandAux = stdIn.next();
                  herdBook.evaluateOffspring(commandAux);
               }
               else
               */
                  herdBook.printPedigree(command);
            }
         }
         /*
            During development, you may want to print out the full message of
            the exception by using e.toString().  That is not recommended for
            production programs, since it gives hankers too much insight into
            the innards of the software, possibly allowing them to exploit it.
            Instead, production programs write error messages to internal log
            files that hackers wouldn't see.
         */
         catch(InputMismatchException e){
            System.out.println("Probably using nextInt or nextDouble"
            + " when the file input is not of that type.");

            System.out.println(e);
            e.printStackTrace();
         }
         catch(Exception e){
            System.out.println("Probably some problem with the input"
            + " data file or the keyboard input.");


            System.out.println(e);
            e.printStackTrace();

         }
      }
   }
}
