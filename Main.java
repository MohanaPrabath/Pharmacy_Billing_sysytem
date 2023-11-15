import java.util.Scanner;

class Medicine {
    private String name;
    private String type;
    private double price;
    private int quantity;
//constructor
    public Medicine(String name, String type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
//getter
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
//setter for ubdate the quentity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Pharmacy {
	//private Medicine medicines[]=new Medicine [5]
    private Medicine medicines[];
	public Pharmacy(int numMedicines) {
        medicines = new Medicine[numMedicines];
    }
//add five madicine details in an array
    public void addMedicine(Medicine medicine, int index) {
        medicines[index] = medicine;
    }
//display madicine
    public void displayMedicines() {
        System.out.println("Medicines Available:");
        for (int i = 0; i < medicines.length; i++) {
            if (medicines[i] != null) {
                System.out.println(i + ". " + medicines[i].getName() + " - Type: " + medicines[i].getType()
                        + ", Price: $" + medicines[i].getPrice() + ", Quantity: " + medicines[i].getQuantity());
            }
        }
    }
//what is the medicine the user want to add bill
    public Medicine getMedicine(int index) {
        if (index >= 0 && index < medicines.length && medicines[index] != null) {
            return medicines[index];
        } else {
            System.out.println("Invalid medicine selection.");
            return null;
        }
    }
//ubdate the madicine quentity
    public void updateMedicineQuantity(int index, int quantity) {
        if (index >= 0 && index < medicines.length && medicines[index] != null) {
			
            medicines[index].setQuantity(medicines[index].getQuantity() - quantity);
        } else {
            System.out.println("Invalid medicine selection.");
        }
    }
}

class Bill {
    private double totalAmount;
    private double receivedAmount;
    private double changeAmount;

    public Bill() {
        totalAmount = 0;
        receivedAmount = 0;
        changeAmount = 0;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void calculateChange() {
        changeAmount = receivedAmount - totalAmount;
    }
}

class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create pharmacy with 5 medicines
        Pharmacy pharmacy = new Pharmacy(5);

        // Create bill
        Bill bill = new Bill();
		
		
        // Add medicines
		//array initialized
		//creating a new instance of a class called Medicine. This class probably represents a type of medication. The arguments passed in this constructor are:
        pharmacy.addMedicine(new Medicine("ActiRelief          ", "Sports", 10.99, 50), 0);
        pharmacy.addMedicine(new Medicine("BabyGuard Drops     ", "Babies", 5.99, 30), 1);
        pharmacy.addMedicine(new Medicine("FemVital Tablets    ", "Ladies", 8.99, 40), 2);
        pharmacy.addMedicine(new Medicine("SeniorEase Capsules ", "Elders", 7.99, 20), 3);
        pharmacy.addMedicine(new Medicine("VitaInject Solution ", "Inject", 15.99, 10), 4);
        // Display menu
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display Medicines");
            System.out.println("2. Add to Bill");
            System.out.println("3. Generate Bill");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    pharmacy.displayMedicines();
                    break;
                case 2:
                    System.out.print("Enter the number of the medicine you want to add to bill: ");
                    int medIndex = scanner.nextInt();
                    Medicine selectedMedicine = pharmacy.getMedicine(medIndex);
                    if (selectedMedicine != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        if (quantity <= selectedMedicine.getQuantity()) {
                            double total = selectedMedicine.getPrice() * quantity;
                            bill.setTotalAmount(bill.getTotalAmount() + total);
                            pharmacy.updateMedicineQuantity(medIndex, quantity);
                            System.out.println("Added " + quantity + " " + selectedMedicine.getName() + "(s) to bill.");
                        } else {
                            System.out.println("Insufficient quantity in stock.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nGenerating Bill...");
                    System.out.println("Total Amount: $" + bill.getTotalAmount());
                    System.out.print("Enter the amount received: $");
                    double receivedAmount = scanner.nextDouble();
                    bill.setReceivedAmount(receivedAmount);
                    bill.calculateChange();
                    System.out.println("Change: $" + bill.getChangeAmount());
                    System.out.println("Thank you for your purchase!******* Come again");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
}
