import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class sixInstrAssembler {
	public static int varCount = 0;
	public static Map<String, Integer> variables = new HashMap<String, Integer>();

	public static void main(String[] args) {
		String filename = "alphanumericExProg.txt";
		int varCount = 0;

		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] instr = line.split(" ");

				for (int i = 0; i < instr.length; i++) {
						instr[i] = instr[i].replaceAll(",", "");
				}

				System.out.println(interpretInstructions(instr));
			}
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public static String[] seperateInstructions(String line) {
		String[] instr = line.split(":");
		return instr;
	}

	public static String interpretInstructions(String[] instr) {
		String machineCode = "";
		String operation = instr[0];

		switch (operation) {
			case "set":
				machineCode = setOperation(machineCode, instr);
				break;
			case "beq":
				machineCode = beqOperation(machineCode, instr);
				break;
			case "add":
				machineCode += ("3, ");
				machineCode = mathsOperator(machineCode, instr);
				break;
			case "sub":
				machineCode += ("4, ");
				machineCode = mathsOperator(machineCode, instr);
				break;
			case "mul":
				machineCode += ("5, ");
				machineCode = mathsOperator(machineCode, instr);
				break;
			case "div":
				machineCode += ("6, ");
				machineCode = mathsOperator(machineCode, instr);
				break;
		}

		return machineCode;
	}

	public static String findVarName(String src) {
		String varName;

		if (!variables.containsKey(src)) {
			variables.put(src, varCount);
			varName = String.valueOf(varCount);
			varCount = varCount + 1;
		} else {
			varName = String.valueOf(variables.get(src));
		}

		return varName;
	}

	public static String setOperation(String machineCode, String[] instr) {
		machineCode += "1, ";
		String src1 = instr[1];
		String k = instr[2];
		if (!variables.containsKey(src1)) {
			variables.put(src1, varCount);
			varCount = varCount + 1;
		}
		machineCode += variables.get(src1) + ", ";
		machineCode += k;

		return machineCode;
	}

	public static String beqOperation(String machineCode, String[] instr) {
		machineCode += ("2, ");

		String k = instr[1];
		machineCode += (k + ", ");

		String src1 = instr[2];
		String src2 = instr[3];
		String varName;

		varName = findVarName(src1);
		machineCode += (varName + ", ");

		varName = findVarName(src2);
		machineCode += varName;

		return machineCode;
	}

	public static String mathsOperator(String machineCode, String[] instr) {
		String dst = instr[1];
		String src1 = instr[2];
		String src2 = instr[3];
		String varName1, varName2, varName3;

		varName1 = findVarName(dst);
		varName2 = findVarName(src1);
		varName3 = findVarName(src2);

		machineCode += (varName1 + ", ");
		machineCode += (varName2 + ", ");
		machineCode += (varName3);

		return machineCode;
	}
}
