import java.util.*;

import javafx.beans.binding.NumberExpression;

public class DomBank {
    public static void main(String[] args) {
        
        boolean offSwitch = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to DomBank Online Banking!\n");
        System.out.println("Command formats:\nCreate a new account: new (name) (amount)\nView account balance: view (name)\nDeposit into account: deposit (name) (amount)\nWithdraw from account: withdraw (name) (amount)\nClose program: close\n");

        HashMap<String,Integer> acctRoster = new HashMap<String,Integer>();

        while (offSwitch == false) {
            String cmd = sc.nextLine();
            String[] cmdArr = readCmd(cmd);
            //String[] cmdArr = cmd.split("\\s+");
            cmd = cmdArr[0];

            switch(cmd) {
                case "close" : offSwitch = true;
                    System.out.println("Closing the app. Thank you!");
                    break;
                case "new" :
                    if (cmdArr.length > 2) {
                        if ((cmdArr[1] != "") && (cmdArr[2] != null) && (acctRoster.get(cmdArr[1]) == null)) {
                            acctRoster.put(cmdArr[1], Integer.parseInt(cmdArr[2]));
                            System.out.println(cmdArr[1] + " has been created!");
                            System.out.println(cmdArr[1] + " holds " + cmdArr[2] + " dollars\n");
                        } else {
                            System.out.println(cmdArr[1] + " already exists\n");
                        }
                    } else {
                        System.out.println("Specify account name and balance\n");
                    }
                    break;
                case "view" :
                    if ((cmdArr.length > 1) && (cmdArr[1] != "") && (acctRoster.get(cmdArr[1]) != null)) {
                        System.out.println(cmdArr[1] + " holds " + acctRoster.get(cmdArr[1]) + " dollars\n");
                    } else {
                        System.out.println("Invalid account name\n");
                    }
                    break;
                case "deposit" :
                    if (cmdArr.length > 2) {
                        if ((cmdArr[1] != "") && (cmdArr[2] != "0") && (acctRoster.get(cmdArr[1]) != null)) {
                            acctRoster.put(cmdArr[1], acctRoster.get(cmdArr[1]) + Integer.parseInt(cmdArr[2]));
                            System.out.println("Deposited " + cmdArr[2] + " dollars into " + cmdArr[1] + "\n");
                        } else {
                            System.out.println("Invalid account name\n");
                        }
                    } else {
                        System.out.println("Specify account name and amount\n");
                    }
                    break;
                case "withdraw" :
                    if (cmdArr.length > 2) {
                        if ((cmdArr[1] != "") && (cmdArr[2] != "0") && (acctRoster.get(cmdArr[1]) != null)) {
                            if (acctRoster.get(cmdArr[1]) >= Integer.parseInt(cmdArr[2])) {
                                acctRoster.put(cmdArr[1], acctRoster.get(cmdArr[1]) - Integer.parseInt(cmdArr[2]));
                                System.out.println("Withdrew " + cmdArr[2] + " dollars from " + cmdArr[1] + "\n");
                            } else {
                                System.out.println("Insufficient funds\n");
                            }
                        } else {
                            System.out.println("Invalid account name\n");
                        }
                    } else {
                        System.out.println("Specify account name and amount\n");
                    }
                    break;
                default : System.out.println("Not a valid command\n");
                    break;
            }
        }
        sc.close();
    }
    public static String[] readCmd(String cmd) {
        String[] cmdList = cmd.split("\\s+");
        if (cmdList.length < 2) {
            cmdList = new String[] {cmdList[0], "", "0"};
        } else if (cmdList.length < 3) {
            cmdList = new String[] {cmdList[0], cmdList[1], "0"};
        } else if (cmdList.length == 3) {
            cmdList[2] = isNum(cmdList[2]);
        }
        return cmdList;
    }
    public static String isNum(String num) {
        int xNum = 0;
        try {
            xNum = Integer.parseInt(num);
        } catch (Exception ex) {
            System.out.println("Invalid amount\n");
        } finally {
            return Integer.toString(xNum);
        }
    }
}
