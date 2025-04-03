/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package expensecalculator;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

/**
 *
 * @author Tejas
 */
public class Dash extends javax.swing.JFrame {

    /**
     * Creates new form Dash
     */
    private int loggedInUserId=-1;
    public Dash() {
    this(-1); // Calls the existing constructor with default value
    }

    public Dash(int userId) {
        setTitle("Dashboard");
        this.loggedInUserId = userId;
        initComponents();
        updateUserData();
    }
    private void updateUserData() {
        // Query to fetch salary, budget, and name for the logged-in user
        String userQuery = "SELECT name, salary, budget FROM users WHERE id = ?";
        // Query to calculate the sum of expenses for the logged-in user
        String expenseQuery = "SELECT SUM(amount) AS total FROM expenses WHERE user_id = ?";

        double salaryValue = 0.0;
        double budgetValue = 0.0;
        double totalExpenseValue = 0.0;
        String userName = ""; // Variable to store the user's name

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Fetch name, salary, and budget
            try (PreparedStatement pstmt = conn.prepareStatement(userQuery)) {
                pstmt.setInt(1, loggedInUserId); // Use the logged-in user's ID
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        userName = rs.getString("name");  // Get the user's name
                        salaryValue = rs.getDouble("salary");
                        budgetValue = rs.getDouble("budget");

                        // Display user name in the 'user' text field
                        user.setText(userName);

                        // Display salary and budget in respective text fields
                        salary.setText(String.format("%.2f", salaryValue));
                        budget.setText(String.format("%.2f", budgetValue));

                        // Make the text fields non-editable
                        user.setEditable(false);
                        salary.setEditable(false);
                        budget.setEditable(false);
                    } else {
                        // If no user data is found, display default values
                        user.setText("Unknown");
                        salary.setText("0.00");
                        budget.setText("0.00");

                        // Make the text fields non-editable
                        user.setEditable(false);
                        salary.setEditable(false);
                        budget.setEditable(false);
                    }
                }
            }

            // Fetch total expenses
            try (PreparedStatement pstmt = conn.prepareStatement(expenseQuery)) {
                pstmt.setInt(1, loggedInUserId); // Use the logged-in user's ID
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        totalExpenseValue = rs.getDouble("total");
                        if (rs.wasNull()) {
                            totalExpenseValue = 0.0; // Handle null case for total
                        }
                    }
                    // Display total expenses
                    totalexpense.setText(String.format("%.2f", totalExpenseValue));

                    // Make the total expense field non-editable
                    totalexpense.setEditable(false);
                }
            }

            // Calculate and update balance and state
            double balanceValue = salaryValue - totalExpenseValue; // Difference between salary and total expenses
            balance.setText(String.format("%.2f", balanceValue)); // Display balance

            // Make the balance field non-editable
            balance.setEditable(false);

            if (balanceValue > budgetValue) {
                state.setText("+ve"); // Positive state
                state.setForeground(java.awt.Color.GREEN); // Set text color to green
            } else {
                state.setText("-ve"); // Negative state
                state.setForeground(java.awt.Color.RED); // Set text color to red
            }

            // Make the state field non-editable
            state.setEditable(false);

        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        descValue = new javax.swing.JTextField();
        food = new javax.swing.JRadioButton();
        enteeyainment = new javax.swing.JRadioButton();
        health = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        insert = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        amountvalue = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Dashboard = new javax.swing.JLabel();
        View = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        salary = new javax.swing.JTextField();
        budget = new javax.swing.JTextField();
        balance = new javax.swing.JTextField();
        state = new javax.swing.JTextField();
        user = new javax.swing.JTextField();
        totalexpense = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        delete1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        update.setBackground(new java.awt.Color(204, 0, 204));
        update.setFont(new java.awt.Font("Mongolian Baiti", 3, 18)); // NOI18N
        update.setText("Update ");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUpdate(evt);
            }
        });

        delete.setBackground(new java.awt.Color(204, 0, 204));
        delete.setFont(new java.awt.Font("Mongolian Baiti", 3, 18)); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDelete(evt);
            }
        });

        logout.setBackground(new java.awt.Color(255, 51, 51));
        logout.setFont(new java.awt.Font("Mongolian Baiti", 3, 18)); // NOI18N
        logout.setText("Log out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutLogOut(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Descrpt");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Id");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Category");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Date");

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        food.setForeground(new java.awt.Color(255, 255, 255));
        food.setText("Food");

        enteeyainment.setForeground(new java.awt.Color(255, 255, 255));
        enteeyainment.setText("Entertainment");
        enteeyainment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enteeyainmentActionPerformed(evt);
            }
        });

        health.setForeground(new java.awt.Color(255, 255, 255));
        health.setText("Health");

        other.setForeground(new java.awt.Color(255, 255, 255));
        other.setText("Other");
        other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherActionPerformed(evt);
            }
        });

        insert.setBackground(new java.awt.Color(204, 0, 204));
        insert.setFont(new java.awt.Font("Mongolian Baiti", 3, 18)); // NOI18N
        insert.setText("Insert");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Amount");

        amountvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountvalueActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Enter id of the expense to delete");

        Dashboard.setBackground(new java.awt.Color(0, 0, 0));
        Dashboard.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Dashboard.setText("Dashboard");

        View.setBackground(new java.awt.Color(204, 0, 204));
        View.setFont(new java.awt.Font("Mongolian Baiti", 3, 18)); // NOI18N
        View.setText("ViewExpense");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View(evt);
            }
        });

        Table.setBackground(new java.awt.Color(0, 0, 0));
        Table.setForeground(new java.awt.Color(255, 255, 255));
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "amount", "date", "description", "category"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table);

        jButton1.setBackground(new java.awt.Color(204, 0, 204));
        jButton1.setFont(new java.awt.Font("Mongolian Baiti", 3, 14)); // NOI18N
        jButton1.setText("Sort by Date");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1Sort(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 0, 204));
        jButton2.setFont(new java.awt.Font("Mongolian Baiti", 3, 14)); // NOI18N
        jButton2.setText("Sort by Amount");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2Sort(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 0, 204));
        jButton4.setFont(new java.awt.Font("Mongolian Baiti", 3, 14)); // NOI18N
        jButton4.setText("Sort by Id");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Salary");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Budget");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Balance");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("State");

        salary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salary(evt);
            }
        });

        budget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budget(evt);
            }
        });

        balance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balance(evt);
            }
        });

        state.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                state(evt);
            }
        });

        user.setBackground(new java.awt.Color(255, 0, 255));
        user.setFont(new java.awt.Font("Mongolian Baiti", 3, 18)); // NOI18N
        user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user(evt);
            }
        });

        totalexpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalexpense(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total Expense");

        delete1.setBackground(new java.awt.Color(204, 0, 204));
        delete1.setFont(new java.awt.Font("Mongolian Baiti", 3, 18)); // NOI18N
        delete1.setText("Pie of Expenses");
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPie(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(Dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(131, 131, 131))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(budget, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(balance, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(totalexpense, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(42, 42, 42)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(food, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(health, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(enteeyainment, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(other, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(date)
                                        .addComponent(amountvalue)
                                        .addComponent(descValue, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(50, 50, 50)
                                        .addComponent(jButton1)
                                        .addGap(41, 41, 41)
                                        .addComponent(jButton2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(View, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel12, jLabel8, jLabel9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(budget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(totalexpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(View, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(amountvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(food)
                    .addComponent(enteeyainment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(health)
                    .addComponent(other))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(descValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUpdate
        // Assuming you have text fields for each value (amount, date, description, category) and a field for the expense ID (for the update)
        String expenseId = id.getText();  // A field to specify the ID of the expense to be updated
        String amount = amountvalue.getText();        // Amount field
        String dateValue = date.getText();            // Date field
        String description = descValue.getText();     // Description field
        String category = null;

        // Determine which radio button is selected for the category
        if (food.isSelected()) {
            category = "Food";
        } else if (enteeyainment.isSelected()) {
            category = "Entertainment";
        } else if (health.isSelected()) {
            category = "Health";
        } else if (other.isSelected()) {
            category = "Other";
        }

        // Validate input
        if (expenseId.isEmpty() || amount.isEmpty() || dateValue.isEmpty() || description.isEmpty() || category == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // SQL query to update the expense record based on the ID and user_id
            String sql = "UPDATE expenses SET amount = ?, date = ?, category = ?, description = ? WHERE id = ? AND user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, amount);    // Set the new amount
            pstmt.setString(2, dateValue); // Set the new date
            pstmt.setString(3, category);  // Set the new category
            pstmt.setString(4, description); // Set the new description
            pstmt.setString(5, expenseId);  // Set the expense ID for the record to update
            pstmt.setInt(6, loggedInUserId); // Set the logged-in user's ID to ensure they can only update their own expenses

            // Execute the update query
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Expense updated successfully!");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No expense found with the provided ID or you are not authorized to update this expense.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
//            amountvalue.setText("");    // Clear amount field
//            date.setText("");           // Clear date field
//            descValue.setText("");      // Clear description field
//
//            // Deselect all radio buttons (optional)
//            food.setSelected(false);
//            enteeyainment.setSelected(false);
//            health.setSelected(false);
//            other.setSelected(false);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        updateUserData();
    }//GEN-LAST:event_updateUpdate

    private void deleteDelete(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDelete
        // TODO add your handling code here:
        String expenseId = id.getText(); // Replace with actual field or selection logic

        // Validate input
        if (expenseId.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please provide an expense ID.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // SQL query to delete the expense based on the ID and user_id
            String sql = "DELETE FROM expenses WHERE id = ? AND user_id = ?"; // Ensure that the expense belongs to the logged-in user

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, expenseId);  // Set the ID of the expense to be deleted
            pstmt.setInt(2, loggedInUserId); // Set the logged-in user's ID to ensure they can only delete their own expenses

            // Execute the delete query
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Expense deleted successfully!");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No expense found with the provided ID or you are not authorized to delete this expense.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        updateUserData();
    }//GEN-LAST:event_deleteDelete

    private void logoutLogOut(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutLogOut
        // TODO add your handling code here:
        LoginPage loginpage = new LoginPage();
        loginpage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutLogOut

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void enteeyainmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enteeyainmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enteeyainmentActionPerformed

    private void otherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otherActionPerformed

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        // TODO add your handling code here:
         String amount = amountvalue.getText();      // Get amount value as a string
        String dateValue = date.getText();          // Get date value as a string (ensure it's in DD-MM-YYYY format)
        String description = descValue.getText();   // Get description value
        String category = null;

        // Determine which radio button is selected
        if (food.isSelected()) {
            category = "Food";
        } else if (enteeyainment.isSelected()) {  // Fixed typo in 'enteeyainment'
            category = "Entertainment";
        } else if (health.isSelected()) {
            category = "Health";
        } else if (other.isSelected()) {
            category = "Other";
        }

        // Validate input
        if (amount.isEmpty() || dateValue.isEmpty() || description.isEmpty() || category == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Parse and validate amount as a double
            double amountValue = Double.parseDouble(amount);

            // Parse and validate date using SimpleDateFormat
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);  // Ensure strict date parsing
            java.util.Date parsedDate = sdf.parse(dateValue);

            // Convert parsed date to java.sql.Date for database insertion
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            // Establish connection to the database
            Connection conn = DatabaseConnection.getConnection();

            // SQL query to insert a row into the "expenses" table with user_id
            String sql = "INSERT INTO expenses (user_id, description, amount, date, category) VALUES (?, ?, ?, ?, ?)";

             // Set parameters, including user_id (logged-in user)
             try ( // Prepare the statement
                     PreparedStatement pst = conn.prepareStatement(sql)) {
                 // Set parameters, including user_id (logged-in user)
                 pst.setInt(1, loggedInUserId);  // Set user_id to the logged-in user's ID
                 pst.setString(2, description);   // Set description
                 pst.setDouble(3, amountValue);   // Set amount
                 pst.setDate(4, sqlDate);         // Set date
                 pst.setString(5, category);      // Set category
                 // Execute the update
                 pst.executeUpdate();
                 // Show success message
                 javax.swing.JOptionPane.showMessageDialog(this, "Expense inserted successfully!", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                 amountvalue.setText("");    // Clear amount field
                 date.setText("");           // Clear date field
                 descValue.setText("");      // Clear description field
                 // Deselect all radio buttons (optional)
                 food.setSelected(false);
                 enteeyainment.setSelected(false);
                 health.setSelected(false);
                 other.setSelected(false);
                 // Close the PreparedStatement
             } // Set user_id to the logged-in user's ID

        } catch (NumberFormatException e) {
            // Handle invalid amount input
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (java.text.ParseException e) {
            // Handle invalid date input
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a valid date in DD-MM-YYYY format.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
// Handle SQL exceptions
            javax.swing.JOptionPane.showMessageDialog(this, "Error inserting expense. Please try again.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        updateUserData();
    }//GEN-LAST:event_insertActionPerformed

    private void amountvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountvalueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountvalueActionPerformed

    private void View(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View
        try {
        // Establish connection to the database
        Connection conn = DatabaseConnection.getConnection();

        // Create a PreparedStatement to execute the SQL query
        String sql = "SELECT * FROM expenses WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the user_id parameter to the logged-in user's ID
            pstmt.setInt(1, loggedInUserId);

            // Get metadata of the ResultSet to retrieve column names
            try ( // Execute the query and store the result in a ResultSet
                    ResultSet rs = pstmt.executeQuery()) {
                // Get metadata of the ResultSet to retrieve column names
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                // Create a DefaultTableModel to set data to the JTable
                DefaultTableModel model = new DefaultTableModel();
                // Add column names to the model
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }   // Add rows of data to the model
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Formatter for date
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
                            // If the column is a DATE type, format it
                            java.sql.Date sqlDate = rs.getDate(i);
                            if (sqlDate != null) {
                                row[i - 1] = sdf.format(sqlDate);
                            } else {
                                row[i - 1] = null;
                            }
                        } else {
                            row[i - 1] = rs.getObject(i);  // Get non-date columns as is
                        }
                    }
                    model.addRow(row);  // Add the row to the model
                }   // Set the model to the JTable
                Table.setModel(model);
                // Close the ResultSet
            }
        } catch (SQLException e) {
            // Handle SQL exceptions

        }

    } catch (SQLException e) {
            // Handle connection errors

    }
        updateUserData();
    }//GEN-LAST:event_View

    private void jButton1Sort(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1Sort
        try {
            // Establish connection to the database
            Connection conn = DatabaseConnection.getConnection();

            // SQL query to select all rows from the "expenses" table for the logged-in user, sorted by date in ascending order
            String sql = "SELECT * FROM expenses WHERE user_id = ? ORDER BY date ASC"; // Sort by date in ascending order
            try ( // Prepare the statement and set the logged-in user's ID as a parameter
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, loggedInUserId); // Use the logged-in user's ID
                
                // Execute the query and store the result in a ResultSet
                ResultSet rs = pstmt.executeQuery();
                
                // Get metadata of the ResultSet to retrieve column names
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                
                // Create a DefaultTableModel to set data to the JTable
                DefaultTableModel model = new DefaultTableModel();
                
                // Add column names to the model
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }
                
                // Add rows of data to the model
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Formatter for date
                
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
                            // If the column is a DATE type, format it
                            java.sql.Date sqlDate = rs.getDate(i);
                            if (sqlDate != null) {
                                row[i - 1] = sdf.format(sqlDate);
                            } else {
                                row[i - 1] = null;
                            }
                        } else {
                            row[i - 1] = rs.getObject(i);  // Get non-date columns as is
                        }
                    }
                    model.addRow(row);  // Add the row to the model
                }
                
                // Set the model to the JTable
                Table.setModel(model);
                
                // Close the ResultSet and PreparedStatement
                rs.close();
            } // Use the logged-in user's ID

        } catch (SQLException e) {
            // Handle SQL exceptions

        }

    }//GEN-LAST:event_jButton1Sort

    private void jButton2Sort(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2Sort
        // TODO add your handling code here:
        try {
            // Establish connection to the database
            Connection conn = DatabaseConnection.getConnection();

            // SQL query to select all rows from the "expenses" table for the logged-in user, sorted by amount in ascending order
            String sql = "SELECT * FROM expenses WHERE user_id = ? ORDER BY amount ASC"; // Sort by amount in ascending order
            try ( // Prepare the statement and set the logged-in user's ID as a parameter
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, loggedInUserId); // Use the logged-in user's ID
                
                // Execute the query and store the result in a ResultSet
                ResultSet rs = pstmt.executeQuery();
                
                // Get metadata of the ResultSet to retrieve column names
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                
                // Create a DefaultTableModel to set data to the JTable
                DefaultTableModel model = new DefaultTableModel();
                
                // Add column names to the model
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }
                
                // Add rows of data to the model
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Formatter for date
                
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
                            // If the column is a DATE type, format it
                            java.sql.Date sqlDate = rs.getDate(i);
                            if (sqlDate != null) {
                                row[i - 1] = sdf.format(sqlDate);
                            } else {
                                row[i - 1] = null;
                            }
                        } else {
                            row[i - 1] = rs.getObject(i);  // Get non-date columns as is
                        }
                    }
                    model.addRow(row);  // Add the row to the model
                }
                
                // Set the model to the JTable
                Table.setModel(model);
                
                // Close the ResultSet and PreparedStatement
                rs.close();
            } // Use the logged-in user's ID

        } catch (SQLException e) {
            // Handle SQL exceptions

        }
    }//GEN-LAST:event_jButton2Sort

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
            try {
            // Establish connection to the database
            Connection conn = DatabaseConnection.getConnection();

                // SQL query to select all rows from the "expenses" table for the logged-in user, ordered by ID
                try ( // Create a Statement to execute the SQL query
                        Statement st = conn.createStatement()) {
                    // SQL query to select all rows from the "expenses" table for the logged-in user, ordered by ID
                    String sql = "SELECT * FROM expenses WHERE user_id = ? ORDER BY id ASC"; // Filter by loggedInUserId and sort by ID
                    
                    // Prepare the statement and set the loggedInUserId
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, loggedInUserId); // Use the logged-in user's ID to filter the expenses
                    
                    // Execute the query and store the result in a ResultSet
                    ResultSet rs = pstmt.executeQuery();
                    
                    // Get metadata of the ResultSet to retrieve column names
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    
                    // Create a DefaultTableModel to set data to the JTable
                    DefaultTableModel model = new DefaultTableModel();
                    
                    // Add column names to the model
                    for (int i = 1; i <= columnCount; i++) {
                        model.addColumn(rsmd.getColumnName(i));
                    }
                    
                    // Add rows of data to the model
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Formatter for date
                    
                    while (rs.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
                                // If the column is a DATE type, format it
                                java.sql.Date sqlDate = rs.getDate(i);
                                if (sqlDate != null) {
                                    row[i - 1] = sdf.format(sqlDate);
                                } else {
                                    row[i - 1] = null;
                                }
                            } else {
                                row[i - 1] = rs.getObject(i);  // Get non-date columns as is
                            }
                        }
                        model.addRow(row);  // Add the row to the model
                    }
                    
                    // Set the model to the JTable
                    Table.setModel(model);
                    
                    // Close the ResultSet and PreparedStatement
                    rs.close();
                    pstmt.close();
                } // Filter by loggedInUserId and sort by ID

        } catch (SQLException e) {
                // Handle SQL exceptions

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void salary(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salary
        // TODO add your handling code here:
    }//GEN-LAST:event_salary

    private void budget(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budget
        // TODO add your handling code here:
    }//GEN-LAST:event_budget

    private void balance(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balance
        // TODO add your handling code here:
    }//GEN-LAST:event_balance

    private void state(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_state
        // TODO add your handling code here:
    }//GEN-LAST:event_state

    private void user(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user
        // TODO add your handling code here:
    }//GEN-LAST:event_user

    private void totalexpense(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalexpense
        // TODO add your handling code here:
    }//GEN-LAST:event_totalexpense

    private void ViewPie(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPie
        // TODO add your handling code here:
        int loggedInUserId = this.loggedInUserId; // Assume you have the user's ID stored in the dashboard
        new ViewPie(loggedInUserId).setVisible(true);
        //this.dispose();
    }//GEN-LAST:event_ViewPie

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Dash().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dashboard;
    private javax.swing.JTable Table;
    private javax.swing.JButton View;
    private javax.swing.JTextField amountvalue;
    private javax.swing.JTextField balance;
    private javax.swing.JTextField budget;
    private javax.swing.JTextField date;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete1;
    private javax.swing.JTextField descValue;
    private javax.swing.JRadioButton enteeyainment;
    private javax.swing.JRadioButton food;
    private javax.swing.JRadioButton health;
    private javax.swing.JTextField id;
    private javax.swing.JButton insert;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JRadioButton other;
    private javax.swing.JTextField salary;
    private javax.swing.JTextField state;
    private javax.swing.JTextField totalexpense;
    private javax.swing.JButton update;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
