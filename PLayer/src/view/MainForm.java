package view;

import controller.Controller;
import org.datacontract.schemas._2004._07.wslayer.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainForm extends JFrame implements ActionListener {

    private final Color DEFAULT_COLOR = this.getBackground();
    private final Color ACTIVE_COLOR = Color.lightGray;

    private JPanel requestPanel;
    private JPanel paramsPanel;
    private JPanel responsePanel;

    private JButton commonResponseButton;
    private JButton dailyResponseButton;
    private JButton statisticResponseButton;
    private JButton goButton;

    private JComboBox sitePicker;
    private JComboBox namePicker;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JFormattedTextField startDate;
    private JFormattedTextField endDate;

    private JTable dataTable;

    private java.util.List<NameResponse> names;
    private java.util.List<SiteResponse> sites;
    private java.util.List<CommonResponse> commons;
    private java.util.List<DailyResponse> dailies;
    private java.util.List<StatisticResponse> statistics;

    private CurrentPanel currentPanel;
    private Controller controller;

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(commonResponseButton.getText())) {
            commonResponseButton.setBackground(ACTIVE_COLOR);
            dailyResponseButton.setBackground(DEFAULT_COLOR);
            statisticResponseButton.setBackground(DEFAULT_COLOR);
            paramsPanel.removeAll();
            paramsPanel.add(sitePicker, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(goButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.validate();
            currentPanel = CurrentPanel.COMMAND;
        }
        else if (e.getActionCommand().equals(dailyResponseButton.getText())) {
            commonResponseButton.setBackground(DEFAULT_COLOR);
            dailyResponseButton.setBackground(ACTIVE_COLOR);
            statisticResponseButton.setBackground(DEFAULT_COLOR);
            paramsPanel.removeAll();
            paramsPanel.add(sitePicker, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(goButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.validate();
            currentPanel = CurrentPanel.DAILY;
        }
        else if (e.getActionCommand().equals(statisticResponseButton.getText())) {
            commonResponseButton.setBackground(DEFAULT_COLOR);
            dailyResponseButton.setBackground(DEFAULT_COLOR);
            statisticResponseButton.setBackground(ACTIVE_COLOR);
            paramsPanel.removeAll();
            paramsPanel.add(sitePicker,  new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(namePicker,  new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(startDateLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(startDate, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(endDateLabel, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(endDate, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.add(goButton, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
            paramsPanel.validate();
            currentPanel = CurrentPanel.STATISTIC;
        }
        else if(e.getActionCommand().equals(goButton.getText())) {
            responsePanel.removeAll();
            if (currentPanel == CurrentPanel.COMMAND) {
                generateCommonTable();
            }
            else if (currentPanel == CurrentPanel.DAILY) {
                generateDailyTable();
            }
            else if (currentPanel == CurrentPanel.STATISTIC) {
                generateStatisticTable();
            }
            responsePanel.validate();
            responsePanel.repaint();
        }
    }

    public MainForm(Controller controller) {
        super();

        this.controller = controller;
        names = controller.getName();
        sites = controller.getSite();

        requestPanel = new JPanel();
        paramsPanel = new JPanel();
        responsePanel = new JPanel();
        setLayout(new GridLayout(1,3));
        add(requestPanel);
        add(paramsPanel);
        add(responsePanel);

        commonResponseButton = new JButton("Common information");
        commonResponseButton.setActionCommand(commonResponseButton.getText());
        commonResponseButton.addActionListener(this);
        commonResponseButton.setFocusPainted(false);
        dailyResponseButton = new JButton("Daily information");
        dailyResponseButton.setActionCommand(dailyResponseButton.getText());
        dailyResponseButton.addActionListener(this);
        dailyResponseButton.setFocusPainted(false);
        statisticResponseButton = new JButton("Statistic information");
        statisticResponseButton.setActionCommand(statisticResponseButton.getText());
        statisticResponseButton.addActionListener(this);
        statisticResponseButton.setFocusPainted(false);

        requestPanel.setLayout(new GridBagLayout());
        requestPanel.add(commonResponseButton, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        requestPanel.add(dailyResponseButton,new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        requestPanel.add(statisticResponseButton,new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));

        sitePicker = new JComboBox();
        for (SiteResponse site : sites) {
            sitePicker.addItem(site.getName().getValue());
        }
        namePicker = new JComboBox();
        for (NameResponse name : names) {
            namePicker.addItem(name.getName().getValue());
        }
        startDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
        endDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
        try {
            (new MaskFormatter("##.##.####")).install(startDate);
            (new MaskFormatter("##.##.####")).install(endDate);
        }
        catch (ParseException ex) { }
        startDateLabel = new JLabel("Start date:");
        endDateLabel = new JLabel("End date:");
        goButton = new JButton("Go");
        goButton.setActionCommand(goButton.getText());
        goButton.addActionListener(this);
        paramsPanel.setLayout(new GridBagLayout());

        responsePanel.setLayout(new GridBagLayout());

        setMainFormVisualParams();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void generateCommonTable() {
        commons = controller.getCommonResponse(sites.get(sitePicker.getSelectedIndex()).getId());
        dataTable = new JTable(commons.size(),2);
        int currentRow = 0;
        for(CommonResponse common : commons){
            for (NameResponse name : names) {
               if (name.getId() == common.getNameId())
                   dataTable.setValueAt(name.getName().getValue(),currentRow,0);
            }
            dataTable.setValueAt(common.getFact(), currentRow,1);
            currentRow++;
        }
        dataTable.getColumn(dataTable.getColumnName(0)) .setHeaderValue("Name");
        dataTable.getColumn(dataTable.getColumnName(1)).setHeaderValue("Number");
        responsePanel.add(dataTable.getTableHeader(), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        responsePanel.add(dataTable, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
    }
    private void generateDailyTable() {
        dailies = controller.getDailyResponse(sites.get(sitePicker.getSelectedIndex()).getId());
        dataTable = new JTable(dailies.size(),2);
        int currentRow = 0;
        for(DailyResponse daily : dailies){
            for (NameResponse name : names) {
                if (name.getId() == daily.getNameId())
                    dataTable.setValueAt(name.getName().getValue(),currentRow,0);
            }
            dataTable.setValueAt(daily.getFact(),currentRow,1);
            currentRow++;
        }
        dataTable.getColumn(dataTable.getColumnName(0)) .setHeaderValue("Name");
        dataTable.getColumn(dataTable.getColumnName(1)).setHeaderValue("Number");
        responsePanel.add(dataTable.getTableHeader(), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        responsePanel.add(dataTable, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
    }
    private void generateStatisticTable() {
        if (startDate.getText().equals("  .  .    ") || endDate.getText().equals("  .  .    "))
            return;
        try{
            statistics = controller.getStatisticResponse(sites.get(sitePicker.getSelectedIndex()).getId(), names.get(namePicker.getSelectedIndex()).getId(),
                    (new SimpleDateFormat("dd.MM.yyyy")).parse(startDate.getText()), (new SimpleDateFormat("dd.MM.yyyy")).parse(endDate.getText()));
        }
        catch(Exception ex) {}
        dataTable = new JTable(statistics.size(),2);
        int currentRow = 0;
        for(StatisticResponse statistic : statistics){
            dataTable.setValueAt(statistic.getPageURL().getValue(),currentRow,0);
            dataTable.setValueAt(statistic.getFact(), currentRow, 1);
            currentRow++;
        }
        dataTable.getColumn(dataTable.getColumnName(0)) .setHeaderValue("Page");
        dataTable.getColumn(dataTable.getColumnName(1)).setHeaderValue("Number");
        responsePanel.add(dataTable.getTableHeader(), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        responsePanel.add(dataTable, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
    }

    private void setMainFormVisualParams() {
        setSize(600,400);
        setTitle("PLayer Client");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private enum CurrentPanel {
        COMMAND, DAILY, STATISTIC
    }
}
