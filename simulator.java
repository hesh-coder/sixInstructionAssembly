import java.io.File;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class simulator {
	public static Map<String, String> memory = new HashMap<String, String>();
	public static List<String> memLocations;

	public static void main(String[] args) {
		String[] lineElements;
		String memAddress;
		String instructions;

		memLocations = new ArrayList<String>();

		try {
			Scanner fileReader = new Scanner(new File("machineCode.txt"));

			while (fileReader.hasNextLine()) {
				instructions = "";
				String line = fileReader.nextLine();

				lineElements = line.split(":");
				memAddress = lineElements[0];
				memLocations.add(memAddress);

				for (int i = 1; i < lineElements.length; i++) {
					instructions += lineElements[i];
				}
				memory.put(memAddress, instructions);

				executeInstructions();
			}
		} catch (Exception e) {
			System.out.println("Error reading file.");
		}
	}

	public static void executeInstructions() {
		String currentSet;
		String[] currentInstructions;
		String operator;

		for (String current : memLocations) {
			currentSet = memory.get(current);
			currentInstructions = currentSet.split(",");
			operator = currentInstructions[0].trim();

			switch (operator) {
				case "1": //set
					String dst = currentInstructions[1].trim();
					String k = currentInstructions[2].trim();
					setMemory(dst, k);
					break;
				case "2": //beq
					break;
				case "3": //add
					break;
				case "4": //sub
					break;
				case "5": //mul
					break;
				case "6": // div
					break;
			}
		}
	}

	public static void setMemory(String dst, String k) {
			memory.put(dst, k);
	}
}
