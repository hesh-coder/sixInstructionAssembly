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
			currentInstructions = currentSet.split(",").trim();
			operator = currentInstructions[0];

			switch (operator) {
				case "1": //set
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
}
