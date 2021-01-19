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
		String dst, k, src1, src2;

		for (String current : memLocations) {
			currentSet = memory.get(current);
			currentInstructions = currentSet.split(",");
			operator = currentInstructions[0].trim();

			switch (operator) {
				case "1": //set
					dst = currentInstructions[1].trim();
					k = currentInstructions[2].trim();
					setMemory(dst, k);
					break;
				case "2": //beq
					break;
				case "3": //add
					dst = currentInstructions[1].trim();
					src1 = currentInstructions[2].trim();
					src2 = currentInstructions[1].trim();
					addOperator(dst, src1, src2);
					break;
				case "4": //sub
					dst = currentInstructions[1].trim();
					src1 = currentInstructions[2].trim();
					src2 = currentInstructions[1].trim();
					subOperator(dst, src1, src2);
					break;
				case "5": //mul
					dst = currentInstructions[1].trim();
					src1 = currentInstructions[2].trim();
					src2 = currentInstructions[1].trim();
					mulOperator(dst, src1, src2);
					break;
				case "6": // div
					dst = currentInstructions[1].trim();
					src1 = currentInstructions[2].trim();
					src2 = currentInstructions[1].trim();
					divOperator(dst, src1, src2);
					break;
			}
		}
	}

	public static void setMemory(String dst, String k) {
			memory.put(dst, k);
	}

	public static void addOperator(String dst, String src1, String src2) {
		String val1 = memory.get(src1);
		String val2 = memory.get(src2);
		String prod = String.valueOf((Integer.parseInt(val1)) + (Integer.parseInt(val2)));
		memory.put(dst, prod);
	}

	public static void subOperator(String dst, String src1, String src2) {
		String val1 = memory.get(src1);
		String val2 = memory.get(src2);
		String prod = String.valueOf((Integer.parseInt(val1)) - (Integer.parseInt(val2)));
		memory.put(dst, prod);
	}

	public static void mulOperator(String dst, String src1, String src2) {
		String val1 = memory.get(src1);
		String val2 = memory.get(src2);
		String prod = String.valueOf((Integer.parseInt(val1)) * (Integer.parseInt(val2)));
		memory.put(dst, prod);
	}

	public static void divOperator(String dst, String src1, String src2) {
		String val1 = memory.get(src1);
		String val2 = memory.get(src2);
		String prod = String.valueOf((Integer.parseInt(val1)) / (Integer.parseInt(val2)));
		memory.put(dst, prod);
	}
}
