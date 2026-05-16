package projectgui;
import javax.swing.*;

class Member {
    int id;
    String name;
    int age;
    String phone;
    String department;

    Member(int id, String name, int age, String phone, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.department = department;
    }
}

public class ProjectGUI {
    static Member[] members = new Member[100];
//    setDefaultCloseOperation(EXIT_ON_CLOSE);
    static int count = 0;
    static int nextId = 100;

    public static void main(String[] args) {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    
                "--Club Member Manager--\n"
                + "1. Register\n"
                + "2. List\n"
                + "3. Find\n"
                + "4. Edit\n"
                + "5. Delete\n"
                + "0. Exit\n"
                + "Choose an option:");
            if (input == null) break;
            if (input.equals("1")) register();
            else if (input.equals("2")) list();
            else if (input.equals("3")) find();
            else if (input.equals("4")) edit();
            else if (input.equals("5")) delete();
            else if (input.equals("0")) break;
            else JOptionPane.showMessageDialog(null, "Invalid option.");
        }
    }

    static void register() {
        if (count >= 100) {
            JOptionPane.showMessageDialog(null, "Member limit reached.");
            return;
        }
        String name = JOptionPane.showInputDialog("Name:");
        if (name == null || name.equals("")) return;
        String ageStr = JOptionPane.showInputDialog("Age:");
        if (ageStr == null || ageStr.equals("")) return;
        int age = Integer.parseInt(ageStr);
        String phone = JOptionPane.showInputDialog("Phone:");
        if (phone == null || phone.equals("")) return;
        String dept = JOptionPane.showInputDialog("Department:");
        if (dept == null || dept.equals("")) return;
        int id = nextId;
        members[count++] = new Member(nextId++, name, age, phone, dept);
        JOptionPane.showMessageDialog(null, "Registered!\nID: " + id);
    }

    static void list() {
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "No members.");
            return;
        }
        String s = "ID\tName\tAge\tPhone\tDepartment\n";
        for (int i = 0; i < count; i++) {
            Member m = members[i];
            s += m.id + "\t" + m.name + "\t" + m.age + "\t" + m.phone + "\t" + m.department + "\n";
        }
        JTextArea ta = new JTextArea(s);
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new java.awt.Dimension(400, 200));
        JOptionPane.showMessageDialog(null, sp, "Member List", JOptionPane.INFORMATION_MESSAGE);
    }

    static void find() {
        String idStr = JOptionPane.showInputDialog("Enter ID:");
        if (idStr == null || idStr.equals("")) return;
        int id = Integer.parseInt(idStr);
        for (int i = 0; i < count; i++) {
            Member m = members[i];
            if (m.id == id) {
                String info = "ID: " + m.id + "\n"
                            + "Name: " + m.name + "\n"
                            + "Age: " + m.age + "\n"
                            + "Phone: " + m.phone + "\n"
                            + "Department: " + m.department;
                JOptionPane.showMessageDialog(null, info);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Not found.");
    }

    static void edit() {
        String idStr = JOptionPane.showInputDialog("Enter ID to edit:");
        if (idStr == null || idStr.equals("")) return;
        int id = Integer.parseInt(idStr);
        for (int i = 0; i < count; i++) {
            Member m = members[i];
            if (m.id == id) {
                String name = JOptionPane.showInputDialog("New name (current: " + m.name + "):");
                if (name != null && !name.equals("")) m.name = name;
                String ageStr2 = JOptionPane.showInputDialog("New age (current: " + m.age + "):");
                if (ageStr2 != null && !ageStr2.equals("")) m.age = Integer.parseInt(ageStr2);
                String phone = JOptionPane.showInputDialog("New phone (current: " + m.phone + "):");
                if (phone != null && !phone.equals("")) m.phone = phone;
                String dept = JOptionPane.showInputDialog("New dept (current: " + m.department + "):");
                if (dept != null && !dept.equals("")) m.department = dept;
                JOptionPane.showMessageDialog(null, "Updated.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Not found.");
    }

    static void delete() {
        String idStr = JOptionPane.showInputDialog("Enter ID to delete:");
        if (idStr == null || idStr.equals("")) return;
        int id = Integer.parseInt(idStr);
        for (int i = 0; i < count; i++) {
            if (members[i].id == id) {
                for (int j = i; j < count - 1; j++) {
                    members[j] = members[j + 1];
                }
                members[--count] = null;
                JOptionPane.showMessageDialog(null, "Deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Not found.");
    }
}