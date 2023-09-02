import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdvancedToDoListApp {

    private DefaultTableModel toDoTableModel;
    private JTable toDoTable;
    private JTextField taskInputField;
    private JCheckBox completedCheckBox;

    public AdvancedToDoListApp() {
        JFrame frame = new JFrame("Advanced ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        toDoTableModel = new DefaultTableModel(new Object[]{"Zadanie", "Zakończone"}, 0);
        toDoTable = new JTable(toDoTableModel);
        JScrollPane scrollPane = new JScrollPane(toDoTable);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskInputField = new JTextField(30);
        JButton addButton = new JButton("Dodaj zadanie");
        completedCheckBox = new JCheckBox("Zakończone");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInputField.getText().trim();
                boolean completed = completedCheckBox.isSelected();
                if (!task.isEmpty()) {
                    toDoTableModel.addRow(new Object[]{task, completed});
                    taskInputField.setText("");
                    completedCheckBox.setSelected(false);
                }
            }
        });

        inputPanel.add(taskInputField);
        inputPanel.add(addButton);
        inputPanel.add(completedCheckBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton removeButton = new JButton("Usuń zaznaczone zadania");
        JButton sortButton = new JButton("Sortuj zadania");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = toDoTable.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    toDoTableModel.removeRow(selectedRows[i]);
                }
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoTableModel.getDataVector().sort((row1, row2) -> {
                    String task1 = (String) row1.get(0);
                    String task2 = (String) row2.get(0);
                    return task1.compareToIgnoreCase(task2);
                });

                toDoTableModel.fireTableDataChanged();
            }
        });

        buttonPanel.add(removeButton);
        buttonPanel.add(sortButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        toDoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = toDoTable.getSelectedRow();
                    if (row != -1) {
                        String task = (String) toDoTableModel.getValueAt(row, 0);
                        boolean completed = (boolean) toDoTableModel.getValueAt(row, 1);
                        String editedTask = JOptionPane.showInputDialog(frame, "Edytuj zadanie:", task);
                        if (editedTask != null) {
                            toDoTableModel.setValueAt(editedTask, row, 0);
                            toDoTableModel.setValueAt(completed, row, 1);
                        }
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdvancedToDoListApp();
            }
        });
    }
}
