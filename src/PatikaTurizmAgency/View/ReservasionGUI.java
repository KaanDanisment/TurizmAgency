package PatikaTurizmAgency.View;

import PatikaTurizmAgency.Helper.Config;
import PatikaTurizmAgency.Helper.Helper;
import PatikaTurizmAgency.Model.Hotel;
import PatikaTurizmAgency.Model.Room;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ReservasionGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_hotel_list;
    private JScrollPane scrll_hotel_list;
    private JTable tbl_hotel_list;
    private JPanel pnl_reservation;
    private JTextField fld_hotel_name;
    private JButton bttn_create_reservation;
    private JComboBox cmb_room_type;
    private JTextField fld_room_count;
    private JTextField fld_search_hotel_name;
    private JButton bttn_search_hotel;
    private JPanel pnl_hotel_srch;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private String city;
    private int days;
    private int adultCount;
    private int childCount;
    private JDateChooser entranceDate;
    private JDateChooser releaseDate;

    public ReservasionGUI(String city, int days, int adultCount, int childCount, JDateChooser entranceDate, JDateChooser releaseDate){
        this.city = city;
        this.days = days;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.entranceDate = entranceDate;
        this.releaseDate = releaseDate;

        add(wrapper);
        setSize(850,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);



        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] col_hotel_list = {"Hotel Name", "City", "District", "Stars","Single Room Stock", "Double Room Stock","Suit Room Stock"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_hotel_list.getColumnModel().getColumn(1).setMaxWidth(70);
        tbl_hotel_list.getColumnModel().getColumn(2).setMaxWidth(70);
        tbl_hotel_list.getColumnModel().getColumn(3).setMaxWidth(50);
        tbl_hotel_list.getColumnModel().getColumn(4).setMaxWidth(120);
        tbl_hotel_list.getColumnModel().getColumn(5).setMaxWidth(120);
        tbl_hotel_list.getColumnModel().getColumn(6).setMaxWidth(120);

        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            String selected_hotel_name =tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString();
            fld_hotel_name.setText(selected_hotel_name);
        });



        bttn_create_reservation.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_room_count) || cmb_room_type.getSelectedItem().toString().equals("")){
                Helper.showMsg("fill");
            }else {
                if (Room.getFetch(fld_hotel_name.getText(), cmb_room_type.getSelectedItem().toString()) == null){
                    Helper.showMsg("There is no room for this hotel information");
                }else {
                    CreateReservationGUI createReservationGUI = new CreateReservationGUI(Room.getFetch(fld_hotel_name.getText(),cmb_room_type.getSelectedItem().toString()),Hotel.getFetch(fld_hotel_name.getText()),Integer.parseInt(fld_room_count.getText()),adultCount,childCount,days,entranceDate.getDate(),releaseDate.getDate());
                    dispose();
                }
            }
        });
        bttn_search_hotel.addActionListener(e -> {
            loadHotelModel(Hotel.getList(fld_search_hotel_name.getText(),city));
        });
    }

    public void loadHotelModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj: Hotel.getListByCity(city)){
            i = 0;
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getDistrict();
            row_hotel_list[i++] = obj.getStars();
            row_hotel_list[i++] = obj.getSingleStock();
            row_hotel_list[i++] = obj.getDoubleStock();
            row_hotel_list[i++] = obj.getSuitStock();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    public void loadHotelModel(ArrayList<Hotel> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj: list){
            i = 0;
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getDistrict();
            row_hotel_list[i++] = obj.getStars();
            row_hotel_list[i++] = obj.getSingleStock();
            row_hotel_list[i++] = obj.getDoubleStock();
            row_hotel_list[i++] = obj.getSuitStock();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }
}