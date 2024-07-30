package Tasks;

import java.util.Scanner; 

public class HotelReservationSystem {
	private int totalRooms = 100;
	protected static int availableRooms = 100;
	private int price  = 1200;
	private int bookedRooms = totalRooms-availableRooms;
	private int x = 0;
	String name;
	
	HotelReservationSystem(){
		Scanner input = new Scanner(System.in);
		System.out.println("\tWelcome to Sunset Cove Hotel!");
		System.out.println("");
		System.out.println("Resident's name: ");
		name = input.nextLine();
		System.out.println("\tHello " + name + ".");
	}
static class Menus extends HotelReservationSystem{
	Scanner input = new Scanner(System.in);
	String strComm;
	int intComm;
	
	void inputStr() {
	    System.out.print(">>> ");
	    strComm = input.nextLine();
	}
	void inputInt() {
	    System.out.print(">>> ");
	    intComm = input.nextInt();
	    input.nextLine();
	}
	
	Menus(){
		System.out.println("\tSelect your option...");
		System.out.println("\tDetails\t\tBook a Room\tAbout Us\tExit");
		while (true) {
			inputStr();
			if (strComm.equalsIgnoreCase("Details") || strComm.equalsIgnoreCase("Details ")) {
				hotelDetails();
			}
			else if(strComm.equalsIgnoreCase("About Us")) {
				System.out.println();
				System.out.println("\tWelcome to Sunset Cove Hotel!");
				System.out.println("Nestled on the pristine shores of our private beach, Sunset Cove Hotel is your ultimate summer getaway. "
						+ "\nWe offer an unparalleled blend of relaxation and luxury, "
						+ "\nMaking it the perfect destination for families looking to create lasting memories.");
				System.out.println();
				System.out.println("\tAbout Us");
				System.out.println("At Sunset Cove Hotel, we pride ourselves on providing exceptional service and world-class accomodations."
						+ "\nOur team, led by our experienced manager, Hani Srour, is dedicated to ensuring your stay is nothing short of perfect."
						+ "\nFrom the warm smiles of our front desk staff, Fatima sharara and Kassem Dakdouk, "
						+ "\nto the meticulous care provided by our housekeeping team, Joe Haddad and Rachid Sayah, "
						+ "\nEvery member of our staff is committed to making your stay as comfortable and enjoyable as possible.");
				System.out.println();
				System.out.println("\tWhat We Offer");
				System.out.println("> Prime Beachfront Location");
				System.out.println("> Refreshing Drinks");
				System.out.println("> Luxurious Accommodations");
				System.out.println("> Family-Friendly Environment");
				System.out.println();
			}
			else if(strComm.equalsIgnoreCase("Exit")){
				System.out.println("Thank You For Passing by!");
				break;
			}
			else if (strComm.equalsIgnoreCase("Book a Room")) {
			    System.out.println("\tNumber of rooms to book: ");
			    inputInt();
			    bookRoom(intComm);
			    billing();
			    
			    System.out.println("\tConfirm your booking (type 'confirm' or 'cancel'): ");
			    inputStr();
			    if (strComm.equalsIgnoreCase("confirm") || strComm.equalsIgnoreCase("confirm ")) {
			        System.out.println("Booking confirmed.");
			    } else if (strComm.equalsIgnoreCase("cancel")) {
			    	reverseBooking(intComm);
			        System.out.println("Booking canceled.");
			    } else {
			        System.out.println("Invalid input. Please type 'confirm' or 'cancel'.");
			    }
			}	
			else {
					System.out.println("\tSorry! Only " + availableRooms + " rooms are available.");
			}
		}
	}
}
	boolean bookRoom(int room) {
		if (room == 0) {
			System.out.println("\tNo rooms available!");
		}
		else if(room <= availableRooms) {
			x = room;
			availableRooms -= room;
			bookedRooms += room;
			return true;
		}
		return false;
		}

	void billing() {
		System.out.println("\t\t\tBill");
		System.out.println("\t\tBooking for " + x + " rooms");
		System.out.println("\t\tPrice: " + (price * x)+"$");
	}
	void reverseBooking(int room) {
		availableRooms += room;
		bookedRooms -= room;
	}
	void hotelDetails() {
		System.out.println("\t\t\tHotel Details");
		System.out.println("\tTotal rooms: " + totalRooms);
		System.out.println("\tAvailable rooms: " + availableRooms);
		System.out.println("\tBooked rooms: " + bookedRooms);
		System.out.println("\tPrice of a room: $" + price);
	}
static class Head{
	public static void main(String[] args) {
		Menus m = new Menus();
	}
}
}
