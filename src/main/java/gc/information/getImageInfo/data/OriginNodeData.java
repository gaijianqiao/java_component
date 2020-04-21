package gc.information.getImageInfo.data;

import com.google.common.collect.Lists;
import gc.information.getImageInfo.domain.SearchNode;

import java.util.List;

public class OriginNodeData {
    public static List<SearchNode> getCatalogList(){
        List<SearchNode> list = Lists.newArrayList();
      /*  SearchNode Accessories= new SearchNode("8386448","Accessories");
        SearchNode desktops_workstations= new SearchNode("12454","desktops_workstations");
        SearchNode digital_cameras_photo_studios= new SearchNode("382085","digital_cameras_photo_studios");
        SearchNode handhelds_calculators= new SearchNode("215348","handhelds_calculators");
        SearchNode ink_toner_paper_printer_supplies= new SearchNode("12771","ink_toner_paper_printer_supplies");
        SearchNode laptops_hybrids= new SearchNode("321957","laptops_hybrids");
        SearchNode monitors= new SearchNode("382087","monitors");
        SearchNode point_of_sale_systems= new SearchNode("7491307","point_of_sale_systems");
        SearchNode printer_multifunction= new SearchNode("18972","printer_multifunction");
        SearchNode projectors= new SearchNode("3338965","projectors");
        SearchNode scanners_copiers_faxes= new SearchNode("15179","scanners_copiers_faxes");
        SearchNode services= new SearchNode("8362107","services");
        SearchNode software= new SearchNode("8133386","software");
        SearchNode tablets= new SearchNode("5169094","tablets");*/
        SearchNode projectors= new SearchNode("3338965","projectors");
        /*list.add(Accessories);
        list.add(desktops_workstations);
        list.add(digital_cameras_photo_studios);
        list.add(handhelds_calculators);
        list.add(ink_toner_paper_printer_supplies);
        list.add(laptops_hybrids);
        list.add(monitors);
        list.add(point_of_sale_systems);
        list.add(printer_multifunction);
        list.add(projectors);
        list.add(scanners_copiers_faxes);
        list.add(services);
        list.add(software);
        list.add(tablets);*/
        list.add(projectors);

        return list;
    }
}
