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
    private final int HORIZONTAL_WINDOW_SIZE = 600;
    private final int VERTICAL_WINDOW_SIZE = 400;
    private final String MAIN_DATA_FORMAT = "dd.MM.yyyy";

    private MainFormPanel requestPanel;
    private MainFormPanel paramsPanel;
    private MainFormPanel responsePanel;

    private MainFormButton commonResponseButton;
    private MainFormButton dailyResponseButton;
    private MainFormButton statisticResponseButton;
    private MainFormButton goButton;

    private JComboBox sitePicker;
    private JComboBox namePicker;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private MainFormFormattedTextField startDate;
    private MainFormFormattedTextField endDate;

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
            currentPanel = CurrentPanel.COMMAND;
            drawPanel(currentPanel);
        } else if (e.getActionCommand().equals(dailyResponseButton.getText())) {
            currentPanel = CurrentPanel.DAILY;
            drawPanel(currentPanel);
        }
        else if (e.getActionCommand().equals(statisticResponseButton.getText())) {
            currentPanel = CurrentPanel.STATISTIC;
            drawPanel(currentPanel);
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
        drawMainForm();
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
        responsePanel.add(dataTable.getTableHeader(), getGridBagAppearance(0,0));
        responsePanel.add(dataTable, getGridBagAppearance(0,1));
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
        responsePanel.add(dataTable.getTableHeader(), getGridBagAppearance(0,0));
        responsePanel.add(dataTable, getGridBagAppearance(0,1));
    }

    private void generateStatisticTable() {
        if (startDate.getText().equals("  .  .    ") || endDate.getText().equals("  .  .    "))
            return;
        try{
            statistics = controller.getStatisticResponse(sites.get(sitePicker.getSelectedIndex()).getId(), names.get(namePicker.getSelectedIndex()).getId(),
                    (new SimpleDateFormat(MAIN_DATA_FORMAT)).parse(startDate.getText()), (new SimpleDateFormat(MAIN_DATA_FORMAT)).parse(endDate.getText()));
        }
        catch(Exception ex) {}
        dataTable = new JTable(statistics.size(),2);
        int currentRow = 0;
        for(StatisticResponse statistic : statistics){
            dataTable.setValueAt(statistic.getPageURL().getValue(),currentRow,0);
            dataTable.setValueAt(statistic.getFact(), currentRow, 1);
            currentRow++;
        }
        dataTable.getColumn(dataTable.getColumnName(0)).setHeaderValue("Page");
        dataTable.getColumn(dataTable.getColumnName(1)).setHeaderValue("Number");
        responsePanel.add(dataTable.getTableHeader(), getGridBagAppearance(0,0));
        responsePanel.add(dataTable, getGridBagAppearance(0,1));
    }

    private void setMainFormVisualParams() {
        setSize(HORIZONTAL_WINDOW_SIZE, VERTICAL_WINDOW_SIZE);
        setTitle("PLayer Client");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private GridBagConstraints getGridBagAppearance(int horizontalPlace, int verticalPlace)
    {
        return new GridBagConstraints(horizontalPlace, verticalPlace, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0);
    }

    private void drawPanel(CurrentPanel currentPanel){
        paramsPanel.removeAll();
        switch (currentPanel) {
            case COMMAND:
                commonResponseButton.setBackground(ACTIVE_COLOR);
                dailyResponseButton.setBackground(DEFAULT_COLOR);
                statisticResponseButton.setBackground(DEFAULT_COLOR);
                paramsPanel.add(sitePicker, getGridBagAppearance(0, 0));
                paramsPanel.add(goButton, getGridBagAppearance(0, 1));
                break;
            case DAILY:
                commonResponseButton.setBackground(DEFAULT_COLOR);
                dailyResponseButton.setBackground(ACTIVE_COLOR);
                statisticResponseButton.setBackground(DEFAULT_COLOR);
                paramsPanel.add(sitePicker, getGridBagAppearance(0,0));
                paramsPanel.add(goButton, getGridBagAppearance(0,1));
                break;
            case STATISTIC:
                paramsPanel.add(sitePicker,  getGridBagAppearance(0,0));
                paramsPanel.add(namePicker,  getGridBagAppearance(0,1));
                paramsPanel.add(startDateLabel, getGridBagAppearance(0,2));
                paramsPanel.add(startDate, getGridBagAppearance(0,3));
                paramsPanel.add(endDateLabel,getGridBagAppearance(0,4));
                paramsPanel.add(endDate,getGridBagAppearance(0,5));
                paramsPanel.add(goButton, getGridBagAppearance(0,6));
                commonResponseButton.setBackground(DEFAULT_COLOR);
                dailyResponseButton.setBackground(DEFAULT_COLOR);
                statisticResponseButton.setBackground(ACTIVE_COLOR);
                break;
        }
        paramsPanel.validate();
    }

    private void drawMainForm()
    {

        requestPanel = new MainFormPanel();
        paramsPanel = new MainFormPanel();
        responsePanel = new MainFormPanel();

        commonResponseButton = new MainFormButton("Common information");
        dailyResponseButton = new MainFormButton("Daily information");
        statisticResponseButton = new MainFormButton("Statistic information");
        goButton = new MainFormButton("Go");

        startDate = new MainFormFormattedTextField();
        endDate = new MainFormFormattedTextField();

        sitePicker = new JComboBox();
        for (SiteResponse site : sites) {
            sitePicker.addItem(site.getName().getValue());
        }
        namePicker = new JComboBox();
        for (NameResponse name : names) {
            namePicker.addItem(name.getName().getValue());
        }

        commonResponseButton.addActionListener(this);
        dailyResponseButton.addActionListener(this);
        statisticResponseButton.addActionListener(this);
        goButton.addActionListener(this);

        requestPanel.add(commonResponseButton, getGridBagAppearance(0,0));
        requestPanel.add(dailyResponseButton,getGridBagAppearance(0,1));
        requestPanel.add(statisticResponseButton,getGridBagAppearance(0,2));

        startDateLabel = new JLabel("Start date:");
        endDateLabel = new JLabel("End date:");

        setLayout(new GridLayout(1,3));
        add(requestPanel);
        add(paramsPanel);
        add(responsePanel);
    }

    private class MainFormButton extends  JButton {
        public MainFormButton(String text) {
            super(text);
            setActionCommand(this.getText());
            setFocusPainted(false);
        }
    }

    private class MainFormPanel extends JPanel {
        public MainFormPanel() {
            super();
            setLayout(new GridBagLayout());
        }
    }

    private class MainFormFormattedTextField extends JFormattedTextField {
        public MainFormFormattedTextField() {
            super(new SimpleDateFormat(MAIN_DATA_FORMAT));
            try {
                (new MaskFormatter("##.##.####")).install(this);
            }
            catch (ParseException ex) {}
        }
    }

    private enum CurrentPanel {
        COMMAND, DAILY, STATISTIC
    }
}
