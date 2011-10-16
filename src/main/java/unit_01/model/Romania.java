package unit_01.model;

public class Romania extends Country {
    public final City Arad;
    public final City Oradea;
    public final City Zerind;
    public final City Sibiu;
    public final City RimnicuVilcea;
    public final City Fagaras;
    public final City Timisoara;
    public final City Lugoj;
    public final City Mehadia;
    public final City Drobeta;
    public final City Craiova;
    public final City Pitesti;
    public final City Bucharest;
    public final City Giurgiu;
    public final City Urziceni;
    public final City Hirsova;
    public final City Eforie;
    public final City Vaslui;
    public final City Iasi;
    public final City Neamt;


    public Romania() {
        Arad = newCity("Arad");
        Oradea = newCity("Oradea");
        Zerind = newCity("Zerind");
        Sibiu = newCity("Sibiu");
        RimnicuVilcea = newCity("Rimnicu Vilcea");
        Fagaras = newCity("Fagaras");
        Timisoara = newCity("Timisoara");
        Lugoj = newCity("Lugoj");
        Mehadia = newCity("Mehadia");
        Drobeta = newCity("Drobeta");
        Craiova = newCity("Craiova");
        Pitesti = newCity("Pitesti");
        Bucharest = newCity("Bucharest");
        Giurgiu = newCity("Giurgiu");
        Urziceni = newCity("Urziceni");
        Hirsova = newCity("Hirsova");
        Eforie = newCity("Eforie");
        Vaslui = newCity("Vaslui");
        Iasi = newCity("Iasi");
        Neamt = newCity("Neamt");
        //
        road(Oradea, 71, Zerind);
        road(Zerind, 75, Arad);
        road(Oradea, 151, Sibiu);
        road(Arad, 140, Sibiu);
        road(Arad, 118, Timisoara);
        road(Timisoara, 111, Lugoj);
        road(Lugoj, 70, Mehadia);
        road(Mehadia, 75, Drobeta);
        road(Drobeta, 120, Craiova);
        road(Sibiu, 99, Fagaras);
        road(Sibiu, 80, RimnicuVilcea);
        road(RimnicuVilcea, 146, Craiova);
        road(RimnicuVilcea, 97, Pitesti);
        road(Craiova, 138, Pitesti);
        road(Pitesti, 101, Bucharest);
        road(Fagaras, 211, Bucharest);
        road(Bucharest, 90, Giurgiu);
        road(Bucharest, 85, Urziceni);
        road(Urziceni, 98, Hirsova);
        road(Hirsova, 86, Eforie);
        road(Urziceni, 142, Vaslui);
        road(Vaslui, 92, Iasi);
        road(Iasi, 87, Neamt);
    }
}