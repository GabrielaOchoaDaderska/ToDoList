import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListApp {

    private DefaultListModel<String> toDoListModel;
    private JList<String> toDoList;
    private JTextField taskInputField;

    public ToDoListApp() {
        JFrame frame = new JFrame("ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        toDoListModel = new DefaultListModel<>();
        toDoList = new JList<>(toDoListModel);
        JScrollPane scrollPane = new JScrollPane(toDoList);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        taskInputField = new JTextField();
        JButton addButton = new JButton("Dodaj zadanie");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInputField.getText().trim();
                if (!task.isEmpty()) {
                    toDoListModel.addElement(task);
                    taskInputField.setText("");
                }
            }
        });

        JButton removeButton = new JButton("Usuń zadanie");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = toDoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    toDoListModel.remove(selectedIndex);
                }
            }
        });

        panel.add(taskInputField, BorderLayout.NORTH);
        panel.add(addButton, BorderLayout.WEST);
        panel.add(removeButton, BorderLayout.EAST);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListApp();
            }
        });
    }
}
