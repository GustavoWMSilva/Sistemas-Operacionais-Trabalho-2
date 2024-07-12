import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class MemoryManagerSimulator {

    private int virtualMemorySizeBits;
    private int physicalMemorySizeBits;
    private int pageSizeBits;
    public int[] pageTable;
    public int[] physicalMemory;
    private int numBitsPage;
    private int numBitsFrame;

    public MemoryManagerSimulator(int virtualMemorySizeBits, int physicalMemorySizeBits, int pageSizeBits) {
        this.virtualMemorySizeBits = virtualMemorySizeBits;
        this.physicalMemorySizeBits = physicalMemorySizeBits;
        this.pageSizeBits = pageSizeBits;

        int numPages = (1 << (virtualMemorySizeBits - pageSizeBits));
        int numFrames = (1 << (physicalMemorySizeBits - pageSizeBits));

        this.pageTable = new int[numPages];
        this.physicalMemory = new int[numFrames];

        // Initialize the page's table with -1, as required the item E
        for (int i = 0; i < pageTable.length; i++) {
            pageTable[i] = -1;
        }

        // Initialize the physical memory frames table with 0, as required the item E
        for (int i = 0; i < physicalMemory.length; i++) {
            physicalMemory[i] = 0;
        }

        BigInteger bigIntPage = BigInteger.valueOf(numPages-1);
        this.numBitsPage = bigIntPage.bitLength();
        BigInteger bigIntFrame = BigInteger.valueOf(numFrames-1);
        this.numBitsFrame = bigIntFrame.bitLength();
    }
    
    // Item B
    public void processVirtualAddresses(int[] virtualAddresses) {


        for (int virtualAddress : virtualAddresses) {
            int pageNumber = virtualAddress >> pageSizeBits;  // int pageNumber = virtualAddress / pageSize;
            int offset = virtualAddress & ((1 << pageSizeBits) - 1);  // int offset = virtualAddress % pageSize;

            if (pageTable[pageNumber] == -1) {
                int freeFrame = findFreeFrame();
                if (freeFrame == -1) { // in case no frame is free
                    System.out.println("Physical memory is full!");  // item F
                    System.out.println();
                    return;  // the program stops, but it will print the current state of the page and frame tables
                }
                pageTable[pageNumber] = freeFrame;
                physicalMemory[freeFrame] = 1;
                System.out.println("Page fault: Page " + pageNumber + " mapped to frame " + freeFrame);
            } else {
                System.out.println("Page hit: Page " + pageNumber + " already mapped to frame " + pageTable[pageNumber]);
            }
            int frameNumber = pageTable[pageNumber];
            int physicalAddress = (frameNumber << pageSizeBits) + offset;
            System.out.println("Virtual Address: " + virtualAddress + " -> Physical Address: " + physicalAddress);
        
        
         // Transform the values to bits
         String binPageNumber = String.format("%"+numBitsPage+"s", Integer.toBinaryString(pageNumber)).replace(' ', '0'); 
         String binFrameNumber = String.format("%"+numBitsFrame+"s", Integer.toBinaryString(frameNumber)).replace(' ', '0');
         String binVirtualAddress = String.format("%"+virtualMemorySizeBits+"s", Integer.toBinaryString(virtualAddress)).replace(' ', '0');
         String binPhysicalAddress = String.format("%"+physicalMemorySizeBits+"s", Integer.toBinaryString(physicalAddress)).replace(' ', '0');
 
         System.out.println("(pagina) Virtual Address: (" +binPageNumber+")"+ binVirtualAddress +" ("+virtualAddress +")"+ " ->(frame) Physical Address: (" +binFrameNumber+")"+binPhysicalAddress +" ("+physicalAddress+")\n");
        }
    }

    private int findFreeFrame() {
        for (int i = 0; i < physicalMemory.length; i++) {
            if (physicalMemory[i] == 0) {
                return i;
            }
        }
        return -1; // No free frame found
    }

    public void printPageTable() {
        System.out.println("Page Table:");
        for (int i = 0; i < pageTable.length; i++) {
            System.out.println("Page " + i + ": Frame " + pageTable[i]);
        }
    }

    public void printPhysicalMemory() {
        System.out.println("Physical Memory:");
        for (int i = 0; i < physicalMemory.length; i++) {
            System.out.println("Frame " + i + ": " + (physicalMemory[i] == 1 ? "Used" : "Free"));  // if true prints used, esle prints free
        }
    }

    public static void main(String[] args) {
        int virtualMemorySizeBits = 16; // Example size in bits (64 KB)
        int physicalMemorySizeBits = 15; // Example size in bits (32 KB)
        int pageSizeBits = 12; // Example size in bits (4 KB)


        if (args.length == 3) { // item A to be used in comand line
            virtualMemorySizeBits = Integer.parseInt(args[0]);
            physicalMemorySizeBits = Integer.parseInt(args[1]);
            pageSizeBits = Integer.parseInt(args[2]);

            System.out.println("virtualMemorySizeBits = " + virtualMemorySizeBits);
            System.out.println();
            System.out.println("physicalMemorySizeBits = " + physicalMemorySizeBits);
            System.out.println();
            System.out.println("pageSizeBits = " + pageSizeBits);
            System.out.println();

        } else {
                
            Scanner s = new Scanner(System.in);

            try  // virtualMemorySizeBits
            {
                System.out.print("Digite o valor da memória virtual em bits: ");
                String virtualMemorySizeBitsStr = s.nextLine();
                virtualMemorySizeBits = Integer.parseInt(virtualMemorySizeBitsStr.trim());

            System.out.println("virtualMemorySizeBits = " + virtualMemorySizeBits);
            System.out.println();

            }
            catch (NumberFormatException e)
            {
            System.out.println("NumberFormatException: " + e.getMessage());
            return;
            }
        
            try  // physicalMemorySizeBits
            {
                System.out.print("Digite o valor da memória física em bits: ");
                String physicalMemorySizeBitsStr = s.nextLine();
                physicalMemorySizeBits = Integer.parseInt(physicalMemorySizeBitsStr.trim());

            System.out.println("physicalMemorySizeBits = " + physicalMemorySizeBits);
            System.out.println();
            }
            catch (NumberFormatException e)
            {
            System.out.println("NumberFormatException: " + e.getMessage());
            return;
            }


            try  // pageSizeBits
            {
                System.out.print("Digite o tamanho da paǵina em bits: ");
                String pageSizeBitsStr = s.nextLine();
                pageSizeBits = Integer.parseInt(pageSizeBitsStr.trim());

            System.out.println("pageSizeBits = " + pageSizeBits);
            System.out.println();
            }
            catch (NumberFormatException e)
            {
            System.out.println("NumberFormatException: " + e.getMessage());
            return;
            }
        }


        MemoryManagerSimulator simulator = new MemoryManagerSimulator(virtualMemorySizeBits, physicalMemorySizeBits, pageSizeBits);

        // int[] virtualAddresses = {200, 8191, 4196, 16383, 8192, 65535};  // Example virtual addresses

        int number = 15;
        int[] virtualAddresses = new int[number];

        int limit = (1 << virtualMemorySizeBits);
        // Generate some random virtual addresses for demonstration
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            virtualAddresses[i] = rand.nextInt(limit);
            System.out.println("Endereço virtual de processo: " + virtualAddresses[i]);

        }
        System.out.println();
        
        simulator.processVirtualAddresses(virtualAddresses);
        simulator.printPageTable();
        System.out.println();
        simulator.printPhysicalMemory();
    }
}
