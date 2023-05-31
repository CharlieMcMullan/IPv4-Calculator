import java.util.Scanner;

public class IPV4 {

    public static void main(String[] args) {//start of main
    	
        Scanner in = new Scanner(System.in);    
        int[] octets = new int[4];//initialises octet array
        int[] SubMask = new int[4];//initialises SubMask array abbreviation of Subnet Mask
        int[] NetAdd = new int[4];//initialises NetAdd array, abbreviation of Network Address
        int[] Wild = new int[4];//initialises Wild array, abbreviation of Wildcard Mask
        int[] Broadcast = new int[4];//initialises Wild array, abbreviation of Broadcast Address
        
        OpeningPrompt();
        
       
        System.out.println("Enter the IPv4 Address");
        for (int i = 0; i < 4; i++) {//start of for loop populating octet array
            octets[i] = in.nextInt();//input 
        }//end of populating for loop

        System.out.println("Enter the Subnet Mask");
        for (int i = 0; i < 4; i++) {//start of for loop populating SubMask array
            SubMask[i] = in.nextInt();//input 
        }//
        System.out.println(" ");
        System.out.print("IPv4 Address: ");
        PrintAddresses(octets);
        System.out.println(" ");
        System.out.print("IPv4 Binary: ");
        Binary(octets);//calls binary function
        System.out.println(" ");
        
        System.out.print("IPv4 Address: ");
        PrintAddresses(SubMask);
        System.out.println(" ");
        System.out.print("IPv4 Binary: ");
        Binary(SubMask);//calls binary function
        System.out.println(" ");
        
        for(int i = 0; i<4; i++) {
        	NetAdd[i]= SubMask[i] & octets[i];
        }
        
        System.out.print("Number of usables Addresses: ");
       int Hosts= UsableHosts(SubMask);
       System.out.print(Hosts);
       System.out.println(" ");
        System.out.println(" ");
        System.out.println(">>Reserved Addresses");
        System.out.print("Network Address: ");
        PrintAddresses(NetAdd);
        System.out.println(" ");
        System.out.print("Network Binary: ");
        Binary(NetAdd);//calls binary function
        
        for(int i = 0; i<4; i++) {
        	 Wild=Wildcard(SubMask);
        }
        

    System.out.print("Broadcast Address:");
    for(int i = 0; i<4; i++) {
    	Broadcast[i]= NetAdd[i] |  Wild[i];	
    }
    System.out.print("Broadcast Address:");
    PrintAddresses(Broadcast);
    System.out.println(" ");
    System.out.print("Broadcast Binary: ");
    Binary(Broadcast);//calls binary function
}//end of main
    public static void OpeningPrompt() {
	   	 for(int i = 0;i<28;i++) {//start of for loop
			 System.out.print("=");
		 }//end of for loop
	   	 System.out.println(" ");
    	 System.out.println("|Available Host calculator |");
    	 for(int i = 0;i<28;i++) {//start of for loop
    		 System.out.print("=");
    	 }//end of for loop
    	 System.out.println(" ");
    	 System.out.println("Please enter the addresses one octet at a time.");
    	 System.out.println("Type the 3 digit number followed by the enter key");
    	 System.out.println(" ");
    	 
    }

    public static void PrintAddresses(int[] array) {
    	 for (int i = 0; i < 4; i++) {//start of print loop to print IPv4
         	System.out.printf("%03d", array[i]);//prints each octet with leading zeros
         	if(i<3) {//adds '.'
         		System.out.print( ".");//adds "." for seperation
         	}//end of if statement
         }//end for loop
    	
    }
    
    public static void Binary(int[] array) {//function to convert addresses to binary
        System.out.print("Binary Address: ");
        for (int i = 0; i < array.length; i++) {//loops through each iteration 
            String binary = Integer.toBinaryString(array[i]);
            binary = String.format("%08d", Integer.parseInt(binary));//adds leading zeros to binary
            array[i] = Integer.parseInt(binary, 2); // assigns array[i] binary string as base 2
            System.out.print(binary);//prints out binary
            if (i < 3) {
                System.out.print(".");//divides octets
            }//end of if statement
        }//end of for loop
        System.out.println();
    }//end of binary

    public static int UsableHosts(int[] array) {//start of host calculator 
        int numberOfZeros = 0;//initializes a varible to count zeros
        for (int i = 0; i < array.length; i++) {//iterates through array
            String binary = Integer.toBinaryString(array[i]);//assigns string "binary" to iterated array
            for (int j = 0; j < binary.length(); j++) {//nested loop to check each character
                if (binary.charAt(j) == '1') {//if iterated character = 0 increment numberOfZeros 
                    numberOfZeros++;
                }//end of if
            }//end of nested for loop
        }//end of for loop 
        int power = 32-numberOfZeros;
        return (int) Math.pow(2,(power)) - 2;
        //int forces an int value, and the equation is 2 to the power of the number of zeros
        //Then 2 is subtracted as two addresses are reserved, the network address and broadcast address
    }//end of host calculator
    
    public static int[] Wildcard(int[] Sub){//takes in network address and subnet mask
    	int[]Wildcard = new int[4];//creates new array for wildcard address
    	for(int i = 0; i <4 ; i++) {//for loop to iterate through arrays
    		Wildcard[i] = ~Sub[i] & 0xFF;
    	}
    	return Wildcard;
    }
    
   // public static void Broadcast(int netAdd,int wild){//Calculates the broadcast address
  //  		int Broadcast= netAdd |  wild;	
   // 		System.out.print(Broadcast);
  //  }
   	 
}//end of IPv4


